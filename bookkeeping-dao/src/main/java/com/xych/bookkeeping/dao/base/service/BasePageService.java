package com.xych.bookkeeping.dao.base.service;

import com.xych.bookkeeping.dao.base.dto.BasePageDTO;
import com.xych.bookkeeping.dao.base.dto.Page;

public interface BasePageService<T extends BasePageDTO> {
    Page<T> findPage(T dto);
}
