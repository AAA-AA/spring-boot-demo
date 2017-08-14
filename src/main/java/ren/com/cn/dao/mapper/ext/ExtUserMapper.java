package ren.com.cn.dao.mapper.ext;

import org.apache.ibatis.annotations.Param;
import ren.com.cn.domain.entity.User;

import java.util.List;

public interface ExtUserMapper {

    int batchInsert(@Param(value = "list") List<User> userList);

    int batchInsertSelective(@Param(value = "list") List<User> userList);
}