package ren.com.cn.common.annotation;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.TYPE_USE;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : hongqiangren.
 * Date: 2017/10/23 16:19
 * Email: renhongqiang1397@gmail.com
 */
@Target({TYPE, METHOD, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface EnableRepeatSubmit {
    boolean enable() default false;
    int onceMaxSeconds() default 3;
}
