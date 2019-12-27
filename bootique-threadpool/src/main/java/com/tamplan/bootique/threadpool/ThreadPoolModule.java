package com.tamplan.bootique.threadpool;

import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.bootique.ConfigModule;
import io.bootique.config.ConfigurationFactory;
import io.bootique.log.BootLogger;
import io.bootique.shutdown.ShutdownManager;

public class ThreadPoolModule extends ConfigModule {

    @Provides
    @Singleton
    public ThreadPoolRepository createThreadPoolRepository(ConfigurationFactory configurationFactory, ShutdownManager shutdownManager, BootLogger logger) {
        logger.trace(()->"Instantiating thread pool repository");

        ThreadPoolRepository threadPoolRepository = configurationFactory.config(ThreadPoolRepositoryFactory.class, configPrefix).createThreadPoolRepository();

        shutdownManager.addShutdownHook(()->{
            logger.trace(()->"Shutting down thread pools");
            threadPoolRepository.shutdown();
        });

        return threadPoolRepository;
    }
}
