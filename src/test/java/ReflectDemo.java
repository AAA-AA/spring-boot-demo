import service.UserDemoService;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : hongqiangren.
 * Date: 2017/10/24 16:57
 * Email: renhongqiang1397@gmail.com
 */
public class ReflectDemo {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {

        Class<?> p = Class.forName("entity.Person");
        Field[] fields = p.getDeclaredFields();

        Object obj = p.newInstance();

        //Field.setAccessible(fields, true);
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();
            fields[i].setAccessible(true);
            fields[i].set(obj,"bbbbbb");
            Object value = fields[i].get(obj);
            System.out.println(String.format("fieldName:%s, value %s",fieldName,value));
        }

        Proxy.newProxyInstance(ReflectDemo.class.getClassLoader(),new Class<?>[] {},new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                return null;
            }
        });
    }
}
