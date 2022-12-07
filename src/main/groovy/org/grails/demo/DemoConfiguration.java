package org.grails.demo;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration(proxyBeanMethods = false)
@AutoConfigureOrder
public class DemoConfiguration {
    @Bean
    public DemoBeanDefinitionRegistryPostProcessor demoBeanDefinitionRegistryPostProcessor() {
        return new DemoBeanDefinitionRegistryPostProcessor();
    }

}