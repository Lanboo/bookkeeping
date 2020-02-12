package com.xych.bookkeeping.dao.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.xych.bookkeeping.dao.base.dto.BasePageDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AlipayRecordDTO extends BasePageDTO {
    private static final long serialVersionUID = 1L;
    /**
     * 用户代码
     */
    private String userCode;
    /**
     * 消费时间
     */
    private String consumeTime;
    /**
     * 消费标题
     */
    private String consumeTitle;
    /**
     * 商户订单号
     */
    private String tradeNo;
    /**
     * 流水号
     */
    private String tradeId;
    /**
     * 对方
     */
    private String other;
    /**
     * 金额，分
     */
    private BigDecimal amount;
    /**
     * 资金流向:1:收入,-1:支出,0:转账
     */
    private Integer fundFlow;
    /**
     * 状态
     */
    private String status;
    /**
     * 流入流出账户
     */
    private String fundTool;
    /**
     * 流出账户
     */
    private String fundToolFrom;
    /**
     * 备注
     */
    private String memo;
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
