package com.xych.bookkeeping.app.drools;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KieSessionHelper {
    @Autowired
    private RuleLoader ruleLoader;

    public KieSession getKieSession(String busiType, String id) {
        return ruleLoader.getKieContainer(busiType, id).getKieBase().newKieSession();
    }

    public StatelessKieSession getStatelessKieSession(String busiType, String id) {
        return ruleLoader.getKieContainer(busiType, id).getKieBase().newStatelessKieSession();
    }
}
