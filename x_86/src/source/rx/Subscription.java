// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx;


public interface Subscription
{

    public abstract boolean isUnsubscribed();

    public abstract void unsubscribe();
}
