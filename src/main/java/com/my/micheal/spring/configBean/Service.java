package com.my.micheal.spring.configBean;

import com.my.micheal.spring.registry.RegistryCenter;
import com.my.micheal.spring.registry.RegistryDelegate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Service implements InitializingBean , ApplicationContextAware {
    private  String id;

    private String intf;

    private String ref;

    private ApplicationContext applicationContext;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntf() {
        return intf;
    }

    public void setIntf(String intf) {
        this.intf = intf;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        RegistryDelegate.registry(this,applicationContext);

        System.out.println("service 属性加载完毕");


    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
