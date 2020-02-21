package com.xych.bookkeeping.dao.mapstruct;

import org.mapstruct.Mapper;

import com.xych.bookkeeping.dao.base.mapstruct.BaseConverter;
import com.xych.bookkeeping.dao.dto.AssetDTO;
import com.xych.bookkeeping.dao.entity.Asset;

@Mapper(componentModel = "spring")
public interface AssetConverter extends BaseConverter<AssetDTO, Asset> {
}
