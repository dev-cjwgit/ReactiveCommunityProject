package com.cjw.reactivecommunityproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@EnableCaching
@SpringBootApplication
@ConfigurationPropertiesScan
public class ReactiveCommunityProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveCommunityProjectApplication.class, args);
    }

}
