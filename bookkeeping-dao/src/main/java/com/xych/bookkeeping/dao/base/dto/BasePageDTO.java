package com.xych.bookkeeping.dao.base.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BasePageDTO extends BaseDto {
    private static final long serialVersionUID = 1L;
    private Integer pageNum;
    private Integer pageSize;
}
