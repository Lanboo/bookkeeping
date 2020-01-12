package com.xych.bookkeeping.dao.base.service;

import java.util.List;

import com.xych.bookkeeping.dao.base.dto.BaseDto;

public interface BaseService<T extends BaseDto> {
    T findOne(T dto);

    List<T> findList(T dto);

    Integer addOne(T dto);

    Integer addList(List<T> dtos);

    Integer update(T dto);

    Integer delete(T dto);
}
