package com.tamplan.bootique.wicket;

import com.google.inject.Binder;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.bootique.ConfigModule;
import io.bootique.config.ConfigurationFactory;
import io.bootique.jetty.JettyModule;
import org.apache.wicket.protocol.http.WebApplication;

public class WicketModule extends ConfigModule {

    public static WicketModuleExtender extend(Binder binder) {
        return new WicketModuleExtender(binder);
    }

    @Override
    public void configure(Binder binder) {
        JettyModule.extend(binder).addContextHandlerExtender(WicketServletContextHandlerExtender.class);
    }

    @Provides
    @Singleton
    public WicketServletContextHandlerExtender getWicketServletContextHandlerExtender(
            Injector injector,
            ConfigurationFactory configurationFactory,
            Class<? extends  WebApplication> webApplicationClass) {

        return new WicketServletContextHandlerExtender(
                injector,
                configurationFactory,
                configPrefix,
                webApplicationClass);
    }
}
