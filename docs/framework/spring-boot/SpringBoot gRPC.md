## gRPC 是什么

> gRPC 是一个高性能、开源和通用的 RPC 框架，面向移动和 HTTP/2 设计。
> gRPC 基于 HTTP/2 标准设计，带来诸如双向流、流控、头部压缩、单 TCP 连接上的多复用请求等特。这些特性使得其在移动设备上表现更好，更省电和节省空间占用。

> 在 gRPC 里，客户端应用可以像调用本地对象一样直接调用另一台不同的机器上服务端应用的方法，使得我们能够更容易地创建分布式应用和服务。
 
> 与许多 RPC 框架类似，gRPC 也是基于以下理念： 
> * 定义一个服务，指定其能够被远程调用的方法（包含参数和返回类型）。
> * 在服务端实现这个接口，并运行一个 gRPC 服务器来处理客户端调用。
> * 在客户端拥有一个存根 Stub 能够像服务端一样的方法。
> ![gRPC图](images/gRPC图.jpg)

## protocol buffers

> gRPC 默认使用 [protocol buffers](https://github.com/protocolbuffers) 来描述服务接口和有效载荷消息结构，如果有需要的话，可以使用其他替代方案。
> 这是 Google 开源的一套成熟的结构数据序列化机制。

> [protocol buffers docs](https://developers.google.com/protocol-buffers/docs/proto3)

> [gRPC SpringBoot Demo](https://github.com/fxbin/myself-wiki/tree/master/code-modules/spring-boot-knowledge/grpc-demo)

## grpc-spring-boot-starter

[官方文档](https://yidongnan.github.io/grpc-spring-boot-starter/zh-CN/)

[DEMO](https://github.com/fxbin/myself-wiki/tree/master/code-modules/spring-boot-knowledge/grpc-starter-demo)

---
参考文档：

* [gRPC 官方文档中文版V1.0](http://doc.oschina.net/grpc?t=56831)