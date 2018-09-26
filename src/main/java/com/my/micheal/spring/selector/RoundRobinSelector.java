package com.my.micheal.spring.selector;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class RoundRobinSelector implements Selector {

    private static Integer index = 0;
    @Override
    public NodeInfo selectNode(List<String> services) throws Exception {
        synchronized (index) {
            if(index > services.size()) {
                index = 0;
            }
        }
        index ++;
        String service = services.get(index);
        JSONObject jsonObject = JSONObject.parseObject(service);
        NodeInfo nodeInfo = new NodeInfo();
        nodeInfo.setHost(jsonObject.getString("host"));
        nodeInfo.setPort(jsonObject.getString("port"));
        nodeInfo.setRef(jsonObject.getString("ref"));

        return nodeInfo;
    }
}
