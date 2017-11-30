package ren.com.cn.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Created by IntelliJ IDEA ^_^
 *
 * @author : hongqiangren.
 * @date: 2017/11/26 23:01
 * @email: renhongqiang1397@gmail.com
 */
public class ContextBootListener implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContext.setApplicationContext(applicationContext);
        ConfigurableApplicationContext configurable = (ConfigurableApplicationContext) applicationContext;
        ConfigurableEnvironment environment = configurable.getEnvironment();
        SpringContext.setConfigurableEnvironment(environment);
    }
}
