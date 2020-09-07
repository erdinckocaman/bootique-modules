package com.tamplan.bootique.examples.wicket;

import com.tamplan.bootique.examples.services.SampleService;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

import javax.inject.Inject;

public class WicketApp extends WebApplication {

    @Inject
    SampleService sampleService;

    public WicketApp() {
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return WicketPage.class;
    }

    public SampleService getSampleService() {
        return sampleService;
    }
}
