package com.tamplan.bootique.threadpool;

import com.google.inject.Module;
import io.bootique.BQModuleProvider;

public class ThreadPoolModuleProvider implements BQModuleProvider {

    @Override
    public Module module() {
        return new ThreadPoolModule();
    }
}
