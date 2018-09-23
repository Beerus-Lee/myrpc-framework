package com.my.micheal.spring.parse;

import com.my.micheal.spring.configBean.Protocol;
import com.my.micheal.spring.configBean.Reference;
import com.my.micheal.spring.configBean.Registry;
import com.my.micheal.spring.configBean.Service;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;


/**
 * 注册标签解析类
 */
public class SOANameSpaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        this.registerBeanDefinitionParser("registry",new RegistryBeanDefinitionParse(Registry.class));
        this.registerBeanDefinitionParser("reference",new ReferenceBeanDefinitionParse(Reference.class));
        this.registerBeanDefinitionParser("protocol",new ProtocalBeanDefinitionParse(Protocol.class));
        this.registerBeanDefinitionParser("service",new ServiceBeanDefinitionParse(Service.class));

    }
}
