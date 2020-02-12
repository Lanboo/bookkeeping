package com.xych.bookkeeping.dao.base.mapper;

import com.xych.bookkeeping.dao.base.entity.BaseEntity;

import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface BaseMapper<T extends BaseEntity> extends Mapper<T>, InsertListMapper<T> {
}
