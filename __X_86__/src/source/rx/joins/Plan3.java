// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.joins;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observer;
import rx.functions.*;

// Referenced classes of package rx.joins:
//            Plan0, Pattern3, ActivePlan3, JoinObserver1, 
//            ActivePlan0

public class Plan3 extends Plan0
{

    public Plan3(Pattern3 pattern3, Func3 func3)
    {
        expression = pattern3;
        selector = func3;
    }

    public ActivePlan0 activate(Map map, final Observer observer, final Action1 deactivate)
    {
        Action1 action1 = Actions.onErrorFrom(observer);
        final JoinObserver1 firstJoinObserver = createObserver(map, expression.first(), action1);
        final JoinObserver1 secondJoinObserver = createObserver(map, expression.second(), action1);
        final JoinObserver1 thirdJoinObserver = createObserver(map, expression.third(), action1);
        final AtomicReference self = new AtomicReference();
        ActivePlan3 activeplan3 = new ActivePlan3(firstJoinObserver, secondJoinObserver, thirdJoinObserver, new Action3() {

            public void call(Object obj, Object obj1, Object obj2)
            {
                Object obj3;
                try
                {
                    obj3 = selector.call(obj, obj1, obj2);
                }
                catch(Throwable throwable)
                {
                    observer.onError(throwable);
                    return;
                }
                observer.onNext(obj3);
            }

            final Plan3 this$0;
            final Observer val$observer;

            
            {
                this$0 = Plan3.this;
                observer = observer1;
                super();
            }
        }
, new Action0() {

            public void call()
            {
                firstJoinObserver.removeActivePlan((ActivePlan0)self.get());
                secondJoinObserver.removeActivePlan((ActivePlan0)self.get());
                thirdJoinObserver.removeActivePlan((ActivePlan0)self.get());
                deactivate.call(self.get());
            }

            final Plan3 this$0;
            final Action1 val$deactivate;
            final JoinObserver1 val$firstJoinObserver;
            final JoinObserver1 val$secondJoinObserver;
            final AtomicReference val$self;
            final JoinObserver1 val$thirdJoinObserver;

            
            {
                this$0 = Plan3.this;
                firstJoinObserver = joinobserver1;
                self = atomicreference;
                secondJoinObserver = joinobserver1_1;
                thirdJoinObserver = joinobserver1_2;
                deactivate = action1;
                super();
            }
        }
);
        self.set(activeplan3);
        firstJoinObserver.addActivePlan(activeplan3);
        secondJoinObserver.addActivePlan(activeplan3);
        thirdJoinObserver.addActivePlan(activeplan3);
        return activeplan3;
    }

    protected Pattern3 expression;
    protected Func3 selector;
}
