// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.joins;

import java.util.Map;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

// Referenced classes of package rx.joins:
//            JoinObserver, JoinObserver1, ActivePlan0

public abstract class Plan0
{

    public Plan0()
    {
    }

    public static JoinObserver1 createObserver(Map map, Observable observable, Action1 action1)
    {
        JoinObserver joinobserver = (JoinObserver)map.get(observable);
        if(joinobserver == null)
        {
            JoinObserver1 joinobserver1 = new JoinObserver1(observable, action1);
            map.put(observable, joinobserver1);
            return joinobserver1;
        } else
        {
            return (JoinObserver1)joinobserver;
        }
    }

    public abstract ActivePlan0 activate(Map map, Observer observer, Action1 action1);
}
