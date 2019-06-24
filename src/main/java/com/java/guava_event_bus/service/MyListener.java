package com.java.guava_event_bus.service;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class MyListener {

    @Autowired
    private AsyncEventBus asyncEventBus;

    @PostConstruct
    public void initialize() {
        asyncEventBus.register(this);
    }

    @Subscribe
    @AllowConcurrentEvents //线程安全，多线程的情况下
   public void sayHello(String param){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("param: {}", param);
    }
}
