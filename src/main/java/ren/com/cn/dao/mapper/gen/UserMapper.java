package ren.com.cn.dao.mapper.gen;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import ren.com.cn.domain.entity.User;
import ren.com.cn.domain.entity.UserExample;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /** need allowMultiQueries=true support */
    int insertList(List<User> records);

    /** need allowMultiQueries=true support */
    int insertListSelective(List<User> records);
}