package com.xych.bookkeeping.app.drools.strategy;

import java.text.MessageFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

import com.xych.bookkeeping.app.common.enums.ExceptionEnum;
import com.xych.bookkeeping.app.common.exception.BusiException;
import com.xych.bookkeeping.app.drools.model.RuleInfo;
import com.xych.bookkeeping.app.drools.utils.RuleUtil;
import com.xych.bookkeeping.dao.dto.RuleDTO;
import com.xych.bookkeeping.dao.dto.RuleDetailDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractRuleStrategy extends RuleStrategy {
    protected static final String LINE_SEPARATOR = System.getProperty("line.separator");
    protected static final String SEMICOLON = ";";

    @Override
    public RuleInfo buildRule(List<RuleDTO> ruleList, Map<Long, List<RuleDetailDTO>> ruleDetailDtosMap) {
        RuleInfo ruleInfo = RuleInfo.builder() //
            .busiType(ruleList.get(0).getBusiType())//
            .id(ruleList.get(0).getUserCode())//
            .sceneId(ruleList.get(0).getTargetField())//
            .build();
        StringBuilder builder = buildStrBuilder(ruleInfo);
        for(RuleDTO rule : ruleList) {
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
    protected void buildRule(StringBuilder builder, RuleDTO rule, List<RuleDetailDTO> ruleDetailList) {
        builder.append("rule \"")//
            .append(rule.getBusiType().toLowerCase())//
            .append("_").append(rule.getTargetField())//
            .append("_").append(rule.getId())//
            .append("\"").append(LINE_SEPARATOR);
        //        builder.append("no-loop true").append(LINE_SEPARATOR);
        builder.append("when").append(LINE_SEPARATOR);
        builder.append(buildLHS(rule, ruleDetailList)).append(LINE_SEPARATOR);
        builder.append("then").append(LINE_SEPARATOR);
        builder.append(buildRHS(rule)).append(LINE_SEPARATOR);
        builder.append("end").append(LINE_SEPARATOR);
    }

    protected String buildRHS(RuleDTO rule) {
        return MessageFormat.format("    targetObject.set{0}({1});", StringUtils.capitalize(rule.getTargetField()), buildFieldValueStr(rule));
    }

    /**
     * 根据目标字段，判断该类型，从而决定是否添加双引号，Long后面添加L
     * @CreateDate 2020年1月20日上午10:55:37
     */
    private String buildFieldValueStr(RuleDTO rule) {
        try {
            String valueStr = rule.getTargetFieldValue();
            Class<?> fieldClass = targetClass().getDeclaredField(rule.getTargetField()).getType();
            if(ClassUtils.isAssignable(fieldClass, Boolean.class)) {
                return valueStr;
            }
            if(ClassUtils.isAssignable(fieldClass, Long.class)) {
                return valueStr + "L";
            }
            if(ClassUtils.isAssignable(fieldClass, String.class)) {
                return "\"" + valueStr + "\"";
            }
            log.info("规则配置错误:目标类型的字段目前只支持Long、String、Boolean:targetClass={},targetField={}", targetClass(), rule.getTargetField());
            throw new BusiException(ExceptionEnum.R00003);
        }
        catch(NoSuchFieldException | SecurityException e) {
            log.info("规则配置错误:类{}不存在{}字段", targetClass(), rule.getTargetField());
            throw new BusiException(ExceptionEnum.R00003);
        }
    }

    /**
     * 创建规则的LHS(条件)
     * @CreateDate 2020年1月19日上午11:05:19
     */
    protected String buildLHS(RuleDTO rule, List<RuleDetailDTO> ruleDetailList) {
        if(CollectionUtils.isEmpty(ruleDetailList)) {
            return "";
        }
        String expression = "    " + rule.getExpression();
        return MessageFormat.format(expression, buildConditions(ruleDetailList));
    }

    private Object[] buildConditions(List<RuleDetailDTO> ruleDetailList) {
        List<RuleDetailDTO> ruleDetails = ruleDetailList.stream() //
            .sorted(Comparator.comparingInt(RuleDetailDTO::getIdx)) //
            .collect(Collectors.toList());
        Object[] conditions = new Object[ruleDetails.size()];
        int idx = 0;
        String expression = originClass().getSimpleName() + "({0} {1} \"{2}\")";
        for(RuleDetailDTO ruleDetail : ruleDetails) {
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
        builder.append("package ").append(RuleUtil.drlPackage(ruleInfo.getBusiType().toLowerCase(), ruleInfo.getId())).append(SEMICOLON).append(LINE_SEPARATOR);
        builder.append(LINE_SEPARATOR);
        buildImports(builder);
        builder.append(LINE_SEPARATOR);
        builder.append("global ").append(targetClass().getName()).append(" targetObject").append(SEMICOLON).append(LINE_SEPARATOR);
        builder.append(LINE_SEPARATOR);
        return builder;
    }

    protected void buildImports(StringBuilder builder) {
        builder.append("import ").append(originClass().getName()).append(SEMICOLON).append(LINE_SEPARATOR);
        builder.append("import ").append(targetClass().getName()).append(SEMICOLON).append(LINE_SEPARATOR);
    }

    protected abstract Class<?> originClass();

    protected abstract Class<?> targetClass();
}
