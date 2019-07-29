package com.nmq.nio.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author niemengquan
 * @create 2019/7/29
 * @modifyUser
 * @modifyDate
 */
public class Reactor implements Runnable {
    final Selector selector;
    final ServerSocketChannel serverSocket;

    Reactor(int port) throws IOException {
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        ServerSocket serverSocketObj = serverSocket.socket();
        serverSocketObj.bind(new InetSocketAddress(port));
        serverSocket.configureBlocking(false);
        // 注册连接事件
        SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        sk.attach(new Acceptor());
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                selector.select();
                Set<SelectionKey> selected = selector.selectedKeys();
                Iterator<SelectionKey> it = selected.iterator();
                while (it.hasNext()) {
                    dispatch(it.next());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void dispatch(SelectionKey key){
        Runnable r = (Runnable)(key.attachment());
        if (r != null) {
            r.run();
        }
    }
    class Acceptor implements Runnable {
        @Override
        public void run() {
            try {
                SocketChannel accept = serverSocket.accept();
                if (accept != null) {
                    new Handler(selector, accept);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
