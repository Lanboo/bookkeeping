package com.xych.bookkeeping.dao.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xych.bookkeeping.dao.base.service.impl.BasePageServiceImpl;
import com.xych.bookkeeping.dao.dto.BookDTO;
import com.xych.bookkeeping.dao.entity.Book;
import com.xych.bookkeeping.dao.mapper.BookMapper;
import com.xych.bookkeeping.dao.mapstruct.BookConverter;
import com.xych.bookkeeping.dao.service.BookServcie;

@Service("bookServcie")
public class BookServiceImpl extends BasePageServiceImpl<BookDTO, Book> implements BookServcie {
    @Autowired
    private BookMapper mapper;
    @Autowired
    private BookConverter converter;

    @Override
    protected BookMapper getMapper() {
        return mapper;
    }

    @Override
    protected BookConverter getConverter() {
        return converter;
    }

}
