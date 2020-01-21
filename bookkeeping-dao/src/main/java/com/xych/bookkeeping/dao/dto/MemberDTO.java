package com.xych.bookkeeping.dao.dto;

import java.util.Date;

import com.xych.bookkeeping.dao.base.dto.BaseDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MemberDTO extends BaseDto {
    private static final long serialVersionUID = 1L;
    private Long id;
    /**
     * 用户代码
     */
    private String userCode;
    /**
     * 成员名称
     */
    private String memberName;
    /**
     * 创建时间
     */
    private Date crtTime;
    /**
     * 修改时间
     */
    private Date uptTime;
}
