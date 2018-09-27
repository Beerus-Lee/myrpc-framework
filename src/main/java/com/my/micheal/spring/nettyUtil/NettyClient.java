package com.my.micheal.spring.nettyUtil;

import com.my.micheal.spring.selector.NodeInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
    private static NettyClientHandler nettyClientHandler ;

    public static String sendMsg(NodeInfo nodeInfo) throws Exception {
        StringBuffer message = new StringBuffer();
        nettyClientHandler = new NettyClientHandler(message, nodeInfo);
        EventLoopGroup group = new NioEventLoopGroup();
        try {//1.2 创建启动类Bootstrap实例，用来设置客户端相关参数
            Bootstrap b = new Bootstrap();
            b.group(group)//1.2.1设置线程池
                    .channel(NioSocketChannel.class)//1.2.2指定用于创建客户端NIO通道的Class对象
                    .option(ChannelOption.TCP_NODELAY, true)//1.2.3设置客户端套接字参数
                    .handler(new ChannelInitializer<SocketChannel>() {//1.2.4设置用户自定义handler
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();

                            p.addLast(nettyClientHandler);

                        }
                    });

            // 1.3启动链接
            ChannelFuture f = b.connect(nodeInfo.getHost(), Integer.parseInt(nodeInfo.getPort())).channel().closeFuture().await();
            return message.toString();

        } finally {
            // 1.5优雅关闭线程池
            group.shutdownGracefully();
        }
    }

}
