package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Spring Boot application.
 *
 * @SpringBootApplication combines:
 *   - @Configuration       : marks this as a source of bean definitions
 *   - @EnableAutoConfiguration : tells Spring Boot to configure beans automatically
 *   - @ComponentScan       : scans this package and sub-packages for components
 */
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        // Bootstraps the Spring ApplicationContext and starts embedded Tomcat
        SpringApplication.run(DemoApplication.class, args);
    }
}
