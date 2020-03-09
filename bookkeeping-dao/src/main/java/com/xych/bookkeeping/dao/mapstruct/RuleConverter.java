package com.xych.bookkeeping.dao.mapstruct;

import org.mapstruct.Mapper;

import com.xych.bookkeeping.dao.base.mapstruct.BaseConverter;
import com.xych.bookkeeping.dao.dto.RuleDTO;
import com.xych.bookkeeping.dao.entity.Rule;

@Mapper(componentModel = "spring")
public interface RuleConverter extends BaseConverter<RuleDTO, Rule> {
}
