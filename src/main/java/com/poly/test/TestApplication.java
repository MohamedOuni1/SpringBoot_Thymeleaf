package com.poly.test;

import com.poly.test.repository.DefiRepository;
import com.poly.test.repository.ThemeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestApplication {
    @Bean
    CommandLineRunner commandLineRunner(DefiRepository pr, ThemeRepository cr) {
        return args -> {
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

}
