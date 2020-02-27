package com.xych.bookkeeping.dao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xych.bookkeeping.dao.base.service.impl.BasePageServiceImpl;
import com.xych.bookkeeping.dao.dto.BookDTO;
import com.xych.bookkeeping.dao.entity.Book;
import com.xych.bookkeeping.dao.mapper.BookMapper;
import com.xych.bookkeeping.dao.mapstruct.BookConverter;
import com.xych.bookkeeping.dao.service.BookServcie;
import com.xych.bookkeeping.dao.utils.ExampleUtil;

import tk.mybatis.mapper.entity.Example;

@Service("bookServcie")
public class BookServiceImpl extends BasePageServiceImpl<BookDTO, Book> implements BookServcie {
    @Autowired
    private BookMapper mapper;
    @Autowired
    private BookConverter converter;

    /**
     * 重写分页查询条件
     * 
     * bookName 模糊查询
     */
    @Override
    protected Example buildQueryPageExample(Book entity) {
        Example example = new Example(entityClass);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", entity.getId());
        criteria.andEqualTo("userCode", entity.getUserCode());
        ExampleUtil.Criteria.addLike(criteria, "bookName", entity.getBookName());
        return example;
    }

    @Override
    protected BookMapper getMapper() {
        return mapper;
    }

    @Override
    protected BookConverter getConverter() {
        return converter;
    }
}
