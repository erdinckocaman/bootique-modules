package com.tamplan.bootique.threadpool;

public class ThreadPoolConfiguration {

    private Integer minThreads;
    private Integer maxThreads;

    public void setMaxThreads(Integer maxThreads) {
        this.maxThreads = maxThreads;
    }

    public Integer getMaxThreads() {
        return maxThreads;
    }

    public void setMinThreads(Integer minThreads) {
        this.minThreads = minThreads;
    }

    public Integer getMinThreads() {
        return minThreads;
    }
}
