package com.xych.bookkeeping.app.mapstruct;

import org.mapstruct.Mapper;

import com.xych.bookkeeping.app.mapstruct.base.BaseVOConverter;
import com.xych.bookkeeping.app.vo.RuleVO;
import com.xych.bookkeeping.dao.dto.RuleDTO;

@Mapper(componentModel = "spring")
public interface RuleVOConverter extends BaseVOConverter<RuleVO, RuleDTO> {
}
