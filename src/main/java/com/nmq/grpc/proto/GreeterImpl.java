package com.nmq.grpc.proto;

import io.grpc.stub.StreamObserver;

/**
 * @author niemengquan
 * @create 2019/6/12
 * @modifyUser
 * @modifyDate
 */
public class GreeterImpl extends GreeterGrpc.GreeterImplBase {
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        System.out.println("Received client: " + request.getName());
        HelloResponse helloResponse = HelloResponse.newBuilder().setMessage("Hello " + request.getName()).build();
        responseObserver.onNext(helloResponse);
        responseObserver.onCompleted();
    }
}
