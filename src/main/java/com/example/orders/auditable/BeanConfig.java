package com.example.orders.auditable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//spring annotation
@Configuration

@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class BeanConfig {

@Bean
    public AuditorAware<String> auditorAware()
{
    return new AuditorAwareImplementation();
}



}
