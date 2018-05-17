package ren.com.cn.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * @author : hongqiangren.
 * @since: 2018/5/17 17:02
 */
@Controller
@RequestMapping(value = "/wechat")
@Slf4j
public class WechatTokenController {

    @RequestMapping(value = {"/token"}, method = RequestMethod.GET)
    @ResponseBody
    public String getToken(@RequestParam(name = "signature") String signature,
                           @RequestParam(name = "timestamp") Long timestamp,
                           @RequestParam(name = "nonce") String nonce,
                           @RequestParam(name = "echostr")String echostr) {
        TreeMap<String,String> map = new TreeMap(Comparator.naturalOrder());
        log.info("nonce:{},signature:{},timestamp:{},echostr:{}",nonce,signature,timestamp,echostr);
        map.put("nonce",nonce);
        map.put("signature",signature);
        map.put("timestamp",String.valueOf(timestamp));
        String compute = DigestUtils.sha1Hex(nonce + signature + timestamp);
        log.info("wechat echostr:----{}-----,campute result:{}",echostr,compute);
        return echostr;
    }


}
