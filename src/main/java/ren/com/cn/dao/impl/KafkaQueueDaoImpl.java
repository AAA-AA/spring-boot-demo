package ren.com.cn.dao.impl;

import com.alibaba.fastjson.JSON;
import kafka.api.PartitionOffsetRequestInfo;
import kafka.common.OffsetMetadataAndError;
import kafka.common.TopicAndPartition;
import kafka.javaapi.*;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ren.com.cn.dao.KafkaQueueDao;
import ren.com.cn.dto.KafkaQueueStatus;

import java.util.*;

/**
 * Created by zhangjing on 15-11-22.
 */
@Repository
@Slf4j
public class KafkaQueueDaoImpl<T> implements KafkaQueueDao<T> {

    private Logger LOGGER = LoggerFactory.getLogger(KafkaQueueDaoImpl.class);

    @Autowired
    private Producer kafkaProducer;

    private ProducerConfig producerConfig;

    private HashMap<String, Integer> brokerMap;


    public void setKafkaProducer(Producer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    public void setProducerConfig(ProducerConfig producerConfig) {
        this.producerConfig = producerConfig;
        if (producerConfig != null) {
            String brokers = producerConfig.brokerList();
            brokerMap = new HashMap<String, Integer>();
            try {
                Arrays.asList(brokers.split(",")).forEach((s) -> {
                    String[] tmp = s.split(":");
                    brokerMap.put(tmp[0], Integer.valueOf(tmp[1]));
                });
            } catch (Exception e) {
                LOGGER.error("配置文件格式错误", e);
            }
        }
    }

    @Override
    public void send(String topic, T msg) throws Exception {

        log.info("KafkaQueueDaoImpl send topic {} msg {}", topic, JSON.toJSONString(msg));

        KeyedMessage<String, String> data = null;
        if (msg instanceof String) {
            data = new KeyedMessage<String, String>(
                    topic, (String) msg);
        } else {
            data = new KeyedMessage<String, String>(
                    topic, JSON.toJSONString(msg));
        }

        kafkaProducer.send(data);
    }

    public List<KafkaQueueStatus> getStatus(String topic, String groupId) {
        List<KafkaQueueStatus> list = new ArrayList<>();
        TreeMap<Integer, PartitionMetadata> metadatas = findLeader(brokerMap,
                topic);
        metadatas.forEach((k, v) -> {
            SimpleConsumer consumer = null;
            try {
                KafkaQueueStatus status = new KafkaQueueStatus();
                status.setPartition(k);
                status.setTopic(topic);
                int partition = k;
                String leadBroker = v.leader().host();
                String clientName = "Client_" + topic + "_" + partition;
                consumer = new SimpleConsumer(leadBroker, v.leader().port(),
                        100000, 64 * 1024, clientName);
                long maxOffset = getLastOffset(consumer, topic, partition,
                        kafka.api.OffsetRequest.LatestTime(), clientName);
                status.setMaxOffset(maxOffset);
                long readOffset = getReadOffset(consumer, v, topic, groupId);
                if (readOffset == -1) {
                    readOffset = 0;
                }
                status.setOffset(readOffset);
                status.setRemain(maxOffset - readOffset);
                list.add(status);
            } catch (Exception e) {
                LOGGER.error("访问队列错误", e);
            } finally {
                if (consumer != null) {
                    consumer.close();
                }
            }
        });
        return list;
    }

    private long getReadOffset(SimpleConsumer consumer,
                               PartitionMetadata metadata, String topic, String groupId) {
        TopicAndPartition topicAndPartition = new TopicAndPartition(topic,
                metadata.partitionId());
        List<TopicAndPartition> requestInfo = new ArrayList<TopicAndPartition>();
        requestInfo.add(topicAndPartition);
        OffsetFetchRequest request = new OffsetFetchRequest(groupId,
                requestInfo, 1, consumer.clientId());
        Iterator<OffsetMetadataAndError> iterator = consumer
                .fetchOffsets(request).offsets().values().iterator();
        Long count = 0L;
        while (iterator.hasNext()) {
            OffsetMetadataAndError tmp = iterator.next();
            count += tmp.offset();
        }
        return count;
    }

    private long getLastOffset(SimpleConsumer consumer, String topic,
                               int partition, long whichTime, String clientName) {
        TopicAndPartition topicAndPartition = new TopicAndPartition(topic,
                partition);
        Map<TopicAndPartition, PartitionOffsetRequestInfo> requestInfo = new HashMap<TopicAndPartition, PartitionOffsetRequestInfo>();
        requestInfo.put(topicAndPartition, new PartitionOffsetRequestInfo(
                whichTime, 1));
        OffsetRequest request = new OffsetRequest(
                requestInfo, kafka.api.OffsetRequest.CurrentVersion(),
                clientName);
        OffsetResponse response = consumer.getOffsetsBefore(request);

        if (response.hasError()) {
            LOGGER.warn("Error fetching data Offset Data the Broker. Reason:{} ", response.errorCode(topic, partition));
            return 0;
        }
        long[] offsets = response.offsets(topic, partition);
        return offsets[0];
    }

    private TreeMap<Integer, PartitionMetadata> findLeader(
            HashMap<String, Integer> brokers, String a_topic) {
        TreeMap<Integer, PartitionMetadata> map = new TreeMap<Integer, PartitionMetadata>();
        brokers.forEach((k, v) -> {
            SimpleConsumer consumer = null;
            try {
                consumer = new SimpleConsumer(k, v, 100000, 64 * 1024,
                        "leaderLookup" + new Date().getTime());
                List<String> topics = Collections.singletonList(a_topic);
                TopicMetadataRequest req = new TopicMetadataRequest(topics);
                TopicMetadataResponse resp = consumer.send(req);
                List<TopicMetadata> metaData = resp.topicsMetadata();
                for (TopicMetadata item : metaData) {
                    for (PartitionMetadata part : item.partitionsMetadata()) {
                        map.put(part.partitionId(), part);
                    }
                }
            } catch (Exception e) {
                LOGGER.error("Error communicating with Broker [{}] to find Leader for [{}, ] Reason: "
                        , e, k, a_topic);
            } finally {
                if (consumer != null)
                    consumer.close();
            }

        });
        return map;
    }
}
