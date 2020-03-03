CREATE TABLE t_record
(
    id BIGINT NOT NULL COMMENT 'id',
    user_code VARCHAR(32) COMMENT '用户代码',
    busi_type VARCHAR(32) COMMENT '业务场景',
    account_book BIGINT COMMENT '账本',
    amount INT COMMENT '金额，分',
    flow TINYINT COMMENT '资金流向:1:收入,-1:支出,0:转账',
    category BIGINT COMMENT '分类',
    asset BIGINT COMMENT '资产',
    record_time CHAR(14) COMMENT '时间',
    record_desc VARCHAR(256) COMMENT '备注',
    family_member VARCHAR(256) COMMENT '成员(允许多成员平摊)',
    alipay_record_id BIGINT COMMENT '支付宝流水表ID',
    crt_time DATETIME COMMENT '创建时间',
    upt_time DATETIME COMMENT '更新时间',
    operator VARCHAR(32) COMMENT '操作人',
    PRIMARY KEY (ID),
    index idx_t_record (record_time)
) COMMENT '流水记录';