package com.xych.bookkeeping.app.drools.service;

import java.util.List;

import com.xych.bookkeeping.app.drools.model.RuleInfo;

public interface RuleService {
    List<RuleInfo> find(String sceneId, String id);
}
