package com.my.micheal.spring.selector;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Set;

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
        Set<String> keys= jsonObject.keySet();
        NodeInfo nodeInfo = new NodeInfo();
        for(String key :keys) {
            String json = jsonObject.getString(key);
            JSONObject node = JSONObject.parseObject(json);
            nodeInfo.setHost(node.getString("host"));
            nodeInfo.setPort(node.getString("port"));
            nodeInfo.setRef(node.getString("ref"));
            nodeInfo.setRef(JSONObject.parseObject(node.getString("service")).getString("ref"));

        }

        return nodeInfo;
    }
}
