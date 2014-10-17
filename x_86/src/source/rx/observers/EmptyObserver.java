// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.observers;

import rx.Observer;

public class EmptyObserver
    implements Observer
{

    public EmptyObserver()
    {
    }

    public void onCompleted()
    {
    }

    public void onError(Throwable throwable)
    {
    }

    public void onNext(Object obj)
    {
    }
}
