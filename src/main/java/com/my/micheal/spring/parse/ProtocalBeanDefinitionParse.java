package com.my.micheal.spring.parse;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class ProtocalBeanDefinitionParse implements BeanDefinitionParser {
    private Class<?> classBean;

    public ProtocalBeanDefinitionParse(Class<?> classBean){
        this.classBean = classBean;
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        GenericBeanDefinition  beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(classBean);
        beanDefinition.setLazyInit(false);
        String id = element.getAttribute("id");
        String name = element.getAttribute("name");
        String host = element.getAttribute("host");
        String port = element.getAttribute("port");

        if(id != null && !"".equals(id)){
            if(parserContext.getRegistry().containsBeanDefinition(id)){
                throw new IllegalStateException("id has exits");
            }
        } else {
            id = classBean.getName().substring(0,1).toLowerCase() + classBean.getName().substring(1);
        }
        beanDefinition.getPropertyValues().add("id",id);

        if(name != null && !"".equals(name)) {
            beanDefinition.getPropertyValues().add("name",name);
        } else {
            throw new IllegalStateException("name is null");
        }

        if(host != null && !"".equals(host)) {
            beanDefinition.getPropertyValues().add("host",host);
        } else {
            throw new IllegalStateException("host is null");
        }

        if(port != null && !"".equals(port)) {
            beanDefinition.getPropertyValues().add("port",port);
        } else {
            throw new IllegalStateException("port is null");
        }

        parserContext.getRegistry().registerBeanDefinition(id,beanDefinition);

        return beanDefinition;
    }
}
