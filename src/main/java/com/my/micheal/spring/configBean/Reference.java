package com.my.micheal.spring.configBean;

import com.my.micheal.spring.advice.InvokeInvocationHandler;
import com.my.micheal.spring.invoke.HttpInvoke;
import com.my.micheal.spring.invoke.Invoke;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.applet.AppletContext;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class Reference implements FactoryBean , ApplicationContextAware {


    private String id;

    private String intf;

    private String check;

    private String protocol;

    private Invoke invoke;

    private ApplicationContext applicationContext;

    private static Map<String, Invoke> protocols = new HashMap<>();

    static {
        protocols.put("http",new HttpInvoke());
        protocols.put("netty",new HttpInvoke());
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
}
