package com.xych.bookkeeping.dao.mapstruct;

import org.mapstruct.Mapper;

import com.xych.bookkeeping.dao.base.mapstruct.BaseConverter;
import com.xych.bookkeeping.dao.dto.RuleDetailDTO;
import com.xych.bookkeeping.dao.entity.RuleDetail;

@Mapper(componentModel = "spring")
public interface RuleDetailConverter extends BaseConverter<RuleDetailDTO, RuleDetail> {
}
