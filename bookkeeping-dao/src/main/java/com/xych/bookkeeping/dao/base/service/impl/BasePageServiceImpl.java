package com.xych.bookkeeping.dao.base.service.impl;

import java.util.List;
import java.util.Objects;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xych.bookkeeping.dao.base.dto.BasePageDTO;
import com.xych.bookkeeping.dao.base.dto.Page;
import com.xych.bookkeeping.dao.base.entity.BaseEntity;
import com.xych.bookkeeping.dao.base.service.BasePageService;

import tk.mybatis.mapper.entity.Example;

public abstract class BasePageServiceImpl<D extends BasePageDTO, E extends BaseEntity> extends BaseServiceImpl<D, E> implements BasePageService<D> {
    @Override
    public Page<D> findPage(D dto) {
        PageHelper.startPage(dto.getCurrent(), dto.getPageSize());
        E entity = getConverter().toEntity(dto);
        Example queryExample = buildQueryPageExample(entity);
        List<E> entityList = Objects.isNull(queryExample) ? getMapper().select(entity) : getMapper().selectByExample(queryExample);
        PageInfo<E> pageInfo = new PageInfo<>(entityList);
        return new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), getConverter().toDtoList(entityList));
    }

    protected Example buildQueryPageExample(E entity) {
        return buildQueryExample(entity);
    }
}
