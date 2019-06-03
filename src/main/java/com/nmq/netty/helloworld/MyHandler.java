package com.nmq.netty.practise;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * hello world netty
 *
 * @author niemengquan
 * @create 2019/6/3
 * @modifyUser
 * @modifyDate
 */
public class MyHandler extends SimpleChannelInboundHandler<HttpObject> {

    /**
     * google 等浏览器的网页图标
     */
    private final String FAVICON_STR = "/favicon.ico";
    @Override
    public void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest httpRequest = (HttpRequest) msg;
            System.out.println("request method name: " +httpRequest.method().name());
            System.out.println("request method uri: " +httpRequest.uri());
            URI uri = new URI(httpRequest.uri());
            if(FAVICON_STR.equals(uri.getPath())){
                System.out.println("request favicon.ico");
            }

            ByteBuf content = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);
            // create http response object
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            ctx.writeAndFlush(response);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }
}