package ren.com.cn.controller;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ren.com.cn.common.base.ResponseDTO;
import ren.com.cn.dao.impl.RedisCacheDaoImpl;
import ren.com.cn.exception.MyException;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/4/17 22:59
 * Email: renhongqiang1397@gmail.com
 */
@Controller
@Slf4j
public class HelloController {

    private RedisCacheDaoImpl redisCacheDao;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello World";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/json")
    public String json() throws MyException {
        throw new MyException("发生错误");
    }

    @RequestMapping(value = "/delivery/callback", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO callback(@RequestBody CallbackVo vo) {
        System.out.println(vo);
        log.info(String.format("回调结果：%s",vo));
        return ResponseDTO.success();
    }

    @Data
    public static class CallbackVo {
        @JsonProperty("open_order_code")
        private String openOrderCode;
        @JsonProperty("partner_order_code")
        private String partnerOrderCode;
        private Integer order_status;
        private Long push_time;
        private String carrier_driver_name;
        private String carrier_driver_phone;
        private String description;
        private String error_code;
        private String station_name;
        private String station_tel;
        private Integer cancel_reason;

        public String getOpenOrderCode() {
            return openOrderCode;
        }

        public void setOpenOrderCode(String openOrderCode) {
            this.openOrderCode = openOrderCode;
        }

        public String getPartnerOrderCode() {
            return partnerOrderCode;
        }

        public void setPartnerOrderCode(String partnerOrderCode) {
            this.partnerOrderCode = partnerOrderCode;
        }
    }

}
