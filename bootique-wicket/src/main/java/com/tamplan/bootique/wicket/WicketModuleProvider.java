package com.tamplan.bootique.wicket;

import io.bootique.BQModuleProvider;
import io.bootique.di.BQModule;
import io.bootique.jetty.JettyModuleProvider;

import java.util.Collection;
import java.util.Collections;

public class WicketModuleProvider implements BQModuleProvider {

    @Override
    public BQModule module() {
        return new WicketModule();
    }

    @Override
    public Collection<BQModuleProvider> dependencies() {
        return Collections.singletonList(new JettyModuleProvider());
    }
}
