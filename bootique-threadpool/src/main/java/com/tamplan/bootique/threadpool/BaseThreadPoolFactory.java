package com.tamplan.bootique.threadpool;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.bootique.config.PolymorphicConfiguration;

import java.util.concurrent.ExecutorService;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", defaultImpl = PlainThreadPoolFactory.class)
public abstract class BaseThreadPoolFactory implements PolymorphicConfiguration {

    public abstract ExecutorService create(String name);
}
