package ren.com.cn.task;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ren.com.cn.service.RedisJobLockService;

import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/5/15 14:28
 * Email: renhongqiang1397@gmail.com
 */
@Service
public class CommonTask {

   private static final Logger LOGGER = LoggerFactory.getLogger(CommonTask.class);

    @Autowired
    private RedissonClient redisson;

    @Autowired
    private RedisJobLockService lockService;

    @Scheduled(cron = "* 00 16 * * ?")
    public void execute() {
        boolean canRun = lockService.canRun("lockDemo");
        if (canRun) {
            try {
                System.out.println("*********拿到锁"+System.currentTimeMillis()+"**********");
                Thread.sleep(70*1000);
                System.out.println("*********"+Thread.currentThread().getName()+"**********");
            } catch (InterruptedException e) {
                LOGGER.error("taskJob error {}",e);
            }finally {
                lockService.unlock("lockDemo");
            }
        }
    }
}
