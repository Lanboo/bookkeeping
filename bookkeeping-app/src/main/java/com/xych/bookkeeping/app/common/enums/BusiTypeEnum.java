package com.xych.bookkeeping.app.common.enums;

/**
 * 业务类型
 * @CreateDate 2020年1月17日下午3:40:06
 */
public enum BusiTypeEnum {
    WEB2ALIPAY("WEB2ALIPAY", "网页抓取支付宝数据"),
    ALIPAY2RECORD("ALIPAY2RECORD", "支付宝数据转系统数据"),
    //
    ;

    private String code;
    private String msg;

    private BusiTypeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "BusiTypeEnum [code=" + code + ", msg=" + msg + "]";
    }
}
