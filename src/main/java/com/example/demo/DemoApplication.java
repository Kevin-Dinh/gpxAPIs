package com.example.demo;

import com.example.demo.config.Settings;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = {"com.example.demo"})
@Configuration
@EnableAsync
@RestController
public class DemoApplication {

	@Autowired
	private Settings settings;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// Create the Flyway instance and point it to the database
	Flyway flyway = Flyway.configure().dataSource("jdbc:postgresql://localhost:5432/postgres", null, null).load();

	// Run migration
	int a = flyway.migrate();
}
