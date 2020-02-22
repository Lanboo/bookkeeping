package com.xych.bookkeeping.app.vo.alipay.record;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class AlipayRecordSaveVO implements Serializable {
    private static final long serialVersionUID = 1L;
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
    private String amount;
    /**
     * 资金流向:1:收入,-1:支出,0:转账
     */
    private String fundFlow;
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
}
