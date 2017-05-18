package ren.com.cn.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value="获取短链接", notes="根据传入的长url获取短url")
    @ApiImplicitParam(name = "originUrl", value = "原始待转换的url", required = true, dataType = "String")
    @RequestMapping(value="/get", method= RequestMethod.GET)
    public String getShortUrl(String originUrl){
        String shortUrl = shortUrlService.getShortUrl(originUrl);
        return shortUrl;
    }


    @ApiOperation(value="test", notes="根据传入的长url获取短url")
    @ApiImplicitParam(name = "url", value = "原始待转换的url", required = true, dataType = "String")
    @RequestMapping(value="/test", method= RequestMethod.GET)
    public String test(@RequestParam(required = true,name = "url") String originUrl){
        String shortUrl = shortUrlService.getShortUrl(originUrl);
        return shortUrl;
    }
}
