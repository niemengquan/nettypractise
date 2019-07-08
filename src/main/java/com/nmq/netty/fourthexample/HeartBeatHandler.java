package com.nmq.netty.fourthexample;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.time.LocalDateTime;

/**
 * @author niemengquan
 * @create 2019/7/8
 * @modifyUser
 * @modifyDate
 */
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // 判断evt是否是IdleStateEvent（用于触发用户事件，包含 读空闲/写空闲/读写空闲）
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;
            if (event.state() == IdleState.READER_IDLE) {
                System.out.println("进入读空闲...");
            } else if (event.state() == IdleState.WRITER_IDLE) {
                System.out.println("进入写空闲...");
            } else if (event.state() == IdleState.ALL_IDLE) {
                System.out.println("channel 读写超时，关闭channel :" + ctx.channel().id());
                Channel channel = ctx.channel();
                // 关闭无用的channel，以防资源浪费
                ctx.channel().writeAndFlush(new TextWebSocketFrame("连接超时，由于长时间未操作，连接已丢失，请重新刷新页面！"));
                channel.close();
            }
        }
    }
}
