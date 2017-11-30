package ren.com.cn.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * link table is t_sms_send_log
 * Copyright © 2017, github and/or its affiliates. All rights reserved.
 **/
@Data
public class SmsSendLog {
    /**自增主键*/
    private Long id;

    /**短信发送唯一标识*/
    private String smsUuid;

    /**国家码*/
    private String countryCode;

    /**手机号*/
    private String telNo;

    /**会员id*/
    private Long userId;

    /**短信模板主键id*/
    private Integer templateId;

    /**短信内容*/
    private String content;

    /**请求服务商时间*/
    private LocalDateTime requestTime;

    /**短信服务渠道商*/
    private Integer channel;

    /**记录同步时间*/
    private LocalDateTime createdAt;

    /**创建人*/
    private String createdBy;

    /**删除状态字段，0有效，1删除*/
    private Integer isDelete;

    /**发送状态：0：发送成功,1：发送失败*/
    private Integer status;

    /**记录更新时间*/
    private LocalDateTime updatedAt;

    /**更新人*/
    private String updatedBy;
}