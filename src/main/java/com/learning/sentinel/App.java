package com.learning.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
      new App().testSentinel();
    }
       static String resource = "Hello World";

    private  void testSentinel() throws IOException {
        initRules();
        int a = 0;
        while(a < 100){
            a ++;
            Entry entry=null;
            try{
                entry= SphU.entry(resource); //它做了什么
                System.out.println(LocalTime.now());
            }catch (BlockException e){//如果被限流了，那么会抛出这个异常
                e.printStackTrace();
            }finally {
                if(entry!=null){
                    entry.exit();// 释放
                }
            }
        }
        System.in.read();
    }


    public  void initRules ()
    {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule flowRule = new FlowRule();
        //这里是一个坑，避免refResource
        flowRule.setResource(resource);
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setCount(10);
        rules.add(flowRule);
        FlowRuleManager.loadRules(rules);

    }
}
