package com.xych.bookkeeping.dao.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xych.bookkeeping.dao.dto.MemberDTO;
import com.xych.bookkeeping.dao.entity.Member;
import com.xych.bookkeeping.dao.mapper.MemberMapper;
import com.xych.bookkeeping.dao.mapstruct.MemberConverter;
import com.xych.bookkeeping.dao.service.MemberServcie;

import tk.mybatis.mapper.entity.Example;

@Service("memberServcie")
public class MemberServiceImpl implements MemberServcie {
    @Autowired
    private MemberMapper mapper;
    @Autowired
    private MemberConverter converter;

    @Override
    public MemberDTO findOne(MemberDTO dto) {
        return converter.toDto(mapper.selectOne(converter.toEntity(dto)));
    }

    @Override
    public List<MemberDTO> findList(MemberDTO dto) {
        return converter.toDtoList(mapper.select(converter.toEntity(dto)));
    }

    @Override
    public Integer addOne(MemberDTO dto) {
        return mapper.insert(converter.toEntity(dto));
    }

    @Override
    public Integer addList(List<MemberDTO> dtos) {
        return mapper.insertList(converter.toEntityList(dtos));
    }

    @Override
    public Integer update(MemberDTO dto) {
        Example example = new Example(Member.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", dto.getId());//id
        return mapper.updateByExampleSelective(converter.toEntity(dto), example);
    }

    @Override
    public Integer delete(MemberDTO dto) {
        Example example = new Example(Member.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", dto.getId());//id
        return mapper.deleteByExample(example);
    }
}
