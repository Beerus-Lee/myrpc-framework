package com.my.micheal.spring.advice;

import com.my.micheal.spring.configBean.Reference;
import com.my.micheal.spring.invoke.Invocation;
import com.my.micheal.spring.invoke.Invoke;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InvokeInvocationHandler implements InvocationHandler {

    private Invoke invoke;

    private Reference reference;

    public InvokeInvocationHandler(Invoke invoke,Reference reference) {
        this.invoke = invoke;
        this.reference = reference;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("================调用到了 invocationInvocationHandler");
        Invocation invocation = new Invocation();
        invocation.setIntf(reference.getIntf());
        invocation.setMethod(method);
        invocation.setObjects(args);
        return invoke.invoke(invocation);
    }
}
