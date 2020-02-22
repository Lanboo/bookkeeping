package com.xych.bookkeeping.app.mapstruct;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

import com.xych.bookkeeping.app.vo.alipay.record.AlipayRecordSaveVO;
import com.xych.bookkeeping.app.vo.alipay.record.AlipayRecordUpdateVO;
import com.xych.bookkeeping.app.vo.alipay.record.AlipayRecordVO;
import com.xych.bookkeeping.dao.dto.AlipayRecordDTO;

@Mapper(componentModel = "spring")
public interface AlipayRecordVOConverter {
    AlipayRecordVO toVo(AlipayRecordDTO dto);

    @InheritConfiguration
    List<AlipayRecordVO> toVoList(List<AlipayRecordDTO> dtoList);

    AlipayRecordDTO toDto(AlipayRecordVO vo);

    AlipayRecordDTO toDto(AlipayRecordUpdateVO vo);

    AlipayRecordDTO toDto(AlipayRecordSaveVO vo);

    @InheritConfiguration
    List<AlipayRecordDTO> toDtoList(List<AlipayRecordVO> voList);
}