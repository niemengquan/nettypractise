package com.nmq.grpc.proto;

import com.nmq.grpc.helloworld.GreeterGrpc;
import com.nmq.grpc.helloworld.HelloRequest;
import com.nmq.grpc.helloworld.HelloResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author niemengquan
 * @create 2019/6/12
 * @modifyUser
 * @modifyDate
 */
public class HelloWorldClient {
    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",8899).usePlaintext().build();
        GreeterGrpc.GreeterBlockingStub greeterBlockingStub = GreeterGrpc.newBlockingStub(channel);
        GreeterGrpc.GreeterStub greeterStub = GreeterGrpc.newStub(channel);
       // Unary rpc
        for (int i=0;i<100;i++){
            HelloResponse helloResponse = greeterBlockingStub.sayHello(HelloRequest.newBuilder().setName("hello").build());
            String message = helloResponse.getMessage();
            System.out.println("Server repsponse:" + message);
        }
        System.out.println("------------------------------------------------------");
        // Server-streaming rpc
        Iterator<HelloResponse> iteratorResponse = greeterBlockingStub.streamResponse(HelloRequest.newBuilder().setName("hello").build());
        while(iteratorResponse.hasNext()){
            HelloResponse next = iteratorResponse.next();
            String message = next.getMessage();
            System.out.println("Server repsponse:" + message);
        }

        System.out.println("-------------------------------------------------------");
         // Client-streaming rpc
        /**
         * 应为是异步的请求，所以这里为了检查服务器端是否已经完成（调用onCompleted method）,这里使用了CountDownLatch 来检测，
         * 注意这是必须的。否则可能无法得到任何的结果。
         */
        final CountDownLatch finishLatch = new CountDownLatch(1);
        StreamObserver<HelloRequest> helloRequestStreamObserver = greeterStub.clientStreaming(new StreamObserver<HelloResponse>() {
            @Override
            public void onNext(HelloResponse value) {
                System.out.println("Received response: " + value.getMessage());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Server response finish");
                finishLatch.countDown();
            }
        });
        helloRequestStreamObserver.onNext(HelloRequest.newBuilder().setName("zhangsan").build());
        helloRequestStreamObserver.onNext(HelloRequest.newBuilder().setName("lisi").build());
        helloRequestStreamObserver.onNext(HelloRequest.newBuilder().setName("wangwu").build());
        helloRequestStreamObserver.onNext(HelloRequest.newBuilder().setName("zhaoliu").build());
        helloRequestStreamObserver.onNext(HelloRequest.newBuilder().setName("xiaoqiang").build());
        helloRequestStreamObserver.onCompleted();
        finishLatch.await();
        channel.shutdown();
    }
}
