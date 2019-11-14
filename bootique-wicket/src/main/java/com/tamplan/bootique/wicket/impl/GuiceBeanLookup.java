package com.tamplan.bootique.wicket.impl;

import com.google.inject.Injector;
import com.tamplan.bootique.wicket.BeanLookup;

public class GuiceBeanLookup implements BeanLookup {
	
	private Injector injector;

	public GuiceBeanLookup(Injector injector) {
		this.injector = injector;
	}

	@Override
	public <T> T lookup(Class<T> clazz) {
		return injector.getInstance(clazz);
	}

}
