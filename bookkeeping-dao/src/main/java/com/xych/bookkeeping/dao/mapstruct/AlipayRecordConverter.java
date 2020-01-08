package com.xych.bookkeeping.dao.mapstruct;

import org.mapstruct.Mapper;

import com.xych.bookkeeping.dao.base.mapstruct.BaseConverter;
import com.xych.bookkeeping.dao.dto.AlipayRecordDTO;
import com.xych.bookkeeping.dao.entity.AlipayRecord;

@Mapper(componentModel = "spring")
public interface AlipayRecordConverter extends BaseConverter<AlipayRecordDTO, AlipayRecord> {
}
