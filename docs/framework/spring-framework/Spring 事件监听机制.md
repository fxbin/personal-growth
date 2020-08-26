## 事件监听机制

* 事件的监听机制更多的用于异步通知以及监控和异常处理
* Java提供了实现事件监听机制的两个基础类
    * `java.util.EventObject`: 自定义事件类型扩展
    * `java.util.EventListener`: 事件的监听器扩展
    
## 自定义事件实现

[自定义事件实现](https://github.com/fxbin/myself-wiki/tree/master/src/main/java/cn/fxbin/record/study/event)

## Spring 容器内的事件监听机制

> Spring的`ApplicationContext`容器内部中的所有事件类型均继承自`org.springframework.context.AppliationEvent`，容器中的所有
> 监听器都实现`org.springframework.context.ApplicationListener`接口，并且以bean的形式注册在容器中。
> 一旦在容器内发布`ApplicationEvent`及其子类型的事件，注册到容器的`ApplicationListener`就会对这些事件进行处理。

* `ApplicationEvent`: 继承自EventObject
* `ContextClosedEvent`: 表示容器在即将关闭时发布的事件类型
* `ContextRefreshedEvent`: 表示容器在初始化或者刷新的时候发布的事件类型
* `ApplicationEventPublisher`: 
* `ApplicationContext`: 继承自 `ApplicationEventPublisher` 接口，提供 `void publishEvent(ApplicationEvent event)` 方法定义，可充当发布者角色
* `ApplicationEventMulticaster`: `ApplicationContext` 将事件的发布以及监听器的管理工作委托给 `ApplicationEventMulticaster` 接口的实现类, 
    在容器启动时，会检查容器内是否存在名为 `applicationEventMulticaster` 的 `ApplicationEventMulticaster` 对象实例。如果有就使用其提供的实现，
    没有就默认初始化一个 `SimpleApplicationEventMulticaster` 作为实现。
