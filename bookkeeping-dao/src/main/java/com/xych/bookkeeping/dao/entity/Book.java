package com.xych.bookkeeping.dao.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import com.xych.bookkeeping.dao.base.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户账本
 * @CreateDate 2020年1月21日下午4:40:36
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_book")
public class Book extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    /**
     * 用户代码
     */
    private String userCode;
    /**
     * 账本名称
     */
    private String bookName;
    /**
     * 创建时间
     */
    private Date crtTime;
    /**
     * 修改时间
     */
    private Date uptTime;
}
