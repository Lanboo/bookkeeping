package com.xych.bookkeeping.dao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xych.bookkeeping.dao.base.service.impl.BasePageServiceImpl;
import com.xych.bookkeeping.dao.dto.UserDTO;
import com.xych.bookkeeping.dao.entity.User;
import com.xych.bookkeeping.dao.mapper.UserMapper;
import com.xych.bookkeeping.dao.mapstruct.UserConverter;
import com.xych.bookkeeping.dao.service.UserServcie;

@Service
public class UserServiceImpl extends BasePageServiceImpl<UserDTO, User> implements UserServcie {
    @Autowired
    private UserMapper mapper;
    @Autowired
    private UserConverter converter;

    @Override
    protected UserMapper getMapper() {
        return mapper;
    }

    @Override
    protected UserConverter getConverter() {
        return converter;
    }
}
