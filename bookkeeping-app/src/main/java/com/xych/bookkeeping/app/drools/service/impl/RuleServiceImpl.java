package com.xych.bookkeeping.app.drools.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xych.bookkeeping.app.common.enums.ExceptionEnum;
import com.xych.bookkeeping.app.common.exception.BusiException;
import com.xych.bookkeeping.app.drools.model.RuleInfo;
import com.xych.bookkeeping.app.drools.service.RuleService;
import com.xych.bookkeeping.app.drools.strategy.RuleBuilder;
import com.xych.bookkeeping.dao.dto.RuleDTO;
import com.xych.bookkeeping.dao.dto.RuleDetailDTO;
import com.xych.bookkeeping.dao.service.RuleDetailServcie;
import com.xych.bookkeeping.dao.service.RuleServcie;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RuleServiceImpl implements RuleService {
    @Autowired
    private RuleServcie ruleServcie;
    @Autowired
    private RuleDetailServcie ruleDetailServcie;
    @Autowired
    private RuleBuilder ruleBuilder;

    @Override
    public List<RuleInfo> find(String sceneId, String id) {
        return doFind(sceneId, id);
    }

    private List<RuleInfo> doFind(String sceneId, String id) {
        List<RuleDTO> rules = findRules(sceneId, id);
        if(CollectionUtils.isEmpty(rules)) {
            log.info("未配置规则:sceneId={},id", sceneId, id);
            throw new BusiException(ExceptionEnum.R00001);
        }
        Map<String, List<RuleDTO>> sceneRuleMap = process(rules);
        Map<Long, List<RuleDetailDTO>> ruleDetailDtosMap = rules.stream().collect(Collectors.toMap(RuleDTO::getId, dto -> {
            return findRuleDetails(dto);
        }));
        List<RuleInfo> ruleInfos = new ArrayList<>();
        sceneRuleMap.forEach((str, ruleList) -> {
            ruleInfos.add(buildRuleInfo(ruleList, ruleDetailDtosMap));
        });
        log.info("规则配置:sceneId={},id={},rules={}", sceneId, id, ruleInfos);
        return ruleInfos;
    }

    private RuleInfo buildRuleInfo(List<RuleDTO> ruleList, Map<Long, List<RuleDetailDTO>> ruleDetailDtosMap) {
        return ruleBuilder.buildRule(ruleList, ruleDetailDtosMap);
    }

    /**
     * 获取规则明细
     * @CreateDate 2020年1月17日下午2:00:21
     */
    private List<RuleDetailDTO> findRuleDetails(RuleDTO rule) {
        RuleDetailDTO dto = new RuleDetailDTO();
        dto.setRuleId(rule.getId());
        return this.ruleDetailServcie.findList(dto);
    }

    /**
     * 处理规则，按照 targetField 分组
     * @CreateDate 2020年1月17日下午3:08:42
     */
    private Map<String, List<RuleDTO>> process(List<RuleDTO> rules) {
        Map<String, List<RuleDTO>> sceneRuleMap = new HashMap<>();
        for(RuleDTO rule : rules) {
            List<RuleDTO> temp = sceneRuleMap.get(rule.getTargetField());
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
    private List<RuleDTO> findRules(String sceneId, String id) {
        RuleDTO dto = new RuleDTO();
        dto.setBusiType(sceneId);
        dto.setUserCode(id);
        List<RuleDTO> ruleDtos = this.ruleServcie.findList(dto);
        return ruleDtos;
    }
}
