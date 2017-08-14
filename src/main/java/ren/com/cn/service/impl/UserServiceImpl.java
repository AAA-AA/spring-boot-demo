package ren.com.cn.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ren.com.cn.dao.mapper.gen.UserMapper;
import ren.com.cn.domain.entity.PageResult;
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
    public PageResult<User> getTableData(int pageNum, int pageSize, String username) {

        PageHelper.startPage(pageNum, pageSize);
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdIsNotNull();
        criteria.andUserNameLike(String.format("%s%s%s", "%", username, "%"));
        List<User> users = userMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(users, pageSize);

        return new PageResult(users, pageInfo.getTotal());

    }

    @Override
    public int batchInsert(List<User> userList) {
        return userMapper.batchInsert(userList);
    }

    @Override
    public int batchInsertSelective(List<User> userList) {
        return userMapper.batchInsertSelective(userList);
    }


    @Override
    public int testBatchInsert() {
        List<User> list = Lists.newArrayList();
        for (int i = 0; i < 1000000; i++) {
            User user = new User();
            user.setUserName(RandomStringUtils.randomAlphabetic(4));
            user.setSex((byte) 1);
            list.add(user);
        }
        Long start = System.currentTimeMillis();
        int resultRows = 0;
        resultRows = batchInsertSelective(list.subList(0,4));
        /*for (int j = 0; j < list.size(); j += 10000) {
            int toIndex = j + 10000;
            if (toIndex > list.size()) {
                resultRows += batchInsert(list.subList(j, list.size()));
            } else {
                resultRows += batchInsert(list.subList(j, toIndex));
            }
        }
        Long end = System.currentTimeMillis();
        log.error("batchInsert {} data cost :{}ms", resultRows, end - start);*/
        return resultRows;
    }
}
