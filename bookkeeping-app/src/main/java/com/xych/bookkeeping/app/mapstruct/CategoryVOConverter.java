package com.xych.bookkeeping.app.mapstruct;

import org.mapstruct.Mapper;

import com.xych.bookkeeping.app.mapstruct.base.BaseVOConverter;
import com.xych.bookkeeping.app.vo.CategoryVO;
import com.xych.bookkeeping.dao.dto.CategoryDTO;

@Mapper(componentModel = "spring")
public interface CategoryVOConverter extends BaseVOConverter<CategoryVO, CategoryDTO>{
}