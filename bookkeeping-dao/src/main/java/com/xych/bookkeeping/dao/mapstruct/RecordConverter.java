package com.xych.bookkeeping.dao.mapstruct;

import org.mapstruct.Mapper;

import com.xych.bookkeeping.dao.base.mapstruct.BaseConverter;
import com.xych.bookkeeping.dao.dto.RecordDTO;
import com.xych.bookkeeping.dao.entity.Record;

@Mapper(componentModel = "spring")
public interface RecordConverter extends BaseConverter<RecordDTO, Record> {
}
