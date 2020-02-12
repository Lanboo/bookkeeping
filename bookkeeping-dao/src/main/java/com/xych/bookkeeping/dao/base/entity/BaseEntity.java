package com.xych.bookkeeping.dao.base.entity;

import java.io.Serializable;

import javax.persistence.Id;

import lombok.Data;

@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
}
