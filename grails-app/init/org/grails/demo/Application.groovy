package org.grails.demo

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration

import groovy.transform.CompileStatic
import org.springframework.context.annotation.Bean

@CompileStatic
class Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }

    @Bean
    DemoBeanDefinitionRegistryPostProcessor demoBeanDefinitionRegistryPostProcessor() {
        new DemoBeanDefinitionRegistryPostProcessor()
    }
}