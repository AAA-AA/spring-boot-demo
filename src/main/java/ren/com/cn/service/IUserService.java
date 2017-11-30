package ren.com.cn.service;

import com.github.pagehelper.PageInfo;
import ren.com.cn.common.annotation.EnableReSubmit;
import ren.com.cn.domain.entity.User;

import java.util.List;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/5/13 00:09
 * Email: renhongqiang1397@gmail.com
 */
public interface IUserService {

    PageInfo getTableData(PageInfo page, String username);

    int batchInsert(List<User> userList);

    int batchInsertSelective(List<User> userList);

    int testBatchInsert();

    @EnableReSubmit(maxWait = 5)
    void saveUser(User user);

    User getById(Long id);
}
