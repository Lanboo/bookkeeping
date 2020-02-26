package com.xych.bookkeeping.app.mapstruct;

import org.mapstruct.Mapper;

import com.xych.bookkeeping.app.mapstruct.base.BaseVOConverter;
import com.xych.bookkeeping.app.vo.BookVO;
import com.xych.bookkeeping.dao.dto.BookDTO;

@Mapper(componentModel = "spring")
public interface BookVOConverter extends BaseVOConverter<BookVO, BookDTO> {
}
