package ren.com.cn.config.yml;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/4/18 18:48
 * Email: renhongqiang1397@gmail.com
 */
@Data
public class RedisConfig {

    private int expireSeconds;

    private int maxWaitMillis;

    private int maxIdle;

    private int minIdle;

    private int maxTotal;

    private List<String> clusterNodes = Lists.newLinkedList();

}
