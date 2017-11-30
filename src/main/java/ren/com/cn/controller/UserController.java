package ren.com.cn.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ren.com.cn.common.annotation.EnableReSubmit;
import ren.com.cn.common.base.ResponseDTO;
import ren.com.cn.config.yml.DeliveryConfig;
import ren.com.cn.domain.entity.User;
import ren.com.cn.service.IUserService;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/4/18 10:17
 * Email: renhongqiang1397@gmail.com
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    @Autowired
    private DeliveryConfig deliveryConfig;

    @Resource
    private IUserService userService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    ;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    @ResponseBody
    public List<User> getUserList() {
        List<User> r = new ArrayList<User>(users.values());
        return r;
    }


    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    @ResponseBody
    //@EnableReSubmit(maxWait = 100, unit = TimeUnit.SECONDS)
    public ResponseDTO saveUser(@RequestBody User user) {
        String apiUrl = deliveryConfig.getApiUrl();
        userService.saveUser(user);
        return ResponseDTO.success();
    }


    @RequestMapping(value = "/testBatchInsert")
    @ResponseBody
    public int testBatchInsert() {
        return userService.testBatchInsert();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(@PathVariable Long id) {

        return userService.getById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }

    @RequestMapping(value = "/getTableData", method = RequestMethod.GET)
    @ResponseBody
    public PageInfo getTableData(PageInfo page, String username) {
        return userService.getTableData(page, username);
    }

}
