package com.xych.bookkeeping.dao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xych.bookkeeping.dao.base.service.impl.BasePageServiceImpl;
import com.xych.bookkeeping.dao.dto.RuleDTO;
import com.xych.bookkeeping.dao.entity.Rule;
import com.xych.bookkeeping.dao.mapper.RuleMapper;
import com.xych.bookkeeping.dao.mapstruct.RuleConverter;
import com.xych.bookkeeping.dao.service.RuleServcie;

@Service
public class RuleServiceImpl extends BasePageServiceImpl<RuleDTO, Rule> implements RuleServcie {
    @Autowired
    private RuleMapper mapper;
    @Autowired
    private RuleConverter converter;

    @Override
    protected RuleMapper getMapper() {
        return mapper;
    }

    @Override
    protected RuleConverter getConverter() {
        return converter;
    }
}
