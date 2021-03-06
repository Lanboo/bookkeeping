CREATE TABLE t_dic
(
    id BIGINT NOT NULL COMMENT 'id',
    dic_key VARCHAR(64) COMMENT '字典key',
    dic_value VARCHAR(256) COMMENT '字典值',
    dic_type VARCHAR(32) COMMENT '字典类型',
    dic_desc VARCHAR(64) COMMENT '字典类型描述',
    parent_id BIGINT COMMENT '父级ID',
    validity CHAR(1) COMMENT '是否有效:0-无效,1-有效',
    idx INT COMMENT '顺序',
    crt_time DATETIME COMMENT '创建时间',
    upt_time DATETIME COMMENT '更新时间',
    PRIMARY KEY (ID),
    UNIQUE KEY uk_t_dic (dic_key,dic_type)
) COMMENT '字典表';