package com.example.foodtaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EntityScan
@EnableJpaRepositories(basePackages = "com.example.foodtaker.Repository")
@EnableAutoConfiguration
public class FoodtakerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodtakerApplication.class, args);
    }

}
