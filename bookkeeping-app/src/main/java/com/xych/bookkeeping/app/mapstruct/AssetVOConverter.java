package com.xych.bookkeeping.app.mapstruct;

import org.mapstruct.Mapper;

import com.xych.bookkeeping.app.mapstruct.base.BaseVOConverter;
import com.xych.bookkeeping.app.vo.AssetVO;
import com.xych.bookkeeping.dao.dto.AssetDTO;

@Mapper(componentModel = "spring")
public interface AssetVOConverter extends BaseVOConverter<AssetVO, AssetDTO> {
}
