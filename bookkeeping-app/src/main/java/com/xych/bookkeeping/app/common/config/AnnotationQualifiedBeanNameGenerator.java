package com.xych.bookkeeping.app.common.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.util.Assert;

/**
 * <pre>
 * 注意：
 * &#64;SpringBootApplication(scanBasePackages = { "com.xych.bookkeeping.app", "com.xych.bookkeeping.dao" })
 * &#64;ComponentScan(nameGenerator = AnnotationQualifiedBeanNameGenerator.class)
 * 按照上面配置的话，com.xych.bookkeeping.dao.*的beanName还是AnnotationBeanNameGenerator生成的，不是当前类生成的
 * 需要按照下面的配置方法：
 * &#64;SpringBootApplication
 * &#64;ComponentScan(basePackages = { "com.xych.bookkeeping.app", "com.xych.bookkeeping.dao" }, nameGenerator = AnnotationQualifiedBeanNameGenerator.class)
 * </pre>
 * 
 * @see org.springframework.context.annotation.AnnotationBeanNameGenerator#buildDefaultBeanName(BeanDefinition)
 * @see org.springframework.context.annotation.ComponentScan#nameGenerator()
 * @see org.springframework.context.annotation.ConfigurationClassPostProcessor#IMPORT_BEAN_NAME_GENERATOR
 * @CreateDate 2020年3月9日下午2:17:14
 */
public class AnnotationQualifiedBeanNameGenerator extends AnnotationBeanNameGenerator {
    @Override
    protected String buildDefaultBeanName(BeanDefinition definition) {
        String beanClassName = definition.getBeanClassName();
        Assert.state(beanClassName != null, "No bean class name set");
        return beanClassName;
    }
}
