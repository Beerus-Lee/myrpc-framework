package com.my.micheal.spring.configBean;

import com.my.micheal.spring.advice.InvokeInvocationHandler;
import com.my.micheal.spring.invoke.HttpInvoke;
import com.my.micheal.spring.invoke.Invoke;
import com.my.micheal.spring.invoke.NettyInvoke;
import com.my.micheal.spring.registry.RegistryCenter;
import com.my.micheal.spring.registry.RegistryDelegate;
import com.my.micheal.spring.selector.RandomSelector;
import com.my.micheal.spring.selector.RoundRobinSelector;
import com.my.micheal.spring.selector.Selector;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reference implements FactoryBean , ApplicationContextAware , InitializingBean {


    private String id;

    private String intf;

    private String check;

    private String protocol;

    private String fetch;

    private Invoke invoke;

    private Selector selector;

    private ApplicationContext applicationContext;

    public Selector getSelector() {
        return selector;
    }

    public String getFetch() {
        return fetch;
    }

    public void setFetch(String fetch) {
        this.fetch = fetch;
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }

    private  List<String> registryServices = new ArrayList<>();

    private static Map<String, Invoke> protocols = new HashMap<>();

    private static Map<String, Selector> selectors = new HashMap<>();

    static {
        protocols.put("http",new HttpInvoke());
        protocols.put("netty",new NettyInvoke());

        selectors.put("random",new RandomSelector());
        selectors.put("robin",new RoundRobinSelector());
    }


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

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public  List<String> getRegistryServices() {
        return registryServices;
    }

    @Override
    public Object getObject() throws Exception {
        if(protocol != null && !"".equals(protocol)){
            invoke = protocols.get(protocol);
        } else {
            Protocol protocol = applicationContext.getBean(Protocol.class);
            if(protocol != null) {
                invoke = protocols.get(protocol.getName());
            } else {
                invoke = protocols.get("http");
            }
        }

        if(fetch != null && !"".endsWith(fetch)) {
            selector = selectors.get(fetch);
        } else {
            selector = selectors.get("random");
        }

        Object proxy = Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class<?>[]{Class.forName(intf)},new InvokeInvocationHandler(invoke,this));

        return proxy;
    }

    @Override
    public Class<?> getObjectType() {
        try {
            if(intf != null && !"".equals(intf)) {
                return Class.forName(intf);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.registryServices = RegistryDelegate.getAllServices(id,applicationContext);
    }
}
