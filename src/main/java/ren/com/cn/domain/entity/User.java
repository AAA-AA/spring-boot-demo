package ren.com.cn.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/4/18 10:15
 * Email: renhongqiang1397@gmail.com
 */
@Data
public class User {

    private Long id;

    private String name;
    private Integer age;
}
