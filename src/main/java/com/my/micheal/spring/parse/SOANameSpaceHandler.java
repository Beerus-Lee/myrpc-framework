package com.my.micheal.spring.parse;

import com.my.micheal.spring.configBean.Protocol;
import com.my.micheal.spring.configBean.Reference;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;


/**
 * 注册标签解析类
 */
public class SOANameSpaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        this.registerBeanDefinitionParser("registry",new RegistryBeanDefinitionParse());
        this.registerBeanDefinitionParser("reference",new ReferenceBeanDefinitionParse(Reference.class));
        this.registerBeanDefinitionParser("protocal",new ProtocalBeanDefinitionParse(Protocol.class));
        this.registerBeanDefinitionParser("service",new ServiceBeanDefinitionParse());

    }
}
