package com.xych.bookkeeping.dao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xych.bookkeeping.dao.base.service.impl.BasePageServiceImpl;
import com.xych.bookkeeping.dao.dto.CategoryDTO;
import com.xych.bookkeeping.dao.entity.Category;
import com.xych.bookkeeping.dao.mapper.CategoryMapper;
import com.xych.bookkeeping.dao.mapstruct.CategoryConverter;
import com.xych.bookkeeping.dao.service.CategoryServcie;

@Service
public class CategoryServiceImpl extends BasePageServiceImpl<CategoryDTO, Category> implements CategoryServcie {
    @Autowired
    private CategoryMapper mapper;
    @Autowired
    private CategoryConverter converter;

    @Override
    protected CategoryMapper getMapper() {
        return mapper;
    }

    @Override
    protected CategoryConverter getConverter() {
        return converter;
    }
}
