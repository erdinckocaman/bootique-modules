package com.tamplan.bootique.wicket;

import org.apache.wicket.Application;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.webapp.WebAppContext;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import java.util.EnumSet;

public class WicketApplicationFactory {

    private CustomWicketFilter wicketFilter;

    private static class CustomWicketFilter extends WicketFilter {
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

    public WebAppContext createWebAppContext(Server jettyServer, final ServletContext servletContext, final Class<? extends WebApplication> webApplicationClass) {
        validateContextPath(contextPath);
        validateWebContentFolder(webContentFolder);

        // setting web context
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setServer(jettyServer);
        webAppContext.setContextPath(contextPath);
        webAppContext.setWar(webContentFolder);

        wicketFilter = new CustomWicketFilter();

        wicketFilter.setFilterPath("");

        FilterHolder wicketFilterHolder = new FilterHolder(wicketFilter);

        wicketFilterHolder.setInitParameter("applicationClassName", webApplicationClass.getName());
        wicketFilterHolder.setName("wicket-filter");

        webAppContext.addFilter(wicketFilterHolder, "/*", EnumSet.allOf(DispatcherType.class));

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
