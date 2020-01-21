package com.xych.bookkeeping.dao.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import com.xych.bookkeeping.dao.base.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_category")
public class Category extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    /**
     * 用户代码
     */
    private String userCode;
    /**
     * 类型名称
     */
    private String categoryName;
    /**
     * 父级类型
     */
    private Long parentId;
    /**
     * 创建时间
     */
    private Date crtTime;
    /**
     * 修改时间
     */
    private Date uptTime;
}
