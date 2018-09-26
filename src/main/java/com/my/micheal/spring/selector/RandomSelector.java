package com.my.micheal.spring.selector;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Random;

public class RandomSelector implements Selector {
    @Override
    public NodeInfo selectNode(List<String> services) throws Exception {
        Random random  =new Random();
        int randomNum = random.nextInt(services.size());
        String service = services.get(randomNum);
        JSONObject jsonObject = JSONObject.parseObject(service);
        NodeInfo nodeInfo = new NodeInfo();
        nodeInfo.setHost(jsonObject.getString("host"));
        nodeInfo.setPort(jsonObject.getString("port"));
        nodeInfo.setRef(jsonObject.getString("ref"));

        return nodeInfo;
    }
}
