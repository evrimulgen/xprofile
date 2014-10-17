// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import rx.Subscriber;
import rx.functions.Action0;
import rx.observables.GroupedObservable;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

// Referenced classes of package rx.operators:
//            BufferUntilSubscriber

public class OperatorPivot
    implements rx.Observable.Operator
{
    private static class GroupState
    {

        private Inner getOrCreateFor(AtomicReference atomicreference, Subscriber subscriber, Object obj, Object obj1)
        {
            Outer outer = getOrCreateOuter(atomicreference, subscriber, obj1);
            if(outer == null)
                return null;
            else
                return getOrCreateInnerSubject(atomicreference, outer, obj, obj1);
        }

        private Inner getOrCreateInnerSubject(AtomicReference atomicreference, Outer outer, Object obj, Object obj1)
        {
            KeyPair keypair = new KeyPair(obj, obj1);
            Inner inner = (Inner)innerSubjects.get(keypair);
            if(inner != null)
                return inner;
            Inner inner1 = Inner.create(this, atomicreference, outer, keypair);
            Inner inner2 = (Inner)innerSubjects.putIfAbsent(keypair, inner1);
            if(inner2 != null)
            {
                return inner2;
            } else
            {
                startK1K2Group(atomicreference, keypair);
                outer.subscriber.onNext(inner1.group);
                return inner1;
            }
        }

        private Outer getOrCreateOuter(AtomicReference atomicreference, Subscriber subscriber, Object obj)
        {
            Outer outer = (Outer)outerSubjects.get(obj);
            if(outer != null)
                return outer;
            if(subscriber.isUnsubscribed())
                return null;
            Outer outer1 = Outer.create(this, atomicreference, obj);
            Outer outer2 = (Outer)outerSubjects.putIfAbsent(obj, outer1);
            if(outer2 != null)
            {
                return outer2;
            } else
            {
                subscriber.onNext(outer1.group);
                return outer1;
            }
        }

        public void completeAll(State state)
        {
            if(completeEmitted.compareAndSet(false, true))
            {
                for(Iterator iterator = outerSubjects.entrySet().iterator(); iterator.hasNext(); ((Outer)((java.util.Map.Entry)iterator.next()).getValue()).subscriber.onCompleted());
                for(Iterator iterator1 = innerSubjects.entrySet().iterator(); iterator1.hasNext(); ((Inner)((java.util.Map.Entry)iterator1.next()).getValue()).subscriber.onCompleted());
                if(state.unsubscribed)
                    parentSubscription.unsubscribe();
                child.onCompleted();
            }
        }

        public void completeK1Group(AtomicReference atomicreference, Object obj)
        {
            State state;
            State state1;
            do
            {
                state = (State)atomicreference.get();
                state1 = state.removeK1(obj);
            } while(!atomicreference.compareAndSet(state, state1));
            if(state1.shouldComplete())
                completeAll(state1);
        }

        public void completeK1K2Group(AtomicReference atomicreference, KeyPair keypair)
        {
            State state;
            State state1;
            do
            {
                state = (State)atomicreference.get();
                state1 = state.removeK1k2(keypair);
            } while(!atomicreference.compareAndSet(state, state1));
            if(state1.shouldComplete())
                completeAll(state1);
        }

        public void startK1Group(AtomicReference atomicreference, Object obj)
        {
            State state;
            do
                state = (State)atomicreference.get();
            while(!atomicreference.compareAndSet(state, state.addK1(obj)));
        }

        public void startK1K2Group(AtomicReference atomicreference, KeyPair keypair)
        {
            State state;
            do
                state = (State)atomicreference.get();
            while(!atomicreference.compareAndSet(state, state.addK1k2(keypair)));
        }

        private final Subscriber child;
        private final AtomicBoolean completeEmitted = new AtomicBoolean();
        private final ConcurrentHashMap innerSubjects = new ConcurrentHashMap();
        private final ConcurrentHashMap outerSubjects = new ConcurrentHashMap();
        private final CompositeSubscription parentSubscription;


        public GroupState(CompositeSubscription compositesubscription, Subscriber subscriber)
        {
            parentSubscription = compositesubscription;
            child = subscriber;
        }
    }

    private static class Inner
    {

        public static Inner create(GroupState groupstate, AtomicReference atomicreference, Outer outer, KeyPair keypair)
        {
            BufferUntilSubscriber bufferuntilsubscriber = BufferUntilSubscriber.create();
            return new Inner(bufferuntilsubscriber, new GroupedObservable(keypair.k1, new rx.Observable.OnSubscribe(groupstate, atomicreference, keypair, bufferuntilsubscriber) {

                public volatile void call(Object obj)
                {
                    call((Subscriber)obj);
                }

                public void call(Subscriber subscriber)
                {
                    subscriber.add(Subscriptions.create(new Action0() {

                        public void call()
                        {
                            groupState.completeK1K2Group(state, keyPair);
                        }

                        final Inner._cls1 this$0;

            
            {
                this$0 = Inner._cls1.this;
                super();
            }
                    }
));
                    subject.unsafeSubscribe(subscriber. new Subscriber(subscriber) {

                        public void onCompleted()
                        {
                            groupState.completeK1K2Group(state, keyPair);
                            o.onCompleted();
                        }

                        public void onError(Throwable throwable)
                        {
                            o.onError(throwable);
                        }

                        public void onNext(Object obj)
                        {
                            if(!isUnsubscribed())
                                o.onNext(obj);
                        }

                        final Inner._cls1 this$0;
                        final Subscriber val$o;

            
            {
                this$0 = final__pcls1;
                o = subscriber1;
                super(Subscriber.this);
            }
                    }
);
                }

                final GroupState val$groupState;
                final KeyPair val$keyPair;
                final AtomicReference val$state;
                final BufferUntilSubscriber val$subject;

            
            {
                groupState = groupstate;
                state = atomicreference;
                keyPair = keypair;
                subject = bufferuntilsubscriber;
                super();
            }
            }
));
        }

        private final GroupedObservable group;
        private final BufferUntilSubscriber subscriber;



        private Inner(BufferUntilSubscriber bufferuntilsubscriber, GroupedObservable groupedobservable)
        {
            subscriber = bufferuntilsubscriber;
            group = groupedobservable;
        }
    }

    private static class KeyPair
    {

        public boolean equals(Object obj)
        {
            if(this != obj) goto _L2; else goto _L1
_L1:
            return true;
_L2:
            KeyPair keypair;
            if(obj == null)
                return false;
            if(getClass() != obj.getClass())
                return false;
            keypair = (KeyPair)obj;
            if(k1 == null)
            {
                if(keypair.k1 != null)
                    return false;
            } else
            if(!k1.equals(keypair.k1))
                return false;
            if(k2 != null)
                continue; /* Loop/switch isn't completed */
            if(keypair.k2 == null) goto _L1; else goto _L3
_L3:
            return false;
            if(k2.equals(keypair.k2)) goto _L1; else goto _L4
_L4:
            return false;
        }

        public int hashCode()
        {
            int i;
            int j;
            Object obj;
            int k;
            if(k1 == null)
                i = 0;
            else
                i = k1.hashCode();
            j = 31 * (i + 31);
            obj = k2;
            k = 0;
            if(obj != null)
                k = k2.hashCode();
            return j + k;
        }

        public String toString()
        {
            return (new StringBuilder()).append(k2).append(".").append(k1).toString();
        }

        private final Object k1;
        private final Object k2;


        KeyPair(Object obj, Object obj1)
        {
            k1 = obj;
            k2 = obj1;
        }
    }

    private static class Outer
    {

        public static Outer create(GroupState groupstate, AtomicReference atomicreference, Object obj)
        {
            BufferUntilSubscriber bufferuntilsubscriber = BufferUntilSubscriber.create();
            return new Outer(bufferuntilsubscriber, new GroupedObservable(obj, new rx.Observable.OnSubscribe(bufferuntilsubscriber) {

                public volatile void call(Object obj)
                {
                    call((Subscriber)obj);
                }

                public void call(Subscriber subscriber)
                {
                    subject.unsafeSubscribe(subscriber. new Subscriber(subscriber) {

                        public void onCompleted()
                        {
                            o.onCompleted();
                        }

                        public void onError(Throwable throwable)
                        {
                            o.onError(throwable);
                        }

                        public volatile void onNext(Object obj)
                        {
                            onNext((GroupedObservable)obj);
                        }

                        public void onNext(GroupedObservable groupedobservable)
                        {
                            if(!isUnsubscribed())
                                o.onNext(groupedobservable);
                        }

                        final Outer._cls1 this$0;
                        final Subscriber val$o;

            
            {
                this$0 = final__pcls1;
                o = subscriber1;
                super(Subscriber.this);
            }
                    }
);
                }

                final BufferUntilSubscriber val$subject;

            
            {
                subject = bufferuntilsubscriber;
                super();
            }
            }
));
        }

        private final GroupedObservable group;
        private final BufferUntilSubscriber subscriber;



        private Outer(BufferUntilSubscriber bufferuntilsubscriber, GroupedObservable groupedobservable)
        {
            subscriber = bufferuntilsubscriber;
            group = groupedobservable;
        }
    }

    private final class PivotSubscriber extends Subscriber
    {

        public void onCompleted()
        {
            State state1;
            State state2;
            do
            {
                state1 = (State)state.get();
                state2 = state1.complete();
            } while(!state.compareAndSet(state1, state2));
            if(state2.shouldComplete())
                groups.completeAll(state2);
        }

        public void onError(Throwable throwable)
        {
            child.onError(throwable);
        }

        public volatile void onNext(Object obj)
        {
            onNext((GroupedObservable)obj);
        }

        public void onNext(GroupedObservable groupedobservable)
        {
            groups.startK1Group(state, groupedobservable.getKey());
            groupedobservable.unsafeSubscribe(parentSubscription. new Subscriber(groupedobservable) {

                public void onCompleted()
                {
                    groups.completeK1Group(state, k1Group.getKey());
                }

                public void onError(Throwable throwable)
                {
                    child.onError(throwable);
                }

                public volatile void onNext(Object obj)
                {
                    onNext((GroupedObservable)obj);
                }

                public void onNext(GroupedObservable groupedobservable)
                {
                    Inner inner = groups.getOrCreateFor(state, child, k1Group.getKey(), groupedobservable.getKey());
                    if(inner == null)
                    {
                        return;
                    } else
                    {
                        groupedobservable.unsafeSubscribe(parentSubscription. new Subscriber(inner) {

                            public void onCompleted()
                            {
                            }

                            public void onError(Throwable throwable)
                            {
                                inner.subscriber.onError(throwable);
                            }

                            public void onNext(Object obj)
                            {
                                inner.subscriber.onNext(obj);
                            }

                            final PivotSubscriber._cls1 this$2;
                            final Inner val$inner;

            
            {
                this$2 = final__pcls1;
                inner = inner1;
                super(CompositeSubscription.this);
            }
                        }
);
                        return;
                    }
                }

                final PivotSubscriber this$1;
                final GroupedObservable val$k1Group;

            
            {
                this$1 = final_pivotsubscriber;
                k1Group = groupedobservable;
                super(CompositeSubscription.this);
            }
            }
);
        }

        private final Subscriber child;
        private final GroupState groups;
        private final CompositeSubscription parentSubscription;
        private final AtomicReference state;
        final OperatorPivot this$0;





        private PivotSubscriber(CompositeSubscription compositesubscription, Subscriber subscriber, AtomicReference atomicreference)
        {
            this$0 = OperatorPivot.this;
            super(compositesubscription);
            parentSubscription = compositesubscription;
            child = subscriber;
            state = atomicreference;
            groups = new GroupState(compositesubscription, subscriber);
        }

    }

    private static class State
    {

        public static State create()
        {
            return new State(false, false, Collections.emptySet(), Collections.emptySet());
        }

        public State addK1(Object obj)
        {
            HashSet hashset = new HashSet(k1Keys);
            hashset.add(obj);
            return new State(completed, unsubscribed, hashset, k1k2Keys);
        }

        public State addK1k2(KeyPair keypair)
        {
            HashSet hashset = new HashSet(k1k2Keys);
            hashset.add(keypair);
            return new State(completed, unsubscribed, k1Keys, hashset);
        }

        public State complete()
        {
            return new State(true, unsubscribed, k1Keys, k1k2Keys);
        }

        public State removeK1(Object obj)
        {
            HashSet hashset = new HashSet(k1Keys);
            hashset.remove(obj);
            return new State(completed, unsubscribed, hashset, k1k2Keys);
        }

        public State removeK1k2(KeyPair keypair)
        {
            HashSet hashset = new HashSet(k1k2Keys);
            hashset.remove(keypair);
            return new State(completed, unsubscribed, k1Keys, hashset);
        }

        public boolean shouldComplete()
        {
            if(k1Keys.size() != 0 || !completed)
                if(unsubscribed)
                {
                    if(k1k2Keys.size() != 0)
                        return false;
                } else
                {
                    return false;
                }
            return true;
        }

        public String toString()
        {
            return (new StringBuilder()).append("State =>  k1: ").append(k1Keys.size()).append(" k1k2: ").append(k1k2Keys.size()).append(" completed: ").append(completed).append(" unsubscribed: ").append(unsubscribed).toString();
        }

        public State unsubscribe()
        {
            return new State(completed, true, k1Keys, k1k2Keys);
        }

        private final boolean completed;
        private final Set k1Keys;
        private final Set k1k2Keys;
        private final boolean unsubscribed;


        private State(boolean flag, boolean flag1, Set set, Set set1)
        {
            completed = flag;
            unsubscribed = flag1;
            k1Keys = set;
            k1k2Keys = set1;
        }
    }


    public OperatorPivot()
    {
    }

    public volatile Object call(Object obj)
    {
        return call((Subscriber)obj);
    }

    public Subscriber call(Subscriber subscriber)
    {
        final AtomicReference state = new AtomicReference(State.create());
        final PivotSubscriber pivotSubscriber = new PivotSubscriber(new CompositeSubscription(), subscriber, state);
        subscriber.add(Subscriptions.create(new Action0() {

            public void call()
            {
                State state1;
                State state2;
                do
                {
                    state1 = (State)state.get();
                    state2 = state1.unsubscribe();
                } while(!state.compareAndSet(state1, state2));
                if(state2.shouldComplete())
                    pivotSubscriber.groups.completeAll(state2);
            }

            final OperatorPivot this$0;
            final PivotSubscriber val$pivotSubscriber;
            final AtomicReference val$state;

            
            {
                this$0 = OperatorPivot.this;
                state = atomicreference;
                pivotSubscriber = pivotsubscriber;
                super();
            }
        }
));
        return pivotSubscriber;
    }
}
