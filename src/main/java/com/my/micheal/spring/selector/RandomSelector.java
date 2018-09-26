package com.my.micheal.spring.selector;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomSelector implements Selector {
    @Override
    public NodeInfo selectNode(List<String> services) throws Exception {
        Random random  =new Random();
        int randomNum = random.nextInt(services.size());
        String service = services.get(randomNum);
        JSONObject jsonObject = JSONObject.parseObject(service);
        Set<String> keys= jsonObject.keySet();
        NodeInfo nodeInfo = new NodeInfo();
        for(String key :keys) {
            String json = jsonObject.getString(key);
            JSONObject node = JSONObject.parseObject(json);
            nodeInfo.setHost(node.getString("host"));
            nodeInfo.setPort(node.getString("port"));
            nodeInfo.setRef(JSONObject.parseObject(node.getString("service")).getString("ref"));
        }
        return nodeInfo;
    }
}
