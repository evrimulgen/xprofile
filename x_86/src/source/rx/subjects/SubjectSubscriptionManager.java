// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.subjects;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subscriptions.Subscriptions;

class SubjectSubscriptionManager
{
    protected static class State
    {

        private State createNewWith(SubjectObserver asubjectobserver[])
        {
            return new State(terminated, terminationLatch, asubjectobserver);
        }

        public State addObserver(SubjectObserver subjectobserver)
        {
            int i = observers.length;
            SubjectObserver asubjectobserver[] = (SubjectObserver[])Arrays.copyOf(observers, i + 1);
            asubjectobserver[i] = subjectobserver;
            return createNewWith(asubjectobserver);
        }

        public State removeObserver(SubjectObserver subjectobserver)
        {
            if(observers.length != 0) goto _L2; else goto _L1
_L1:
            return this;
_L2:
            int i;
            int j;
            SubjectObserver asubjectobserver[];
            int k;
            i = -1 + observers.length;
            j = 0;
            asubjectobserver = new SubjectObserver[i];
            k = 0;
_L6:
            if(k >= observers.length) goto _L4; else goto _L3
_L3:
            SubjectObserver subjectobserver1;
            subjectobserver1 = observers[k];
            if(subjectobserver1.equals(subjectobserver))
                continue; /* Loop/switch isn't completed */
            if(j == i) goto _L1; else goto _L5
_L5:
            asubjectobserver[j] = subjectobserver1;
            j++;
            k++;
              goto _L6
_L4:
            if(j == 0)
                return createNewWith(EMPTY_O);
            if(j < i)
            {
                SubjectObserver asubjectobserver1[] = new SubjectObserver[j];
                System.arraycopy(asubjectobserver, 0, asubjectobserver1, 0, j);
                return createNewWith(asubjectobserver1);
            } else
            {
                return createNewWith(asubjectobserver);
            }
        }

        public State terminate()
        {
            if(terminated)
                throw new IllegalStateException("Already terminated.");
            else
                return new State(true, new CountDownLatch(1), observers);
        }

        final SubjectObserver EMPTY_O[];
        final SubjectObserver observers[];
        final boolean terminated;
        final CountDownLatch terminationLatch;

        State()
        {
            EMPTY_O = new SubjectObserver[0];
            terminated = false;
            terminationLatch = null;
            observers = EMPTY_O;
        }

        private State(boolean flag, CountDownLatch countdownlatch, SubjectObserver asubjectobserver[])
        {
            EMPTY_O = new SubjectObserver[0];
            terminationLatch = countdownlatch;
            terminated = flag;
            observers = asubjectobserver;
        }
    }

    protected static class SubjectObserver
        implements Observer
    {

        public void onCompleted()
        {
            actual.onCompleted();
        }

        public void onError(Throwable throwable)
        {
            actual.onError(throwable);
        }

        public void onNext(Object obj)
        {
            actual.onNext(obj);
        }

        private final Observer actual;
        protected volatile boolean caughtUp;

        SubjectObserver(Observer observer)
        {
            caughtUp = false;
            actual = observer;
        }
    }


    SubjectSubscriptionManager()
    {
        state = new AtomicReference(new State());
    }

    public rx.Observable.OnSubscribe getOnSubscribeFunc(final Action1 onSubscribe, final Action1 onTerminated, final Action1 onUnsubscribe)
    {
        return new rx.Observable.OnSubscribe() {

            public volatile void call(Object obj)
            {
                call((Subscriber)obj);
            }

            public void call(Subscriber subscriber)
            {
                SubjectObserver subjectobserver;
                subjectobserver = new SubjectObserver(subscriber);
                if(onSubscribe != null)
                    onSubscribe.call(subjectobserver);
_L6:
                State state1 = (State)state.get();
                if(!state1.terminated) goto _L2; else goto _L1
_L1:
                boolean flag;
                State state2;
                flag = false;
                state2 = state1;
                try
                {
                    state1.terminationLatch.await();
                }
                catch(InterruptedException interruptedexception)
                {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Interrupted waiting for termination.", interruptedexception);
                }
_L5:
                if(state2.terminated && !flag)
                    onTerminated.call(subjectobserver);
_L4:
                return;
_L2:
                flag = true;
                subscriber.add(Subscriptions.create(subjectobserver. new Action0() {

                    public void call()
                    {
                        State state1;
                        State state2;
                        do
                        {
                            state1 = (State)state.get();
                            state2 = state1.removeObserver(observer);
                        } while(!state.compareAndSet(state1, state2));
                        if(onUnsubscribe != null)
                            onUnsubscribe.call(observer);
                    }

                    final _cls1 this$1;
                    final SubjectObserver val$observer;

            
            {
                this$1 = final__pcls1;
                observer = SubjectObserver.this;
                super();
            }
                }
));
                if(subscriber.isUnsubscribed()) goto _L4; else goto _L3
_L3:
                state2 = state1.addObserver(subjectobserver);
                if(!state.compareAndSet(state1, state2)) goto _L6; else goto _L5
            }

            final SubjectSubscriptionManager this$0;
            final Action1 val$onSubscribe;
            final Action1 val$onTerminated;
            final Action1 val$onUnsubscribe;

            
            {
                this$0 = SubjectSubscriptionManager.this;
                onSubscribe = action1;
                onUnsubscribe = action1_1;
                onTerminated = action1_2;
                super();
            }
        }
;
    }

    public SubjectObserver[] rawSnapshot()
    {
        return ((State)state.get()).observers;
    }

    protected Collection terminate(Action0 action0)
    {
        State state2;
        java.util.List list;
        State state1;
        do
        {
            state1 = (State)state.get();
            if(state1.terminated)
                return null;
            state2 = state1.terminate();
        } while(!state.compareAndSet(state1, state2));
        list = Arrays.asList(state2.observers);
        action0.call();
        state2.terminationLatch.countDown();
        return list;
        Exception exception;
        exception;
        state2.terminationLatch.countDown();
        throw exception;
    }

    private AtomicReference state;

}
