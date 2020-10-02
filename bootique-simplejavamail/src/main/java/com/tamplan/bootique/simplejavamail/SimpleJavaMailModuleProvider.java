package com.tamplan.bootique.simplejavamail;

import io.bootique.BQModuleProvider;
import io.bootique.di.BQModule;

public class SimpleJavaMailModuleProvider implements BQModuleProvider {

    @Override
    public BQModule module() {
        return new SimpleJavaMailModule();
    }
}
