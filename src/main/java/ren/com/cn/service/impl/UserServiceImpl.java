package ren.com.cn.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ren.com.cn.dao.mapper.gen.UserMapper;
import ren.com.cn.domain.entity.User;
import ren.com.cn.domain.entity.UserExample;
import ren.com.cn.service.IUserService;

import java.util.List;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/5/13 00:10
 * Email: renhongqiang1397@gmail.com
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo getTableData(PageInfo page, String username) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdIsNotNull().andUserNameLike(String.format("%s%s%s", "%",username,"%"));
        List<User> list = userMapper.selectByExample(example);

        Long total = ((Page) list).getTotal();
        page.setList(list);
        page.setTotal(total);
        return page;

    }

    @Override
    public int batchInsert(List<User> userList) {
        return userMapper.insertList(userList);
    }

    @Override
    public int batchInsertSelective(List<User> userList) {
        return userMapper.insertListSelective(userList);
    }


    @Override
    public int testBatchInsert() {
        List<User> list = Lists.newArrayList();
        for (int i = 0; i < 1000000; i++) {
            User user = new User();
            user.setUserName(RandomStringUtils.randomAlphabetic(4));
            user.setSex(1);
            list.add(user);
        }
        int resultRows = 0;
        resultRows = batchInsertSelective(list.subList(0, 4));
        return resultRows;
    }

    @Override
    public void saveUser(User user) {
        userMapper.insertSelective(user);
    }

    @Override
    public User getById(Long id) {
        return userMapper.selectByPrimaryKey(id.intValue());
    }
}
