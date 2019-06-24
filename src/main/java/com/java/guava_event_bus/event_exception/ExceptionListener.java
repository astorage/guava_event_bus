package com.java.guava_event_bus.event_exception;

import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionListener {

    @Subscribe
    public void m1(final String event){
        log.info("Received event [{}] and will take m1", event);
    }
    @Subscribe
    public void m2(final String event){
        log.info("Received event [{}] and will take m2", event);
    }
    @Subscribe
    public void m3(final String event){
        throw new RuntimeException();
    }

}
