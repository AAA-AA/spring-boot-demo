package queue;

import ren.com.cn.domain.entity.Email;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/4/25 14:46
 * Email: renhongqiang1397@gmail.com
 */
public class LombokDemo {

    public static void main(String[] args) {

        Email email = new Email();

        email.setAddress("A");

        System.out.println(email.getAddress());
    }
}
