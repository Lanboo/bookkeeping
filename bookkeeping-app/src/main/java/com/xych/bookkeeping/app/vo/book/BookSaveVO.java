package com.xych.bookkeeping.app.vo.book;

import java.io.Serializable;

import lombok.Data;

@Data
public class BookSaveVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 账本名称
     */
    private String bookName;
}
