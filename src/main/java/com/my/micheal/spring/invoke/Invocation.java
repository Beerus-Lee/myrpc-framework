package com.my.micheal.spring.invoke;

import com.my.micheal.spring.configBean.Reference;

import java.lang.reflect.Method;

public class Invocation {
    private String intf;

    private Method method;

    private Object[] objects;

    private Reference reference;

    public Reference getReference() {
        return reference;
    }

    public void setReference(Reference reference) {
        this.reference = reference;
    }

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
