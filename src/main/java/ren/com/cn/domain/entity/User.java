package ren.com.cn.domain.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * link table is t_user
 * Copyright © 2017, github and/or its affiliates. All rights reserved.
 **/
@Data
public class User {
    private Integer userId;

    private String userName;

    private Integer sex;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ctime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime mtime;

    /**0为正常，1为删除*/
    private Integer status;
}