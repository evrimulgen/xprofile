// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.joins;

import java.util.Queue;
import rx.Notification;
import rx.functions.Action0;
import rx.functions.Action1;

// Referenced classes of package rx.joins:
//            ActivePlan0, JoinObserver1

public class ActivePlan1 extends ActivePlan0
{

    public ActivePlan1(JoinObserver1 joinobserver1, Action1 action1, Action0 action0)
    {
        onNext = action1;
        onCompleted = action0;
        first = joinobserver1;
        addJoinObserver(joinobserver1);
    }

    public void match()
    {
        Notification notification;
label0:
        {
            if(!first.queue().isEmpty())
            {
                notification = (Notification)first.queue().peek();
                if(!notification.isOnCompleted())
                    break label0;
                onCompleted.call();
            }
            return;
        }
        dequeue();
        onNext.call(notification.getValue());
    }

    private final JoinObserver1 first;
    private final Action0 onCompleted;
    private final Action1 onNext;
}
