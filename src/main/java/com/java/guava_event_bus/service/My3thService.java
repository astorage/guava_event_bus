package com.java.guava_event_bus.service;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class My3thService {

    @Autowired
    private AsyncEventBus asyncEventBus;

    @PostConstruct
    public void initialize() {
        asyncEventBus.register(this);
    }

    @Subscribe
   public void sayHello(Integer param){
        log.info("param: {}", param);
   }
}
