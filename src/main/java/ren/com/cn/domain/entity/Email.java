package ren.com.cn.domain.entity;

import lombok.Data;

import java.sql.Date;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/4/25 14:43
 * Email: renhongqiang1397@gmail.com
 */
@Data
public class Email {

    private String address;

    private String from;

    private String to;

    private Date ctime;

    private Date mtime;

    public String getAddress() {

        if ("A".equals(address)) {
            this.address = "B";
        }
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
