package com.xych.bookkeeping.app.drools.strategy;

import java.text.MessageFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.xych.bookkeeping.app.drools.model.RuleInfo;
import com.xych.bookkeeping.app.drools.utils.RuleUtil;
import com.xych.bookkeeping.dao.dto.RecordRuleDTO;
import com.xych.bookkeeping.dao.dto.RecordRuleDetailDTO;

public abstract class AbstractRuleStrategy extends RuleStrategy {
    protected static final String LINE_SEPARATOR = System.getProperty("line.separator");

    @Override
    public RuleInfo buildRule(List<RecordRuleDTO> ruleList, Map<Long, List<RecordRuleDetailDTO>> ruleDetailDtosMap) {
        RuleInfo ruleInfo = RuleInfo.builder() //
            .busiType(ruleList.get(0).getBusiType())//
            .id(ruleList.get(0).getUserCode())//
            .sceneId(ruleList.get(0).getTargetField())//
            .build();
        StringBuilder builder = buildStrBuilder(ruleInfo);
        for(RecordRuleDTO rule : ruleList) {
            buildRule(builder, rule, ruleDetailDtosMap.get(rule.getId()));
            builder.append(LINE_SEPARATOR);
        }
        ruleInfo.setContent(builder.toString());
        return ruleInfo;
    }

    /**
     * 创建规则
     * @CreateDate 2020年1月19日上午11:04:51
     */
    protected void buildRule(StringBuilder builder, RecordRuleDTO rule, List<RecordRuleDetailDTO> ruleDetailList) {
        builder.append("rule \"")//
            .append(rule.getBusiType().toLowerCase())//
            .append("_").append(rule.getTargetField())//
            .append("_").append(rule.getId())//
            .append("\"").append(LINE_SEPARATOR);
        builder.append("no-loop true").append(LINE_SEPARATOR);
        builder.append("when").append(LINE_SEPARATOR);
        builder.append(buildLHS(rule, ruleDetailList)).append(LINE_SEPARATOR);
        builder.append("then").append(LINE_SEPARATOR);
        builder.append(buildRHS(rule)).append(LINE_SEPARATOR);
        builder.append("end").append(LINE_SEPARATOR);
    }

    protected String buildRHS(RecordRuleDTO rule) {
        return MessageFormat.format("    target.set{0}(\"{1}\")", StringUtils.capitalize(rule.getTargetField()), rule.getTargetFieldValue());
    }

    /**
     * 创建规则的LHS(条件)
     * @CreateDate 2020年1月19日上午11:05:19
     */
    protected String buildLHS(RecordRuleDTO rule, List<RecordRuleDetailDTO> ruleDetailList) {
        if(CollectionUtils.isEmpty(ruleDetailList)) {
            return "";
        }
        String expression = "    " + rule.getExpression();
        return MessageFormat.format(expression, buildConditions(ruleDetailList));
    }

    private Object[] buildConditions(List<RecordRuleDetailDTO> ruleDetailList) {
        List<RecordRuleDetailDTO> ruleDetails = ruleDetailList.stream() //
            .sorted(Comparator.comparingInt(RecordRuleDetailDTO::getIdx)) //
            .collect(Collectors.toList());
        Object[] conditions = new Object[ruleDetails.size()];
        int idx = 0;
        String expression = originClass().getSimpleName() + "({0} {1} \"{2}\")";
        for(RecordRuleDetailDTO ruleDetail : ruleDetails) {
            conditions[idx] = MessageFormat.format(expression, ruleDetail.getOriginField(), ruleDetail.getOriginOperator(), ruleDetail.getOriginFieldValue());
            idx++;
        }
        return conditions;
    }

    /**
     * 创建drl文件头
     * @CreateDate 2020年1月19日上午11:03:33
     */
    protected StringBuilder buildStrBuilder(RuleInfo ruleInfo) {
        StringBuilder builder = new StringBuilder();
        builder.append("package ").append(RuleUtil.drlPackage(ruleInfo.getBusiType().toLowerCase(), ruleInfo.getId())).append(LINE_SEPARATOR);
        builder.append(LINE_SEPARATOR);
        buildImports(builder);
        builder.append(LINE_SEPARATOR);
        builder.append("global ").append(targetClass().getSimpleName()).append(" target").append(LINE_SEPARATOR);
        builder.append(LINE_SEPARATOR);
        return builder;
    }

    protected void buildImports(StringBuilder builder) {
        builder.append("import ").append(originClass().getName()).append(LINE_SEPARATOR);
        builder.append("import ").append(targetClass().getName()).append(LINE_SEPARATOR);
    }

    protected abstract Class<?> originClass();

    protected abstract Class<?> targetClass();
}
