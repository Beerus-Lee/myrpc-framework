package com.my.micheal.spring.nettyUtil;

import com.my.micheal.spring.selector.NodeInfo;

public class NettyDelegate {
    public static void startService(String port) throws Exception{
        System.out.println("启动netty服务");
        NettyServer.start(port);
    }


    public static String connectionService(NodeInfo nodeInfo) throws Exception {
        System.out.println("连接netty服务");
        String response = NettyClient.sendMsg(nodeInfo);

        return  response;
    }
}
