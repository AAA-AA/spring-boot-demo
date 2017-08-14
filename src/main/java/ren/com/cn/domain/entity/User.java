package ren.com.cn.domain.entity;

import java.util.Date;
import lombok.Data;

@Data
public class User {
    private Integer userId;

    private String userName;

    private Byte sex;

    private Date ctime;

    private Date mtime;

    /**
     * 0为正常，1为删除
     */
    private Byte status;
}