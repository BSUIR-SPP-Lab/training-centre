package com.bsuir.trainingcenter.config;

import com.bsuir.trainingcenter.dao.Impl.UserDAOImpl;
import com.bsuir.trainingcenter.dao.UserDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DAOConfig {

    @Bean
    public UserDAO userDAO(){
        return new UserDAOImpl();
    }
}
