package com.xych.bookkeeping.app.vo.category;

import java.util.Date;
import java.util.List;

import com.xych.bookkeeping.app.vo.base.BasePageVO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CategoryVO extends BasePageVO {
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private String id;
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
    private String parentId;
    /**
     * 创建时间
     */
    private Date crtTime;
    /**
     * 修改时间
     */
    private Date uptTime;
    /**
     * 子节点数据
     */
    private List<CategoryVO> children;
}
