package com.xych.bookkeeping.dao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xych.bookkeeping.dao.base.service.impl.BasePageServiceImpl;
import com.xych.bookkeeping.dao.dto.RecordDTO;
import com.xych.bookkeeping.dao.entity.Record;
import com.xych.bookkeeping.dao.mapper.RecordMapper;
import com.xych.bookkeeping.dao.mapstruct.RecordConverter;
import com.xych.bookkeeping.dao.service.RecordServcie;
import com.xych.bookkeeping.dao.utils.ExampleUtil;

import tk.mybatis.mapper.entity.Example;

@Service("recordServcie")
public class RecordServiceImpl extends BasePageServiceImpl<RecordDTO, Record> implements RecordServcie {
    @Autowired
    private RecordMapper mapper;
    @Autowired
    private RecordConverter converter;

    @Override
    protected Example buildQueryPageExample(Record entity) {
        Example example = new Example(entityClass);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", entity.getId());
        criteria.andEqualTo("userCode", entity.getUserCode());
        criteria.andEqualTo("accountBook", entity.getAccountBook());
        criteria.andEqualTo("flow", entity.getFlow());
        criteria.andEqualTo("category", entity.getCategory());
        criteria.andEqualTo("asset", entity.getAsset());
        criteria.andBetween("recordTime", entity.getRecordTimeStart(), entity.getRecordTimeEnd());
        criteria.andEqualTo("recordDesc", entity.getRecordDesc());
        ExampleUtil.Criteria.addLike(criteria, "familyMember", entity.getFamilyMember());
        return example;
    }

    @Override
    protected RecordMapper getMapper() {
        return mapper;
    }

    @Override
    protected RecordConverter getConverter() {
        return converter;
    }
}
