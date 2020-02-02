package com.tamplan.bootique.jdbi;

import com.google.inject.Module;
import io.bootique.BQModuleProvider;
import io.bootique.jdbc.JdbcModuleProvider;

import java.util.Collection;
import java.util.Collections;

public class JdbiModuleProvider implements BQModuleProvider {

    @Override
    public Module module() {
        return new JdbiModule();
    }

    @Override
    public Collection<BQModuleProvider> dependencies() {
         return Collections.singletonList(new JdbcModuleProvider());
    }
}
