package com.xych.bookkeeping.app.vo;

import java.util.Date;

import com.xych.bookkeeping.app.vo.base.BasePageVO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DicVO extends BasePageVO {
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private String id;
    /**
     * 字典key
     */
    private String dicKey;
    /**
     * 字典值
     */
    private String dicValue;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 字典类型
     */
    private String dicType;
    /**
     * 字典类型描述
     */
    private String dicDesc;
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
