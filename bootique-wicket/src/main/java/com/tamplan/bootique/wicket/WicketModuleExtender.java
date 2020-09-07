package com.tamplan.bootique.wicket;

import io.bootique.ModuleExtender;
import io.bootique.di.Binder;
import io.bootique.di.Key;
import io.bootique.di.TypeLiteral;
import org.apache.wicket.protocol.http.WebApplication;

import java.util.Objects;

public class WicketModuleExtender  extends ModuleExtender {

    public WicketModuleExtender(Binder binder) {
        super(binder);
    }

    @Override
    public WicketModuleExtender initAllExtensions() {
        return this;
    }

    public WicketModuleExtender setApplicationClass(Class<? extends WebApplication> applicationClass) {
        Objects.requireNonNull(applicationClass);
        
        binder.bind(Key.get(new TypeLiteral<Class<? extends  WebApplication>>() {})).toInstance(applicationClass);

        return this;
    }
}
