package com.nmq.nio.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
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
            selectionKeys.stream().forEach(selectionKey -> {
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
                        ServerSocketChannel socketChannel = (ServerSocketChannel) selectionKey.channel();
                        client = socketChannel.accept();
                        // 开始读取客户端的消息
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
