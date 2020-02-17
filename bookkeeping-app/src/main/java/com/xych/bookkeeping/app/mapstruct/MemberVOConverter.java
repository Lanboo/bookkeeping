package com.xych.bookkeeping.app.mapstruct;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

import com.xych.bookkeeping.app.vo.member.MemberUpdateVO;
import com.xych.bookkeeping.app.vo.member.MemberVO;
import com.xych.bookkeeping.dao.dto.MemberDTO;

@Mapper(componentModel = "spring")
public interface MemberVOConverter {
    MemberVO toVo(MemberDTO dto);

    @InheritConfiguration
    List<MemberVO> toVoList(List<MemberDTO> dtoList);

    MemberDTO toDto(MemberVO vo);

    MemberDTO toDto(MemberUpdateVO vo);

    @InheritConfiguration
    List<MemberDTO> toDtoList(List<MemberVO> voList);
}
