package com.xych.bookkeeping.dao.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xych.bookkeeping.dao.dto.AlipayRecordDTO;
import com.xych.bookkeeping.dao.entity.AlipayRecord;
import com.xych.bookkeeping.dao.mapper.AlipayRecordMapper;
import com.xych.bookkeeping.dao.mapstruct.AlipayRecordConverter;
import com.xych.bookkeeping.dao.service.AlipayRecordServcie;

import tk.mybatis.mapper.entity.Example;

@Service("alipayRecordServcie")
public class AlipayRecordServiceImpl implements AlipayRecordServcie {
    @Autowired
    private AlipayRecordMapper mapper;
    @Autowired
    private AlipayRecordConverter converter;

    @Override
    public AlipayRecordDTO findOne(AlipayRecordDTO dto) {
        return converter.toDto(mapper.selectOne(converter.toEntity(dto)));
    }

    @Override
    public List<AlipayRecordDTO> findList(AlipayRecordDTO dto) {
        return converter.toDtoList(mapper.select(converter.toEntity(dto)));
    }

    @Override
    public Integer addOne(AlipayRecordDTO dto) {
        return mapper.insert(converter.toEntity(dto));
    }

    @Override
    public Integer update(AlipayRecordDTO dto) {
        Example example = new Example(AlipayRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", dto.getId());//id
        return mapper.updateByExampleSelective(converter.toEntity(dto), example);
    }

    @Override
    public Integer delete(AlipayRecordDTO dto) {
        Example example = new Example(AlipayRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", dto.getId());//id
        return mapper.deleteByExample(example);
    }
}
