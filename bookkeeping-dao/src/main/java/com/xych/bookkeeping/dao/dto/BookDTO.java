package com.xych.bookkeeping.dao.dto;

import java.util.Date;

import com.xych.bookkeeping.dao.base.dto.BasePageDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 用户账本
 * @CreateDate 2020年1月21日下午4:40:36
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BookDTO extends BasePageDTO {
    private static final long serialVersionUID = 1L;
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
