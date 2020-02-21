package com.xych.bookkeeping.app.vo.account;

import java.util.Date;

import com.xych.bookkeeping.app.vo.base.BasePageVO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AccountVO extends BasePageVO {
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
    /**
     * 创建时间
     */
    private Date crtTime;
    /**
     * 修改时间
     */
    private Date uptTime;
}
