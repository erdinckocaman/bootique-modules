package com.tamplan.bootique.examples;

import com.tamplan.bootique.examples.services.SampleService;
import com.tamplan.bootique.examples.wicket.WicketApp;
import com.tamplan.bootique.simplejavamail.MailerRepository;
import com.tamplan.bootique.threadpool.ThreadPoolRepository;
import com.tamplan.bootique.wicket.WicketModule;
import io.bootique.BQRuntime;
import io.bootique.Bootique;
import io.bootique.ConfigModule;
import io.bootique.di.Binder;
import io.bootique.di.Provides;

import javax.inject.Singleton;

public class BootiqueExamplesModule extends ConfigModule {

    public static void main(String[] args){
        BQRuntime runtime = Bootique.app(args).autoLoadModules().module(BootiqueExamplesModule.class).createRuntime();

        SampleService sampleService = runtime.getInstance(SampleService.class);

        sampleService.addTaskToThreadPool();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void configure(Binder binder) {
        WicketModule.extend(binder).setApplicationClass(WicketApp.class);
    }

    @Singleton
    @Provides
    public SampleService getSampleService(MailerRepository mailerRepository, ThreadPoolRepository threadPoolRepository) {
        return new SampleService(mailerRepository, threadPoolRepository);
    }

}
