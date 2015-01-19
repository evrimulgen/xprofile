// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.joins;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observer;
import rx.functions.*;

// Referenced classes of package rx.joins:
//            Plan0, Pattern2, ActivePlan2, JoinObserver1, 
//            ActivePlan0

public class Plan2 extends Plan0
{

    public Plan2(Pattern2 pattern2, Func2 func2)
    {
        expression = pattern2;
        selector = func2;
    }

    public ActivePlan0 activate(Map map, final Observer observer, final Action1 deactivate)
    {
        Action1 action1 = Actions.onErrorFrom(observer);
        final JoinObserver1 firstJoinObserver = createObserver(map, expression.first(), action1);
        final JoinObserver1 secondJoinObserver = createObserver(map, expression.second(), action1);
        final AtomicReference self = new AtomicReference();
        ActivePlan2 activeplan2 = new ActivePlan2(firstJoinObserver, secondJoinObserver, new Action2() {

            public void call(Object obj, Object obj1)
            {
                Object obj2;
                try
                {
                    obj2 = selector.call(obj, obj1);
                }
                catch(Throwable throwable)
                {
                    observer.onError(throwable);
                    return;
                }
                observer.onNext(obj2);
            }

            final Plan2 this$0;
            final Observer val$observer;

            
            {
                this$0 = Plan2.this;
                observer = observer1;
                super();
            }
        }
, new Action0() {

            public void call()
            {
                firstJoinObserver.removeActivePlan((ActivePlan0)self.get());
                secondJoinObserver.removeActivePlan((ActivePlan0)self.get());
                deactivate.call(self.get());
            }

            final Plan2 this$0;
            final Action1 val$deactivate;
            final JoinObserver1 val$firstJoinObserver;
            final JoinObserver1 val$secondJoinObserver;
            final AtomicReference val$self;

            
            {
                this$0 = Plan2.this;
                firstJoinObserver = joinobserver1;
                self = atomicreference;
                secondJoinObserver = joinobserver1_1;
                deactivate = action1;
                super();
            }
        }
);
        self.set(activeplan2);
        firstJoinObserver.addActivePlan(activeplan2);
        secondJoinObserver.addActivePlan(activeplan2);
        return activeplan2;
    }

    protected Pattern2 expression;
    protected Func2 selector;
}
