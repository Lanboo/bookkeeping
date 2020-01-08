CREATE TABLE t_user
(
    id INT NOT NULL,
    user_code VARCHAR(32) COMMENT '用户名',
    user_pwd VARCHAR(32) COMMENT '用户密码',
    crt_time DATETIME,
    upt_time DATETIME,
    PRIMARY KEY (id),
    UNIQUE KEY uk_t_user (user_code)
) COMMENT '用户表';