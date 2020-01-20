package com.xych.bookkeeping.app.drools;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

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

import com.xych.bookkeeping.app.common.enums.ExceptionEnum;
import com.xych.bookkeeping.app.common.exception.BusiException;
import com.xych.bookkeeping.app.drools.model.RuleInfo;
import com.xych.bookkeeping.app.drools.service.RuleService;
import com.xych.bookkeeping.app.drools.utils.RuleUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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

    public KieContainer getKieContainer(String busiType, String id) {
        String kbaseName = busiType + "_" + id;
        KieContainer kieContainer = kieContainerMap.get(kbaseName);
        if(kieContainer != null) {
            return kieContainer;
        }
        else {
            reload(busiType, id);
            return kieContainerMap.get(kbaseName);
        }
    }

    /**
     * 重新加载所有规则
     */
    public void reload(String busiType, String id) {
        List<RuleInfo> ruleInfos = this.ruleService.find(busiType, id);
        String kbaseName = busiType + "_" + id;
        String busiTypeTemp = busiType.toLowerCase();
        //
        KieModuleModel kieModuleModel = kieServices.newKieModuleModel();
        KieBaseModel kieBaseModel = kieModuleModel.newKieBaseModel(kbaseName);
        kieBaseModel.setDefault(true);
        kieBaseModel.addPackage(RuleUtil.drlPackage(busiTypeTemp, id));
        kieBaseModel.newKieSessionModel(kbaseName);
        //
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        String basePath = RuleUtil.drlPath(busiTypeTemp, id);
        for(RuleInfo ruleInfo : ruleInfos) {
            String path = basePath + "/" + ruleInfo.getSceneId() + ".drl";
            kieFileSystem.write(path, ruleInfo.getContent());
        }
        kieFileSystem.writeKModuleXML(kieModuleModel.toXML());
        //
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem).buildAll();
        //
        Results results = kieBuilder.getResults();
        if(results.hasMessages(Message.Level.ERROR)) {
            log.info("配置的规则解析错误:sceneId={},id={},results={}", busiType, id, results.getMessages());
            throw new BusiException(ExceptionEnum.R00002);
        }
        //
        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        kieContainerMap.put(kbaseName, kieContainer);
    }
}
