package com.xych.bookkeeping.app.drools.strategy;

import org.springframework.stereotype.Component;

import com.xych.bookkeeping.app.common.enums.BusiTypeEnum;
import com.xych.bookkeeping.app.drools.model.RuleInfo;
import com.xych.bookkeeping.dao.dto.AlipayRecordDTO;
import com.xych.bookkeeping.dao.dto.RecordDTO;

@Component
public class Alipay2RecodRuleStrategy extends AbstractRuleStrategy {
    @Override
    protected Class<?> originClass() {
        return AlipayRecordDTO.class;
    }

    @Override
    protected Class<?> targetClass() {
        return RecordDTO.class;
    }

    @Override
    protected BusiTypeEnum busiType() {
        return BusiTypeEnum.ALIPAY2RECORD;
    }

    public static void main(String[] args) {
        RuleInfo ruleInfo = RuleInfo.builder().sceneId("ALIPAY2RECORD".toLowerCase()).id("xych").build();
        System.out.println(new Alipay2RecodRuleStrategy().buildStrBuilder(ruleInfo));
    }
}
