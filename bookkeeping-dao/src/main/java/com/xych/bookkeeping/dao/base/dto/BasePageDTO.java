package com.xych.bookkeeping.dao.base.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BasePageDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    private Integer current;
    private Integer pageSize;
}
