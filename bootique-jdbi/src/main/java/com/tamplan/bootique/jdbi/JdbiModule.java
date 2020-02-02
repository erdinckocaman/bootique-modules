package com.tamplan.bootique.jdbi;

import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.bootique.ConfigModule;
import io.bootique.config.ConfigurationFactory;
import io.bootique.jdbc.DataSourceFactory;
import io.bootique.type.TypeRef;

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
