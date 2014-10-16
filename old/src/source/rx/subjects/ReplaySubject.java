// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.subjects;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import rx.Notification;
import rx.functions.Action0;
import rx.functions.Action1;

// Referenced classes of package rx.subjects:
//            Subject, SubjectSubscriptionManager

public final class ReplaySubject extends Subject
{
    private static class History
    {

        public void complete(Notification notification)
        {
            terminalValue.set(notification);
        }

        public boolean next(Object obj)
        {
            if(terminalValue.get() == null)
            {
                list.add(obj);
                index.getAndIncrement();
                return true;
            } else
            {
                return false;
            }
        }

        private final AtomicInteger index = new AtomicInteger(0);
        private final ArrayList list;
        private final AtomicReference terminalValue = new AtomicReference();




        public History(int i)
        {
            list = new ArrayList(i);
        }
    }

    private static class ReplayState
    {

        final History history;
        final ConcurrentHashMap replayState = new ConcurrentHashMap();

        public ReplayState(int i)
        {
            history = new History(i);
        }
    }


    protected ReplaySubject(rx.Observable.OnSubscribe onsubscribe, SubjectSubscriptionManager subjectsubscriptionmanager, ReplayState replaystate)
    {
        super(onsubscribe);
        subscriptionManager = subjectsubscriptionmanager;
        state = replaystate;
    }

    private boolean caughtUp(SubjectSubscriptionManager.SubjectObserver subjectobserver)
    {
        boolean flag = true;
        if(!subjectobserver.caughtUp)
        {
            subjectobserver.caughtUp = flag;
            replayObserver(subjectobserver);
            flag = false;
        }
        return flag;
    }

    public static ReplaySubject create()
    {
        return create(16);
    }

    public static ReplaySubject create(int i)
    {
        SubjectSubscriptionManager subjectsubscriptionmanager = new SubjectSubscriptionManager();
        ReplayState replaystate = new ReplayState(i);
        return new ReplaySubject(subjectsubscriptionmanager.getOnSubscribeFunc(new Action1(replaystate) {

            public volatile void call(Object obj)
            {
                call((SubjectSubscriptionManager.SubjectObserver)obj);
            }

            public void call(SubjectSubscriptionManager.SubjectObserver subjectobserver)
            {
                int j = ReplaySubject.replayObserverFromIndex(state.history, Integer.valueOf(0), subjectobserver);
                state.replayState.put(subjectobserver, Integer.valueOf(j));
            }

            final ReplayState val$state;

            
            {
                state = replaystate;
                super();
            }
        }
, new Action1(replaystate) {

            public volatile void call(Object obj)
            {
                call((SubjectSubscriptionManager.SubjectObserver)obj);
            }

            public void call(SubjectSubscriptionManager.SubjectObserver subjectobserver)
            {
                Integer integer = (Integer)state.replayState.remove(subjectobserver);
                ReplaySubject.replayObserverFromIndex(state.history, integer, subjectobserver);
            }

            final ReplayState val$state;

            
            {
                state = replaystate;
                super();
            }
        }
, new Action1(replaystate) {

            public volatile void call(Object obj)
            {
                call((SubjectSubscriptionManager.SubjectObserver)obj);
            }

            public void call(SubjectSubscriptionManager.SubjectObserver subjectobserver)
            {
                state.replayState.remove(subjectobserver);
            }

            final ReplayState val$state;

            
            {
                state = replaystate;
                super();
            }
        }
), subjectsubscriptionmanager, replaystate);
    }

    private void replayObserver(SubjectSubscriptionManager.SubjectObserver subjectobserver)
    {
        Integer integer = (Integer)state.replayState.get(subjectobserver);
        if(integer != null)
        {
            int i = replayObserverFromIndex(state.history, integer, subjectobserver);
            state.replayState.put(subjectobserver, Integer.valueOf(i));
            return;
        } else
        {
            throw new IllegalStateException((new StringBuilder()).append("failed to find lastEmittedLink for: ").append(subjectobserver).toString());
        }
    }

    private static int replayObserverFromIndex(History history, Integer integer, SubjectSubscriptionManager.SubjectObserver subjectobserver)
    {
        for(; integer.intValue() < history.index.get(); integer = Integer.valueOf(1 + integer.intValue()))
            subjectobserver.onNext(history.list.get(integer.intValue()));

        if(history.terminalValue.get() != null)
            ((Notification)history.terminalValue.get()).accept(subjectobserver);
        return integer.intValue();
    }

    public void onCompleted()
    {
        Collection collection = subscriptionManager.terminate(new Action0() {

            public void call()
            {
                state.history.complete(Notification.createOnCompleted());
            }

            final ReplaySubject this$0;

            
            {
                this$0 = ReplaySubject.this;
                super();
            }
        }
);
        if(collection != null)
        {
            Iterator iterator = collection.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                SubjectSubscriptionManager.SubjectObserver subjectobserver = (SubjectSubscriptionManager.SubjectObserver)iterator.next();
                if(caughtUp(subjectobserver))
                    subjectobserver.onCompleted();
            } while(true);
        }
    }

    public void onError(final Throwable e)
    {
        Collection collection = subscriptionManager.terminate(new Action0() {

            public void call()
            {
                state.history.complete(Notification.createOnError(e));
            }

            final ReplaySubject this$0;
            final Throwable val$e;

            
            {
                this$0 = ReplaySubject.this;
                e = throwable;
                super();
            }
        }
);
        if(collection != null)
        {
            Iterator iterator = collection.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                SubjectSubscriptionManager.SubjectObserver subjectobserver = (SubjectSubscriptionManager.SubjectObserver)iterator.next();
                if(caughtUp(subjectobserver))
                    subjectobserver.onError(e);
            } while(true);
        }
    }

    public void onNext(Object obj)
    {
        if(state.history.terminalValue.get() == null)
        {
            state.history.next(obj);
            SubjectSubscriptionManager.SubjectObserver asubjectobserver[] = subscriptionManager.rawSnapshot();
            int i = asubjectobserver.length;
            int j = 0;
            while(j < i) 
            {
                SubjectSubscriptionManager.SubjectObserver subjectobserver = asubjectobserver[j];
                if(caughtUp(subjectobserver))
                    subjectobserver.onNext(obj);
                j++;
            }
        }
    }

    int subscriberCount()
    {
        return state.replayState.size();
    }

    private final ReplayState state;
    private final SubjectSubscriptionManager subscriptionManager;


}
