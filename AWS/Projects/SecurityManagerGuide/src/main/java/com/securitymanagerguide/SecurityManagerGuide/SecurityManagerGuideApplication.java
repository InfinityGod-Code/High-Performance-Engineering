package com.securitymanagerguide.SecurityManagerGuide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class SecurityManagerGuideApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityManagerGuideApplication.class, args);
	}

}
