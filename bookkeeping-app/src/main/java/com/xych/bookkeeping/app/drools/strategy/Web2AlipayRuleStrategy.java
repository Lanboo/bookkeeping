package com.xych.bookkeeping.app.drools.strategy;

import org.springframework.stereotype.Component;

import com.xych.bookkeeping.app.common.enums.BusiTypeEnum;
import com.xych.bookkeeping.dao.dto.AlipayRecordDTO;

@Component
public class Web2AlipayRuleStrategy extends AbstractRuleStrategy {
    @Override
    protected Class<?> originClass() {
        return AlipayRecordDTO.class;
    }

    @Override
    protected Class<?> targetClass() {
        return AlipayRecordDTO.class;
    }

    @Override
    protected BusiTypeEnum getBusiType() {
        return BusiTypeEnum.WEB2ALIPAY;
    }
}
