package ren.com.cn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ren.com.cn.dao.RedisCacheDao;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/5/8 16:23
 * Email: renhongqiang1397@gmail.com
 */
@RestController
@RequestMapping(value = "/purge")
public class PurgeCacheController {

    private RedisCacheDao cacheDao;

    @RequestMapping(value = "/shortUrl")
    public String purgeShortUrlCache(HttpServletRequest request,@RequestParam(required = true ,value = "raw_key") String raw_key) throws UnsupportedEncodingException {
        request.getRequestURI();

        String key = DigestUtils.md5DigestAsHex(raw_key.getBytes("utf-8"));
        cacheDao.delKey(key);
        return  "ok";
    }





}
