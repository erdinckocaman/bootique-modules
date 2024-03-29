package com.tamplan.bootique.wicket;

import com.tamplan.bootique.wicket.impl.DefaultBeanLookup;
import io.bootique.config.ConfigurationFactory;
import io.bootique.di.Injector;
import io.bootique.jetty.server.ServletContextHandlerExtender;
import org.apache.wicket.protocol.http.WebApplication;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.gzip.GzipHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.webapp.WebAppContext;

import java.util.Objects;

public class WicketServletContextHandlerExtender implements  ServletContextHandlerExtender {

    private final ConfigurationFactory configurationFactory;
    private final String configPrefix;
    private final Class<? extends WebApplication> webApplicationClass;
    private final Injector injector;
    private WicketApplicationFactory wicketApplicationFactory;

    public WicketServletContextHandlerExtender(
            Injector injector,
            ConfigurationFactory configurationFactory,
            String configPrefix,
            Class<? extends WebApplication> webApplicationClass) {

        Objects.requireNonNull(configurationFactory);
        Objects.requireNonNull(configPrefix);
        Objects.requireNonNull(webApplicationClass);

        this.configurationFactory = configurationFactory;
        this.configPrefix = configPrefix;
        this.webApplicationClass = webApplicationClass;
        this.injector = injector;

    }

    @Override
    public void onHandlerInstalled(ServletContextHandler servletContextHandler) {
        Server jettyServer = servletContextHandler.getServer();

        wicketApplicationFactory = configurationFactory.config(WicketApplicationFactory.class, configPrefix);

        WebAppContext wicketAppContext = wicketApplicationFactory.createWebAppContext(
                jettyServer,
                servletContextHandler.getServletContext(),
                webApplicationClass,
                new DefaultBeanLookup(injector));

        wicketAppContext.setGzipHandler(new GzipHandler());

        HandlerCollection handlerCollection = new HandlerCollection(jettyServer.getHandler());

        handlerCollection.addHandler(wicketAppContext);

        jettyServer.setHandler(handlerCollection);

        handlerCollection.setServer(jettyServer);
    }


    public WebApplication getWebApplication() {
        return wicketApplicationFactory.getWebApplication();
    }
}
