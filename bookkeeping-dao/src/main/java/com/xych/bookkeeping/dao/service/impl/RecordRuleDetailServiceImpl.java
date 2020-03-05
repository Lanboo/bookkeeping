package com.xych.bookkeeping.dao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xych.bookkeeping.dao.base.service.impl.BasePageServiceImpl;
import com.xych.bookkeeping.dao.dto.RecordRuleDetailDTO;
import com.xych.bookkeeping.dao.entity.RecordRuleDetail;
import com.xych.bookkeeping.dao.mapper.RecordRuleDetailMapper;
import com.xych.bookkeeping.dao.mapstruct.RecordRuleDetailConverter;
import com.xych.bookkeeping.dao.service.RecordRuleDetailServcie;

@Service
public class RecordRuleDetailServiceImpl extends BasePageServiceImpl<RecordRuleDetailDTO, RecordRuleDetail> implements RecordRuleDetailServcie {
    @Autowired
    private RecordRuleDetailMapper mapper;
    @Autowired
    private RecordRuleDetailConverter converter;

    @Override
    protected RecordRuleDetailMapper getMapper() {
        return mapper;
    }

    @Override
    protected RecordRuleDetailConverter getConverter() {
        return converter;
    }
}
