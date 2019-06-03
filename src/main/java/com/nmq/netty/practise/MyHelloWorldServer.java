package com.nmq.netty.practise;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * netty hello world application;all request will received 'Hello World' string.
 * @author niemengquan
 * @create 2019/6/3
 * @modifyUser
 * @modifyDate
 */
public class MyHelloWorldServer {
    public static void main(String[] args) throws Exception{
        EventLoopGroup bossGroup  = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap  server = new ServerBootstrap();
            server.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new MyTestServerInitializer());
            ChannelFuture channelFuture = server.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
