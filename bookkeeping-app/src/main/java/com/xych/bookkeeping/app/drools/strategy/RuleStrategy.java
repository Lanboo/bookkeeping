package com.xych.bookkeeping.app.drools.strategy;

import java.util.List;
import java.util.Map;

import com.xych.bookkeeping.app.common.enums.BusiTypeEnum;
import com.xych.bookkeeping.app.drools.model.RuleInfo;
import com.xych.bookkeeping.dao.dto.RecordRuleDTO;
import com.xych.bookkeeping.dao.dto.RecordRuleDetailDTO;

public abstract class RuleStrategy {
    public abstract RuleInfo buildRule(List<RecordRuleDTO> ruleList, Map<Long, List<RecordRuleDetailDTO>> ruleDetailDtosMap);

    protected abstract BusiTypeEnum getBusiType();
}
