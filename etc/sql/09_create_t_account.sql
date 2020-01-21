CREATE TABLE t_account
(
    id BIGINT NOT NULL COMMENT 'id',
    user_code VARCHAR(32) COMMENT '用户代码',
    account_name VARCHAR(64) COMMENT '账户名称',
    account_pattern CHAR(1) COMMENT '账户模式（0:资产账户,1:负债账户）',
    account_type VARCHAR(32) COMMENT '账户类型（比如:储蓄卡,支付宝,借出款,基金;信用卡,蚂蚁花呗,欠款,房贷）',
    balance BIGINT default 0 COMMENT '余额,分',
    crt_time DATETIME COMMENT '创建时间',
    upt_time DATETIME COMMENT '更新时间',
    PRIMARY KEY (ID)
) COMMENT '用户账户表';