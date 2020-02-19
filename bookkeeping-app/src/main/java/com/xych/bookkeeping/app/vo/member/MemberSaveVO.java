package com.xych.bookkeeping.app.vo.member;

import java.io.Serializable;

import lombok.Data;

@Data
public class MemberSaveVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 成员名称
     */
    private String memberName;
}
