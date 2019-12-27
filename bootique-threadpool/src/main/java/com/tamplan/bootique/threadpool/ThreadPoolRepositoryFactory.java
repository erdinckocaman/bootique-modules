package com.tamplan.bootique.threadpool;

import java.util.Map;
import java.util.concurrent.*;

public class ThreadPoolRepositoryFactory {

    private Map<String, ThreadPoolConfiguration> configs;

    public void setConfigs(Map<String, ThreadPoolConfiguration> configs) {
        this.configs = configs;
    }

    public ThreadPoolRepository createThreadPoolRepository() {
        ThreadPoolRepository threadPoolRepository = new ThreadPoolRepository();

        configs.forEach((name, config)->{
            threadPoolRepository.addThreadPool(name, buildThreadPool(config));
        });

        return threadPoolRepository;
    }

    private ExecutorService buildThreadPool(ThreadPoolConfiguration config) {
        return new ThreadPoolExecutor(
                config.getMinThreads(),
                config.getMaxThreads(),
                60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                task -> {
                    Thread thread = new Thread(task);

                    thread.setDaemon(true);

                    return thread;
                });
    }
}
