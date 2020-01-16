package com.xych.bookkeeping.app.common.enums;

public enum BusiTypeEnum {
    WEB2ALIPAY("web2alipay", "网页抓取支付宝数据"),
    ALIPAY2RECORD("alipay2record", "支付宝数据转系统数据"),
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
