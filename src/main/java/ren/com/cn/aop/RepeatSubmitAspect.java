package ren.com.cn.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import ren.com.cn.common.annotation.EnableReSubmit;
import ren.com.cn.common.base.ReturnCode;
import ren.com.cn.config.RedisPool;
import ren.com.cn.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IntelliJ IDEA ^_^
 *
 * @author : hongqiangren.
 * @date: 2017/10/23 16:24
 * Email: renhongqiang1397@gmail.com
 */
@Aspect
@Component
@Slf4j
public class RepeatSubmitAspect {

    private static Map<String, Object> test = new ConcurrentHashMap(16);
    private Jedis jedis = RedisPool.getJedis();

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private HttpServletRequest request;

    @Pointcut(value = "@within(ren.com.cn.common.annotation.EnableReSubmit) || @annotation(ren.com.cn.common.annotation.EnableReSubmit)")
    public void allMethods() {
    }

    @Around(value = "allMethods()")
    public Object execute(ProceedingJoinPoint pjp) throws Throwable {
        EnableReSubmit annotation = AnnotationUtils.getAnnotation(((MethodSignature) pjp.getSignature()).getMethod(), EnableReSubmit.class);
        if (annotation != null && !annotation.enable()) {
            Object args = pjp.getArgs()[0];
            String key = JSON.toJSONString(args + request.getRemoteHost());
            String value = jedis.get(key);
            if (value != null) {
                throw new ServiceException(ReturnCode.RE_SUBMIT);
            } else {
                jedis.setex(key, (int) TimeoutUtils.
                        toSeconds(annotation.maxWait(), annotation.unit()), "exist");


            }
        }
        Object proceed = pjp.proceed();
        return proceed;
    }
}
