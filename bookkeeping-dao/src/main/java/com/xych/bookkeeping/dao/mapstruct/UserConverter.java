package com.xych.bookkeeping.dao.mapstruct;

import org.mapstruct.Mapper;

import com.xych.bookkeeping.dao.base.mapstruct.BaseConverter;
import com.xych.bookkeeping.dao.dto.UserDTO;
import com.xych.bookkeeping.dao.entity.User;

@Mapper(componentModel = "spring")
public interface UserConverter extends BaseConverter<UserDTO, User> {
}
