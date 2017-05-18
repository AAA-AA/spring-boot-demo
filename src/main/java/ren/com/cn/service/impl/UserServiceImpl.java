package ren.com.cn.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ren.com.cn.dao.mapper.TUserMapper;
import ren.com.cn.dao.mapper.mbg.MBGTUserMapper;
import ren.com.cn.domain.entity.PageResult;
import ren.com.cn.domain.entity.TUser;
import ren.com.cn.domain.entity.TUserExample;
import ren.com.cn.service.IUserService;

import java.util.List;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/5/13 00:10
 * Email: renhongqiang1397@gmail.com
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private TUserMapper userMapper;

    @Override
    public PageResult<TUser> getTableData(int pageNum, int pageSize, String username) {

        PageHelper.startPage(pageNum,pageSize);
        TUserExample example = new TUserExample();
        TUserExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdIsNotNull();
        criteria.andUserNameLike(String.format("%s%s%s","%",username,"%"));
        List<TUser> users = userMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(users,pageSize);

        return new PageResult(users, pageInfo.getTotal());

    }
}
