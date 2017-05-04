package queue;

import ren.com.cn.exception.MyException;
import ren.com.cn.exception.NoCheckException;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/4/24 22:53
 * Email: renhongqiang1397@gmail.com
 */
public class ExceptionDemo {

    public static void main(String[] args) {
        int a = 2/0;
        try {
            throw new MyException("");
        } catch (MyException e) {
            e.printStackTrace();
        }
        throw new NoCheckException(2,"lalala");

    }
}
