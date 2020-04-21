package com.nmq.netty.ws;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;
import java.util.Iterator;

/**
 *
 * @author niemengquan
 * @create 2019/6/19
 * @modifyUser
 * @modifyDate
 */
public class TextWebSocketFrameHanlder extends SimpleChannelInboundHandler<TextWebSocketFrame>{
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    /** 连接客服消息体 */
    private  String WS_CONNECT_STAFFSERVICE = "ws:connect:staffservice";
    /** 心跳消息体 */
    private  String WS_PING_MSG = "ws:ping:msg";
    /** 心跳响应消息体 */
    private  String WS_PONG_MSG = "ws:pong:msg";
    /** 获取自身channelId消息体 */
    private  String WS_CONNECT_OWNERID = "ws:connect:ownerId";
    /**
     * 消息体设置：
     * 发送消息的格式： channelId:msg，其中channelId是你的交流对象
     *
     * 设置连接客服的消息体前缀为：ws:connect:staffservice
     * 设置端到端的消息发送的消息体前缀是对方的：channelId
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        String text = msg.text();
        System.out.println("收到消息： " + text);
        if (WS_CONNECT_OWNERID.equals(text)){
            // 给自己发一条消息告知自己的Id信息
            ctx.channel().writeAndFlush(new TextWebSocketFrame("ws:connect:ownerId" +  ctx.channel().id().asLongText()));
            return;
        }
        // 如果收到的消息是连接客服的消息的话
        if (WS_CONNECT_STAFFSERVICE.equals(text)){
            // 随机获取一个已有的连接分配出去
            Iterator<Channel> iterator = channelGroup.iterator();
            while (iterator.hasNext()){
                Channel channel = iterator.next();
                // 随机交换channelid，建立一对一的连接
                if (channel != ctx.channel()){
                    // 将客服的标识Id返回给客户端
                    ctx.writeAndFlush(new TextWebSocketFrame(WS_CONNECT_STAFFSERVICE + channel.id().asLongText()));
                    ctx.writeAndFlush(new TextWebSocketFrame("客服" + channel.id().asShortText()+"为您服务！"));
                    // 同时给客服发一条消息说谁和你连接上了
                    channel.writeAndFlush(new TextWebSocketFrame(WS_CONNECT_STAFFSERVICE + ctx.channel().id().asLongText()));
                    channel.writeAndFlush(new TextWebSocketFrame("客户" + ctx.channel().id().asShortText() + ":已连接！"));
                    break;
                }
            }
            return;
        }
        // 心跳消息
        if (WS_PING_MSG.equals(text)){
            System.out.println("收到心跳包：" +  ctx.channel().id().asShortText());
            ctx.channel().writeAndFlush(new TextWebSocketFrame(WS_PONG_MSG));
            return;
        }
        // 分发消息 channelId:msg
        channelGroup.forEach(channel -> {
            if (channel != ctx && channel.id().asLongText().startsWith(text.split(":")[0])){
                channel.writeAndFlush(new TextWebSocketFrame(text));
            }
        });
        //ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器时间：" + LocalDateTime.now()));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAddered" + ctx.channel().id().asLongText());
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("【服务器】-" + channel.remoteAddress() + "加入\n");
        channelGroup.add(channel);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive" + ctx.channel().id().asLongText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved" + ctx.channel().id().asLongText());
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("【服务器】-" + channel.remoteAddress() + "离开\n");
        System.out.println(channelGroup.size());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常发生" + cause.getMessage());
        ctx.close();
    }
}
