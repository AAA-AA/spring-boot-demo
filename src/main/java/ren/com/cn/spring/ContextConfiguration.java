package ren.com.cn.spring;

import org.springframework.context.annotation.Bean;

/**
 * Created by IntelliJ IDEA ^_^
 *
 * @author : hongqiangren.
 * @date: 2017/11/26 23:13
 * @email: renhongqiang1397@gmail.com
 */
public class ContextConfiguration {

    @Bean
    public ContextBootListener bootContextListener() {
        return new ContextBootListener();
    }
}
