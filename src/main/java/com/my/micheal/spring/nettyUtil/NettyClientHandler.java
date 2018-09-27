package com.my.micheal.spring.nettyUtil;

import com.alibaba.fastjson.JSONObject;
import com.my.micheal.spring.selector.NodeInfo;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.atomic.AtomicInteger;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    private final byte[] request;

    private StringBuffer resultMsg;

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 创建一个客户端 handler.
     */
    public NettyClientHandler(StringBuffer resultMsg,NodeInfo nodeInfo) {
        request =JSONObject.toJSONString(nodeInfo).getBytes();
        this.resultMsg = resultMsg;
    }
    //(2.1)
    @Override
    public void channelActive(ChannelHandlerContext ctx) {

        System.out.println("--- client already connected----");

        ByteBuf message = null;
        for (int i = 0; i < 1; ++i) {
            message = Unpooled.buffer(request.length);
            message.writeBytes(request);
            ctx.writeAndFlush(message);
        }
    }
    //(2.2)
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf message = (ByteBuf) msg;
        byte[] response = new byte[message.readableBytes()];
        message.readBytes(response);
        this.resultMsg = resultMsg.append(new String(response));
        System.out.println(atomicInteger.getAndIncrement() + "receive from server:" + new String(response));
        ctx.close();
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }


}
