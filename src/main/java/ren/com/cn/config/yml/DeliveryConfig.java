package ren.com.cn.config.yml;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA ^_^
 *
 * @author : hongqiangren.
 * @date: 2017/11/7 19:48
 * @email: renhongqiang1397@gmail.com
 */
@ConfigurationProperties("delivery")
@Component
@Data
public class DeliveryConfig {

    private String appId;
    private String apiUrl;
    private String appSecret;
    private String callbackUrl;
}
