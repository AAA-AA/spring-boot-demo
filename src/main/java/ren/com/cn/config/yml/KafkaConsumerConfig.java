package ren.com.cn.config.yml;

import lombok.Data;

/**
 * Created by user on 2016/8/29.
 */
@Data
public class KafkaConsumerConfig {
    private String zookeeperConnect;
    private String zookeeperSessionTimeoutMs;
    private String zookeeperSyncTimeMs;
    private String groupId;
    private String autoCommitIntervalMs;
}
