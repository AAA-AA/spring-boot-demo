package ren.com.cn.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/5/17 23:31
 * Email: renhongqiang1397@gmail.com
 */
public class DynamicProxy implements InvocationHandler {

    private Object object;

    public Object bindRelation(Object object) {
        this.object = object;
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("Welcome");
        Object result = method.invoke(object, args);
        return result;
    }
}
