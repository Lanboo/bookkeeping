package com.xych.bookkeeping.dao.entity;

import java.util.Date;

import javax.persistence.Table;

import com.xych.bookkeeping.dao.base.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_asset")
public class Asset extends BaseEntity {
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
