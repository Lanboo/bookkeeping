CREATE TABLE t_rule_detail
(
    id BIGINT NOT NULL COMMENT 'id',
    rule_id BIGINT COMMENT '规则id',
    idx TINYINT COMMENT '序号',
    origin_field VARCHAR(128) COMMENT '源字段',
    origin_operator VARCHAR(16) COMMENT '关系:等于,大于,小于,大于等于,小于等于,不等于,包含,不包含',
    origin_field_value VARCHAR(128) COMMENT '源字段值',
    PRIMARY KEY (ID),
    UNIQUE KEY uk_t_rule_detail (rule_id,idx)
) COMMENT '记录转换规则明细';