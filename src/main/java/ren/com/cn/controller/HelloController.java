package ren.com.cn.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ren.com.cn.dao.impl.RedisCacheDaoImpl;
import ren.com.cn.exception.MyException;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/4/17 22:59
 * Email: renhongqiang1397@gmail.com
 */
@Controller
public class HelloController {

    @Autowired
    private RedisCacheDaoImpl redisCacheDao;

    @RequestMapping("/hello")
    @ResponseBody
    public String index() {
        return "Hello World";
    }


    @RequestMapping("/json")
    public String json() throws MyException {
        throw new MyException("发生错误");
    }
}
