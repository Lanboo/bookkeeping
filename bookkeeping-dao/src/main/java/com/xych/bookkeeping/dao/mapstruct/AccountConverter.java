package com.xych.bookkeeping.dao.mapstruct;

import org.mapstruct.Mapper;

import com.xych.bookkeeping.dao.base.mapstruct.BaseConverter;
import com.xych.bookkeeping.dao.dto.AccountDTO;
import com.xych.bookkeeping.dao.entity.Account;

@Mapper(componentModel = "spring")
public interface AccountConverter extends BaseConverter<AccountDTO, Account> {
}
