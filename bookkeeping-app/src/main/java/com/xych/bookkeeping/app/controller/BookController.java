package com.xych.bookkeeping.app.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xych.bookkeeping.app.common.support.UserSupport;
import com.xych.bookkeeping.app.mapstruct.BookVOConverter;
import com.xych.bookkeeping.app.vo.base.PageVO;
import com.xych.bookkeeping.app.vo.book.BookSaveVO;
import com.xych.bookkeeping.app.vo.book.BookVO;
import com.xych.bookkeeping.dao.base.dto.Page;
import com.xych.bookkeeping.dao.dto.BookDTO;
import com.xych.bookkeeping.dao.service.BookServcie;
import com.xych.uid.UidGenerator;

@Controller
@RequestMapping("book")
public class BookController {
    @Autowired
    private BookServcie bookService;
    @Autowired
    private BookVOConverter voConverter;
    @Autowired
    private UidGenerator defaultUidGenerator;
    @Autowired
    private UserSupport userSupport;

    @PostMapping("/query")
    @ResponseBody
    public PageVO<BookVO> query(@Valid @RequestBody BookVO book) {
        Page<BookDTO> dtoPage = this.bookService.findPage(voConverter.toDto(book));
        return new PageVO<>(dtoPage.getPageNum(), dtoPage.getPageSize(), dtoPage.getTotal(), voConverter.toVoList(dtoPage.getData()));
    }

    @PostMapping("/save")
    @ResponseBody
    public void save(@Valid @RequestBody BookSaveVO book) {
        BookDTO dto = new BookDTO();
        dto.setId(defaultUidGenerator.getUID());
        dto.setUserCode(userSupport.getUser().getUserCode());
        dto.setBookName(book.getBookName());
        dto.setCrtTime(new Date());
        dto.setUptTime(dto.getCrtTime());
        this.bookService.addOne(dto);
    }

    @PostMapping("/remove")
    @ResponseBody
    public void remove(@Valid @RequestBody List<Long> ids) {
        this.bookService.deleteByIds(ids);
    }
}
