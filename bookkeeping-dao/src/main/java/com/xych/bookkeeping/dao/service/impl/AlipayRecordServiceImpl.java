package com.xych.bookkeeping.dao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xych.bookkeeping.dao.base.service.impl.BasePageServiceImpl;
import com.xych.bookkeeping.dao.dto.AlipayRecordDTO;
import com.xych.bookkeeping.dao.entity.AlipayRecord;
import com.xych.bookkeeping.dao.mapper.AlipayRecordMapper;
import com.xych.bookkeeping.dao.mapstruct.AlipayRecordConverter;
import com.xych.bookkeeping.dao.service.AlipayRecordServcie;

@Service("alipayRecordServcie")
public class AlipayRecordServiceImpl extends BasePageServiceImpl<AlipayRecordDTO, AlipayRecord> implements AlipayRecordServcie {
    @Autowired
    private AlipayRecordMapper mapper;
    @Autowired
    private AlipayRecordConverter converter;

    @Override
    protected AlipayRecordMapper getMapper() {
        return mapper;
    }

    @Override
    protected AlipayRecordConverter getConverter() {
        return converter;
    }
}
