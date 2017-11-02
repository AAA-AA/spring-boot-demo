package ren.com.cn.invoker;

import ren.com.cn.common.annotation.EnableReSubmit;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA ^_^
 *
 * @author : hongqiangren.
 * @date: 2017/10/26 15:04
 * @email: renhongqiang1397@gmail.com
 */
public final class HttpInvoker implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> clazz = method.getDeclaringClass();
        if (clazz == Object.class) {
            return method.invoke(this,args);
        }
        EnableReSubmit repeatSubmit = method.getAnnotation(EnableReSubmit.class);
        if (null == repeatSubmit) {
            throw new IllegalArgumentException("need @EnableReSubmit");
        }
        return null;
    }
}
