// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.joins;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observer;
import rx.functions.*;

// Referenced classes of package rx.joins:
//            Plan0, Pattern1, ActivePlan1, JoinObserver1, 
//            ActivePlan0

public class Plan1 extends Plan0
{

    public Plan1(Pattern1 pattern1, Func1 func1)
    {
        expression = pattern1;
        selector = func1;
    }

    public ActivePlan0 activate(Map map, final Observer observer, final Action1 deactivate)
    {
        Action1 action1 = Actions.onErrorFrom(observer);
        final JoinObserver1 firstJoinObserver = createObserver(map, expression.first(), action1);
        final AtomicReference self = new AtomicReference();
        ActivePlan1 activeplan1 = new ActivePlan1(firstJoinObserver, new Action1() {

            public void call(Object obj)
            {
                Object obj1;
                try
                {
                    obj1 = selector.call(obj);
                }
                catch(Throwable throwable)
                {
                    observer.onError(throwable);
                    return;
                }
                observer.onNext(obj1);
            }

            final Plan1 this$0;
            final Observer val$observer;

            
            {
                this$0 = Plan1.this;
                observer = observer1;
                super();
            }
        }
, new Action0() {

            public void call()
            {
                firstJoinObserver.removeActivePlan((ActivePlan0)self.get());
                deactivate.call(self.get());
            }

            final Plan1 this$0;
            final Action1 val$deactivate;
            final JoinObserver1 val$firstJoinObserver;
            final AtomicReference val$self;

            
            {
                this$0 = Plan1.this;
                firstJoinObserver = joinobserver1;
                self = atomicreference;
                deactivate = action1;
                super();
            }
        }
);
        self.set(activeplan1);
        firstJoinObserver.addActivePlan(activeplan1);
        return activeplan1;
    }

    public Pattern1 expression()
    {
        return expression;
    }

    public Func1 selector()
    {
        return selector;
    }

    protected Pattern1 expression;
    protected Func1 selector;
}
