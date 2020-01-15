package com.xych.bookkeeping.dao.mapper;

import com.xych.bookkeeping.dao.entity.RecordRule;

import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface RecordRuleMapper extends Mapper<RecordRule>, InsertListMapper<RecordRule> {
}
