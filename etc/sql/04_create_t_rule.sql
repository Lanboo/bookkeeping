CREATE TABLE t_rule
(
    id BIGINT NOT NULL COMMENT 'id',
    user_code VARCHAR(32) COMMENT '用户代码',
    busi_type VARCHAR(32) COMMENT '业务场景',
    target_field VARCHAR(128) COMMENT '目标字段',
    target_field_value VARCHAR(128) COMMENT '输出结果',
    expression VARCHAR(256) COMMENT '明细表达式',
    crt_time DATETIME COMMENT '创建时间',
    upt_time DATETIME COMMENT '更新时间',
    PRIMARY KEY (ID),
    UNIQUE KEY uk_t_rule (user_code,target_field,target_field_value)
) COMMENT '记录转换规则';