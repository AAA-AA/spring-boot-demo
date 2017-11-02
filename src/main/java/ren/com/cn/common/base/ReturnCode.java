package ren.com.cn.common.base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    NOT_FOUND("-404","not found"),
    RE_SUBMIT("-410","repeat submit"),
    SERVER_ERROR("-500","internal error, contact the developer please"),
    TEST_TEMPLATE("-501","internal error, contact the developer please contact {} {} ")

    ;

    private String code;
    private String msg;

    ReturnCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    Pattern split = Pattern.compile("\\{}");

    public String format(Object... args) {
        String msg = this.getMsg();
        return format(msg,args);
    }

    String format(String formatter, Object... args) {
        if (null == args || args.length < 1 || null == formatter || !split.matcher(formatter).find()) {
            return formatter;
        }
        StringBuffer sb = new StringBuffer();
        Matcher matcher = split.matcher(formatter);
        for (int i = 0; i < args.length && matcher.find(); i++) {
            matcher.appendReplacement(sb,args[i].toString());
        }
        matcher.appendTail(sb);
        return sb.toString();
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
