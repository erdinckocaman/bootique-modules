package com.tamplan.bootique.examples.wicket;

import org.apache.wicket.markup.html.WebPage;

public class WicketPage extends WebPage {

    public WicketPage() {
        ((WicketApp)getApplication()).sampleService.getSomething();
    }
}
