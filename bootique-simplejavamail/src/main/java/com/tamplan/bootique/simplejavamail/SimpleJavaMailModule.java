package com.tamplan.bootique.simplejavamail;

import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.bootique.ConfigModule;
import io.bootique.config.ConfigurationFactory;

public class SimpleJavaMailModule extends ConfigModule {

    @Provides
    @Singleton
    public MailerRepository createMailerRepository(ConfigurationFactory configurationFactory) {
        return configurationFactory.config(MailerRepositoryFactory.class, configPrefix).createMailerRepository();
    }
}
