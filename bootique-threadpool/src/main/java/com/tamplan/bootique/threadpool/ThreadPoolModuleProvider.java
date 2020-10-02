package com.tamplan.bootique.threadpool;

import io.bootique.BQModuleProvider;
import io.bootique.di.BQModule;

public class ThreadPoolModuleProvider implements BQModuleProvider {

    @Override
    public BQModule module() {
        return new ThreadPoolModule();
    }
}
