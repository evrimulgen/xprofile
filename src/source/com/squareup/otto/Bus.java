// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.otto;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.*;

// Referenced classes of package com.squareup.otto:
//            HandlerFinder, ThreadEnforcer, EventProducer, EventHandler, 
//            DeadEvent

public class Bus
{
    static class EventWithHandler
    {

        final Object event;
        final EventHandler handler;

        public EventWithHandler(Object obj, EventHandler eventhandler)
        {
            event = obj;
            handler = eventhandler;
        }
    }


    public Bus()
    {
        this("default");
    }

    public Bus(ThreadEnforcer threadenforcer)
    {
        this(threadenforcer, "default");
    }

    public Bus(ThreadEnforcer threadenforcer, String s)
    {
        this(threadenforcer, s, HandlerFinder.ANNOTATED);
    }

    Bus(ThreadEnforcer threadenforcer, String s, HandlerFinder handlerfinder)
    {
        handlersByType = new ConcurrentHashMap();
        producersByType = new ConcurrentHashMap();
        eventsToDispatch = new ThreadLocal() {

            protected volatile Object initialValue()
            {
                return initialValue();
            }

            protected ConcurrentLinkedQueue initialValue()
            {
                return new ConcurrentLinkedQueue();
            }

            final Bus this$0;

            
            {
                this$0 = Bus.this;
                super();
            }
        }
;
        isDispatching = new ThreadLocal() {

            protected Boolean initialValue()
            {
                return Boolean.valueOf(false);
            }

            protected volatile Object initialValue()
            {
                return initialValue();
            }

            final Bus this$0;

            
            {
                this$0 = Bus.this;
                super();
            }
        }
;
        flattenHierarchyCache = new HashMap();
        enforcer = threadenforcer;
        identifier = s;
        handlerFinder = handlerfinder;
    }

    public Bus(String s)
    {
        this(ThreadEnforcer.MAIN, s);
    }

    private void dispatchProducerResultToHandler(EventHandler eventhandler, EventProducer eventproducer)
    {
        Object obj1 = eventproducer.produceEvent();
        Object obj = obj1;
_L1:
        InvocationTargetException invocationtargetexception;
        if(obj == null)
        {
            return;
        } else
        {
            dispatch(obj, eventhandler);
            return;
        }
        invocationtargetexception;
        throwRuntimeException((new StringBuilder()).append("Producer ").append(eventproducer).append(" threw an exception.").toString(), invocationtargetexception);
        obj = null;
          goto _L1
    }

    private Set getClassesFor(Class class1)
    {
        LinkedList linkedlist = new LinkedList();
        HashSet hashset = new HashSet();
        linkedlist.add(class1);
        do
        {
            if(linkedlist.isEmpty())
                break;
            Class class2 = (Class)linkedlist.remove(0);
            hashset.add(class2);
            Class class3 = class2.getSuperclass();
            if(class3 != null)
                linkedlist.add(class3);
        } while(true);
        return hashset;
    }

    private static void throwRuntimeException(String s, InvocationTargetException invocationtargetexception)
    {
        Throwable throwable = invocationtargetexception.getCause();
        if(throwable != null)
            throw new RuntimeException(s, throwable);
        else
            throw new RuntimeException(s);
    }

    protected void dispatch(Object obj, EventHandler eventhandler)
    {
        try
        {
            eventhandler.handleEvent(obj);
            return;
        }
        catch(InvocationTargetException invocationtargetexception)
        {
            throwRuntimeException((new StringBuilder()).append("Could not dispatch event: ").append(obj.getClass()).append(" to handler ").append(eventhandler).toString(), invocationtargetexception);
        }
    }

    protected void dispatchQueuedEvents()
    {
        if(((Boolean)isDispatching.get()).booleanValue())
            return;
        isDispatching.set(Boolean.valueOf(true));
_L2:
        EventWithHandler eventwithhandler = (EventWithHandler)((ConcurrentLinkedQueue)eventsToDispatch.get()).poll();
        if(eventwithhandler == null)
        {
            isDispatching.set(Boolean.valueOf(false));
            return;
        }
        if(!eventwithhandler.handler.isValid()) goto _L2; else goto _L1
_L1:
        dispatch(eventwithhandler.event, eventwithhandler.handler);
          goto _L2
        Exception exception;
        exception;
        isDispatching.set(Boolean.valueOf(false));
        throw exception;
    }

    protected void enqueueEvent(Object obj, EventHandler eventhandler)
    {
        ((ConcurrentLinkedQueue)eventsToDispatch.get()).offer(new EventWithHandler(obj, eventhandler));
    }

    Set flattenHierarchy(Class class1)
    {
        Set set = (Set)flattenHierarchyCache.get(class1);
        if(set == null)
        {
            set = getClassesFor(class1);
            flattenHierarchyCache.put(class1, set);
        }
        return set;
    }

    Set getHandlersForEventType(Class class1)
    {
        return (Set)handlersByType.get(class1);
    }

    EventProducer getProducerForEventType(Class class1)
    {
        return (EventProducer)producersByType.get(class1);
    }

    public void post(Object obj)
    {
        if(obj == null)
            throw new NullPointerException("Event to post must not be null.");
        enforcer.enforce(this);
        Set set = flattenHierarchy(obj.getClass());
        boolean flag = false;
        Iterator iterator = set.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Set set1 = getHandlersForEventType((Class)iterator.next());
            if(set1 != null && !set1.isEmpty())
            {
                flag = true;
                Iterator iterator1 = set1.iterator();
                while(iterator1.hasNext()) 
                    enqueueEvent(obj, (EventHandler)iterator1.next());
            }
        } while(true);
        if(!flag && !(obj instanceof DeadEvent))
            post(new DeadEvent(this, obj));
        dispatchQueuedEvents();
    }

    public void register(Object obj)
    {
        Iterator iterator2;
        if(obj == null)
            throw new NullPointerException("Object to register must not be null.");
        enforcer.enforce(this);
        Map map = handlerFinder.findAllProducers(obj);
        Iterator iterator = map.keySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Class class3 = (Class)iterator.next();
            EventProducer eventproducer1 = (EventProducer)map.get(class3);
            EventProducer eventproducer2 = (EventProducer)producersByType.putIfAbsent(class3, eventproducer1);
            if(eventproducer2 != null)
                throw new IllegalArgumentException((new StringBuilder()).append("Producer method for type ").append(class3).append(" found on type ").append(eventproducer1.target.getClass()).append(", but already registered by type ").append(eventproducer2.target.getClass()).append(".").toString());
            Set set = (Set)handlersByType.get(class3);
            if(set != null && !set.isEmpty())
            {
                Iterator iterator4 = set.iterator();
                while(iterator4.hasNext()) 
                    dispatchProducerResultToHandler((EventHandler)iterator4.next(), eventproducer1);
            }
        } while(true);
        Map map1 = handlerFinder.findAllSubscribers(obj);
        Class class2;
        Object obj1;
        for(Iterator iterator1 = map1.keySet().iterator(); iterator1.hasNext(); ((Set) (obj1)).addAll((Set)map1.get(class2)))
        {
            class2 = (Class)iterator1.next();
            obj1 = (Set)handlersByType.get(class2);
            if(obj1 != null)
                continue;
            CopyOnWriteArraySet copyonwritearrayset = new CopyOnWriteArraySet();
            obj1 = (Set)handlersByType.putIfAbsent(class2, copyonwritearrayset);
            if(obj1 == null)
                obj1 = copyonwritearrayset;
        }

        iterator2 = map1.entrySet().iterator();
_L2:
        EventProducer eventproducer;
        Iterator iterator3;
        java.util.Map.Entry entry;
        do
        {
            if(!iterator2.hasNext())
                break MISSING_BLOCK_LABEL_513;
            entry = (java.util.Map.Entry)iterator2.next();
            Class class1 = (Class)entry.getKey();
            eventproducer = (EventProducer)producersByType.get(class1);
        } while(eventproducer == null || !eventproducer.isValid());
        iterator3 = ((Set)entry.getValue()).iterator();
_L4:
        if(!iterator3.hasNext()) goto _L2; else goto _L1
_L1:
        EventHandler eventhandler = (EventHandler)iterator3.next();
        if(!eventproducer.isValid()) goto _L2; else goto _L3
_L3:
        if(eventhandler.isValid())
            dispatchProducerResultToHandler(eventhandler, eventproducer);
          goto _L4
    }

    public String toString()
    {
        return (new StringBuilder()).append("[Bus \"").append(identifier).append("\"]").toString();
    }

    public void unregister(Object obj)
    {
        if(obj == null)
            throw new NullPointerException("Object to unregister must not be null.");
        enforcer.enforce(this);
        Class class1;
        for(Iterator iterator = handlerFinder.findAllProducers(obj).entrySet().iterator(); iterator.hasNext(); ((EventProducer)producersByType.remove(class1)).invalidate())
        {
            java.util.Map.Entry entry1 = (java.util.Map.Entry)iterator.next();
            class1 = (Class)entry1.getKey();
            EventProducer eventproducer = getProducerForEventType(class1);
            EventProducer eventproducer1 = (EventProducer)entry1.getValue();
            if(eventproducer1 == null || !eventproducer1.equals(eventproducer))
                throw new IllegalArgumentException((new StringBuilder()).append("Missing event producer for an annotated method. Is ").append(obj.getClass()).append(" registered?").toString());
        }

        Set set;
        Collection collection;
label0:
        for(Iterator iterator1 = handlerFinder.findAllSubscribers(obj).entrySet().iterator(); iterator1.hasNext(); set.removeAll(collection))
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator1.next();
            set = getHandlersForEventType((Class)entry.getKey());
            collection = (Collection)entry.getValue();
            if(set == null || !set.containsAll(collection))
                throw new IllegalArgumentException((new StringBuilder()).append("Missing event handler for an annotated method. Is ").append(obj.getClass()).append(" registered?").toString());
            Iterator iterator2 = set.iterator();
            do
            {
                if(!iterator2.hasNext())
                    continue label0;
                EventHandler eventhandler = (EventHandler)iterator2.next();
                if(collection.contains(eventhandler))
                    eventhandler.invalidate();
            } while(true);
        }

    }

    public static final String DEFAULT_IDENTIFIER = "default";
    private final ThreadEnforcer enforcer;
    private final ThreadLocal eventsToDispatch;
    private final Map flattenHierarchyCache;
    private final HandlerFinder handlerFinder;
    private final ConcurrentMap handlersByType;
    private final String identifier;
    private final ThreadLocal isDispatching;
    private final ConcurrentMap producersByType;
}
