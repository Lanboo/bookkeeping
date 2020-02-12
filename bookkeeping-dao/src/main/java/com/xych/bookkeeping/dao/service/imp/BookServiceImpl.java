package com.xych.bookkeeping.dao.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xych.bookkeeping.dao.base.mapper.BaseMapper;
import com.xych.bookkeeping.dao.base.mapstruct.BaseConverter;
import com.xych.bookkeeping.dao.base.service.impl.BasePageServiceImpl;
import com.xych.bookkeeping.dao.dto.BookDTO;
import com.xych.bookkeeping.dao.entity.Book;
import com.xych.bookkeeping.dao.service.BookServcie;

@Service("bookServcie")
public class BookServiceImpl extends BasePageServiceImpl<BookDTO, Book> implements BookServcie {

    @Override
    @Resource
    protected void setMapper(BaseMapper<Book> mapper) {
        this.mapper = mapper;
    }

    @Override
    @Resource
    protected void setConverter(BaseConverter<BookDTO, Book> converter) {
        this.converter = converter;
    }
}
