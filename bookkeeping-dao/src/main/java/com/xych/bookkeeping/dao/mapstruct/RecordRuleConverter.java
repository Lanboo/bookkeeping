package com.xych.bookkeeping.dao.mapstruct;

import org.mapstruct.Mapper;

import com.xych.bookkeeping.dao.base.mapstruct.BaseConverter;
import com.xych.bookkeeping.dao.dto.RecordRuleDTO;
import com.xych.bookkeeping.dao.entity.RecordRule;

@Mapper(componentModel = "spring")
public interface RecordRuleConverter extends BaseConverter<RecordRuleDTO, RecordRule> {
}
