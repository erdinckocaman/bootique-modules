package com.tamplan.bootique.threadpool;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@JsonTypeName("plain")
public class PlainThreadPoolFactory extends BaseThreadPoolFactory {

    private int minThreads;
    private int maxThreads;

    public void setMinThreads(int minThreads) {
        //Preconditions.checkArgument(minThreads > 0);
        this.minThreads = minThreads;
    }

    public void setMaxThreads(int maxThreads) {
        //Preconditions.checkArgument(maxThreads > 0);
        this.maxThreads = maxThreads;
    }

    @Override
    public ExecutorService create(String name) {
        return new ThreadPoolExecutor(
                minThreads,
                maxThreads,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                task -> {
                    Thread thread = new Thread(task);

                    thread.setDaemon(true);

                    thread.setName("BQ Thread Pool-" + name + "#" + thread.getId());

                    return thread;
                });
    }
}
