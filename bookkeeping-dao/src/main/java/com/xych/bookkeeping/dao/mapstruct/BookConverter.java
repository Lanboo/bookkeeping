package com.xych.bookkeeping.dao.mapstruct;

import org.mapstruct.Mapper;

import com.xych.bookkeeping.dao.base.mapstruct.BaseConverter;
import com.xych.bookkeeping.dao.dto.BookDTO;
import com.xych.bookkeeping.dao.entity.Book;

@Mapper(componentModel = "spring")
public interface BookConverter extends BaseConverter<BookDTO, Book> {
}
