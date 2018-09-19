package com.my.micheal.spring.invoke;

public class HttpInvoke implements Invoke {

    @Override
    public String invoke(Invocation invocation) throws Exception {
        System.out.println("调用httpInvoke=========");
        return null;
    }
}
