package com.xych.bookkeeping.dao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xych.bookkeeping.dao.base.service.impl.BasePageServiceImpl;
import com.xych.bookkeeping.dao.dto.RecordDTO;
import com.xych.bookkeeping.dao.entity.Record;
import com.xych.bookkeeping.dao.mapper.RecordMapper;
import com.xych.bookkeeping.dao.mapstruct.RecordConverter;
import com.xych.bookkeeping.dao.service.RecordServcie;

@Service("recordServcie")
public class RecordServiceImpl extends BasePageServiceImpl<RecordDTO, Record> implements RecordServcie {
    @Autowired
    private RecordMapper mapper;
    @Autowired
    private RecordConverter converter;

    @Override
    protected RecordMapper getMapper() {
        return mapper;
    }

    @Override
    protected RecordConverter getConverter() {
        return converter;
    }
}
