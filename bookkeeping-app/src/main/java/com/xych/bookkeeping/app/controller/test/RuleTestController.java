package com.xych.bookkeeping.app.controller.test;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.command.Command;
import org.kie.api.runtime.ExecutionResults;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.command.CommandFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xych.bookkeeping.app.drools.KieSessionHelper;
import com.xych.bookkeeping.app.drools.RuleLoader;
import com.xych.bookkeeping.app.drools.model.RuleInfo;
import com.xych.bookkeeping.app.drools.service.RuleService;
import com.xych.bookkeeping.dao.dto.AlipayRecordDTO;
import com.xych.bookkeeping.dao.dto.RecordDTO;
import com.xych.bookkeeping.dao.service.AlipayRecordServcie;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("test/rule")
public class RuleTestController {
    @Autowired
    private RuleService ruleService;
    @Autowired
    private RuleLoader ruleLoader;
    @Autowired
    private KieSessionHelper kieSessionHelper;
    @Autowired
    private AlipayRecordServcie alipayRecordServcie;

    @PostMapping("/find")
    @ResponseBody
    public List<RuleInfo> find(String busiType, String id) {
        return ruleService.find(busiType, id);
    }

    @PostMapping("/load")
    @ResponseBody
    public void load(String busiType, String id) {
        ruleLoader.reload(busiType, id);
    }

    @PostMapping("/test")
    @ResponseBody
    public void test(String busiType, String id) {
        KieSession kieSession = kieSessionHelper.getKieSession(busiType, id);
        List<AlipayRecordDTO> alipayRecords = this.alipayRecordServcie.findList(new AlipayRecordDTO());
        for(AlipayRecordDTO alipayRecord : alipayRecords) {
            log.info("alipayRecord={}", alipayRecord);
            RecordDTO recordDto = new RecordDTO();
            kieSession.setGlobal("targetObject", recordDto);
            kieSession.insert(alipayRecord);
            kieSession.fireAllRules();
            log.info("recordDto={}", recordDto);
        }
    }

    @PostMapping("/test2")
    @ResponseBody
    public void test2(String busiType, String id) {
        StatelessKieSession kieSession = kieSessionHelper.getStatelessKieSession(busiType, id);
        List<AlipayRecordDTO> alipayRecords = this.alipayRecordServcie.findList(new AlipayRecordDTO());
        for(AlipayRecordDTO alipayRecord : alipayRecords) {
            //            log.info("alipayRecord={}", alipayRecord);
            RecordDTO recordDto = new RecordDTO();
            List<Command<?>> cmds = new ArrayList<>();
            cmds.add(CommandFactory.newSetGlobal("targetObject", recordDto));
            cmds.add(CommandFactory.newInsert(alipayRecord));
            ExecutionResults results = kieSession.execute(CommandFactory.newBatchExecution(cmds));
            log.info("recordDto={}", recordDto);
            log.info("results={}", results);
        }
    }
}
