package com.nmq.grpc.helloworld;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.21.0)",
    comments = "Source: helloworld.proto")
public final class GreeterGrpc {

  private GreeterGrpc() {}

  public static final String SERVICE_NAME = "helloworld.Greeter";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.nmq.grpc.helloworld.HelloRequest,
      com.nmq.grpc.helloworld.HelloResponse> getSayHelloMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SayHello",
      requestType = com.nmq.grpc.helloworld.HelloRequest.class,
      responseType = com.nmq.grpc.helloworld.HelloResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.nmq.grpc.helloworld.HelloRequest,
      com.nmq.grpc.helloworld.HelloResponse> getSayHelloMethod() {
    io.grpc.MethodDescriptor<com.nmq.grpc.helloworld.HelloRequest, com.nmq.grpc.helloworld.HelloResponse> getSayHelloMethod;
    if ((getSayHelloMethod = GreeterGrpc.getSayHelloMethod) == null) {
      synchronized (GreeterGrpc.class) {
        if ((getSayHelloMethod = GreeterGrpc.getSayHelloMethod) == null) {
          GreeterGrpc.getSayHelloMethod = getSayHelloMethod = 
              io.grpc.MethodDescriptor.<com.nmq.grpc.helloworld.HelloRequest, com.nmq.grpc.helloworld.HelloResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.Greeter", "SayHello"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.nmq.grpc.helloworld.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.nmq.grpc.helloworld.HelloResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GreeterMethodDescriptorSupplier("SayHello"))
                  .build();
          }
        }
     }
     return getSayHelloMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.nmq.grpc.helloworld.HelloRequest,
      com.nmq.grpc.helloworld.HelloResponse> getStreamResponseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamResponse",
      requestType = com.nmq.grpc.helloworld.HelloRequest.class,
      responseType = com.nmq.grpc.helloworld.HelloResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.nmq.grpc.helloworld.HelloRequest,
      com.nmq.grpc.helloworld.HelloResponse> getStreamResponseMethod() {
    io.grpc.MethodDescriptor<com.nmq.grpc.helloworld.HelloRequest, com.nmq.grpc.helloworld.HelloResponse> getStreamResponseMethod;
    if ((getStreamResponseMethod = GreeterGrpc.getStreamResponseMethod) == null) {
      synchronized (GreeterGrpc.class) {
        if ((getStreamResponseMethod = GreeterGrpc.getStreamResponseMethod) == null) {
          GreeterGrpc.getStreamResponseMethod = getStreamResponseMethod = 
              io.grpc.MethodDescriptor.<com.nmq.grpc.helloworld.HelloRequest, com.nmq.grpc.helloworld.HelloResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.Greeter", "StreamResponse"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.nmq.grpc.helloworld.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.nmq.grpc.helloworld.HelloResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GreeterMethodDescriptorSupplier("StreamResponse"))
                  .build();
          }
        }
     }
     return getStreamResponseMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.nmq.grpc.helloworld.HelloRequest,
      com.nmq.grpc.helloworld.HelloResponse> getClientStreamingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClientStreaming",
      requestType = com.nmq.grpc.helloworld.HelloRequest.class,
      responseType = com.nmq.grpc.helloworld.HelloResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.nmq.grpc.helloworld.HelloRequest,
      com.nmq.grpc.helloworld.HelloResponse> getClientStreamingMethod() {
    io.grpc.MethodDescriptor<com.nmq.grpc.helloworld.HelloRequest, com.nmq.grpc.helloworld.HelloResponse> getClientStreamingMethod;
    if ((getClientStreamingMethod = GreeterGrpc.getClientStreamingMethod) == null) {
      synchronized (GreeterGrpc.class) {
        if ((getClientStreamingMethod = GreeterGrpc.getClientStreamingMethod) == null) {
          GreeterGrpc.getClientStreamingMethod = getClientStreamingMethod = 
              io.grpc.MethodDescriptor.<com.nmq.grpc.helloworld.HelloRequest, com.nmq.grpc.helloworld.HelloResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.Greeter", "ClientStreaming"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.nmq.grpc.helloworld.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.nmq.grpc.helloworld.HelloResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GreeterMethodDescriptorSupplier("ClientStreaming"))
                  .build();
          }
        }
     }
     return getClientStreamingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.nmq.grpc.helloworld.HelloRequest,
      com.nmq.grpc.helloworld.HelloResponse> getBidirectionalStreamingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BidirectionalStreaming",
      requestType = com.nmq.grpc.helloworld.HelloRequest.class,
      responseType = com.nmq.grpc.helloworld.HelloResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.nmq.grpc.helloworld.HelloRequest,
      com.nmq.grpc.helloworld.HelloResponse> getBidirectionalStreamingMethod() {
    io.grpc.MethodDescriptor<com.nmq.grpc.helloworld.HelloRequest, com.nmq.grpc.helloworld.HelloResponse> getBidirectionalStreamingMethod;
    if ((getBidirectionalStreamingMethod = GreeterGrpc.getBidirectionalStreamingMethod) == null) {
      synchronized (GreeterGrpc.class) {
        if ((getBidirectionalStreamingMethod = GreeterGrpc.getBidirectionalStreamingMethod) == null) {
          GreeterGrpc.getBidirectionalStreamingMethod = getBidirectionalStreamingMethod = 
              io.grpc.MethodDescriptor.<com.nmq.grpc.helloworld.HelloRequest, com.nmq.grpc.helloworld.HelloResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.Greeter", "BidirectionalStreaming"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.nmq.grpc.helloworld.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.nmq.grpc.helloworld.HelloResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GreeterMethodDescriptorSupplier("BidirectionalStreaming"))
                  .build();
          }
        }
     }
     return getBidirectionalStreamingMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GreeterStub newStub(io.grpc.Channel channel) {
    return new GreeterStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GreeterBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GreeterBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GreeterFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GreeterFutureStub(channel);
  }

  /**
   */
  public static abstract class GreeterImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Seneds a greeting
     * </pre>
     */
    public void sayHello(com.nmq.grpc.helloworld.HelloRequest request,
        io.grpc.stub.StreamObserver<com.nmq.grpc.helloworld.HelloResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSayHelloMethod(), responseObserver);
    }

    /**
     */
    public void streamResponse(com.nmq.grpc.helloworld.HelloRequest request,
        io.grpc.stub.StreamObserver<com.nmq.grpc.helloworld.HelloResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getStreamResponseMethod(), responseObserver);
    }

    /**
     * <pre>
     * Client-streaming example
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.nmq.grpc.helloworld.HelloRequest> clientStreaming(
        io.grpc.stub.StreamObserver<com.nmq.grpc.helloworld.HelloResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getClientStreamingMethod(), responseObserver);
    }

    /**
     * <pre>
     * Bidirectional-streaming
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.nmq.grpc.helloworld.HelloRequest> bidirectionalStreaming(
        io.grpc.stub.StreamObserver<com.nmq.grpc.helloworld.HelloResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getBidirectionalStreamingMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSayHelloMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.nmq.grpc.helloworld.HelloRequest,
                com.nmq.grpc.helloworld.HelloResponse>(
                  this, METHODID_SAY_HELLO)))
          .addMethod(
            getStreamResponseMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.nmq.grpc.helloworld.HelloRequest,
                com.nmq.grpc.helloworld.HelloResponse>(
                  this, METHODID_STREAM_RESPONSE)))
          .addMethod(
            getClientStreamingMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                com.nmq.grpc.helloworld.HelloRequest,
                com.nmq.grpc.helloworld.HelloResponse>(
                  this, METHODID_CLIENT_STREAMING)))
          .addMethod(
            getBidirectionalStreamingMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.nmq.grpc.helloworld.HelloRequest,
                com.nmq.grpc.helloworld.HelloResponse>(
                  this, METHODID_BIDIRECTIONAL_STREAMING)))
          .build();
    }
  }

  /**
   */
  public static final class GreeterStub extends io.grpc.stub.AbstractStub<GreeterStub> {
    private GreeterStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreeterStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreeterStub(channel, callOptions);
    }

    /**
     * <pre>
     * Seneds a greeting
     * </pre>
     */
    public void sayHello(com.nmq.grpc.helloworld.HelloRequest request,
        io.grpc.stub.StreamObserver<com.nmq.grpc.helloworld.HelloResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSayHelloMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void streamResponse(com.nmq.grpc.helloworld.HelloRequest request,
        io.grpc.stub.StreamObserver<com.nmq.grpc.helloworld.HelloResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getStreamResponseMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Client-streaming example
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.nmq.grpc.helloworld.HelloRequest> clientStreaming(
        io.grpc.stub.StreamObserver<com.nmq.grpc.helloworld.HelloResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getClientStreamingMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * Bidirectional-streaming
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.nmq.grpc.helloworld.HelloRequest> bidirectionalStreaming(
        io.grpc.stub.StreamObserver<com.nmq.grpc.helloworld.HelloResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getBidirectionalStreamingMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class GreeterBlockingStub extends io.grpc.stub.AbstractStub<GreeterBlockingStub> {
    private GreeterBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreeterBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreeterBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Seneds a greeting
     * </pre>
     */
    public com.nmq.grpc.helloworld.HelloResponse sayHello(com.nmq.grpc.helloworld.HelloRequest request) {
      return blockingUnaryCall(
          getChannel(), getSayHelloMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.nmq.grpc.helloworld.HelloResponse> streamResponse(
        com.nmq.grpc.helloworld.HelloRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getStreamResponseMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GreeterFutureStub extends io.grpc.stub.AbstractStub<GreeterFutureStub> {
    private GreeterFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreeterFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreeterFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Seneds a greeting
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.nmq.grpc.helloworld.HelloResponse> sayHello(
        com.nmq.grpc.helloworld.HelloRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSayHelloMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SAY_HELLO = 0;
  private static final int METHODID_STREAM_RESPONSE = 1;
  private static final int METHODID_CLIENT_STREAMING = 2;
  private static final int METHODID_BIDIRECTIONAL_STREAMING = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GreeterImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GreeterImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAY_HELLO:
          serviceImpl.sayHello((com.nmq.grpc.helloworld.HelloRequest) request,
              (io.grpc.stub.StreamObserver<com.nmq.grpc.helloworld.HelloResponse>) responseObserver);
          break;
        case METHODID_STREAM_RESPONSE:
          serviceImpl.streamResponse((com.nmq.grpc.helloworld.HelloRequest) request,
              (io.grpc.stub.StreamObserver<com.nmq.grpc.helloworld.HelloResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CLIENT_STREAMING:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.clientStreaming(
              (io.grpc.stub.StreamObserver<com.nmq.grpc.helloworld.HelloResponse>) responseObserver);
        case METHODID_BIDIRECTIONAL_STREAMING:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.bidirectionalStreaming(
              (io.grpc.stub.StreamObserver<com.nmq.grpc.helloworld.HelloResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class GreeterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GreeterBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.nmq.grpc.helloworld.HelloWorldProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Greeter");
    }
  }

  private static final class GreeterFileDescriptorSupplier
      extends GreeterBaseDescriptorSupplier {
    GreeterFileDescriptorSupplier() {}
  }

  private static final class GreeterMethodDescriptorSupplier
      extends GreeterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GreeterMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GreeterGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GreeterFileDescriptorSupplier())
              .addMethod(getSayHelloMethod())
              .addMethod(getStreamResponseMethod())
              .addMethod(getClientStreamingMethod())
              .addMethod(getBidirectionalStreamingMethod())
              .build();
        }
      }
    }
    return result;
  }
}
