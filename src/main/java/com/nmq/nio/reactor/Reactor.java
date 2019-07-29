package com.nmq.nio.reactor;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

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
    }

    @Override
    public void run() {

    }
}
