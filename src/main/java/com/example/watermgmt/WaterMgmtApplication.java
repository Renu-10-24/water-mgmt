package com.example.watermgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class WaterMgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaterMgmtApplication.class, args);
	}

}
