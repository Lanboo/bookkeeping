CREATE TABLE t_book
(
    id BIGINT NOT NULL COMMENT 'id',
    user_code VARCHAR(32) COMMENT '用户代码',
    book_name VARCHAR(64) COMMENT '账本名称',
    crt_time DATETIME COMMENT '创建时间',
    upt_time DATETIME COMMENT '更新时间',
    PRIMARY KEY (ID)
) COMMENT '用户账本表';