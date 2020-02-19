package com.xych.bookkeeping.app.vo.category;

import java.io.Serializable;

import lombok.Data;

@Data
public class CategorySaveVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 类型名称
     */
    private String categoryName;
    /**
     * 父级类型
     */
    private String parentId;
}
