package org.grails.demo

import grails.boot.Grails

import groovy.transform.CompileStatic
import org.springframework.context.annotation.Bean

@CompileStatic
class Application {
    static void main(String[] args) {
        Grails.run(Application, args)
    }

    // @Bean
    // DemoBeanDefinitionRegistryPostProcessor demoBeanDefinitionRegistryPostProcessor() {
    //     new DemoBeanDefinitionRegistryPostProcessor()
    // }
}