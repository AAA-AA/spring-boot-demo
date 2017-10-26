package ren.com.cn.controller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ren.com.cn.common.base.ResponseDTO;

import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA ^_^
 *
 * @author : hongqiangren.
 * @date: 2017/10/25 19:52
 * @email: renhongqiang1397@gmail.com
 */
@Controller
@RequestMapping(value = "/common")
@Slf4j
public class CommonController {
    @Autowired
    private RedissonClient client;

    @RequestMapping(value = "/getLock")
    @ResponseBody
    public ResponseDTO getLock(String key) {
        RLock lock = client.getLock(key);
        try {
            if (lock.tryLock(5,3*60,TimeUnit.SECONDS)) {
                log.info("加锁啦, 开始执行任务");
                Thread.sleep(1000*60);
                log.info("任务完成");
                if (lock.isLocked()) {
                    lock.unlock();
                    log.info("锁提前释放");
                }
            }else {
                log.info("已有同步任务在执行！");
            }
        }catch (Exception e) {
            log.error("执行任务失败{}",e);
        }
        return ResponseDTO.success();
    }



}
