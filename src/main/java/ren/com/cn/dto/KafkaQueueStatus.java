package ren.com.cn.dto;

import lombok.Data;

@Data
public class KafkaQueueStatus {
    private String topic;
    private Integer partition;
    private Long maxOffset;
    private Long offset;
    private Long remain;
}
