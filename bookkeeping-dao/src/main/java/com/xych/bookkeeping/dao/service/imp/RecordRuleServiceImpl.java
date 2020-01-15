package com.xych.bookkeeping.dao.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xych.bookkeeping.dao.dto.RecordRuleDTO;
import com.xych.bookkeeping.dao.entity.Record;
import com.xych.bookkeeping.dao.entity.RecordRule;
import com.xych.bookkeeping.dao.mapper.RecordRuleMapper;
import com.xych.bookkeeping.dao.mapstruct.RecordRuleConverter;
import com.xych.bookkeeping.dao.service.RecordRuleServcie;

import tk.mybatis.mapper.entity.Example;

@Service("recordRuleServcie")
public class RecordRuleServiceImpl implements RecordRuleServcie {
    @Autowired
    private RecordRuleMapper mapper;
    @Autowired
    private RecordRuleConverter converter;

    @Override
    public RecordRuleDTO findOne(RecordRuleDTO dto) {
        return converter.toDto(mapper.selectOne(converter.toEntity(dto)));
    }

    @Override
    public List<RecordRuleDTO> findList(RecordRuleDTO dto) {
        return converter.toDtoList(mapper.select(converter.toEntity(dto)));
    }

    @Override
    public Integer addOne(RecordRuleDTO dto) {
        return mapper.insert(converter.toEntity(dto));
    }

    @Override
    public Integer addList(List<RecordRuleDTO> dtos) {
        return mapper.insertList(converter.toEntityList(dtos));
    }

    @Override
    public Integer update(RecordRuleDTO dto) {
        Example example = new Example(RecordRule.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", dto.getId());//id
        return mapper.updateByExampleSelective(converter.toEntity(dto), example);
    }

    @Override
    public Integer delete(RecordRuleDTO dto) {
        Example example = new Example(Record.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", dto.getId());//id
        return mapper.deleteByExample(example);
    }
}
