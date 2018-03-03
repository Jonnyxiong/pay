package com.ucpaas.sms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AgentPayController {
    @RequestMapping("/pay")
    public String index() {
        return "done";
    }
}