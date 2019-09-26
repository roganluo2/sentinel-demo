package com.learning.sentinel;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.google.common.collect.Lists;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SentinelDemoApplication
{
    public static void main(String[] args) {
        initRules();
        SpringApplication.run(SentinelDemoApplication.class, args);
    }

    public static void initRules()
    {
        List<FlowRule> rules = Lists.newArrayList();
        FlowRule flowRule = new FlowRule();
        flowRule.setResource("sayHello");
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setCount(10);
        rules.add(flowRule);
        FlowRuleManager.loadRules(rules);

    }

}
