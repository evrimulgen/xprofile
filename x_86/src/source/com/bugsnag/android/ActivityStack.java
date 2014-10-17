// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.bugsnag.android;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import java.lang.ref.WeakReference;
import java.util.*;

public class ActivityStack
{

    public ActivityStack()
    {
    }

    public static void add(Activity activity)
    {
        prune();
        storedContexts.add(new WeakReference(activity));
    }

    public static void clearTopActivity()
    {
        topContext = null;
        inForeground = false;
        lastScreenClosed = SystemClock.elapsedRealtime();
    }

    public static String getContextName(Context context)
    {
        String s = context.getClass().getName();
        return s.substring(1 + s.lastIndexOf('.'));
    }

    public static List getNames()
    {
        prune();
        LinkedList linkedlist = new LinkedList();
        Iterator iterator = storedContexts.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            WeakReference weakreference = (WeakReference)iterator.next();
            if(weakreference.get() != null)
                linkedlist.add(getContextName((Context)weakreference.get()));
        } while(true);
        return linkedlist;
    }

    public static String getTopActivityName()
    {
        Context context;
        if(topContext != null)
            context = (Context)topContext.get();
        else
            context = null;
        if(context != null)
            return getContextName(context);
        else
            return null;
    }

    public static boolean inForeground()
    {
        return inForeground;
    }

    private static void prune()
    {
        LinkedList linkedlist = new LinkedList();
        Iterator iterator = storedContexts.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            WeakReference weakreference1 = (WeakReference)iterator.next();
            if(weakreference1.get() == null)
                linkedlist.add(weakreference1);
        } while(true);
        WeakReference weakreference;
        for(Iterator iterator1 = linkedlist.iterator(); iterator1.hasNext(); storedContexts.remove(weakreference))
            weakreference = (WeakReference)iterator1.next();

    }

    public static void remove(Activity activity)
    {
        prune();
        Iterator iterator = storedContexts.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            WeakReference weakreference = (WeakReference)iterator.next();
            if(weakreference.get() != activity)
                continue;
            storedContexts.remove(weakreference);
            break;
        } while(true);
    }

    public static Long sessionLength()
    {
        if(inForeground)
            return Long.valueOf(SystemClock.elapsedRealtime() - sessionStartTime);
        else
            return null;
    }

    public static void setTopActivity(Activity activity)
    {
        topContext = new WeakReference(activity);
        inForeground = true;
        if(10000L + lastScreenClosed < SystemClock.elapsedRealtime())
            sessionStartTime = SystemClock.elapsedRealtime();
    }

    private static boolean inForeground = false;
    private static long lastScreenClosed = 0L;
    private static long sessionStartTime = 0L;
    private static List storedContexts = new LinkedList();
    private static WeakReference topContext;

}
