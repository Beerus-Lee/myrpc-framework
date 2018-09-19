package com.my.micheal.spring.parse;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class ReferenceBeanDefinitionParse implements BeanDefinitionParser {

    private Class<?> classBean;

    public ReferenceBeanDefinitionParse(Class<?> classBean){
        this.classBean = classBean;
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        GenericBeanDefinition  beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(classBean);
        beanDefinition.setLazyInit(false);
        String id = element.getAttribute("id");
        String intf = element.getAttribute("interface");
        String check = element.getAttribute("check");
        String protocol = element.getAttribute("protocol");

        if(id != null && !"".equals(id)){
            if(parserContext.getRegistry().containsBeanDefinition(id)){
                throw new IllegalStateException("id has exits");
            }
            beanDefinition.getPropertyValues().add("id",id);
        }
        else{
            id = classBean.getName().substring(0,1).toLowerCase()+classBean.getName().substring(1);
            beanDefinition.getPropertyValues().add("id",id);
        }

        if(intf != null && !"".equals(intf)){

            beanDefinition.getPropertyValues().add("intf",intf);
        }

        if(check != null && !"".equals(check)){

            beanDefinition.getPropertyValues().add("check",check);
        }

        if(protocol != null && !"".equals(protocol)){
            if(parserContext.getRegistry().containsBeanDefinition(protocol)){
                throw new IllegalStateException("protocol has exits");
            }
            beanDefinition.getPropertyValues().add("protocol",protocol);
        }
        parserContext.getRegistry().registerBeanDefinition(id,beanDefinition);


        return beanDefinition;
    }
}
