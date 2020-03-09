package com.xych.bookkeeping.dao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xych.bookkeeping.dao.base.service.impl.BasePageServiceImpl;
import com.xych.bookkeeping.dao.dto.RuleDetailDTO;
import com.xych.bookkeeping.dao.entity.RuleDetail;
import com.xych.bookkeeping.dao.mapper.RuleDetailMapper;
import com.xych.bookkeeping.dao.mapstruct.RuleDetailConverter;
import com.xych.bookkeeping.dao.service.RuleDetailServcie;

@Service
public class RuleDetailServiceImpl extends BasePageServiceImpl<RuleDetailDTO, RuleDetail> implements RuleDetailServcie {
    @Autowired
    private RuleDetailMapper mapper;
    @Autowired
    private RuleDetailConverter converter;

    @Override
    protected RuleDetailMapper getMapper() {
        return mapper;
    }

    @Override
    protected RuleDetailConverter getConverter() {
        return converter;
    }
}
