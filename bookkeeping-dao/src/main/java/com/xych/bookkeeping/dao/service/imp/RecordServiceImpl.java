package com.xych.bookkeeping.dao.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xych.bookkeeping.dao.dto.RecordDTO;
import com.xych.bookkeeping.dao.entity.Record;
import com.xych.bookkeeping.dao.mapper.RecordMapper;
import com.xych.bookkeeping.dao.mapstruct.RecordConverter;
import com.xych.bookkeeping.dao.service.RecordServcie;

import tk.mybatis.mapper.entity.Example;

@Service("recordServcie")
public class RecordServiceImpl implements RecordServcie {
    @Autowired
    private RecordMapper mapper;
    @Autowired
    private RecordConverter converter;

    @Override
    public RecordDTO findOne(RecordDTO dto) {
        return converter.toDto(mapper.selectOne(converter.toEntity(dto)));
    }

    @Override
    public List<RecordDTO> findList(RecordDTO dto) {
        return converter.toDtoList(mapper.select(converter.toEntity(dto)));
    }

    @Override
    public Integer addOne(RecordDTO dto) {
        return mapper.insert(converter.toEntity(dto));
    }

    @Override
    public Integer addList(List<RecordDTO> dtos) {
        return mapper.insertList(converter.toEntityList(dtos));
    }

    @Override
    public Integer update(RecordDTO dto) {
        Example example = new Example(Record.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", dto.getId());//id
        return mapper.updateByExampleSelective(converter.toEntity(dto), example);
    }

    @Override
    public Integer delete(RecordDTO dto) {
        Example example = new Example(Record.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", dto.getId());//id
        return mapper.deleteByExample(example);
    }
}
