package com.xych.bookkeeping.dao.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xych.bookkeeping.dao.dto.RecordRuleDetailDTO;
import com.xych.bookkeeping.dao.entity.RecordRuleDetail;
import com.xych.bookkeeping.dao.mapper.RecordRuleDetailMapper;
import com.xych.bookkeeping.dao.mapstruct.RecordRuleDetailConverter;
import com.xych.bookkeeping.dao.service.RecordRuleDetailServcie;

import tk.mybatis.mapper.entity.Example;

@Service("recordRuleDetailServcie")
public class RecordRuleDetailServiceImpl implements RecordRuleDetailServcie {
    @Autowired
    private RecordRuleDetailMapper mapper;
    @Autowired
    private RecordRuleDetailConverter converter;

    @Override
    public RecordRuleDetailDTO findOne(RecordRuleDetailDTO dto) {
        return converter.toDto(mapper.selectOne(converter.toEntity(dto)));
    }

    @Override
    public List<RecordRuleDetailDTO> findList(RecordRuleDetailDTO dto) {
        return converter.toDtoList(mapper.select(converter.toEntity(dto)));
    }

    @Override
    public Integer addOne(RecordRuleDetailDTO dto) {
        return mapper.insert(converter.toEntity(dto));
    }

    @Override
    public Integer addList(List<RecordRuleDetailDTO> dtos) {
        return mapper.insertList(converter.toEntityList(dtos));
    }

    @Override
    public Integer update(RecordRuleDetailDTO dto) {
        Example example = new Example(RecordRuleDetail.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", dto.getId());//id
        return mapper.updateByExampleSelective(converter.toEntity(dto), example);
    }

    @Override
    public Integer delete(RecordRuleDetailDTO dto) {
        Example example = new Example(RecordRuleDetail.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", dto.getId());//id
        return mapper.deleteByExample(example);
    }
}
