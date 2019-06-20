package com.java.guava_event_bus.controller;

import com.google.common.eventbus.AsyncEventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private AsyncEventBus asyncEventBus;

    @Autowired
    private AsyncEventBus asyncEvent2Bus;

    /**
     * 说明：
     * 1、同一个eventBus，不同的类型post，会分到对应类型的subscribe去。反过来说subscriber会订阅自己注册的事件总线上的指定类型的事件
     * 2、不同的eventBus互不干扰
     * @param message
     * @return
     */
    @RequestMapping("/testEventBus")
    public String testEventBus( String message) {
        asyncEventBus.post(message);
        asyncEventBus.post(2);
        asyncEvent2Bus.post(message);
        return "{}";
    }
}
