package ren.com.cn.proxy;

import lombok.extern.slf4j.Slf4j;
import ren.com.cn.domain.entity.User;
import ren.com.cn.service.HttpService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.UnknownServiceException;
import java.util.Date;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/5/17 23:31
 * Email: renhongqiang1397@gmail.com
 */
@Slf4j
public class DynamicProxy implements InvocationHandler {


    public static void test(String[] args) throws Throwable {
        DynamicProxy proxy = new DynamicProxy();
        Object instance = Proxy.newProxyInstance(DynamicProxy.class.getClassLoader(), HttpService.class.getInterfaces(), proxy);

        Method[] declaredMethods = HttpService.class.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.println(method.getName());
        }

        User user = (User) proxy.invoke(instance, HttpService.class.getMethods()[0], args);
        log.info("getUser {}", user);

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        User user = new User();
        user.setUserName("llal");
        user.setCtime(new Date());
        return user;
    }
}
