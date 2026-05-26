package com.emkay.bank.banking_platform.platform;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RestController
public class PlatformController {

    @GetMapping("api/platform/status")
    public Map<String, Object> status(){
        return Map.of("service", "banking_platform",
                "status",   "UP",
                "timestamp: ", Instant.now().toString());
    }
}
