package ren.com.cn.test;

import lombok.Data;

import java.util.List;

@Data
public class Page<T> {
    private List<T> data;


}
