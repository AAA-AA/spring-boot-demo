package threadLocalDemo;

import ren.com.cn.domain.entity.User;

import java.util.Timer;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/5/4 17:37
 * Email: renhongqiang1397@gmail.com
 */
public final class ThreadLocalDemo {

    private static final ThreadLocal<User> t = new ThreadLocal<>();

    private static Timer timer = new Timer();

    public static User getUser() {
        if (null == t.get()) {
            t.set(new User());
        }
        return t.get();
    }


    public static void main(String[] args) {

        for (int i = 0;i < 20;i++) {
            new Thread() {
                @Override
                public void run() {
                    User user2 = getUser();
                    System.out.println(Thread.currentThread().getName()+t.get());
                }
            }.start();
        }

        User user = getUser();
        user.setName("ren");

        User user1 = getUser();

        System.out.println(user1.getName());


    }
}
