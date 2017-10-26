package ren.com.cn.common.base;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/8/18 14:53
 * Email: renhongqiang1397@gmail.com
 */
public enum ReturnCode {
    SUCCESS("0","ok"),
    BAD_REQUEST("-400","bad request"),
    UNAUTHORIZED("-401","unauthorized"),
    ACCESS_DENYED("-403","forbidden"),






    SERVER_ERROR("-500","internal error, contact the developer please")



    ;






    private String code;
    private String msg;

    ReturnCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
