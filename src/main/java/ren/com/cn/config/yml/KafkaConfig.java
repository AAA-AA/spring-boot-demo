package ren.com.cn.config.yml;

import lombok.Data;

/**
 * Created by user on 2016/8/5.
 */
@Data
public class KafkaConfig {
    private String brokerList;
    private String serializerClass;
    private String keySerializerClass;
    private String partitionerClass;
    private String requestRequiredAcks;
    private String connectTimeoutMs;
    private String socketTimeoutMs;
    private KafkaConsumerConfig consumer;
}
