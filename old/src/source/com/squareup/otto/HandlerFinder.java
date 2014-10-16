// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.otto;

import java.util.Map;

// Referenced classes of package com.squareup.otto:
//            AnnotatedHandlerFinder

interface HandlerFinder
{

    public abstract Map findAllProducers(Object obj);

    public abstract Map findAllSubscribers(Object obj);

    public static final HandlerFinder ANNOTATED = new HandlerFinder() {

        public Map findAllProducers(Object obj)
        {
            return AnnotatedHandlerFinder.findAllProducers(obj);
        }

        public Map findAllSubscribers(Object obj)
        {
            return AnnotatedHandlerFinder.findAllSubscribers(obj);
        }

    }
;

}
