package com.my.micheal.spring.invoke;

import com.alibaba.fastjson.JSONObject;
import com.my.micheal.spring.nettyUtil.NettyDelegate;
import com.my.micheal.spring.selector.NodeInfo;
import com.my.micheal.spring.selector.Selector;

import java.util.List;

public class NettyInvoke implements Invoke {
    @Override
    public String invoke(Invocation invocation) throws Exception {
        List<String> servicesList = invocation.getReference().getRegistryServices();
        System.out.println("获取服务列表"+ JSONObject.toJSONString(servicesList));
        Selector selector = invocation.getReference().getSelector();
        NodeInfo nodeInfo = selector.selectNode(servicesList);
        String msg = NettyDelegate.connectionService(nodeInfo);

        return msg;
    }
}
