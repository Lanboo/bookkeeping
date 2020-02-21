package com.xych.bookkeeping.app.mapstruct;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

import com.xych.bookkeeping.app.vo.asset.AssetSaveVO;
import com.xych.bookkeeping.app.vo.asset.AssetUpdateVO;
import com.xych.bookkeeping.app.vo.asset.AssetVO;
import com.xych.bookkeeping.dao.dto.AssetDTO;

@Mapper(componentModel = "spring")
public interface AssetVOConverter {

    AssetVO toVo(AssetDTO dto);

    @InheritConfiguration
    List<AssetVO> toVoList(List<AssetDTO> dtoList);

    AssetDTO toDto(AssetVO vo);

    AssetDTO toDto(AssetUpdateVO vo);

    AssetDTO toDto(AssetSaveVO vo);

    @InheritConfiguration
    List<AssetDTO> toDtoList(List<AssetVO> voList);
}
