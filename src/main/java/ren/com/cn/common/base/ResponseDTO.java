package ren.com.cn.common.base;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/8/18 14:16
 * Email: renhongqiang1397@gmail.com
 */
public class ResponseDTO {
    private String code;
    private Object data;
    private String message;

    public ResponseDTO(ReturnCode returnCode, Object data) {
        this.code = returnCode.getCode();
        this.data = data;
        this.message = returnCode.getMsg();
    }

    public ResponseDTO(String code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public static ResponseDTO success() {
        return new ResponseDTO(ReturnCode.SUCCESS, null);
    }

    public static ResponseDTO success(Object data) {
        return new ResponseDTO(ReturnCode.SUCCESS, data);
    }

    public static ResponseDTO error(ReturnCode returnCode) {
        return new ResponseDTO(returnCode, null);
    }

    public static ResponseDTO error(ReturnCode returnCode, Object data) {
        ResponseDTO dto = new ResponseDTO(returnCode, data);
        return dto;
    }

    public static ResponseDTO error(String message, Object data) {
        ResponseDTO dto = new ResponseDTO(ReturnCode.SERVER_ERROR.getCode(), data, message);
        return dto;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
