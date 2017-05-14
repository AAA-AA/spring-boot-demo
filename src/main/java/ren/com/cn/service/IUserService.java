package ren.com.cn.service;

import ren.com.cn.domain.entity.PageResult;
import ren.com.cn.domain.entity.TUser;

import java.util.Map;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/5/13 00:09
 * Email: renhongqiang1397@gmail.com
 */
public interface IUserService {

    PageResult<TUser> getTableData(int pageNum, int pageSize, String username);

}
