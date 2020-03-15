package com.xych.bookkeeping.dao.dto;

import java.util.Date;
import java.util.List;

import com.xych.bookkeeping.dao.base.dto.BasePageDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RuleDTO extends BasePageDTO {
    private static final long serialVersionUID = 1L;
    /**
     * 用户代码
     */
    private String userCode;
    /**
     * 业务场景
     */
    private String busiType;
    /**
     * 目标字段
     */
    private String targetField;
    /**
     * 输出结果
     */
    private String targetFieldValue;
    /**
     * 明细表达式
     */
    private String expression;
    /**
     * 创建时间
     */
    private Date crtTime;
    /**
     * 修改时间
     */
    private Date uptTime;
    /**
     * 规则明细
     */
    private List<RuleDetailDTO> details;
}
