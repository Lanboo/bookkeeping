package com.xych.bookkeeping.app.mapstruct.base;

import java.util.List;

import org.mapstruct.InheritConfiguration;

import com.xych.bookkeeping.app.vo.base.BaseVO;
import com.xych.bookkeeping.dao.base.dto.BaseDTO;

public interface BaseVOConverter<VO extends BaseVO, DTO extends BaseDTO> {
    VO toVo(DTO dto);

    @InheritConfiguration
    List<VO> toVoList(List<DTO> dtoList);

    DTO toDto(VO vo);

    @InheritConfiguration
    List<DTO> toDtoList(List<VO> voList);
}
