package com.xych.bookkeeping.app.drools.strategy;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Component;

import com.xych.bookkeeping.app.common.enums.BusiTypeEnum;
import com.xych.bookkeeping.app.common.enums.ExceptionEnum;
import com.xych.bookkeeping.app.common.exception.BusiException;
import com.xych.bookkeeping.app.drools.model.RuleInfo;
import com.xych.bookkeeping.dao.dto.RecordRuleDTO;
import com.xych.bookkeeping.dao.dto.RecordRuleDetailDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RuleBuilder {
    Map<BusiTypeEnum, RuleStrategy> strategyMap = new ConcurrentHashMap<>();

    public RuleInfo buildRule(List<RecordRuleDTO> ruleList, Map<Long, List<RecordRuleDetailDTO>> ruleDetailDtosMap) {
        if(CollectionUtils.isEmpty(ruleList) || MapUtils.isEmpty(ruleDetailDtosMap)) {
            throw new BusiException(ExceptionEnum.PARAM_ERROR);
        }
        String busiTypeStr = ruleList.get(0).getBusiType();
        BusiTypeEnum busiType = EnumUtils.getEnum(BusiTypeEnum.class, busiTypeStr);
        if(busiType == null || Objects.isNull(strategyMap.get(busiType))) {
            log.info("创建规则:业务类型错误:busiTypeStr={}", busiTypeStr);
            throw new BusiException(ExceptionEnum.PARAM_ERROR, "业务类型错误");
        }
        RuleStrategy ruleStrategy = strategyMap.get(busiType);
        return ruleStrategy.buildRule(ruleList, ruleDetailDtosMap);
    }

    public void register(BusiTypeEnum busiType, RuleStrategy ruleStrategy) {
        strategyMap.put(busiType, ruleStrategy);
    }
}
