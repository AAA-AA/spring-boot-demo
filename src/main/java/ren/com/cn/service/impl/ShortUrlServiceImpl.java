package ren.com.cn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import ren.com.cn.common.utils.BaseCodeUtils;
import ren.com.cn.dao.RedisCacheDao;
import ren.com.cn.service.ShortUrlService;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/5/7 21:48
 * Email: renhongqiang1397@gmail.com
 */
@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    private static final String SHORT_URL_PREFFIX = "http://t.net/";

    private static final String CACHE_KEY = "cache_key";

    @Autowired
    private RedisCacheDao cacheDao;

    @Override
    public String getShortUrl(String originUrl) {
        if (checkCache(originUrl)) {
            try {
                return (String)cacheDao.getFromValue(DigestUtils.md5DigestAsHex(originUrl.getBytes("utf-8")) ,String.class);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        Long increment = cacheDao.increment(CACHE_KEY, 100L);
        int salt = 1000;
        return generateShortUrl(increment+salt,originUrl);
    }

    private boolean checkCache(String originUrl) {
        try {
            return cacheDao.hasKey(DigestUtils.md5DigestAsHex(originUrl.getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }


    private String generateShortUrl(Long code,String originUrl) {
        String suffix = BaseCodeUtils._10_to_62(code);
        String targetUrl = String.format("%s%s",SHORT_URL_PREFFIX,suffix);
        try {
            cacheDao.putToValue(DigestUtils.md5DigestAsHex(originUrl.getBytes("utf-8")),targetUrl,24L, TimeUnit.HOURS);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return targetUrl;
    }


}
