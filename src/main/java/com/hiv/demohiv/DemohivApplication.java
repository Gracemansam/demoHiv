package com.hiv.demohiv;

import com.hiv.demohiv.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.hiv.demohiv")
@EnableTransactionManagement
public class DemohivApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemohivApplication.class, args);
	}



}
