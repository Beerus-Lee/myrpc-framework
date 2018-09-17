package com.my.micheal.spring.parse;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;


/**
 * 注册标签解析类
 */
public class SOANameSpaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        this.registerBeanDefinitionParser("registry",new RegistryBeanDefinitionParse());
        this.registerBeanDefinitionParser("reference",new ReferenceBeanDefinitionParse());
        this.registerBeanDefinitionParser("protocal",new ProtocalBeanDefinitionParse());
        this.registerBeanDefinitionParser("service",new ServiceBeanDefinitionParse());

    }
}
