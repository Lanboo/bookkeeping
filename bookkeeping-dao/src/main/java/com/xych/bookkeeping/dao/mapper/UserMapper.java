package com.xych.bookkeeping.dao.mapper;

import com.xych.bookkeeping.dao.entity.User;

import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<User>, InsertListMapper<User> {
}
