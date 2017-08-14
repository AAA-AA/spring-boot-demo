package ren.com.cn.service;

import org.apache.ibatis.annotations.Param;
import ren.com.cn.domain.entity.PageResult;
import ren.com.cn.domain.entity.User;

import java.util.List;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/5/13 00:09
 * Email: renhongqiang1397@gmail.com
 */
public interface IUserService {

    PageResult<User> getTableData(int pageNum, int pageSize, String username);

    int batchInsert(List<User> userList);

    int batchInsertSelective(List<User> userList);

    int testBatchInsert();

}
