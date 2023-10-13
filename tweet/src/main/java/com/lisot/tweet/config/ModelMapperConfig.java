package com.lisot.tweet.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperConfig {

    @Bean
    ModelMapper modelMapper (){
        return new ModelMapper();
    }
}
