package com.xych.bookkeeping.dao.service.imp;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xych.bookkeeping.dao.base.service.impl.BasePageServiceImpl;
import com.xych.bookkeeping.dao.dto.MemberDTO;
import com.xych.bookkeeping.dao.entity.Member;
import com.xych.bookkeeping.dao.mapper.MemberMapper;
import com.xych.bookkeeping.dao.mapstruct.MemberConverter;
import com.xych.bookkeeping.dao.service.MemberServcie;

import tk.mybatis.mapper.entity.Example;

@Service("memberServcie")
public class MemberServiceImpl extends BasePageServiceImpl<MemberDTO, Member> implements MemberServcie {
    @Autowired
    private MemberMapper mapper;
    @Autowired
    private MemberConverter converter;

    /**
     * 重写分页查询条件
     * 
     * memberName 模糊查询
     */
    @Override
    protected Example buildQueryPageExample(Member entity) {
        Example example = new Example(entityClass);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", entity.getId());
        criteria.andEqualTo("userCode", entity.getUserCode());
        if(StringUtils.isNotEmpty(entity.getMemberName())) {
            criteria.andLike("memberName", "%" + entity.getMemberName() + "%");
        }
        return example;
    }

    @Override
    protected MemberMapper getMapper() {
        return mapper;
    }

    @Override
    protected MemberConverter getConverter() {
        return converter;
    }
}
