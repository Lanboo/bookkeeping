package com.xych.bookkeeping.dao.mapstruct;

import org.mapstruct.Mapper;

import com.xych.bookkeeping.dao.base.mapstruct.BaseConverter;
import com.xych.bookkeeping.dao.dto.MemberDTO;
import com.xych.bookkeeping.dao.entity.Member;

@Mapper(componentModel = "spring")
public interface MemberConverter extends BaseConverter<MemberDTO, Member> {
}
