package com.xych.bookkeeping.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xych.bookkeeping.app.mapstruct.BookVOConverter;
import com.xych.bookkeeping.app.vo.book.BookVO;
import com.xych.bookkeeping.dao.base.dto.Page;
import com.xych.bookkeeping.dao.dto.BookDTO;
import com.xych.bookkeeping.dao.service.BookServcie;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("book")
public class BookController {
    @Autowired
    private BookServcie bookService;
    @Autowired
    private BookVOConverter voConverter;

    @GetMapping("/query")
    @ResponseBody
    public Page<BookVO> query(BookVO book) {
        Page<BookDTO> dtoPage = this.bookService.findPage(voConverter.toDto(book));
        log.info("{}", dtoPage);
        return new Page<>(dtoPage.getPageNum(), dtoPage.getPageSize(), dtoPage.getTotal(), voConverter.toVoList(dtoPage.getList()));
    }
}
