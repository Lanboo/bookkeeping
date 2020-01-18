package com.xych.bookkeeping.app.drools.strategy;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.xych.bookkeeping.app.common.enums.BusiTypeEnum;
import com.xych.bookkeeping.app.drools.model.RuleInfo;
import com.xych.bookkeeping.dao.dto.RecordRuleDTO;
import com.xych.bookkeeping.dao.dto.RecordRuleDetailDTO;

public abstract class RuleStrategy {
    @Autowired
    private RuleBuilder ruleBuilder;

    public abstract RuleInfo buildRule(List<RecordRuleDTO> ruleList, Map<Long, List<RecordRuleDetailDTO>> ruleDetailDtosMap);

    protected abstract BusiTypeEnum busiType();

    @PostConstruct
    private void register() {
        this.ruleBuilder.register(busiType(), this);
    }
}
