package com.xych.bookkeeping.app.vo.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BasePageVO extends BaseVO {
    private static final long serialVersionUID = 1L;
    /**
     * 当前页
     */
    private Integer current;
    /**
     * 每页数量
     */
    private Integer pageSize;
}
