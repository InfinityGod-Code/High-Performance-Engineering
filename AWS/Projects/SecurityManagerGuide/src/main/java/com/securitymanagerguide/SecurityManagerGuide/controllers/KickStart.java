package com.securitymanagerguide.SecurityManagerGuide.controllers;


import com.securitymanagerguide.SecurityManagerGuide.configs.DatabaseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class KickStart {

    private final DatabaseConfig config;


    @Autowired
    KickStart(DatabaseConfig databaseConfig){
        this.config = databaseConfig;
    }


    @GetMapping("/configs")
    public ResponseEntity<Map<String,String>> getDetails(){
        Map<String,String> mapper = new HashMap<>();
        mapper.put("url",config.getUrl());
        mapper.put("password",config.getPassword());
        mapper.put("username",config.getUsername());
        mapper.put("driverClassName",config.getDriverClassName());
        return new ResponseEntity<>(mapper, HttpStatus.valueOf(200));
    }
}
