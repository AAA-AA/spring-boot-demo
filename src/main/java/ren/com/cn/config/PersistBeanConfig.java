package ren.com.cn.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import ren.com.cn.common.enums.GuavaCacheEnum;
import ren.com.cn.common.factory.GsonFactory;
import ren.com.cn.common.factory.RedisGson;
import ren.com.cn.common.utils.JsonUtils;
import ren.com.cn.config.yml.AppConfig;
import ren.com.cn.config.yml.KafkaConfig;

import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/4/18 18:49
 * Email: renhongqiang1397@gmail.com
 */

@Configuration
@EnableAsync
@EnableScheduling
@EnableCaching
public class PersistBeanConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersistBeanConfig.class);

    @Autowired
    private AppConfig appConfig;

    @Bean
    public Producer kafkaProducer() {
        Properties properties = new Properties();
        KafkaConfig kafkaConfig = appConfig.getKafka();
        properties.setProperty("metadata.broker.list", kafkaConfig.getBrokerList());
        properties.setProperty("serializer.class", kafkaConfig.getSerializerClass());
        LOGGER.info("{} - {}", "kafka", JsonUtils.toJson(kafkaConfig));

        properties.setProperty("key.serializer.class", kafkaConfig.getKeySerializerClass());
        properties.setProperty("partitioner.class", kafkaConfig.getPartitionerClass());
        properties.setProperty("request.required.acks", kafkaConfig.getRequestRequiredAcks());
        properties.setProperty("connect.timeout.ms", kafkaConfig.getConnectTimeoutMs());
        properties.setProperty("socket.timeout.ms", kafkaConfig.getSocketTimeoutMs());
        return new Producer(new ProducerConfig(properties));
    }

    @Bean
    public JedisCluster jedisCluster() {
        List<String> clusterNodes = appConfig.getRedis().getClusterNodes();
        LOGGER.info("{},{}", "redis", JsonUtils.toJson(appConfig.getRedis()));
        Set<HostAndPort> nodes = Sets.newLinkedHashSet();
        for (String node : clusterNodes) {
            String[] ip_port_pair = node.split(":");
            nodes.add(new HostAndPort(ip_port_pair[0].trim(), Integer.valueOf(ip_port_pair[1].trim())));
        }
        return new JedisCluster(nodes, appConfig.getRedis().getMaxWaitMillis(), 20);
    }

    @Bean
    public RedisGson redisGson() {
        return GsonFactory.createRedisCacheGson();
    }

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        List<GuavaCache> caches = Lists.newLinkedList();

        Lists.newArrayList(GuavaCacheEnum.values()).forEach(e -> {
            caches.add(new GuavaCache(e.name(), CacheBuilder.newBuilder().expireAfterWrite(e.expireAfterWrite, TimeUnit.MINUTES).maximumSize(e.maximumSize).build()));
        });
        cacheManager.setCaches(caches);
        return cacheManager;
    }


}
