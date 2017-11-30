package ren.com.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import ren.com.cn.controller.HelloController;
import ren.com.cn.service.impl.UserServiceImpl;
import ren.com.cn.spring.EnableContextX;
import ren.com.cn.spring.SpringContext;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/4/17 23:04
 * Email: renhongqiang1397@gmail.com
 */
@SpringBootApplication
@EnableContextX
@ComponentScan("ren.com.cn")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        UserServiceImpl userService = SpringContext.getBean(UserServiceImpl.class);

    }
}
