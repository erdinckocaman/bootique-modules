package com.tamplan.bootique.examples;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.tamplan.bootique.examples.wicket.WicketApp;
import com.tamplan.bootique.wicket.WicketModule;
import io.bootique.BQCoreModule;
import io.bootique.BQRuntime;
import io.bootique.command.CommandOutcome;
import io.bootique.test.junit.BQDaemonTestFactory;
import org.apache.wicket.protocol.http.WebApplication;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BootiqueWicketModuleTest {

    @Rule
    public BQDaemonTestFactory testFactory = new BQDaemonTestFactory();

    @Test
    public void shouldLaunchWebApp() {
        BQRuntime runtime = testFactory.app("--server").autoLoadModules().module(new Module() {
            @Override
            public void configure(Binder binder) {
                BQCoreModule.extend(binder).addConfig("classpath:config_wicket.yml");
                WicketModule.extend(binder).setApplicationClass(WicketApp.class);
            }
        }).createRuntime();

        CommandOutcome commandOutcome = runtime.run();

        assertTrue(commandOutcome.isSuccess());

        WebApplication webApplication = runtime.getInstance(WebApplication.class);

        assertNotNull(webApplication);

    }
}
