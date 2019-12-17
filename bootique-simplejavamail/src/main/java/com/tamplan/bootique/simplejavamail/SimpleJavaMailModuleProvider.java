package com.tamplan.bootique.simplejavamail;

import com.google.inject.Module;
import io.bootique.BQModuleProvider;

public class SimpleJavaMailModuleProvider implements BQModuleProvider {

    @Override
    public Module module() {
        return new SimpleJavaMailModule();
    }
}
