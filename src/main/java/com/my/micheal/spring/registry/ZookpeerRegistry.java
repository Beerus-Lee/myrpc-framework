package com.my.micheal.spring.registry;

import com.my.micheal.spring.configBean.Service;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ZookpeerRegistry implements RegistryCenter{
    @Override
    public boolean registry(Service service, ApplicationContext applicationContext) throws Exception {
        return false;
    }

    @Override
    public List<String> getAllServices(String id ,ApplicationContext applicationContext) {
        return null;
    }
}
