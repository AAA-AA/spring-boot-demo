package ren.com.cn.exception;

import ren.com.cn.common.base.ReturnCode;

import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA ^_^
 *
 * @author : hongqiangren.
 * @date: 2017/10/27 19:04
 * @email: renhongqiang1397@gmail.com
 */
public class ServiceException extends RuntimeException{
    private static Pattern pattern = Pattern.compile("\\{}");
    private ReturnCode returnCode;

    private ServiceException() {
    }

    public ServiceException(ReturnCode returnCode) {
        super(returnCode.getMsg());
        this.returnCode = returnCode;
    }


    /** Error message for client. Support {} template */
    public ServiceException(ReturnCode returnCode,Object... args) {
        super(returnCode.format(args));
        this.returnCode = returnCode;
    }

    public static void main(String[] args) {
        throw new ServiceException(ReturnCode.TEST_TEMPLATE,1);
    }

}
