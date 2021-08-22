package com.tamplan.bootique.jdbi;


import io.bootique.ConfigModule;
import io.bootique.config.ConfigurationFactory;
import io.bootique.di.Provides;
import io.bootique.jdbc.DataSourceFactory;
import io.bootique.type.TypeRef;

import javax.inject.Singleton;
import java.util.Map;

public class JdbiModule extends ConfigModule {

    @Provides
    @Singleton
    public JdbiFactory getJdbiFactory(ConfigurationFactory configurationFactory, DataSourceFactory dataSourceFactory) {
        Map<String, JdbiProperties> configs = configurationFactory
                .config(new TypeRef<Map<String, JdbiProperties>>() {
                }, configPrefix);

        return new JdbiFactory(dataSourceFactory, configs);
    }


}
