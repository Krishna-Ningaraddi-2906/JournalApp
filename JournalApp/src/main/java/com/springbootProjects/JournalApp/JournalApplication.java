package com.springbootProjects.JournalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement
public class JournalApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(JournalApplication.class, args);
        } catch (Exception e) {
            System.out.println("Application failed" + e.getMessage());
        }

    }


    // This is the configration or the implementation for the TransactionManagement
    @Bean
    public PlatformTransactionManager add(MongoDatabaseFactory dbFactory) {

        return new MongoTransactionManager(dbFactory);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();

    }
}