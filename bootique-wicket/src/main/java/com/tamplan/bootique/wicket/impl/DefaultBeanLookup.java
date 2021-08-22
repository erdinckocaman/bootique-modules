package com.tamplan.bootique.wicket.impl;

import com.tamplan.bootique.wicket.BeanLookup;
import io.bootique.di.Injector;

public class DefaultBeanLookup implements BeanLookup {
	
	private Injector injector;

	public DefaultBeanLookup(Injector injector) {
		this.injector = injector;
	}

	@Override
	public <T> T lookup(Class<T> clazz) {
		return injector.getInstance(clazz);
	}

}
