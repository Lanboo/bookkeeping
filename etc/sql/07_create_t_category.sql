CREATE TABLE t_category
(
    id BIGINT NOT NULL COMMENT 'id',
    user_code VARCHAR(32) COMMENT '用户代码',
    category_name VARCHAR(64) COMMENT '类型名称',
    parent_id BIGINT COMMENT '父级类型',
    crt_time DATETIME COMMENT '创建时间',
    upt_time DATETIME COMMENT '更新时间',
    PRIMARY KEY (ID)
) COMMENT '用户类型表';