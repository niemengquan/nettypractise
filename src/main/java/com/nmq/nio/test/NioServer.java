package com.nmq.nio.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * server 端保存每个连接，并转发消息给其他的客户端
 * @author niemengquan
 * @create 2019/6/18
 * @modifyUser
 * @modifyDate
 */
public class NioServer {
    public static void main(String[] args) throws IOException {
        Map<String,SocketChannel> clientMaps = new HashMap<>();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 设置非阻塞模式
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));

        Selector selector = Selector.open();
        // 监听连接建立的事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true)  {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            selectionKeys.forEach(selectionKey -> {
                final SocketChannel client;
                try {
                    if (selectionKey.isAcceptable()){
                        ServerSocketChannel socketChannel = (ServerSocketChannel) selectionKey.channel();
                        client = socketChannel.accept();
                        // 配置非阻塞
                        client.configureBlocking(false);
                        // 注册read事件的
                        client.register(selector,SelectionKey.OP_READ);
                        // 保存客户端的连接信息
                        String key = "【" + UUID.randomUUID().toString()+ "】";
                        clientMaps.put(key,client);
                    } else if (selectionKey.isReadable()) {
                        client = (SocketChannel) selectionKey.channel();
                        // 开始读取客户端的消息
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        // 这里去取的时候如果连接中断了，会抛出异常
                        int count = 0;
                        try {
                            count = client.read(byteBuffer);
                        }catch (IOException err){
                            System.out.println(client + ":" + "退出！");
                            // 说明该链接已经丢失
                            selectionKey.cancel();
                            client.socket().close();
                            client.close();
                            return;
                        }
                        if (count > 0){
                            byteBuffer.flip();
                            // 中文字符的编码
                            Charset charset = Charset.forName("utf-8");
                            CharsetDecoder charsetDecoder = charset.newDecoder();
                            String receivedMsg = String.valueOf(charsetDecoder.decode(byteBuffer).array());
                            System.out.println(client +"：" + receivedMsg);
                            // 获取该连接的key
                            String senderKey = null;
                            for(Map.Entry<String,SocketChannel> entry: clientMaps.entrySet()){
                                if (entry.getValue() == client){
                                    senderKey = entry.getKey();
                                    break;
                                }
                            }
                            // 向其他的客户端，发送该客户端发送的消息
                            for (Map.Entry<String ,SocketChannel> entry: clientMaps.entrySet()){
                                if(client == entry.getValue()){
                                    continue;
                                }
                                SocketChannel channel = entry.getValue();
                                ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                                writeBuffer.put((senderKey + ":" + receivedMsg).getBytes());
                                writeBuffer.flip();
                                try {
                                    channel.write(writeBuffer);
                                }catch (IOException err){
                                    System.out.println(client + ":" + "已退出！" + senderKey + "消息发送失败！");
                                    // 说明该链接已经丢失
                                    selectionKey.cancel();
                                    client.socket().close();
                                    client.close();
                                    return;
                                }
                            }
                        }
                    }
                }catch (Exception err){
                    err.printStackTrace();
                }

            });
            // 处理完成之后一定要执行清空操作
            selectionKeys.clear();
        }
    }
}
