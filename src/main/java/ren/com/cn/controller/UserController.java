package ren.com.cn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ren.com.cn.domain.entity.PageResult;
import ren.com.cn.domain.entity.TUser;
import ren.com.cn.domain.entity.User;
import ren.com.cn.service.IUserService;

import javax.annotation.Resource;
import java.util.*;

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

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public String postUser(@RequestBody User user) {
        users.put(user.getId(), user);
        return "success";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(@PathVariable Long id) {

        return users.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String putUser(@PathVariable Long id, @RequestBody User user) {
        User u = users.get(id);
        u.setId(user.getId());
        users.put(id, u);
        return "success";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }

    @RequestMapping(value = "/getTableData",method = RequestMethod.GET)
    @ResponseBody
    public PageResult<TUser> getTableData(@RequestParam(name = "pageNum") int pageNum, int pageSize, String username) {
        return userService.getTableData(pageNum, pageSize, username);
    }

}
