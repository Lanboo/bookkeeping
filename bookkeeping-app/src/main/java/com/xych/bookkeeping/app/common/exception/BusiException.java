package com.xych.bookkeeping.app.common.exception;

import com.xych.bookkeeping.app.common.enums.ExceptionEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusiException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String code;
    private String msg;

    public BusiException() {
        super();
    }

    public BusiException(Throwable cause) {
        super(cause);
    }

    public BusiException(String code, String msg) {
        super(code + ":" + msg);
        this.code = code;
        this.msg = msg;
    }

    public BusiException(String code, String msg, Throwable cause) {
        super(code + ":" + msg, cause);
        this.code = code;
        this.msg = msg;
    }

    public BusiException(ExceptionEnum exceEnum) {
        this(exceEnum.getCode(), exceEnum.getMsg());
    }

    public BusiException(ExceptionEnum exceEnum, Throwable cause) {
        this(exceEnum.getCode(), exceEnum.getMsg(), cause);
    }

    public BusiException(ExceptionEnum exceEnum, String msg) {
        this(exceEnum.getCode(), msg);
    }

    public BusiException(ExceptionEnum exceEnum, String msg, Throwable cause) {
        this(exceEnum.getCode(), msg, cause);
    }
}
