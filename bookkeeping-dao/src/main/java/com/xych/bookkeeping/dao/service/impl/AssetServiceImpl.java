package com.xych.bookkeeping.dao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xych.bookkeeping.dao.base.service.impl.BasePageServiceImpl;
import com.xych.bookkeeping.dao.dto.AssetDTO;
import com.xych.bookkeeping.dao.entity.Asset;
import com.xych.bookkeeping.dao.mapper.AssetMapper;
import com.xych.bookkeeping.dao.mapstruct.AssetConverter;
import com.xych.bookkeeping.dao.service.AssetServcie;

@Service
public class AssetServiceImpl extends BasePageServiceImpl<AssetDTO, Asset> implements AssetServcie {
    @Autowired
    private AssetMapper mapper;
    @Autowired
    private AssetConverter converter;

    @Override
    protected AssetMapper getMapper() {
        return mapper;
    }

    @Override
    protected AssetConverter getConverter() {
        return converter;
    }
}
