package com.xych.bookkeeping.app.drools.strategy;

import java.util.List;
import java.util.Map;

import com.xych.bookkeeping.app.common.enums.BusiTypeEnum;
import com.xych.bookkeeping.app.drools.model.RuleInfo;
import com.xych.bookkeeping.dao.dto.RuleDTO;
import com.xych.bookkeeping.dao.dto.RuleDetailDTO;

public abstract class RuleStrategy {
    public abstract RuleInfo buildRule(List<RuleDTO> ruleList, Map<Long, List<RuleDetailDTO>> ruleDetailDtosMap);

    protected abstract BusiTypeEnum getBusiType();
}
