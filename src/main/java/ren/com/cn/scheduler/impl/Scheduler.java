package ren.com.cn.scheduler.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ren.com.cn.domain.entity.SmsSendLog;
import ren.com.cn.scheduler.IScheduler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author : hongqiangren.
 * @since: 2018/1/16 00:32
 */
@Service
@Slf4j
public class Scheduler implements IScheduler{



    @Scheduled(cron = "0/10 * * * * ? ")
    public void refresh() {
        SmsSendLog temp = null;
        for (int i = 0;i < 10000;i++) {
            SmsSendLog sendLog = new SmsSendLog();
            sendLog.setChannel(1);
            sendLog.setContent("jljdfa");
            sendLog.setCreatedAt(LocalDateTime.now());
            sendLog.setSmsUuid(DigestUtils.md5Hex(String.valueOf(RandomUtils.nextInt(1,10))));
            log.debug(JSON.toJSONString(sendLog));
        }
    }


}
