package com.tamplan.bootique.wicket.exception;

public class InjectionToWicketObjectFailedException extends RuntimeException {

    public InjectionToWicketObjectFailedException(Exception e) {
        super(e);
    }
}
