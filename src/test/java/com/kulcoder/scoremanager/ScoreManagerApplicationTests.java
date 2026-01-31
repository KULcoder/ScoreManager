package com.kulcoder.scoremanager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ScoreManagerApplicationTests {

    // 1. This checks if the Spring Context loads successfully.
    // If your app crashes on startup, this will fail.
    @Test
    void contextLoads() {
    }

    // 2. A true "dummy" test just to see a green checkmark in logs
    @Test
    void simpleSanityCheck() {
        assertTrue(true, "This will always pass");
    }

}