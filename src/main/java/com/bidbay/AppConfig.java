package com.bidbay;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.bidbay.models.dao.ISubastaDao;
import com.bidbay.models.dao.SubastaDaoImpl;

@Configuration
@ComponentScan(basePackages = {"com.bidbay"})
public class AppConfig {
    @Bean
    public ISubastaDao subastaDao() {
        return new SubastaDaoImpl();
    }
   
}

