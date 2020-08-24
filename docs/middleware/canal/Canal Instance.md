## CanalInstance

![CanalInstance Class](images/CanalInstance%20Class.png)

从源码中不难看出，CanalInstance 包含了以下几个核心组件

### 核心组件

* EventParser
> 负责解析 binlog日志，其职责就是根据 binlog 的存储格式将有效数据提取出来
* EventSink
> 链接parse和store的桥接器，进行数据过滤，加工，分发的工作。核心接口为CanalEventSink
* EventStore
> 用来存储经 canal 转换的数据，被 Canal Client 进行消费的数据，目前 Canal 只提供了基于内存的存储实现。核心接口为CanalEventStore
* MetaManager
> 增量订阅&消费信息管理器，核心接口为CanalMetaManager，主要用于记录canal消费到的mysql binlog的位置
* ConfigMQConfig 
> mq的配置
>

### Instance Module 划分

![CanalInstance Modules](images/CanalInstance%20Modules.png)

* Core 模块中，定义了 `CanalInstance`、`CanalInstanceGenerator` 接口，抽象类子类 `AbstractCanalInstance`，以及mq的配置实体 `CanalMQConfig` , `CanalInstanceGenerator` 通过 destination 产生特定的 `CanalInstance`
* Manager 模块中，提供了基于Manager配置方式的 `CanalInstanceWithManager` 实现，CanalInstance实例会根据远程配置中心的内容来创建。
* Spring 模块，提供了基于spring配置方式的 `CanalInstanceWithSpring` 实现，CanalInstance实例的创建，通过spring配置文件来创建。

* CanalInstance UML

    ![CanalInstance UML](images/CanalInstance%20UML.png)

    * `CanalLifeCycle`: 所有 canal 模块的生命周期接口