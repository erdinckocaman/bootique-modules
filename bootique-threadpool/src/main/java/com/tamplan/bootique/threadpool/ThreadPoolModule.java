package com.tamplan.bootique.threadpool;

import io.bootique.ConfigModule;
import io.bootique.config.ConfigurationFactory;
import io.bootique.di.Provides;
import io.bootique.log.BootLogger;
import io.bootique.shutdown.ShutdownManager;
import io.bootique.type.TypeRef;

import javax.inject.Singleton;
import java.util.Map;

public class ThreadPoolModule extends ConfigModule {

    @Provides
    @Singleton
    public ThreadPoolRepository createThreadPoolRepository(ConfigurationFactory configurationFactory, ShutdownManager shutdownManager, BootLogger logger) {
        logger.trace(()->"Instantiating thread pool repository");

        final ThreadPoolRepository threadPoolRepository = new ThreadPoolRepository();

        Map<String, BaseThreadPoolFactory> factories = configurationFactory.config(new TypeRef<Map<String, BaseThreadPoolFactory>>() {
        }, configPrefix + ".pools");

        factories.forEach((name, factory) -> {
            threadPoolRepository.addThreadPool(name, factory.create(name));
        });

        return threadPoolRepository;
    }
}
