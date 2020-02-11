package com.xych.bookkeeping.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xych.bookkeeping.app.vo.book.BookVO;
import com.xych.bookkeeping.dao.service.BookServcie;

@Controller
@RequestMapping("book")
public class BookController {
    @Autowired
    private BookServcie bookService;

    @PostMapping("/query")
    @ResponseBody
    public List<BookVO> query(BookVO book) {
        return null;
    }
}
