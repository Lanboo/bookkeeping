package com.xych.bookkeeping.dao.mapper;

import com.xych.bookkeeping.dao.entity.Member;

import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface MemberMapper extends Mapper<Member>, InsertListMapper<Member> {
}
