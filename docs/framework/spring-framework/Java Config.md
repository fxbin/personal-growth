## Java Config

### 是什么

> java config是指基于java配置的spring。传统的Spring一般都是基本xml配置的，后来spring3.0新增了许多java config的注解，特别是spring boot，基本都是清一色的java config。

### 为什么

* JavaConfig 使用纯Java代码的方式，可以充分利用复用、继承、多态等特性
* 有更多的自由度来控制Bean的初始化，注入以及复杂对象的构建
* 由于只用到Java，只需有IDE就可以尽情的掌控配置逻辑

### 怎么实现

[Spring Java Config 示例代码](https://github.com/fxbin/personal-growth/tree/master/src/main/java/cn/fxbin/record/study/spring/javaconfig)

### BeanFactory注册 Bean

* `org.springframework.context.annotation.AnnotationConfigApplicationContext`

`org.springframework.context.support.AbstractApplicationContext.refresh` 方法

```java
public abstract class AbstractApplicationContext extends DefaultResourceLoader
		implements ConfigurableApplicationContext {


    // ...


    public void refresh() throws BeansException, IllegalStateException {
        synchronized (this.startupShutdownMonitor) {
            // Prepare this context for refreshing.
            // 准备刷新上下文环境
            prepareRefresh();
    
            // Tell the subclass to refresh the internal bean factory.
            // 初始化 BeanFactory, 并进行 XML 文件读取
            ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();
    
            // Prepare the bean factory for use in this context.
            // 对BeanFactory 在当前上下文中进行功能填充
            prepareBeanFactory(beanFactory);
    
            try {
                // Allows post-processing of the bean factory in context subclasses.
                // 子类覆盖方法做额外处理
                postProcessBeanFactory(beanFactory);
    
                // Invoke factory processors registered as beans in the context.
                // 激活 N 多 BeanFactory 处理器
                invokeBeanFactoryPostProcessors(beanFactory);
    
                // Register bean processors that intercept bean creation.
                // 注册拦截Bean创建的Bean 处理器
                registerBeanPostProcessors(beanFactory);
    
                // Initialize message source for this context.
                // 国际化
                initMessageSource();
    
                // Initialize event multicaster for this context.
                // 初始化应用消息广播器
                initApplicationEventMulticaster();
    
                // Initialize other special beans in specific context subclasses.
                // 在特定上下文初始化其它特殊的Bean
                onRefresh();
    
                // Check for listener beans and register them.
                // 检查监听器Bean并且注册
                registerListeners();
    
                // Instantiate all remaining (non-lazy-init) singletons.
                // 实例化所有非延迟加载的实例
                finishBeanFactoryInitialization(beanFactory);
    
                // Last step: publish corresponding event.
                // 发布通信事件
                finishRefresh();
            }
    
            catch (BeansException ex) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Exception encountered during context initialization - " +
                            "cancelling refresh attempt: " + ex);
                }
    
                // Destroy already created singletons to avoid dangling resources.
                destroyBeans();
    
                // Reset 'active' flag.
                cancelRefresh(ex);
    
                // Propagate exception to caller.
                throw ex;
            }
    
            finally {
                // Reset common introspection caches in Spring's core, since we
                // might not ever need metadata for singleton beans anymore...
                resetCommonCaches();
            }
        }
    }

    // ...

}

```

* `org.springframework.context.annotation.ConfigurationClassPostProcessor`

    * Spring 容器初始化时注册 `ConfigurationClassPostProcessor`
    * Spring 容器初始化执行 `refresh()` 方法中调用 `ConfigurationClassPostProcessor`
    * `ConfigurationClassPostProcessor` 处理器借助 `ConfigurationClassParser` 完成配置类解析
    * `ConfigurationClassParser` 配置内解析过程中完成嵌套的 `MemberClass`、`@PropertySource` 注解、`@ComponentScan` 注解（扫描package 下的所有Class 并进行迭代解析，
    主要是 `@Component`组件解析及注册）、`@ImportResource`、`@Bean` 等处理
    * 完成 `@Bean` 注册，`@ImportResource` 指定 bean 的注册以及 `@Import` 的 bean 注册
    * 有`@Bean`注解的方法在解析的时候作为`ConfigurationClass`的一个属性，最后还是会转换成`BeanDefinition`进行处理， 而实例化的时候会作为一个工厂方法进行Bean的创建