package cn.fxbin.record.grpc.userservice.rpc;

import cn.fxbin.record.grpc.userservice.api.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * UserServiceGrpcImpl
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/29 11:02
 */
@GrpcService
public class UserServiceGrpcImpl extends UserServiceGrpc.UserServiceImplBase {

    /**
     * @param request
     * @param responseObserver
     */
    @Override
    public void get(UserGetRequest request, StreamObserver<UserGetResponse> responseObserver) {
        // 创建响应对象
        UserGetResponse.Builder builder = UserGetResponse.newBuilder();
        builder.setId(request.getId())
                .setName("没有昵称：" + request.getId())
                .setGender(request.getId() % 2 + 1);
        // 返回响应
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }

    /**
     * @param request
     * @param responseObserver
     */
    @Override
    public void create(UserCreateRequest request, StreamObserver<UserCreateResponse> responseObserver) {
        // 创建响应对象
        UserCreateResponse.Builder builder = UserCreateResponse.newBuilder();
        builder.setId((int) (System.currentTimeMillis() / 1000));
        // 返回响应
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
}
