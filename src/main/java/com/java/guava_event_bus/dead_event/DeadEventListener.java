package com.java.guava_event_bus.dead_event;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;


public class DeadEventListener {
    @Subscribe
    public void handle(DeadEvent event){
        //获取事件源
        System.out.println(event.getSource());//DEAD-EVENT-BUS
        //获取事件
        System.out.println(event.getEvent());//DeadEventListener event
    }

}
