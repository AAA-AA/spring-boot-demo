package ren.com.cn.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.RedissonMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ren.com.cn.common.annotation.EnableRepeatSubmit;
import ren.com.cn.exception.MyException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    private static Map<String,Object> test = new ConcurrentHashMap(16);

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private HttpServletRequest request;

    @Pointcut(value = "@within(ren.com.cn.common.annotation.EnableRepeatSubmit) || @annotation(ren.com.cn.common.annotation.EnableRepeatSubmit)")
    public void allMethods() {
    }

    @Around(value = "allMethods()")
    public Object execute(ProceedingJoinPoint pjp) throws Throwable {
        EnableRepeatSubmit annotation = AnnotationUtils.getAnnotation(((MethodSignature) pjp.getSignature()).getMethod(), EnableRepeatSubmit.class);
        if (annotation != null && !annotation.enable()) {

            Object args = pjp.getArgs()[0];
            String key = JSON.toJSONString(args+request.getRemoteHost());
            RedissonMap oldObject = (RedissonMap)redissonClient.getMap(key);
            if (oldObject.size() != 0) {
                throw new MyException("存在重复提交行为，请检查");
            }else {

                annotation.onceMaxSeconds();
            }
            System.out.println(key);
        }
        Object proceed = pjp.proceed();
        return proceed;
    }

    @AfterThrowing(value = "allMethods())", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Throwable exception) throws Throwable {
        //记录异常日志
        if (exception instanceof MyException) {
            return;
        }
        log.error("服务端异常,api:{}.{}", new Object[]{joinPoint.getTarget().
                getClass().getName(), joinPoint.getSignature().getName()}, exception);

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        String key = String.format("vip.vip-svr-web.%s-%s", request.getRequestURI(), request.getMethod());
        log.error(key);
        response.getWriter().print(key);
        response.flushBuffer();
        response.getWriter().close();
    }
}
