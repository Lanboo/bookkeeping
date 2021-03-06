package com.xych.bookkeeping.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.xych.bookkeeping.dao.base.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_alipay_record")
public class AlipayRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 用户代码
     */
    private String userCode;
    /**
     * 消费时间
     */
    private Date consumeTime;
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
    // 非数据库字段
    /**
     * 消费开始时间
     */
    @Transient
    private Date consumeTimeStart;
    /**
     * 消费结束时间
     */
    @Transient
    private Date consumeTimeEnd;
}
