package com.xych.bookkeeping.dao.service;

import java.util.List;

import com.xych.bookkeeping.dao.base.service.BasePageService;
import com.xych.bookkeeping.dao.dto.BookDTO;

public interface BookServcie extends BasePageService<BookDTO> {
    Integer deleteByIds(List<Long> ids);
}
