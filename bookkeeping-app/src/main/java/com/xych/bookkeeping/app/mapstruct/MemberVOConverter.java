package com.xych.bookkeeping.app.mapstruct;

import org.mapstruct.Mapper;

import com.xych.bookkeeping.app.mapstruct.base.BaseVOConverter;
import com.xych.bookkeeping.app.vo.MemberVO;
import com.xych.bookkeeping.dao.dto.MemberDTO;

@Mapper(componentModel = "spring")
public interface MemberVOConverter extends BaseVOConverter<MemberVO, MemberDTO> {
}
