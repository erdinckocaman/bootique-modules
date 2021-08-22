package com.tamplan.bootique.jdbi;

import io.bootique.BQModuleProvider;
import io.bootique.di.BQModule;
import io.bootique.jdbc.JdbcModuleProvider;

import java.util.Collection;
import java.util.Collections;

public class JdbiModuleProvider implements BQModuleProvider {

    @Override
    public BQModule module() {
        return new JdbiModule();
    }

    @Override
    public Collection<BQModuleProvider> dependencies() {
         return Collections.singletonList(new JdbcModuleProvider());
    }
}
