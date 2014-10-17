// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.joins;

import java.util.*;

// Referenced classes of package rx.joins:
//            JoinObserver

public abstract class ActivePlan0
{

    public ActivePlan0()
    {
    }

    protected void addJoinObserver(JoinObserver joinobserver)
    {
        joinObservers.put(joinobserver, joinobserver);
    }

    protected void dequeue()
    {
        for(Iterator iterator = joinObservers.values().iterator(); iterator.hasNext(); ((JoinObserver)iterator.next()).dequeue());
    }

    public abstract void match();

    protected final Map joinObservers = new HashMap();
}
