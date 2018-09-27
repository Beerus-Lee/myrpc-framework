package com.my.micheal.spring.nettyUtil;

import com.alibaba.fastjson.JSONObject;
import com.my.micheal.spring.selector.NodeInfo;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("--- accepted client---");
        ctx.fireChannelActive();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf message = (ByteBuf) msg;
        byte[] response = new byte[message.readableBytes()];
        message.readBytes(response);
        String result = new String(response);
        NodeInfo nodeInfo = JSONObject.toJavaObject(JSONObject.parseObject(result),NodeInfo.class);
        Class clazz = Class.forName(nodeInfo.getRef());
        List<Class<?>> clazzType = new ArrayList<>();
        for(Object paramType : nodeInfo.getParamTypes()) {
            clazzType.add(Class.forName(paramType.toString()));
        }
        Method method = clazz.getDeclaredMethod(nodeInfo.getMethodName(),clazzType.toArray(new Class<?>[]{}));

        Object resp = method.invoke(clazz.newInstance(),nodeInfo.getParams());

        ByteBuf seneMsg = Unpooled.buffer(JSONObject.toJSONString(resp).length());
        seneMsg.writeBytes(JSONObject.toJSONString(resp).getBytes());
        ctx.writeAndFlush(seneMsg);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
