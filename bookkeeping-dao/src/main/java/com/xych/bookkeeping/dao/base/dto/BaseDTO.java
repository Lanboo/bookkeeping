package com.xych.bookkeeping.dao.base.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Long id;
}
