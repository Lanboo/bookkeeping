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
    private Class<E> entityClass;

    @SuppressWarnings("unchecked")
    public BaseServiceImpl() {
        super();
        entityClass = ((Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
    }

    protected abstract BaseMapper<E> getMapper();

    protected abstract BaseConverter<D, E> getConverter();

    @Override
    public D findOne(D dto) {
        return getConverter().toDto(getMapper().selectOne(getConverter().toEntity(dto)));
    }

    @Override
    public List<D> findList(D dto) {
        return getConverter().toDtoList(getMapper().select(getConverter().toEntity(dto)));
    }

    @Override
    public Integer addOne(D dto) {
        return getMapper().insert(getConverter().toEntity(dto));
    }

    @Override
    public Integer addList(List<D> dtos) {
        return getMapper().insertList(getConverter().toEntityList(dtos));
    }

    @Override
    public Integer update(D dto) {
        Example example = new Example(entityClass);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", dto.getId());//id
        return getMapper().updateByExampleSelective(getConverter().toEntity(dto), example);
    }

    @Override
    public Integer delete(D dto) {
        Example example = new Example(entityClass);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", dto.getId());//id
        return getMapper().deleteByExample(example);
    }

}
