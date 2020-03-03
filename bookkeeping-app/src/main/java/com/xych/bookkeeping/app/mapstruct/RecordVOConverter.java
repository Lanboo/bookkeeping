package com.xych.bookkeeping.app.mapstruct;

import org.mapstruct.Mapper;

import com.xych.bookkeeping.app.mapstruct.base.BaseVOConverter;
import com.xych.bookkeeping.app.vo.RecordVO;
import com.xych.bookkeeping.dao.dto.RecordDTO;

@Mapper(componentModel = "spring")
public interface RecordVOConverter extends BaseVOConverter<RecordVO, RecordDTO> {
}