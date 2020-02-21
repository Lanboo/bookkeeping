package com.xych.bookkeeping.app.vo.asset;

import java.io.Serializable;

import lombok.Data;

@Data
public class AssetSaveVO implements Serializable {
    private static final long serialVersionUID = 1L;
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
     * 余额(元)
     */
    private String balance;

}
