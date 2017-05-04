package ren.com.cn.exception;

import lombok.Data;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/4/24 22:55
 * Email: renhongqiang1397@gmail.com
 */
@Data
public class NoCheckException extends RuntimeException {

    private int code;

    private String message;

    private Object data;

    public NoCheckException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public NoCheckException(int code,String message,Object data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return "NoCheckException{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
