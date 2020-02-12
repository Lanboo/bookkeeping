package com.xych.bookkeeping.dao.base.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.xych.bookkeeping.dao.base.dto.BaseDTO;
import com.xych.bookkeeping.dao.base.entity.BaseEntity;
import com.xych.bookkeeping.dao.base.mapper.BaseMapper;
import com.xych.bookkeeping.dao.base.mapstruct.BaseConverter;
import com.xych.bookkeeping.dao.base.service.BaseService;

import tk.mybatis.mapper.entity.Example;

public abstract class BaseServiceImpl<D extends BaseDTO, E extends BaseEntity> implements BaseService<D> {
    protected BaseMapper<E> mapper;
    protected BaseConverter<D, E> converter;
    private Class<E> entityClass;

    @SuppressWarnings("unchecked")
    public BaseServiceImpl() {
        super();
        entityClass = ((Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
    }

    protected abstract void setMapper(BaseMapper<E> mapper);

    protected abstract void setConverter(BaseConverter<D, E> converter);

    @Override
    public D findOne(D dto) {
        return converter.toDto(mapper.selectOne(converter.toEntity(dto)));
    }

    @Override
    public List<D> findList(D dto) {
        return converter.toDtoList(mapper.select(converter.toEntity(dto)));
    }

    @Override
    public Integer addOne(D dto) {
        return mapper.insert(converter.toEntity(dto));
    }

    @Override
    public Integer addList(List<D> dtos) {
        return mapper.insertList(converter.toEntityList(dtos));
    }

    @Override
    public Integer update(D dto) {
        Example example = new Example(entityClass);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", dto.getId());//id
        return mapper.updateByExampleSelective(converter.toEntity(dto), example);
    }

    @Override
    public Integer delete(D dto) {
        Example example = new Example(entityClass);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", dto.getId());//id
        return mapper.deleteByExample(example);
    }

}
