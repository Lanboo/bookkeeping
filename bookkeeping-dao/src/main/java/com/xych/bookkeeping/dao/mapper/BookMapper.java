package com.xych.bookkeeping.dao.mapper;

import com.xych.bookkeeping.dao.entity.Book;

import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface BookMapper extends Mapper<Book>, InsertListMapper<Book> {
}
