package com.springbootProjects.JournalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JournalApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(JournalApplication.class, args);
	}


	// This is the configration or the implementation for the TransactionManagement
	@Bean
	public PlatformTransactionManager add(MongoDatabaseFactory dbFactory)
	{
		return new MongoTransactionManager(dbFactory);
	}
}