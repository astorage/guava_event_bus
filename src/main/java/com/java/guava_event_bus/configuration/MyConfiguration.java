package com.java.guava_event_bus.configuration;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

@Configuration
public class MyConfiguration {

    @Autowired
    private ExecutorService executorService;

    @Bean
    public ExecutorService getThreadPool() {
        int corePoolSize = 10;
        int maximumPoolSize = 30;
        long keepAliveTime = 60;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
        ExecutorService executorService = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        return executorService;
    }


    @Bean
    @Primary
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        return new ThreadPoolTaskExecutor();
    }


    /**
     * 同步eventBus
     * @return
     */
    @Bean
    public EventBus eventBus() {
        EventBus eventBus = new EventBus("eventBus");
        return eventBus;
    }

    /**
     * 异步eventBus ,名称为default
     * @param taskExecutor
     * @return
     */
    @Bean(name = "asyncEventBusChoose")
    public AsyncEventBus asyncEventBusChoose(Executor taskExecutor) {
        return new AsyncEventBus(taskExecutor);
    }

    /**
     * 异步eventBus
     * @return
     */
    @Bean(name = "asyncEventBus")
    public AsyncEventBus asyncEventBus() {
        return new AsyncEventBus("asyncEventBus",executorService);
    }

    /**
     * 异步eventBus
     * @return
     */
    @Bean(name = "asyncEvent2Bus")
    public AsyncEventBus asyncEvent2Bus() {
        return new AsyncEventBus("asyncEvent2Bus",executorService);
    }



}
