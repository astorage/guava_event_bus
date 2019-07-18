# guava_event_bus

 EventBus解决的问题。 它不是通用的消息系统，也不是用来做进程间的通信的，而是在进程内，用于解耦两段直接调用的业务逻辑；

## 依赖
    compile group: 'com.google.guava', name: 'guava', version: '28.0-jre'


##注意区别同步事件总线和异步事件总线差异
    
   1、同步事件总线EventBus
   
    2019-06-20 16:07:44.826  INFO 10736 --- [nio-8080-exec-2] c.j.g.service.sync.MySyncListener        : param: hello world
    2019-06-20 16:07:45.827  INFO 10736 --- [nio-8080-exec-2] c.j.g.service.sync.MySyncListener        : param: sdfsdfksdfjk
    2019-06-20 16:07:45.827  INFO 10736 --- [nio-8080-exec-2] c.j.g.controller.MyController            : sync 耗时：4005
    
   2、异步事件总线AsyncEventBus
    
    2019-06-20 16:07:31.201  INFO 10736 --- [pool-1-thread-2] c.j.g.service.MySecondListener           : param: hello world
    2019-06-20 16:07:31.202  INFO 10736 --- [pool-1-thread-4] c.j.g.service.My4thListener              : param: hello world
    2019-06-20 16:07:31.202  INFO 10736 --- [pool-1-thread-3] c.j.g.service.My3thListener              : param: 2
    2019-06-20 16:07:31.202  INFO 10736 --- [nio-8080-exec-1] c.j.g.controller.MyController            : async 耗时：3
    2019-06-20 16:07:32.201  INFO 10736 --- [pool-1-thread-1] c.j.guava_event_bus.service.MyListener   : param: hello world
    
   说明：
   
   1、使用的线程差异，同步事件总线使用的nio线程；异步事件总线线程池。
   
   2、耗时差异，同步事件会等待[所有的]订阅者处理完成；异步事件总线不会等待订阅者完成。
         
   eventBus功能探索      
   1、子类订阅者注册到时间总线，当向总线发送消息，父类的订阅者也会处理事件消息
   
   2、不同类型的参数。eventBus会根据Listener的参数类型的不同，分别向不同的Subscribe发送不同的消息
   
   3、当作为参数的event之间有继承关系时，使用eventBus发送消息，eventt的父类listener也会对此消息进行处理。
   
   订阅者是按方法的粒度，之说以这样讲，应为@Subscribe应用在方法上
   
   
    EventBus：核心类，代表了一个事件总线。Publish事件也由它发起。
    AsyncEventBus：在分发事件的时候，将其压入一个全局队列的异步分发模式。
    Subscriber：对某个事件的处理器抽象，封装了事件的订阅者以及处理器，并负责事件处理（该类的类名及其语义有些不明确，后续会谈到）。
    SubscriberRegistry：订阅注册表，它用于存储Subscriber跟Event的对应关系，以便于EventBus在publish一个事件时，可以找到它对应的Subscriber。
    Dispatcher：事件分发器，它定义了事件的分发策略。
    @Subscribe：用于标识事件处理器的注解，当EventBus publish一个事件后，相应的Subscriber将会得到通知并执行事件处理器。
    @AllowConcurrentEvents：该注解跟@Subscribe一同使用，标识该订阅者的处理方法为线程安全的，该注解还用于标识该方法将可能会被EventBus在多线程环境下执行。
    DeadEvent：死信（没有订阅者关注的事件）对象。
    SubscribeExceptionHandler：订阅者抛出异常的处理器。
    SubscribeExceptionContext：订阅者抛出异常的上下文对象


   讲解源码的一篇博客 https://blog.csdn.net/yanghua_kobe/article/details/46317297
    
    
   线程池可以返回Future<T>
   
   测试yixia 
   