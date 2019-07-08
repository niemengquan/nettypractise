package com.nmq.netty.fourthexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;


/**
 * @author niemengquan
 * @create 2019/6/19
 * @modifyUser
 * @modifyDate
 */
public class WebSocketChannelInitializer  extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new HttpObjectAggregator(8192));
        // ====================== 增加心跳支持 start    ======================
        // 针对客户端，如果在20s没有向服务端发送读写心跳(ALL)，则主动断开
        // 如果是读空闲或者写空闲，不处理
        pipeline.addLast(new IdleStateHandler(8, 10, 20));
        // 自定义的空闲状态检测
        pipeline.addLast(new HeartBeatHandler());
        // ====================== 增加心跳支持 end    ======================

        //websocket服务器处理的协议，用于指定给客户端连接访问的路由："/ws"
        //本handler会帮你处理一些繁重的复杂的事。会帮你处理握手动作: handshaking
        //对于websocket来讲，都是以frames进行传输的，不同的数据类型对应的frames也不同。
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        pipeline.addLast(new TextWebSocketFrameHanlder());
    }
}
