package com.xych.bookkeeping.app.vo;

import java.util.Date;

import com.xych.bookkeeping.app.vo.base.BasePageVO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RecordVO extends BasePageVO {
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
    private String amount;
    /**
     * 资金流向:1:收入,-1:支出,0:转账
     */
    private String flow;
    /**
     * 分类
     */
    private String category;
    /**
     * 账户
     */
    private String asset;
    /**
     * 时间
     */
    private Date recordTime;
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
    private String alipayRecordId;
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
