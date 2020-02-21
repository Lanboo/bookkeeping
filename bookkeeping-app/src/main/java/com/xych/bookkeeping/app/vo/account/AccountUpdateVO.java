package com.xych.bookkeeping.app.vo.account;

import java.io.Serializable;

import lombok.Data;

@Data
public class AccountUpdateVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private String id;
    /**
     * 账户名称
     */
    private String accountName;
    /**
     * 账户模式（0:资产账户,1:负债账户）
     */
    private String accountPattern;
    /**
     * 账户类型（比如:储蓄卡,支付宝,借出款,基金;信用卡,蚂蚁花呗,欠款,房贷）
     */
    private String accountType;
    /**
     * 余额(元)
     */
    private String balance;

}
