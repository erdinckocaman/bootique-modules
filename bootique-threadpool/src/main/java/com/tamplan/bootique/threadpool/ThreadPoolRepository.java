package com.tamplan.bootique.threadpool;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;

public class ThreadPoolRepository {

    private Map<String, ExecutorService> executors;

    public ThreadPoolRepository() {
        executors = new HashMap<>();
    }

    public void shutdown() {
        executors.values().forEach(executorService -> {
            executorService.shutdownNow();
        });
    }

    protected void addThreadPool(String name, ExecutorService threadPool) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(threadPool);

        executors.put(name, threadPool);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                threadPool.shutdownNow();
            }
        });
    }

    public ExecutorService getExecutor(String name) {
        Objects.requireNonNull(name);

        return executors.get(name);
    }
}
