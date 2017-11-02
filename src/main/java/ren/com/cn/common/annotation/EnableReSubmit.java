package ren.com.cn.common.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

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
public @interface EnableReSubmit {
    /**
     * whether to allow resubmit
     * @return
     */
    boolean enable() default false;

    /**
     * max wait time after you submit
     * @return
     */
    int maxWait() default 3;

    /**
     * timeunit, works for maxWait   近铁城市广场北座金沙江路1518弄17楼
     * @return
     */
    TimeUnit unit() default TimeUnit.SECONDS;
}
