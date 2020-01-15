CREATE TABLE t_record_rule
(
    id BIGINT NOT NULL COMMENT 'id',
    user_code VARCHAR(32) COMMENT '用户代码',
    target_field VARCHAR(128) COMMENT '目标字段',
    target_field_value VARCHAR(128) COMMENT '输出结果',
    rule_engine VARCHAR(256) COMMENT '明细表达式',
    PRIMARY KEY (ID),
    UNIQUE KEY uk_t_record_rule (user_code,target_field)
) COMMENT '记录转换规则';