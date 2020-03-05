package com.xych.bookkeeping.dao.mapstruct;

import org.mapstruct.Mapper;

import com.xych.bookkeeping.dao.base.mapstruct.BaseConverter;
import com.xych.bookkeeping.dao.dto.DicDTO;
import com.xych.bookkeeping.dao.entity.Dic;

@Mapper(componentModel = "spring")
public interface DicConverter extends BaseConverter<DicDTO, Dic> {
}
