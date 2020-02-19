package com.xych.bookkeeping.app.vo.member;

import java.io.Serializable;

import lombok.Data;

@Data
public class MemberUpdateVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private String id;
    /**
     * 账本名称
     */
    private String memberName;
}
