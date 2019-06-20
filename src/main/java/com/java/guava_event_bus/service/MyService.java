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
public class MyService {

    @Autowired
    private AsyncEventBus asyncEventBus;

    @PostConstruct
    public void initialize() {
        asyncEventBus.register(this);
    }

    @Subscribe
    @AllowConcurrentEvents
   public void sayHello(String param){
        log.info("param: {}", param);
   }
}
