package com.xych.bookkeeping.dao.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xych.bookkeeping.dao.dto.CategoryDTO;
import com.xych.bookkeeping.dao.entity.Category;
import com.xych.bookkeeping.dao.mapper.CategoryMapper;
import com.xych.bookkeeping.dao.mapstruct.CategoryConverter;
import com.xych.bookkeeping.dao.service.CategoryServcie;

import tk.mybatis.mapper.entity.Example;

@Service("categoryServcie")
public class CategoryServiceImpl implements CategoryServcie {
    @Autowired
    private CategoryMapper mapper;
    @Autowired
    private CategoryConverter converter;

    @Override
    public CategoryDTO findOne(CategoryDTO dto) {
        return converter.toDto(mapper.selectOne(converter.toEntity(dto)));
    }

    @Override
    public List<CategoryDTO> findList(CategoryDTO dto) {
        return converter.toDtoList(mapper.select(converter.toEntity(dto)));
    }

    @Override
    public Integer addOne(CategoryDTO dto) {
        return mapper.insert(converter.toEntity(dto));
    }

    @Override
    public Integer addList(List<CategoryDTO> dtos) {
        return mapper.insertList(converter.toEntityList(dtos));
    }

    @Override
    public Integer update(CategoryDTO dto) {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", dto.getId());//id
        return mapper.updateByExampleSelective(converter.toEntity(dto), example);
    }

    @Override
    public Integer delete(CategoryDTO dto) {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", dto.getId());//id
        return mapper.deleteByExample(example);
    }
}
