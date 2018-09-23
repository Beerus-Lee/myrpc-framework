package com.my.micheal.spring.invoke;

import com.alibaba.fastjson.JSONObject;
import com.my.micheal.spring.configBean.Reference;

public class HttpInvoke implements Invoke {

    @Override
    public String invoke(Invocation invocation) throws Exception {
        System.out.println("调用httpInvoke=========,当前服务列表为："+ JSONObject.toJSONString(Reference.getRegistryServices()));
        return null;
    }
}
