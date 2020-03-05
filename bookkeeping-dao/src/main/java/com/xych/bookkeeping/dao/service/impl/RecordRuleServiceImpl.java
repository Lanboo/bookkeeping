package com.xych.bookkeeping.dao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xych.bookkeeping.dao.base.service.impl.BasePageServiceImpl;
import com.xych.bookkeeping.dao.dto.RecordRuleDTO;
import com.xych.bookkeeping.dao.entity.RecordRule;
import com.xych.bookkeeping.dao.mapper.RecordRuleMapper;
import com.xych.bookkeeping.dao.mapstruct.RecordRuleConverter;
import com.xych.bookkeeping.dao.service.RecordRuleServcie;

@Service
public class RecordRuleServiceImpl extends BasePageServiceImpl<RecordRuleDTO, RecordRule> implements RecordRuleServcie {
    @Autowired
    private RecordRuleMapper mapper;
    @Autowired
    private RecordRuleConverter converter;

    @Override
    protected RecordRuleMapper getMapper() {
        return mapper;
    }

    @Override
    protected RecordRuleConverter getConverter() {
        return converter;
    }
}
