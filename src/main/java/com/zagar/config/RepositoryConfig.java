package com.zagar.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zagar.email.JsonEmailRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by naayadaa on 03.05.17.
 */
@Configuration
public class RepositoryConfig {

    @Bean
    JsonEmailRepository registrationTerraRepository(){
        return new JsonEmailRepository("app/repository/terragram/registration.json", new ObjectMapper());
    }

    @Bean
    JsonEmailRepository registrationInstaRepository(){
        return new JsonEmailRepository("app/repository/instaleads/registration.json", new ObjectMapper());
    }

    @Bean
    JsonEmailRepository questionTerraRepository(){
        return new JsonEmailRepository("app/repository/terragram/question.json", new ObjectMapper());
    }

    @Bean
    JsonEmailRepository questionInstaRepository(){
        return new JsonEmailRepository("app/repository/instaleads/question.json", new ObjectMapper());
    }
}
