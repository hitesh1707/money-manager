package com.example.moneymanager.config;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PreflightController {

    @RequestMapping(value = "/**", method = org.springframework.web.bind.annotation.RequestMethod.OPTIONS)
    public void handleOptions() {
        // Just return 200 OK
    }
}