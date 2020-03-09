package com.xych.bookkeeping.app.drools.service;

import java.util.List;

import com.xych.bookkeeping.app.drools.model.RuleInfo;

public interface RuleInfoService {
    List<RuleInfo> find(String sceneId, String id);
}
