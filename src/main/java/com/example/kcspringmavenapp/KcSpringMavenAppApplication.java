package com.example.kcspringmavenapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class KcSpringMavenAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(KcSpringMavenAppApplication.class, args);
	}

}
