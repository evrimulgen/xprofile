// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.tapstream.sdk;


// Referenced classes of package com.tapstream.sdk:
//            Event, Hit

public interface Api
{

    public abstract void fireEvent(Event event);

    public abstract void fireHit(Hit hit, Hit.CompletionHandler completionhandler);
}
