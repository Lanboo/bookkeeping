package com.xych.bookkeeping.app.vo;

import java.util.Date;

import com.xych.bookkeeping.app.vo.base.BasePageVO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BookVO extends BasePageVO {
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
