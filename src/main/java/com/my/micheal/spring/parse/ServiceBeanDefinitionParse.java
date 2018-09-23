package com.my.micheal.spring.parse;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class ServiceBeanDefinitionParse implements BeanDefinitionParser {

    private Class<?> classBean;

    public ServiceBeanDefinitionParse(Class<?> classBean) {
        this.classBean = classBean;
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        GenericBeanDefinition beanDefinition  = new GenericBeanDefinition();
        beanDefinition.setBeanClass(classBean);
        beanDefinition.setLazyInit(false);

        String id = element.getAttribute("id");
        String intf = element.getAttribute("interface");
        String ref = element.getAttribute("ref");

        if(id != null && !"".equals(id)) {
            if(parserContext.getRegistry().containsBeanDefinition("id")) {
                throw new IllegalStateException("id has exits");
            } else {
               beanDefinition.getPropertyValues().add("id",id);
            }
        } else {
          id = classBean.getName().substring(0,1).toLowerCase() + classBean.getName().substring(1);
          beanDefinition.getPropertyValues().add("id",id);
        }

        if(intf != null && !"".equals(intf)) {
            beanDefinition.getPropertyValues().add("intf",intf);
        }

        if(ref != null && !"".equals(ref)) {
            beanDefinition.getPropertyValues().add("ref",ref);
        }

        parserContext.getRegistry().registerBeanDefinition(id,beanDefinition);


        return beanDefinition;
    }
}
