package com.tamplan.bootique.wicket;

import org.apache.wicket.Application;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.webapp.WebAppContext;

import javax.servlet.DispatcherType;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

public class WicketApplicationFactory {

    private CustomWicketFilter wicketFilter;

    private static class CustomWicketFilter extends WicketFilter {

        private final BeanLookup beanLookup;

        public CustomWicketFilter(BeanLookup beanLookup) {
            this.beanLookup = beanLookup;
        }

        @Override
        public void init(boolean isServlet, FilterConfig filterConfig) throws ServletException {
            super.init(isServlet, filterConfig);
            this.getApplication().setMetaData(BeanLookupMetaDataKey.getInstance(), beanLookup);
        }

        @Override
        public WebApplication getApplication() {
            return super.getApplication();
        }

    }

    private String contextPath;
    private String webContentFolder;
    private RuntimeConfigurationType mode;

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public void setWebContentFolder(String webContentFolder) {
        this.webContentFolder = webContentFolder;
    }

    public void setMode(RuntimeConfigurationType mode) {
        this.mode = mode;
    }

    public WebAppContext createWebAppContext(
            Server jettyServer,
            final ServletContext servletContext,
            final Class<? extends WebApplication> webApplicationClass,
            BeanLookup beanLookup) {

        validateContextPath(contextPath);
        validateWebContentFolder(webContentFolder);

        // setting web context
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setServer(jettyServer);
        webAppContext.setContextPath(contextPath);
        webAppContext.setWar(webContentFolder);


        wicketFilter = new CustomWicketFilter(beanLookup);

        wicketFilter.setFilterPath(contextPath);

        FilterHolder wicketFilterHolder = new FilterHolder(wicketFilter);

        wicketFilterHolder.setInitParameter("applicationClassName", webApplicationClass.getName());
        wicketFilterHolder.setName("wicket-filter");

        webAppContext.addFilter(wicketFilterHolder, "/*", EnumSet.of(DispatcherType.REQUEST, DispatcherType.ASYNC));

        if ( mode == null ) {
            mode = RuntimeConfigurationType.DEVELOPMENT;
        }

        System.setProperty("wicket." + Application.CONFIGURATION, mode.name());

        return webAppContext;
    }

    public WebApplication getWebApplication() {
        return wicketFilter.getApplication();
    }

    private void validateWebContentFolder(String webContentFolder) {

    }

    private void validateContextPath(String contextPath) {
    }

}
