package ren.com.cn.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import ren.com.cn.exception.ServiceException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA ^_^
 *
 * @author : hongqiangren.
 * @date: 2017/11/26 22:54
 * @email: renhongqiang1397@gmail.com
 */
public class SpringContext {
    private static ApplicationContext ctx;
    private static ConfigurableEnvironment env;

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) ctx.getBean(name);
    }

    public static <T> T getBean(Class<T> cls) {
        return ctx.getBean(cls);
    }

    public static String getProp(String key, String def) {
        return null == env ? def : env.getProperty(key, def);
    }

    public static String getProp(String key) {
        return getProp(key, "");
    }


    public static ApplicationContext getContext() {
        return SpringContext.ctx;
    }

    static void setApplicationContext(ApplicationContext ctx) {
        SpringContext.ctx = ctx;
    }

    public static ConfigurableEnvironment getEnv() {
        return SpringContext.env;
    }

    static void setConfigurableEnvironment(ConfigurableEnvironment env) {
        SpringContext.env = env;
    }


}
