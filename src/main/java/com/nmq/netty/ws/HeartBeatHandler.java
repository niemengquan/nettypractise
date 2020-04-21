package com.nmq.netty.ws;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author niemengquan
 * @create 2019/7/8
 * @modifyUser
 * @modifyDate
 */
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {
    /** 空闲次数 */
    private int idle_count = 1;
    /** 最大空闲次数 */
    private int max_idle_count = 3;

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
                System.out.println(this +"channel 读写超时，第" + idle_count + "次");
                if (idle_count >= max_idle_count){
                    Channel channel = ctx.channel();
                    // 关闭无用的channel，以防资源浪费
                    ctx.channel().writeAndFlush(new TextWebSocketFrame("连接超时，由于长时间未操作，连接已丢失，请重新刷新页面！"));
                    channel.close();
                } else {
                    //发送提示消息
                    ctx.channel().writeAndFlush(new TextWebSocketFrame("系统检测到您长时间未进行操作，请问您还要别的什么问题吗！"));
                }
                idle_count++;
            }
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        idle_count = 1;
        // 这里一定要将消息重新发送出去
        ctx.fireChannelRead(msg);
    }

}
