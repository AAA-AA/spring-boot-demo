package ren.com.cn.dto;

import lombok.Data;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/4/18 16:35
 * Email: renhongqiang1397@gmail.com
 */
@Data
public class ErrorInfo<T> {

    public static final Integer OK = 0;
    public static final Integer ERROR = 100;

    private Integer code;
    private String message;
    private String url;
    private T data;

}
