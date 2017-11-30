package ren.com.cn.main;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.MethodReplacer;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/5/5 14:18
 * Email: renhongqiang1397@gmail.com
 */
@Service
public class DemoBean implements InitializingBean,MethodReplacer {

    private Timer timer = new Timer();
    private AtomicInteger count = new AtomicInteger(0);

    private void refresh() {
        /*timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("第" + count.incrementAndGet() + "次执行"+new Date());
            }
        }, 1000, RandomUtils.nextLong(1000 * 1, 1000 * 10));*/
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        refresh();
    }

    @Override
    public Object reimplement(Object o, Method method, Object[] objects) throws Throwable {
        return null;
    }
}
