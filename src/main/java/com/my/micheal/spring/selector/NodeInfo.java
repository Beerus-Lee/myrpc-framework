package com.my.micheal.spring.selector;

public class NodeInfo {
    private String ref;

    private String host;

    private String port;

    private String methodName;

    private Object[] params;

    private Object[] paramTypes;

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Object[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Object[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
