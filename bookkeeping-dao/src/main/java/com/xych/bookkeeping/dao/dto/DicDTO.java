package com.xych.bookkeeping.dao.dto;

import java.util.Date;

import com.xych.bookkeeping.dao.base.dto.BasePageDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DicDTO extends BasePageDTO {
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
    private Integer idx;
    /**
     * 创建时间
     */
    private Date crtTime;
    /**
     * 修改时间
     */
    private Date uptTime;
}
