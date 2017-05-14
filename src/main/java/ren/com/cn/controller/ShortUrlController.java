package ren.com.cn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ren.com.cn.service.ShortUrlService;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/5/7 21:43
 * Email: renhongqiang1397@gmail.com
 */
@RestController
@RequestMapping(value = "/shortUrl")
public class ShortUrlController {

    @Autowired
    private ShortUrlService shortUrlService;

    @RequestMapping(value="/get", method= RequestMethod.GET)
    public String getShortUrl(@RequestParam(required = true,name = "url") String originUrl){
        String shortUrl = shortUrlService.getShortUrl(originUrl);
        return shortUrl;
    }

}
