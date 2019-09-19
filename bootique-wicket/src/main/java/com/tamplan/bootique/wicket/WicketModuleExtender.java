package com.tamplan.bootique.wicket;

import com.google.inject.Binder;
import com.google.inject.TypeLiteral;
import io.bootique.ModuleExtender;
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

        binder.bind(new TypeLiteral<Class<? extends  WebApplication>>() {}).toInstance(applicationClass);

        return this;
    }
}
