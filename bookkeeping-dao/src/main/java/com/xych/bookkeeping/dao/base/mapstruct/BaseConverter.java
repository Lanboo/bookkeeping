package com.xych.bookkeeping.dao.base.mapstruct;

import java.util.List;

import org.mapstruct.InheritConfiguration;

import com.xych.bookkeeping.dao.base.dto.BaseDto;
import com.xych.bookkeeping.dao.base.entity.BaseEntity;

public interface BaseConverter<D extends BaseDto, E extends BaseEntity> {
    D toDto(E entity);

    @InheritConfiguration
    List<D> toDtoList(List<E> entityList);

    E toEntity(D dto);

    @InheritConfiguration
    List<E> toEntityList(List<D> dtoList);
}
