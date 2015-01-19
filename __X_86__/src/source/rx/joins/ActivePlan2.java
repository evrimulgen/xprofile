// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.joins;

import java.util.Queue;
import rx.Notification;
import rx.functions.Action0;
import rx.functions.Action2;

// Referenced classes of package rx.joins:
//            ActivePlan0, JoinObserver1

public class ActivePlan2 extends ActivePlan0
{

    public ActivePlan2(JoinObserver1 joinobserver1, JoinObserver1 joinobserver1_1, Action2 action2, Action0 action0)
    {
        onNext = action2;
        onCompleted = action0;
        first = joinobserver1;
        second = joinobserver1_1;
        addJoinObserver(joinobserver1);
        addJoinObserver(joinobserver1_1);
    }

    public void match()
    {
        Notification notification;
        Notification notification1;
label0:
        {
            if(!first.queue().isEmpty() && !second.queue().isEmpty())
            {
                notification = (Notification)first.queue().peek();
                notification1 = (Notification)second.queue().peek();
                if(!notification.isOnCompleted() && !notification1.isOnCompleted())
                    break label0;
                onCompleted.call();
            }
            return;
        }
        dequeue();
        onNext.call(notification.getValue(), notification1.getValue());
    }

    private final JoinObserver1 first;
    private final Action0 onCompleted;
    private final Action2 onNext;
    private final JoinObserver1 second;
}
