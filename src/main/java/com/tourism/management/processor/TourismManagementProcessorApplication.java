package com.tourism.management.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@EnableAutoConfiguration(exclude = {
//        SecurityAutoConfiguration.class
        ManagementWebSecurityAutoConfiguration.class
})
@ComponentScan(basePackages={"com.tourism.management.processor"})
public class TourismManagementProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TourismManagementProcessorApplication.class, args);
	}

}
