package com.xych.bookkeeping.app.drools;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.collections4.CollectionUtils;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.builder.model.KieBaseModel;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xych.bookkeeping.app.common.enums.BusiTypeEnum;
import com.xych.bookkeeping.app.drools.model.RuleInfo;
import com.xych.bookkeeping.app.drools.service.RuleService;
import com.xych.bookkeeping.app.drools.utils.RuleUtil;

@Component
public class RuleLoader {
    private final KieServices kieServices = KieServices.Factory.get();
    /**
     * key:busiType_id_sceneId
     * value:KieContainer，每个场景对应一个KieContainer
     */
    private final ConcurrentMap<String, KieContainer> kieContainerMap = new ConcurrentHashMap<>();
    @Autowired
    private RuleService ruleService;

    /**
     * 重新加载所有规则
     */
    public void reload(String sceneId, String id) {
        List<RuleInfo> ruleInfos = this.ruleService.find(sceneId, id);
        if(CollectionUtils.isEmpty(ruleInfos)) {
            // TODO 抛出一个明确的自定义异常
            throw new RuntimeException();
        }
        String kbaseName = sceneId + "_" + id;
        //
        KieModuleModel kieModuleModel = kieServices.newKieModuleModel();
        KieBaseModel kieBaseModel = kieModuleModel.newKieBaseModel(kbaseName);
        kieBaseModel.setDefault(true);
        kieBaseModel.addPackage(RuleUtil.drlPackage(sceneId, id));
        kieBaseModel.newKieSessionModel(kbaseName);
        //
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        String path = RuleUtil.drlPath(sceneId, id);
        for(RuleInfo ruleInfo : ruleInfos) {
            kieFileSystem.write(path, ruleInfo.getContent());
        }
        kieFileSystem.writeKModuleXML(kieModuleModel.toXML());
        //
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem).buildAll();
        //
        Results results = kieBuilder.getResults();
        if(results.hasMessages(Message.Level.ERROR)) {
            // TODO 抛出一个明确的自定义异常
            throw new IllegalStateException("rule error");
        }
        //
        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        kieContainerMap.put(kbaseName, kieContainer);
    }
}
