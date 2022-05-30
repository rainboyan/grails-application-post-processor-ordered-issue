package org.grails.demo

import grails.plugins.GrailsPluginManager
import groovy.util.logging.Slf4j
import org.springframework.beans.BeansException
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor
import org.springframework.core.Ordered

@Slf4j
class DemoBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor, Ordered {
    @Override
    void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        log.info("postProcessBeanDefinitionRegistry")
//        log.info("GrailsPluginManager Definition: " + registry.getBeanDefinition(GrailsPluginManager.BEAN_NAME))
    }

    @Override
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.info("postProcessBeanFactory")
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory()
        if (parentBeanFactory instanceof ConfigurableBeanFactory) {
            ConfigurableBeanFactory configurableBeanFactory = parentBeanFactory
            log.info("GrailsPluginManager exists? " + configurableBeanFactory.containsSingleton(GrailsPluginManager.BEAN_NAME))
        }
    }

    @Override
    int getOrder() {
        return 0
    }
}
