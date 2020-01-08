CREATE TABLE t_alipay_record
(
    id BIGINT NOT NULL,
    user_code VARCHAR(32),
    consume_time CHAR(14) COMMENT '交易时间',
    consume_title VARCHAR(128) COMMENT '标题',
    trade_no VARCHAR(64) COMMENT '商户订单号',
    trade_id VARCHAR(64) COMMENT '流水号',
    other VARCHAR(128) COMMENT '对方',
    amount INT COMMENT '金额，分',
    fund_flow TINYINT COMMENT '资金流向:1:收入,-1:支出,0:转账',
    status VARCHAR(16) COMMENT '状态',
    fund_tool VARCHAR(16) COMMENT '支付途径',
    crt_time DATETIME,
    upt_time DATETIME,
    operator VARCHAR(32),
    PRIMARY KEY (ID),
    index idx_t_alipay_record (consume_time,trade_id)
) COMMENT '支付宝流水记录';