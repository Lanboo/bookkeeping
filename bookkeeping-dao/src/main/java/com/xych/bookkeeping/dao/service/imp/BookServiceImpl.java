package com.xych.bookkeeping.dao.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xych.bookkeeping.dao.base.dto.Page;
import com.xych.bookkeeping.dao.dto.BookDTO;
import com.xych.bookkeeping.dao.entity.Book;
import com.xych.bookkeeping.dao.mapper.BookMapper;
import com.xych.bookkeeping.dao.mapstruct.BookConverter;
import com.xych.bookkeeping.dao.service.BookServcie;

import tk.mybatis.mapper.entity.Example;

@Service("bookServcie")
public class BookServiceImpl implements BookServcie {
    @Autowired
    private BookMapper mapper;
    @Autowired
    private BookConverter converter;

    @Override
    public BookDTO findOne(BookDTO dto) {
        return converter.toDto(mapper.selectOne(converter.toEntity(dto)));
    }

    @Override
    public List<BookDTO> findList(BookDTO dto) {
        return converter.toDtoList(mapper.select(converter.toEntity(dto)));
    }

    @Override
    public Page<BookDTO> findPage(BookDTO dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<Book> entityList = mapper.select(converter.toEntity(dto));
        PageInfo<Book> pageInfo = new PageInfo<>(entityList);
        return new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), converter.toDtoList(entityList));
    }

    @Override
    public Integer addOne(BookDTO dto) {
        return mapper.insert(converter.toEntity(dto));
    }

    @Override
    public Integer addList(List<BookDTO> dtos) {
        return mapper.insertList(converter.toEntityList(dtos));
    }

    @Override
    public Integer update(BookDTO dto) {
        Example example = new Example(Book.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", dto.getId());//id
        return mapper.updateByExampleSelective(converter.toEntity(dto), example);
    }

    @Override
    public Integer delete(BookDTO dto) {
        Example example = new Example(Book.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", dto.getId());//id
        return mapper.deleteByExample(example);
    }
}
