package com.xych.bookkeeping.dao.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xych.bookkeeping.dao.base.dto.Page;
import com.xych.bookkeeping.dao.base.service.impl.BasePageServiceImpl;
import com.xych.bookkeeping.dao.dto.RuleDTO;
import com.xych.bookkeeping.dao.dto.RuleDetailDTO;
import com.xych.bookkeeping.dao.entity.Rule;
import com.xych.bookkeeping.dao.mapper.RuleMapper;
import com.xych.bookkeeping.dao.mapstruct.RuleConverter;
import com.xych.bookkeeping.dao.service.RuleDetailServcie;
import com.xych.bookkeeping.dao.service.RuleServcie;

@Service
public class RuleServiceImpl extends BasePageServiceImpl<RuleDTO, Rule> implements RuleServcie {
    @Autowired
    private RuleMapper mapper;
    @Autowired
    private RuleConverter converter;
    @Autowired
    private RuleDetailServcie ruleDetailServcie;

    @Override
    public Page<RuleDTO> findPage(RuleDTO dto) {
        Page<RuleDTO> page = super.findPage(dto);
        if(CollectionUtils.isNotEmpty(page.getData())) {
            for(RuleDTO rule : page.getData()) {
                if(StringUtils.isNotEmpty(rule.getExpression())) {
                    RuleDetailDTO detailTemp = new RuleDetailDTO();
                    detailTemp.setRuleId(rule.getId());
                    rule.setDetails(this.ruleDetailServcie.findList(detailTemp));
                }
            }
        }
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addOne(RuleDTO dto) {
        if(!Objects.isNull(dto)) {
            List<RuleDetailDTO> details = dto.getDetails();
            if(CollectionUtils.isNotEmpty(details)) {
                this.ruleDetailServcie.addList(details);
            }
        }
        return super.addOne(dto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addList(List<RuleDTO> dtos) {
        Integer cnt = 0;
        for(RuleDTO dto : dtos) {
            cnt += this.addOne(dto);
        }
        return cnt;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer update(RuleDTO dto) {
        if(!Objects.isNull(dto)) {
            List<RuleDetailDTO> details = dto.getDetails();
            if(CollectionUtils.isNotEmpty(details)) {
                RuleDetailDTO detailTemp = new RuleDetailDTO();
                detailTemp.setRuleId(dto.getId());
                this.ruleDetailServcie.delete(detailTemp);
                this.ruleDetailServcie.addList(details);
            }
        }
        return super.update(dto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer delete(RuleDTO dto) {
        List<RuleDTO> rules = this.findList(dto);
        List<Long> ids = rules.stream().map(rule -> rule.getId()).collect(Collectors.toList());
        return this.deleteByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteByIds(List<Long> ids) {
        for(Long ruleId : ids) {
            RuleDetailDTO detailTemp = new RuleDetailDTO();
            detailTemp.setRuleId(ruleId);
            this.ruleDetailServcie.delete(detailTemp);
        }
        return super.deleteByIds(ids);
    }

    @Override
    protected RuleMapper getMapper() {
        return mapper;
    }

    @Override
    protected RuleConverter getConverter() {
        return converter;
    }
}
