package com.nmq.grpc.proto;

import com.nmq.grpc.helloworld.GreeterGrpc;
import com.nmq.grpc.helloworld.HelloRequest;
import com.nmq.grpc.helloworld.HelloResponse;
import io.grpc.stub.StreamObserver;

import java.util.UUID;

/**
 * grpc protocbuf implement
 * @author niemengquan
 * @create 2019/6/12
 * @modifyUser
 * @modifyDate
 */
public class GreeterImpl extends GreeterGrpc.GreeterImplBase {
    /**
     * Unary RPCs where the client sends a single request to the server and gets a single response back,
     * just like a normal function call
     * @param request
     * @param responseObserver
     */
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        System.out.println("Received client invoke sayHello: " + request.getName());
        HelloResponse helloResponse = HelloResponse.newBuilder().setMessage("Hello " + request.getName()).build();
        responseObserver.onNext(helloResponse);
        responseObserver.onCompleted();
    }

    /**
     * Server streaming RPCs where the client sends a request to the server and gets a stream to read a sequence of messages back.
     * The client reads from the returned stream until there are no more messages. gRPC guarantees message ordering within an individual RPC call.
     * @param request
     * @param responseObserver
     */
    @Override
    public void streamResponse(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        System.out.println("-----------------------Server-streaming rpc-------------------------------");

        System.out.println("Received client invoke streamResponse method: " + request.getName());
        HelloResponse helloResponse = HelloResponse.newBuilder().setMessage(UUID.randomUUID().toString()).build();
        responseObserver.onNext(helloResponse);
        responseObserver.onNext(HelloResponse.newBuilder().setMessage(UUID.randomUUID().toString()).build());
        responseObserver.onNext(HelloResponse.newBuilder().setMessage(UUID.randomUUID().toString()).build());
        responseObserver.onNext(HelloResponse.newBuilder().setMessage(UUID.randomUUID().toString()).build());
        responseObserver.onNext(HelloResponse.newBuilder().setMessage(UUID.randomUUID().toString()).build());
        responseObserver.onCompleted();
    }

    /**
     * Client streaming RPCs where the client writes a sequence of messages and sends them to the server, again using a provided stream.
     * Once the client has finished writing the messages, it waits for the server to read them and return its response. Again gRPC guarantees
     * message ordering within an individual RPC call.
     * @param responseObserver
     * @return
     */
    @Override
    public StreamObserver<HelloRequest> clientStreaming(StreamObserver<HelloResponse> responseObserver) {
        System.out.println("-----------------------Client-streaming rpc-------------------------------");

        return new StreamObserver<HelloRequest>() {
            @Override
            public void onNext(HelloRequest value) {
                System.out.println("ClientStreaming method received: " + value.getName());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                // called when the client has finished writing messages
                System.out.println("Request onCompleted!");
                responseObserver.onNext(HelloResponse.newBuilder().setMessage("Server reponse competed").build());
                responseObserver.onCompleted();
            }
        };
    }

    /**
     * Bidirectional streaming RPCs where both sides send a sequence of messages using a read-write stream. The two streams operate independently,
     * so clients and servers can read and write in whatever order they like: for example, the server could wait to receive all the client messages
     * before writing its responses, or it could alternately read a message then write a message, or some other combination of reads and writes.
     * The order of messages in each stream is preserved.
     * @param responseObserver
     * @return
     */
    @Override
    public StreamObserver<HelloRequest> bidirectionalStreaming(StreamObserver<HelloResponse> responseObserver) {
        System.out.println("-----------------------Bidirectional-streaming rpc-------------------------------");

        return new StreamObserver<HelloRequest>() {
            @Override
            public void onNext(HelloRequest value) {
                System.out.println("ClientStreaming method received: " + value.getName());
                responseObserver.onNext(HelloResponse.newBuilder().setMessage(UUID.randomUUID().toString()).build());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                // called when the client has finished writing messages
                System.out.println("Request onCompleted!");
                responseObserver.onNext(HelloResponse.newBuilder().setMessage("Message sent when the server is closed").build());
                responseObserver.onCompleted();
            }
        };
    }
}
