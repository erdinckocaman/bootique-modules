package com.tamplan.bootique.simplejavamail;


import io.bootique.ConfigModule;
import io.bootique.config.ConfigurationFactory;
import io.bootique.di.Provides;

import javax.inject.Singleton;

public class SimpleJavaMailModule extends ConfigModule {

    @Provides
    @Singleton
    public MailerRepository createMailerRepository(ConfigurationFactory configurationFactory) {
        return configurationFactory.config(MailerRepositoryFactory.class, configPrefix).createMailerRepository();
    }
}
