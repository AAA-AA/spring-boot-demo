package ren.com.cn.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ren.com.cn.domain.third.UserMsg;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author : hongqiangren.
 * @since: 2018/5/17 17:02
 */
@Controller
@RequestMapping(value = "/wechat")
@Slf4j
public class WechatTokenController {

    private final static String  TOKEN = "ren111";

    @RequestMapping(value = {"/token"}, method = RequestMethod.GET)
    @ResponseBody
    public String getToken(@RequestParam(name = "signature",required = false) String signature,
                           @RequestParam(name = "timestamp",required = false) String timestamp,
                           @RequestParam(name = "nonce",required = false) String nonce,
                           @RequestParam(name = "echostr",required = false)String echostr) throws NoSuchAlgorithmException {
        log.info("nonce:{},signature:{},timestamp:{},echostr:{}",nonce,signature,timestamp,echostr);
        String signHex = getSignHex(timestamp,nonce);
        log.info("wechat signature:----{}-----,campute signature:{}",signature,signHex);
        return echostr;
    }

    @RequestMapping(value = "/receiveMsg", method = RequestMethod.POST)
    @ResponseBody
    public void getUserMsg(@RequestBody UserMsg userMsg) {
        log.info("receive msg {}",userMsg);
    }

    private String getSignHex(String timestamp, String nonce) throws NoSuchAlgorithmException {
        String[] array = new String[] { TOKEN, timestamp, nonce};
        StringBuffer sb = new StringBuffer();
        // 字符串排序
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
        }
        String str = sb.toString();
        // SHA1签名生成
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(str.getBytes());
        byte[] digest = md.digest();

        StringBuffer hexstr = new StringBuffer();
        String shaHex = "";
        for (int i = 0; i < digest.length; i++) {
            shaHex = Integer.toHexString(digest[i] & 0xFF);
            if (shaHex.length() < 2) {
                hexstr.append(0);
            }
            hexstr.append(shaHex);
        }
        return hexstr.toString();
    }


}
