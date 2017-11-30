package ren.com.cn.spring;

import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Created by IntelliJ IDEA ^_^
 *
 * @author : hongqiangren.
 * @date: 2017/11/26 22:58
 * @email: renhongqiang1397@gmail.com
 */
public class ApplicationRunListener implements SpringApplicationRunListener {
    @Override
    public void started() {

    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment configurableEnvironment) {
        SpringContext.setConfigurableEnvironment(configurableEnvironment);
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext configurableApplicationContext) {
        SpringContext.setApplicationContext(configurableApplicationContext);
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext configurableApplicationContext) {

    }

    @Override
    public void finished(ConfigurableApplicationContext configurableApplicationContext, Throwable throwable) {

    }
}
