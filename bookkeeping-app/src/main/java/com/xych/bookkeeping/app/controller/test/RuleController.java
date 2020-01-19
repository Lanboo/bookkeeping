package com.xych.bookkeeping.app.controller.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xych.bookkeeping.app.drools.model.RuleInfo;
import com.xych.bookkeeping.app.drools.service.RuleService;

@Controller
@RequestMapping("test/rule")
public class RuleController {
    @Autowired
    private RuleService ruleService;

    @PostMapping("/find")
    @ResponseBody
    public List<RuleInfo> find(String sceneId, String id) {
        return ruleService.find(sceneId, id);
    }
}
