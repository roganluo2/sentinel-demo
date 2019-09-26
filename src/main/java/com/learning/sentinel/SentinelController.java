package com.learning.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentinelController {

    @GetMapping("/sayHello")
    @SentinelResource("sayHello")
    public String sayHello(String name)
    {
        return "Hello " + name;
    }


}
