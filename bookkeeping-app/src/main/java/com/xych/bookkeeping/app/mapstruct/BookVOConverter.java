package com.xych.bookkeeping.app.mapstruct;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

import com.xych.bookkeeping.app.vo.book.BookUpdateVO;
import com.xych.bookkeeping.app.vo.book.BookVO;
import com.xych.bookkeeping.dao.dto.BookDTO;

@Mapper(componentModel = "spring")
public interface BookVOConverter {
    BookVO toVo(BookDTO dto);

    @InheritConfiguration
    List<BookVO> toVoList(List<BookDTO> dtoList);

    BookDTO toDto(BookVO vo);
    
    BookDTO toDto(BookUpdateVO vo);

    @InheritConfiguration
    List<BookDTO> toDtoList(List<BookVO> voList);
}
