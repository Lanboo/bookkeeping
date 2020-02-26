package com.xych.bookkeeping.app.mapstruct;

import org.mapstruct.Mapper;

import com.xych.bookkeeping.app.mapstruct.base.BaseVOConverter;
import com.xych.bookkeeping.app.vo.AlipayRecordVO;
import com.xych.bookkeeping.dao.dto.AlipayRecordDTO;

@Mapper(componentModel = "spring")
public interface AlipayRecordVOConverter extends BaseVOConverter<AlipayRecordVO, AlipayRecordDTO> {
}