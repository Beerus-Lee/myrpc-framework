package com.my.micheal.spring.configBean;

import com.my.micheal.spring.registry.RedisRegistry;
import com.my.micheal.spring.registry.RegistryCenter;
import com.my.micheal.spring.registry.ZookpeerRegistry;

import java.util.HashMap;
import java.util.Map;

public class Registry {
    private String id;

    private String protocol;

    private String address;

    private static Map<String, RegistryCenter> registries = new HashMap<>();

    static {
        registries.put("redis",new RedisRegistry());
        registries.put("zookpeer",new ZookpeerRegistry());
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static Map<String, RegistryCenter> getRegistries() {
        return registries;
    }

    public static void setRegistries(Map<String, RegistryCenter> registries) {
        Registry.registries = registries;
    }
}
