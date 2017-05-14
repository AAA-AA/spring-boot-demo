package ren.com.cn.domain.entity;

import lombok.Data;

import java.util.List;

/**
 * Created by user on 2016/11/10.
 */
@Data
public class PageResult<T> {

    private List<T> data;

    private long total;

    public PageResult(List<T> data, long total){
        this.data= data;
        this.total=total;
    }
}
