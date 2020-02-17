package com.xych.bookkeeping.dao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xych.bookkeeping.dao.base.service.impl.BasePageServiceImpl;
import com.xych.bookkeeping.dao.dto.AccountDTO;
import com.xych.bookkeeping.dao.entity.Account;
import com.xych.bookkeeping.dao.mapper.AccountMapper;
import com.xych.bookkeeping.dao.mapstruct.AccountConverter;
import com.xych.bookkeeping.dao.service.AccountServcie;

@Service("accountServcie")
public class AccountServiceImpl extends BasePageServiceImpl<AccountDTO, Account> implements AccountServcie {
    @Autowired
    private AccountMapper mapper;
    @Autowired
    private AccountConverter converter;

    @Override
    protected AccountMapper getMapper() {
        return mapper;
    }

    @Override
    protected AccountConverter getConverter() {
        return converter;
    }
}
