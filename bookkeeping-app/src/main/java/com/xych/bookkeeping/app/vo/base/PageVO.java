package com.xych.bookkeeping.app.vo.base;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 分页查询结果集
 * @CreateDate 2020年2月11日下午5:16:57
 */
@Data
public class PageVO<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    //当前页
    private int current;
    //每页的数量
    private int pageSize;
    //总记录数
    private long total;
    //结果集
    private List<T> data;

    //当前页的数量
    private int size;

    //总页数
    private int pages;
    //前一页
    private int prePageNum;
    //下一页
    private int nextPageNum;

    //是否为第一页
    private boolean firstPage = false;
    //是否为最后一页
    private boolean lastPage = false;
    //是否有前一页
    private boolean hasPreviousPage = false;
    //是否有下一页
    private boolean hasNextPage = false;

    public PageVO(int pageSize, long total) {
        this(1, pageSize, total, null);
    }

    public PageVO(int current, int pageSize, long total) {
        this(current, pageSize, total, null);
    }

    public PageVO(int current, int pageSize, long total, List<T> list) {
        this.current = current > 1 ? current : 1;
        this.pageSize = pageSize;
        this.total = total;
        this.data = list;

        if(list != null) {
            this.size = list.size();
        }
        else {
            this.size = pageSize;
        }

        this.pages = (int) Math.ceil(1.0 * this.total / this.pageSize);
        this.current = this.current > this.pages ? this.pages : this.current;

        this.firstPage = this.current == 1;
        this.lastPage = this.current == this.pages;
        this.hasPreviousPage = !this.firstPage;
        this.hasNextPage = !this.lastPage;

        this.prePageNum = this.firstPage ? 1 : this.current - 1;
        this.nextPageNum = this.lastPage ? this.pages : this.current + 1;
    }

}
