# Improvement: Make GrailsApplicationPostProcessor implements Ordered

Spring Framework invoke the BeanDefinitionRegistryPostProcessors that implement `PriorityOrdered` first, then `Ordered`, and the rest will be last invoked.

But `GrailsApplicationPostProcessor` is not Ordered, If Grails user create a `BeanDefinitionRegistryPostProcessor` and configure in `Application`, 
`GrailsApplicationPostProcessor` will be invoked after `DemoBeanDefinitionRegistryPostProcessor`, it is not as we expected.

`Application`,
```
class Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }

    @Bean
    DemoBeanDefinitionRegistryPostProcessor demoBeanDefinitionRegistryPostProcessor() {
        new DemoBeanDefinitionRegistryPostProcessor()
    }
}
```

`DemoBeanDefinitionRegistryPostProcessor`,
```
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
```

When `grails run-app`, the logs will be below:

```
2022-05-30 17:31:48.520  INFO --- [  restartedMain] .DemoBeanDefinitionRegistryPostProcessor : postProcessBeanFactory
2022-05-30 17:31:48.523  INFO --- [  restartedMain] .DemoBeanDefinitionRegistryPostProcessor : GrailsPluginManager exists? false
```

The demo code here: 

* https://github.com/rainboyan/grails-application-post-processor-ordered-issue

Grails Issue 12540

* https://github.com/grails/grails-core/issues/12540