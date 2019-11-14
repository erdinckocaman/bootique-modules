package com.tamplan.bootique.wicket;

import org.apache.wicket.MetaDataKey;

public class BeanLookupMetaDataKey extends MetaDataKey<BeanLookup> {

    private static BeanLookupMetaDataKey beanLookupMetaDataKey;

    private BeanLookupMetaDataKey() {
    }

    public static BeanLookupMetaDataKey getInstance() {
        if ( beanLookupMetaDataKey == null ) {
            beanLookupMetaDataKey = new BeanLookupMetaDataKey();
        }

        return beanLookupMetaDataKey;
    }
}
