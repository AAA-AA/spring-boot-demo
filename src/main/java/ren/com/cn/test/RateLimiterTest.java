package ren.com.cn.test;

import com.google.common.util.concurrent.RateLimiter;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/8/21 11:21
 * Email: renhongqiang1397@gmail.com
 */
public class RateLimiterTest {


    public static void main(String[] args) {
        RateLimiter limiter = RateLimiter.create(1);

        for (int i = 0; i < 1000; i++) {
            if (limiter.tryAcquire()) {
                System.out.println(new Date() + "*******" + i);
            }
        }

        for (int i = 0; i < 1000; i++) {
            if (limiter.tryAcquire(1,4, TimeUnit.SECONDS)) {
                System.out.println(new Date() + "*******" + i);
            }
        }
    }
}
