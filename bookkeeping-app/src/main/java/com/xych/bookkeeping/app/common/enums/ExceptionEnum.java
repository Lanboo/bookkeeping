package com.xych.bookkeeping.app.common.enums;

/**
 * 异常枚举
 * @author Lanboo
 * @CreateDate 2020年1月18日下午3:02:45
 */
public enum ExceptionEnum {
    SYSTEM_ERROR("S00001", "系统异常"),
    SYSTEM_TIMEOUT("S00002", "系统超时"),
    PARAM_ERROR("S00003", "参数错误"),
    //
    ;

    private String code;
    private String msg;

    private ExceptionEnum(String code, String msg) {
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
        return "ExceptionEnum [code=" + code + ", msg=" + msg + "]";
    }
}
