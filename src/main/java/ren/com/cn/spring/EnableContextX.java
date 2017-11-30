package ren.com.cn.spring;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by IntelliJ IDEA ^_^
 *
 * @author : hongqiangren.
 * @date: 2017/11/26 23:15
 * @email: renhongqiang1397@gmail.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(ContextConfiguration.class)
public @interface EnableContextX {
}
