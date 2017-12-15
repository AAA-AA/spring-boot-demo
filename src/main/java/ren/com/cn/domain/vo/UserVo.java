package ren.com.cn.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created by IntelliJ IDEA ^_^
 *
 * @author : hongqiangren.
 * @date: 2017/12/15 17:07
 * @email: renhongqiang1397@gmail.com
 */
@Data
public class UserVo {
    private Integer userId;

    private String userName;

    private Integer sex;

    private String ctime;

    private String mtime;

    /**0为正常，1为删除*/
    private Integer status;
}
