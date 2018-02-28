package com.bsuir.trainingcenter.config;

import com.bsuir.trainingcenter.service.UserService;
import com.bsuir.trainingcenter.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public UserService userService(){
        return new UserServiceImpl();
    }

}
