package ren.com.cn.dao.mapper.gen;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import ren.com.cn.domain.entity.SmsSendLog;
import ren.com.cn.domain.entity.SmsSendLogExample;

public interface SmsSendLogMapper {
    long countByExample(SmsSendLogExample example);

    int deleteByExample(SmsSendLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmsSendLog record);

    int insertSelective(SmsSendLog record);

    List<SmsSendLog> selectByExample(SmsSendLogExample example);

    SmsSendLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SmsSendLog record, @Param("example") SmsSendLogExample example);

    int updateByExample(@Param("record") SmsSendLog record, @Param("example") SmsSendLogExample example);

    int updateByPrimaryKeySelective(SmsSendLog record);

    int updateByPrimaryKey(SmsSendLog record);

    /** need allowMultiQueries=true support */
    int insertList(List<SmsSendLog> records);

    /** need allowMultiQueries=true support */
    int insertListSelective(List<SmsSendLog> records);
}