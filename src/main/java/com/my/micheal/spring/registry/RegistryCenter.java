package com.my.micheal.spring.registry;

import com.my.micheal.spring.configBean.Service;
import org.springframework.context.ApplicationContext;

import java.util.List;

public interface RegistryCenter {

    boolean registry(Service service, ApplicationContext applicationContext) throws Exception;

    List<String> getAllServices(String id ,ApplicationContext applicationContext);

}
