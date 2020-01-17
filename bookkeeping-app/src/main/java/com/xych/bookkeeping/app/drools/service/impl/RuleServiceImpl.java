package com.xych.bookkeeping.app.drools.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xych.bookkeeping.app.drools.model.RuleInfo;
import com.xych.bookkeeping.app.drools.service.RuleService;
import com.xych.bookkeeping.dao.dto.RecordRuleDTO;
import com.xych.bookkeeping.dao.dto.RecordRuleDetailDTO;
import com.xych.bookkeeping.dao.service.RecordRuleDetailServcie;
import com.xych.bookkeeping.dao.service.RecordRuleServcie;

@Service("ruleService")
public class RuleServiceImpl implements RuleService {
    @Autowired
    private RecordRuleServcie recordRuleServcie;
    @Autowired
    private RecordRuleDetailServcie recordRuleDetailServcie;

    @Override
    public List<RuleInfo> find(String sceneId, String id) {
        return doFind(sceneId, id);
    }

    private List<RuleInfo> doFind(String sceneId, String id) {
        List<RecordRuleDTO> rules = findRules(sceneId, id);
        Map<String, List<RecordRuleDTO>> sceneRuleMap = process(rules);
        Map<Long, List<RecordRuleDetailDTO>> ruleDetailDtosMap = rules.stream().collect(Collectors.toMap(RecordRuleDTO::getId, dto -> {
            return findRuleDetails(dto);
        }));
        RuleInfo tempRuleInfo = RuleInfo.builder().sceneId(sceneId).id(id).build();
        List<RuleInfo> ruleInfos = new ArrayList<>();
        sceneRuleMap.forEach((str, ruleList) -> {
            ruleInfos.add(buildRuleInfo(tempRuleInfo, ruleList, ruleDetailDtosMap));
        });
        return ruleInfos;
    }

    private RuleInfo buildRuleInfo(RuleInfo tempRuleInfo, List<RecordRuleDTO> ruleList, Map<Long, List<RecordRuleDetailDTO>> ruleDetailDtosMap) {
        RuleInfo ruleInfo = RuleInfo.builder().sceneId(tempRuleInfo.getSceneId()).id(tempRuleInfo.getId()).build();
        return null;
    }

    /**
     * 获取规则明细
     * @CreateDate 2020年1月17日下午2:00:21
     */
    private List<RecordRuleDetailDTO> findRuleDetails(RecordRuleDTO rule) {
        RecordRuleDetailDTO dto = new RecordRuleDetailDTO();
        dto.setRuleId(rule.getId());
        return this.recordRuleDetailServcie.findList(dto);
    }

    /**
     * 处理规则，按照 targetField 分组
     * @CreateDate 2020年1月17日下午3:08:42
     */
    private Map<String, List<RecordRuleDTO>> process(List<RecordRuleDTO> rules) {
        Map<String, List<RecordRuleDTO>> sceneRuleMap = new HashMap<>();
        for(RecordRuleDTO rule : rules) {
            List<RecordRuleDTO> temp = sceneRuleMap.get(rule.getTargetField());
            if(temp == null) {
                temp = new ArrayList<>();
                sceneRuleMap.put(rule.getTargetField(), temp);
            }
            temp.add(rule);
        }
        return sceneRuleMap;
    }

    /**
     * 获取规则
     * @CreateDate 2020年1月17日下午2:00:21
     */
    private List<RecordRuleDTO> findRules(String sceneId, String id) {
        RecordRuleDTO dto = new RecordRuleDTO();
        dto.setBusiType(sceneId);
        dto.setUserCode(id);
        List<RecordRuleDTO> ruleDtos = this.recordRuleServcie.findList(dto);
        return ruleDtos;
    }
}
