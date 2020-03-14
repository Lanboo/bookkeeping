package com.xych.bookkeeping.dao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xych.bookkeeping.dao.base.service.impl.BasePageServiceImpl;
import com.xych.bookkeeping.dao.dto.DicDTO;
import com.xych.bookkeeping.dao.entity.Dic;
import com.xych.bookkeeping.dao.mapper.DicMapper;
import com.xych.bookkeeping.dao.mapstruct.DicConverter;
import com.xych.bookkeeping.dao.service.DicServcie;
import com.xych.bookkeeping.dao.utils.ExampleUtil;

import tk.mybatis.mapper.entity.Example;

@Service
public class DicServiceImpl extends BasePageServiceImpl<DicDTO, Dic> implements DicServcie {
    @Autowired
    private DicMapper mapper;
    @Autowired
    private DicConverter converter;

    @Override
    protected Example buildQueryPageExample(Dic entity) {
        Example example = new Example(entityClass);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", entity.getId());
        criteria.andEqualTo("dicType", entity.getDicType());
        ExampleUtil.Criteria.addLike(criteria, "dicDesc", entity.getDicDesc());
        criteria.andEqualTo("dicKey", entity.getDicKey());
        ExampleUtil.Criteria.addLike(criteria, "dicValue", entity.getDicValue());
        criteria.andEqualTo("validity", entity.getValidity());
        example.orderBy("dicType").asc()//
            .orderBy("idx").asc()//
            .orderBy("dicKey").asc()//
            .orderBy("id").asc();
        return example;
    }

    @Override
    protected DicMapper getMapper() {
        return mapper;
    }

    @Override
    protected DicConverter getConverter() {
        return converter;
    }
}
