package com.xych.bookkeeping.dao.entity;

import java.util.Date;

import javax.persistence.Table;

import com.xych.bookkeeping.dao.base.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_dic")
public class Dic extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 字典类型
     */
    private String dicType;
    /**
     * 字典类型描述
     */
    private String dicDesc;
    /**
     * 字典key
     */
    private String dicKey;
    /**
     * 字典值
     */
    private String dicValue;
    /**
     * 父级ID
     */
    private String parentId;
    /**
     * 是否有效:0-无效,1-有效
     */
    private String validity;
    /**
     * 顺序
     */
    private String idx;
    /**
     * 创建时间
     */
    private Date crtTime;
    /**
     * 修改时间
     */
    private Date uptTime;
}
