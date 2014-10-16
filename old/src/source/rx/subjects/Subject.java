// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.subjects;

import rx.Observable;
import rx.Observer;

public abstract class Subject extends Observable
    implements Observer
{

    protected Subject(rx.Observable.OnSubscribe onsubscribe)
    {
        super(onsubscribe);
    }
}
