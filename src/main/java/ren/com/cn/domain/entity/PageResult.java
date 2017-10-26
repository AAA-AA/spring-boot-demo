package ren.com.cn.domain.entity;


import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * Created by user on 2016/11/10.
 */
@Data
public class PageResult<T> {

    private List<T> data;
    private Integer pageNum;
    private Integer pageSize;
    private Integer total;

    public PageResult(List<T> data) {
        this.data = data;
        this.total = data.size();
    }

    public PageResult() {
    }

    public PageResult(List<T> data, Integer pageNum, Integer pageSize) {
        this.data = data;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = data.size();
    }

    public static PageResult.Builder create() {
        return new Builder();
    }



    public static class Builder {
        private List data;
        private Integer pageNum;
        private Integer pageSize;
        private Integer total;

        public Builder() {
            this.data = Collections.emptyList();
            this.pageSize = 0;
            this.pageNum = 0;
            this.total = data.size();
        }

        public Builder(List data, Integer pageNum, Integer pageSize, Integer total) {
            this.data = data;
            this.pageNum = pageNum;
            this.pageSize = pageSize;
            this.total = total;
        }

        public Builder setData(List data) {
            this.data = data;
            return this;
        }

        public Builder setPageNum(Integer pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        public Builder setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Builder setTotal(Integer total) {
            this.total = total;
            return this;
        }

        public PageResult build() {
            return new PageResult(data, pageNum, pageSize);
        }

    }

}
