package com.xych.bookkeeping.dao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xych.bookkeeping.dao.base.service.impl.BasePageServiceImpl;
import com.xych.bookkeeping.dao.dto.AlipayRecordDTO;
import com.xych.bookkeeping.dao.entity.AlipayRecord;
import com.xych.bookkeeping.dao.mapper.AlipayRecordMapper;
import com.xych.bookkeeping.dao.mapstruct.AlipayRecordConverter;
import com.xych.bookkeeping.dao.service.AlipayRecordServcie;
import com.xych.bookkeeping.dao.utils.ExampleUtil;

import tk.mybatis.mapper.entity.Example;

@Service("alipayRecordServcie")
public class AlipayRecordServiceImpl extends BasePageServiceImpl<AlipayRecordDTO, AlipayRecord> implements AlipayRecordServcie {
    @Autowired
    private AlipayRecordMapper mapper;
    @Autowired
    private AlipayRecordConverter converter;

    @Override
    protected Example buildQueryPageExample(AlipayRecord entity) {
        Example example = new Example(entityClass);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", entity.getId());
        criteria.andEqualTo("userCode", entity.getUserCode());
        criteria.andBetween("consumeTime", entity.getConsumeTimeStart(), entity.getConsumeTimeEnd());
        ExampleUtil.Criteria.addLike(criteria, "consumeTitle", entity.getConsumeTitle());
        criteria.andEqualTo("tradeNo", entity.getTradeNo());
        criteria.andEqualTo("tradeId", entity.getTradeId());
        ExampleUtil.Criteria.addLike(criteria, "other", entity.getOther());
        criteria.andEqualTo("amount", entity.getAmount());
        criteria.andEqualTo("fundFlow", entity.getFundFlow());
        criteria.andEqualTo("status", entity.getStatus());
        ExampleUtil.Criteria.addLike(criteria, "fundTool", entity.getFundTool());
        criteria.andEqualTo("fundToolFrom", entity.getFundToolFrom());
        criteria.andEqualTo("memo", entity.getMemo());
        return example;
    }

    @Override
    protected AlipayRecordMapper getMapper() {
        return mapper;
    }

    @Override
    protected AlipayRecordConverter getConverter() {
        return converter;
    }
}
