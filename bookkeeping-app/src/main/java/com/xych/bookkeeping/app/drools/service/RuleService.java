package com.xych.bookkeeping.app.drools.service;

import java.util.List;

import com.xych.bookkeeping.app.common.enums.BusiTypeEnum;
import com.xych.bookkeeping.app.drools.model.RuleInfo;

public interface RuleService {
    List<RuleInfo> find(BusiTypeEnum busiType, String id, String sceneId);
}
