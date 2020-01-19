package com.xych.bookkeeping.dao.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.xych.bookkeeping.dao.base.dto.BaseDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RecordDTO extends BaseDto {
    private static final long serialVersionUID = 1L;
    private Long id;
    /**
     * 用户代码
     */
    private String userCode;
    /**
     * 业务场景
     */
    private String busiType;
    /**
     * 账本
     */
    private String accountBook;
    /**
     * 金额，分
     */
    private BigDecimal amount;
    /**
     * 资金流向:1:收入,-1:支出,0:转账
     */
    private Integer flow;
    /**
     * 分类
     */
    private Long category;
    /**
     * 账户
     */
    private Long accountType;
    /**
     * 时间
     */
    private String recordTime;
    /**
     * 备注
     */
    private String recordDesc;
    /**
     * 成员(允许多成员平摊)
     */
    private String familyMember;
    /**
     * 支付宝流水表ID
     */
    private Long alipayRecordId;
    /**
     * 创建时间
     */
    private Date crtTime;
    /**
     * 修改时间
     */
    private Date uptTime;
    /**
     * 操作人
     */
    private String operator;
}
