package com.my.micheal.spring.invoke;

import java.lang.reflect.Method;

public class Invocation {
    private String intf;

    private Method method;

    private Object[] objects;

    public String getIntf() {
        return intf;
    }

    public void setIntf(String intf) {
        this.intf = intf;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object[] getObjects() {
        return objects;
    }

    public void setObjects(Object[] objects) {
        this.objects = objects;
    }
}
