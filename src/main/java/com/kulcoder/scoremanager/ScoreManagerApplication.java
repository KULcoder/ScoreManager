package com.kulcoder.scoremanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public final class ScoreManagerApplication {

    // Add this to satisfy Checkstyle
    private ScoreManagerApplication() { }

    public static void main(String[] args) {
        SpringApplication.run(ScoreManagerApplication.class, args);
    }

}
