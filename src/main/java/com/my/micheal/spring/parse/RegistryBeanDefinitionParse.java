package com.my.micheal.spring.parse;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class RegistryBeanDefinitionParse implements BeanDefinitionParser {

    private Class<?> classBean;

    public RegistryBeanDefinitionParse(Class<?> classBean) {
        this.classBean = classBean;
    }


    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(classBean);
        beanDefinition.setLazyInit(false);

        String id = element.getAttribute("id");

        String protocol = element.getAttribute("protocol");

        String address = element.getAttribute("address");

        if (id != null && !"".equals(id)) {
            if (parserContext.getRegistry().containsBeanDefinition(id)) {
                throw new IllegalStateException("id has exits");
            } else {
                beanDefinition.getPropertyValues().add("id", id);
            }
        } else {
            id = classBean.getName().substring(0, 1).toLowerCase() + classBean.getName().substring(1);
            beanDefinition.getPropertyValues().add("id", id);
        }

        if(protocol != null && !"".equals(protocol)) {
            beanDefinition.getPropertyValues().add("protocol",protocol);
        }

        if(address != null && !"".equals(address)) {
            beanDefinition.getPropertyValues().add("address",address);
        }

        parserContext.getRegistry().registerBeanDefinition(id,beanDefinition);

        return beanDefinition;
    }
}
