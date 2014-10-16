// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;


// Referenced classes of package com.google.tagmanager:
//            Runtime

class EventEvaluator
{

    public EventEvaluator(Runtime runtime, ResourceUtil.ExpandedResource expandedresource)
    {
        if(runtime == null)
            throw new NullPointerException("runtime cannot be null");
        mRuntime = runtime;
        if(expandedresource != runtime.getResource())
        {
            throw new IllegalArgumentException("resource must be the same as the resource in runtime");
        } else
        {
            mResource = runtime.getResource();
            return;
        }
    }

    void evaluateEvent(String s)
    {
        throw new UnsupportedOperationException("this code not yet written");
    }

    private final ResourceUtil.ExpandedResource mResource;
    private final Runtime mRuntime;
}
