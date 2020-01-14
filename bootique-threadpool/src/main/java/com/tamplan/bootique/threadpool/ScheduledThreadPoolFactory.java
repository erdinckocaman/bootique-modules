package com.tamplan.bootique.threadpool;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@JsonTypeName("scheduled")
public class ScheduledThreadPoolFactory extends BaseThreadPoolFactory {

    int corePoolSize;

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    @Override
    public ExecutorService create(String name) {
        return  new ScheduledThreadPoolExecutor(corePoolSize,
                task -> {
                    Thread thread = new Thread(task, "BQ Scheduled Thread Pool#" + name);

                    thread.setDaemon(true);

                    return thread;
                });
    }
}
