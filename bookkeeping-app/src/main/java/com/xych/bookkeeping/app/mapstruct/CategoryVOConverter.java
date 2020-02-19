package com.xych.bookkeeping.app.mapstruct;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

import com.xych.bookkeeping.app.vo.category.CategoryUpdateVO;
import com.xych.bookkeeping.app.vo.category.CategoryVO;
import com.xych.bookkeeping.dao.dto.CategoryDTO;

@Mapper(componentModel = "spring")
public interface CategoryVOConverter {
    CategoryVO toVo(CategoryDTO dto);

    @InheritConfiguration
    List<CategoryVO> toVoList(List<CategoryDTO> dtoList);

    CategoryDTO toDto(CategoryVO vo);

    CategoryDTO toDto(CategoryUpdateVO vo);

    @InheritConfiguration
    List<CategoryDTO> toDtoList(List<CategoryVO> voList);
}