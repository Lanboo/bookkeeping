package com.xych.bookkeeping.app.drools.strategy;

import java.util.List;
import java.util.Map;

import com.xych.bookkeeping.app.common.enums.BusiTypeEnum;
import com.xych.bookkeeping.app.drools.model.RuleInfo;
import com.xych.bookkeeping.app.drools.utils.RuleUtil;
import com.xych.bookkeeping.dao.dto.AlipayRecordDTO;
import com.xych.bookkeeping.dao.dto.RecordRuleDTO;
import com.xych.bookkeeping.dao.dto.RecordRuleDetailDTO;

public class Alipay2RecodRuleStrategy extends RuleStrategy {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    @Override
    public RuleInfo buildRule(List<RecordRuleDTO> ruleList, Map<Long, List<RecordRuleDetailDTO>> ruleDetailDtosMap) {
        RuleInfo ruleInfo = RuleInfo.builder() //
            .sceneId(ruleList.get(0).getBusiType())//
            .id(ruleList.get(0).getUserCode())//
            .build();
        StringBuilder builder = buildStrBuilder(ruleInfo);
        for(RecordRuleDTO rule : ruleList) {
            appendRule(builder, rule, ruleDetailDtosMap.get(rule.getId()));
            builder.append(LINE_SEPARATOR);
        }
        ruleInfo.setContent(builder.toString());
        return ruleInfo;
    }

    private void appendRule(StringBuilder builder, RecordRuleDTO rule, List<RecordRuleDetailDTO> ruleDetailList) {
        builder.append("rule \"").append(rule.getId()).append("\"").append(LINE_SEPARATOR);
        builder.append("no-loop true").append(LINE_SEPARATOR);
        builder.append("when").append(LINE_SEPARATOR);
        builder.append(buildRuleStr(rule, ruleDetailList)).append(LINE_SEPARATOR);
        builder.append("then").append(LINE_SEPARATOR);
        builder.append("end").append(LINE_SEPARATOR);
    }

    private String buildRuleStr(RecordRuleDTO rule, List<RecordRuleDetailDTO> ruleDetailList) {
        String expression = rule.getExpression();
        for(RecordRuleDetailDTO ruleDetail : ruleDetailList) {
            expression = expression.replaceAll("[" + ruleDetail.getIdx() + "]", buildRuleDetailStr(ruleDetail));
        }
        return expression;
    }

    private String buildRuleDetailStr(RecordRuleDetailDTO ruleDetail) {
        return null;
    }

    public StringBuilder buildStrBuilder(RuleInfo ruleInfo) {
        StringBuilder builder = new StringBuilder();
        builder.append("package ").append(RuleUtil.drlPackage(ruleInfo.getSceneId().toLowerCase(), ruleInfo.getId())).append(LINE_SEPARATOR);
        builder.append(LINE_SEPARATOR);
        builder.append("import ").append(AlipayRecordDTO.class.getName()).append(LINE_SEPARATOR);
        builder.append(LINE_SEPARATOR);
        return builder;
    }

    @Override
    protected BusiTypeEnum busiType() {
        return BusiTypeEnum.ALIPAY2RECORD;
    }

    public static void main(String[] args) {
        RuleInfo ruleInfo = RuleInfo.builder().sceneId("ALIPAY2RECORD".toLowerCase()).id("xych").build();
        System.out.println(new Alipay2RecodRuleStrategy().buildStrBuilder(ruleInfo));
    }
}
