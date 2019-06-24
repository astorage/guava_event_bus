package com.java.guava_event_bus.service.sync;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class MySyncListener {

    @Autowired
    private EventBus eventBus;

    @PostConstruct
    public void initialize() {
        eventBus.register(this);
    }

    @Subscribe
    @AllowConcurrentEvents
    public void sayHello(String param){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("param: {}", param);
    }
}
