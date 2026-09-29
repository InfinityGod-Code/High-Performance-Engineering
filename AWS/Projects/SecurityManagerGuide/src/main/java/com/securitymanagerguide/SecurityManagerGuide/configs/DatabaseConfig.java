package com.securitymanagerguide.SecurityManagerGuide.configs;


import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@Profile("local")
public class DatabaseConfig {
    String url;
    String username;
    String password;
    String driverClassName;
}
