package ren.com.cn.config.yml;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/4/18 19:13
 * Email: renhongqiang1397@gmail.com
 */
@Data
@ConfigurationProperties()
@Component
public class AppConfig {

    private KafkaConfig kafka;

    private RedisConfig redis;

    private EmailConfig email;
}
