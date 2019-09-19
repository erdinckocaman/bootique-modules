package com.tamplan.bootique.wicket;

import org.apache.wicket.Application;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.webapp.WebAppContext;

import javax.servlet.DispatcherType;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.Vector;

public class WicketApplicationFactory {

    private static class CustomerWicketFilter extends WicketFilter {
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

    public WebAppContext createWebAppContext(Server jettyServer) {
        validateContextPath(contextPath);
        validateWebContentFolder(webContentFolder);

        // setting web context
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setServer(jettyServer);
        webAppContext.setContextPath(contextPath);
        webAppContext.setWar(webContentFolder);

        return webAppContext;
    }


    private void validateWebContentFolder(String webContentFolder) {

    }

    private void validateContextPath(String contextPath) {
    }

    public WebApplication initFilter(final ServletHandler servletHandler, final ServletContext servletContext, final Class<? extends WebApplication> webApplicationClass) {
        // setting wicket filter
        CustomerWicketFilter wicketFilter = new CustomerWicketFilter();

        wicketFilter.setFilterPath("/*");


        if ( mode == null ) {
            mode = RuntimeConfigurationType.DEVELOPMENT;
        }

        System.setProperty("wicket." + Application.CONFIGURATION, mode.name());

        try {
            wicketFilter.init(new FilterConfig() {
                @Override
                public String getFilterName() {
                    return "wicket-filter";
                }

                @Override
                public ServletContext getServletContext() {
                    return servletContext;
                }

                @Override
                public String getInitParameter(String name) {
                    if ( "applicationClassName".equals(name)) {
                        return webApplicationClass.getName();
                    }else {
                        return null;
                    }
                }

                @Override
                public Enumeration<String> getInitParameterNames() {
                    Vector<String> params = new Vector<>();
                    params.add("applicationClassName");

                    return params.elements();
                }
            });
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }

        FilterHolder wicketFilterHolder = new FilterHolder(wicketFilter);

        servletHandler.addFilterWithMapping(
                wicketFilterHolder,
                "/*",
                EnumSet.allOf(DispatcherType.class));

        WebApplication application = wicketFilter.getApplication();

        return application;
    }
}
