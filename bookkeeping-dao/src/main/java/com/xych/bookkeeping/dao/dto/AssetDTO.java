package com.xych.bookkeeping.dao.dto;

import java.util.Date;

import com.xych.bookkeeping.dao.base.dto.BasePageDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AssetDTO extends BasePageDTO {
    private static final long serialVersionUID = 1L;
    /**
     * 用户代码
     */
    private String userCode;
    /**
     * 资产名称
     */
    private String assetName;
    /**
     * 资产模式（0:资产账户,1:负债账户）
     */
    private String assetPattern;
    /**
     * 资产类型（比如:储蓄卡,支付宝,借出款,基金;信用卡,蚂蚁花呗,欠款,房贷）
     */
    private String assetType;
    /**
     * 初始金额
     */
    private Long initialAmount;
    /**
     * 余额
     */
    private Long balance;
    /**
     * 创建时间
     */
    private Date crtTime;
    /**
     * 修改时间
     */
    private Date uptTime;
}
