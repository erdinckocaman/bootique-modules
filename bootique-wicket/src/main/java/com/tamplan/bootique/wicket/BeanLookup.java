package com.tamplan.bootique.wicket;

public interface BeanLookup {

    <T> T lookup(Class<T> clazz);

}
