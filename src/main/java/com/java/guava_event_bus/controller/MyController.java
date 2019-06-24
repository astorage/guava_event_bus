package com.java.guava_event_bus.controller;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * springboot环境中是用guava_event_bus
 */
@Slf4j
@RestController
public class MyController {

    @Autowired
    private AsyncEventBus asyncEventBus;

    @Autowired
    private AsyncEventBus asyncEvent2Bus;

    @Autowired
    private EventBus eventBus;

    /**
     * 说明：
     * 1、同一个eventBus，不同的类型post，会分到对应类型的subscribe去。反过来说subscriber会订阅自己注册的事件总线上的指定类型的事件
     * 2、不同的eventBus互不干扰
     * 3、每个事件，自定义个对应的事件类型
     * 4、同一个事件，如果有多个订阅者。事件触发，所有的订阅者都会受到事件信息
     * @param message
     * @return
     */
    @RequestMapping("/testEventBus")
    public String testEventBus( String message) {
        long start = System.currentTimeMillis();
        asyncEventBus.post(message);
        asyncEventBus.post(2);
        asyncEvent2Bus.post(message);
        long end = System.currentTimeMillis();
        log.info("async 耗时：{}", (end - start));
        return "{}";
    }


    @RequestMapping("/testSyncEventBus")
    public String testSyncEventBus( String message) {
        long start = System.currentTimeMillis();
        eventBus.post(message);
        eventBus.post(2);
        eventBus.post("sdfsdfksdfjk");
        long end = System.currentTimeMillis();
        log.info("sync 耗时：{}", (end - start));
        return "{}";
    }
}
