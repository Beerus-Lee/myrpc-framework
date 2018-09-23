package com.my.micheal.spring.registry;

import com.my.micheal.spring.configBean.Registry;
import com.my.micheal.spring.configBean.Service;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class RegistryDelegate {

    public static boolean registry(Service service, ApplicationContext applicationContext) throws Exception {
        Registry registry = applicationContext.getBean(Registry.class);
        boolean isRegistrySuccess ;
        if(registry != null) {
            RegistryCenter registryCenter = Registry.getRegistries().get(registry.getProtocol());
            isRegistrySuccess =  registryCenter.registry(service,applicationContext);
            return isRegistrySuccess;
        }

        return false;

    }

    public static List<String> getAllServices(String id , ApplicationContext applicationContext) {
        Registry registry = applicationContext.getBean(Registry.class);
        if(registry != null) {
            RegistryCenter registryCenter = Registry.getRegistries().get(registry.getProtocol());
            return registryCenter.getAllServices(id,applicationContext);
        }
        return null;
    }
}
