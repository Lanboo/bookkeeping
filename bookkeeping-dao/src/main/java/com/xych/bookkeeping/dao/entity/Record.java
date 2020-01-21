package com.xych.bookkeeping.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import com.xych.bookkeeping.dao.base.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_record")
public class Record extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @Id
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
    private Long accountBook;
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
