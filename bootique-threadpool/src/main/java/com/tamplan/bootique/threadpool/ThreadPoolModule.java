package com.tamplan.bootique.threadpool;

import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.bootique.ConfigModule;
import io.bootique.config.ConfigurationFactory;
import io.bootique.log.BootLogger;
import io.bootique.shutdown.ShutdownManager;
import io.bootique.type.TypeRef;

import java.util.Map;

public class ThreadPoolModule extends ConfigModule {

    @Provides
    @Singleton
    public ThreadPoolRepository createThreadPoolRepository(ConfigurationFactory configurationFactory, ShutdownManager shutdownManager, BootLogger logger) {
        logger.trace(()->"Instantiating thread pool repository");

        final ThreadPoolRepository threadPoolRepository = new ThreadPoolRepository();

        Map<String, BaseThreadPoolFactory> factories = configurationFactory.config(new TypeRef<Map<String, BaseThreadPoolFactory>>() {
        }, configPrefix + ".configs");

        factories.forEach((name, factory) -> {
            threadPoolRepository.addThreadPool(name, factory.create(name));
        });

        return threadPoolRepository;
    }
}
