package com.xych.bookkeeping.dao.mapstruct;

import org.mapstruct.Mapper;

import com.xych.bookkeeping.dao.base.mapstruct.BaseConverter;
import com.xych.bookkeeping.dao.dto.CategoryDTO;
import com.xych.bookkeeping.dao.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryConverter extends BaseConverter<CategoryDTO, Category> {
}
