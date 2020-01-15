package com.xych.bookkeeping.dao.mapstruct;

import org.mapstruct.Mapper;

import com.xych.bookkeeping.dao.base.mapstruct.BaseConverter;
import com.xych.bookkeeping.dao.dto.RecordRuleDetailDTO;
import com.xych.bookkeeping.dao.entity.RecordRuleDetail;

@Mapper(componentModel = "spring")
public interface RecordRuleDetailConverter extends BaseConverter<RecordRuleDetailDTO, RecordRuleDetail> {
}
