package ren.com.cn.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ren.com.cn.common.utils.DateConvertUtils;
import ren.com.cn.dao.RedisCacheDao;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/5/15 14:34
 * Email: renhongqiang1397@gmail.com
 */
@Service
public class RedisJobLockService {
    private final static Logger LOGGER = LoggerFactory.getLogger("task");

    private RedisCacheDao cacheService;

    public static final String JOB_LOCK_VIP_KEY = "jobLock%s";

    private static final int DEFAULT_JOB_LOCK_TIME = 3600;

    /**
     * 增加指定的jobId的key， value就是key的值。
     * key的默认失效时间半小时。
     *
     * @param jobId
     * @return
     */
    public Boolean lock(String jobId) {
        return this.lock(jobId, DEFAULT_JOB_LOCK_TIME);
    }

    /**
     * 增加指定的jobId的key， value就是key的值。通过jobLockTime指定失效时间，单位为秒。
     *
     * @param jobId
     * @param jobLockTime
     * @return
     */
    public Boolean lock(String jobId, int jobLockTime) {

        Boolean result = cacheService.setNX(String.format(JOB_LOCK_VIP_KEY, jobId),
                System.currentTimeMillis() + jobLockTime * 1000 + "");
        if (result != null && result.booleanValue()) {
            this.cacheService.expireAt(String.format(JOB_LOCK_VIP_KEY, jobId), DateConvertUtils.add(Calendar.SECOND,
                    new Date(System.currentTimeMillis()), jobLockTime)); // 制定时间后自动取消该key
        }
        return result;
    }

    public Boolean unlock(String jobId) {
        cacheService.delKey(String.format(JOB_LOCK_VIP_KEY, jobId));
        return true;
    }

    public boolean canRun(String jobName) {
        boolean isUnLock = false;
        /*** 判断是否已有任务再跑 **/

        Boolean locked = lock(jobName);
        if (!locked) {
            LOGGER.info(String.format("任务(%s)已经在运行", jobName));
        } else {
            isUnLock = true;
        }
        return isUnLock;
    }
}
