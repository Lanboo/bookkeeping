package com.xych.bookkeeping.app.drools.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xych.bookkeeping.app.common.enums.BusiTypeEnum;
import com.xych.bookkeeping.app.drools.model.RuleInfo;
import com.xych.bookkeeping.app.drools.service.RuleService;

@Service("ruleService")
public class RuleServiceImpl implements RuleService {
    @Override
    public List<RuleInfo> find(BusiTypeEnum busiType, String id, String sceneId) {
        return null;
    }
}
