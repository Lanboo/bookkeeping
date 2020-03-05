package com.xych.bookkeeping.app.mapstruct;

import org.mapstruct.Mapper;

import com.xych.bookkeeping.app.mapstruct.base.BaseVOConverter;
import com.xych.bookkeeping.app.vo.DicVO;
import com.xych.bookkeeping.dao.dto.DicDTO;

@Mapper(componentModel = "spring")
public interface DicVOConverter extends BaseVOConverter<DicVO, DicDTO> {
}
