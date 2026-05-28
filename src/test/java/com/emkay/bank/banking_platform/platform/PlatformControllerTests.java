package com.emkay.bank.banking_platform.platform;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PlatformControllerTests {

    @Test
    void returnPlatformStatus() {
        PlatformController platform = new PlatformController();
        Map<String, Object> response = platform.status();
        assertEquals("banking_platform", response.get("service"));
        assertEquals("UP-BreakFix",response.get("status"));
    }

}
