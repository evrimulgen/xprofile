// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.joins;

import rx.Subscription;

public interface JoinObserver
    extends Subscription
{

    public abstract void dequeue();

    public abstract void subscribe(Object obj);
}
