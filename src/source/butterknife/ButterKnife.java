// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package butterknife;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

public class ButterKnife
{
    public static abstract class Finder extends Enum
    {

        public static Finder valueOf(String s)
        {
            return (Finder)Enum.valueOf(butterknife/ButterKnife$Finder, s);
        }

        public static Finder[] values()
        {
            return (Finder[])$VALUES.clone();
        }

        public abstract View findById(Object obj, int i);

        private static final Finder $VALUES[];
        public static final Finder ACTIVITY;
        public static final Finder VIEW;

        static 
        {
            VIEW = new Finder("VIEW", 0) {

                public View findById(Object obj, int i)
                {
                    return ((View)obj).findViewById(i);
                }

            }
;
            ACTIVITY = new Finder("ACTIVITY", 1) {

                public View findById(Object obj, int i)
                {
                    return ((Activity)obj).findViewById(i);
                }

            }
;
            Finder afinder[] = new Finder[2];
            afinder[0] = VIEW;
            afinder[1] = ACTIVITY;
            $VALUES = afinder;
        }

        private Finder(String s, int i)
        {
            super(s, i);
        }

    }

    public static class UnableToInjectException extends RuntimeException
    {

        UnableToInjectException(String s, Throwable throwable)
        {
            super(s, throwable);
        }
    }

    public static class UnableToResetException extends RuntimeException
    {

        UnableToResetException(String s, Throwable throwable)
        {
            super(s, throwable);
        }
    }


    private ButterKnife()
    {
    }

    public static View findById(Activity activity, int i)
    {
        return activity.findViewById(i);
    }

    public static View findById(View view, int i)
    {
        return view.findViewById(i);
    }

    private static Method findInjectorForClass(Class class1)
        throws NoSuchMethodException
    {
        Method method = (Method)INJECTORS.get(class1);
        if(method != null)
        {
            if(debug)
                Log.d("ButterKnife", "HIT: Cached in injector map.");
            return method;
        }
        String s = class1.getName();
        if(s.startsWith("android.") || s.startsWith("java."))
        {
            if(debug)
                Log.d("ButterKnife", "MISS: Reached framework class. Abandoning search.");
            return NO_OP;
        }
        Method method1;
        try
        {
            method1 = Class.forName((new StringBuilder()).append(s).append("$$ViewInjector").toString()).getMethod("inject", new Class[] {
                butterknife/ButterKnife$Finder, class1, java/lang/Object
            });
            if(debug)
                Log.d("ButterKnife", "HIT: Class loaded injection class.");
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            if(debug)
                Log.d("ButterKnife", (new StringBuilder()).append("Not found. Trying superclass ").append(class1.getSuperclass().getName()).toString());
            method1 = findInjectorForClass(class1.getSuperclass());
        }
        INJECTORS.put(class1, method1);
        return method1;
    }

    private static Method findResettersForClass(Class class1)
        throws NoSuchMethodException
    {
        Method method = (Method)RESETTERS.get(class1);
        if(method != null)
        {
            if(debug)
                Log.d("ButterKnife", "HIT: Cached in injector map.");
            return method;
        }
        String s = class1.getName();
        if(s.startsWith("android.") || s.startsWith("java."))
        {
            if(debug)
                Log.d("ButterKnife", "MISS: Reached framework class. Abandoning search.");
            return NO_OP;
        }
        Method method1;
        try
        {
            method1 = Class.forName((new StringBuilder()).append(s).append("$$ViewInjector").toString()).getMethod("reset", new Class[] {
                class1
            });
            if(debug)
                Log.d("ButterKnife", "HIT: Class loaded injection class.");
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            if(debug)
                Log.d("ButterKnife", (new StringBuilder()).append("Not found. Trying superclass ").append(class1.getSuperclass().getName()).toString());
            method1 = findResettersForClass(class1.getSuperclass());
        }
        RESETTERS.put(class1, method1);
        return method1;
    }

    public static void inject(Activity activity)
    {
        inject(activity, activity, Finder.ACTIVITY);
    }

    public static void inject(View view)
    {
        inject(view, view, Finder.VIEW);
    }

    public static void inject(Object obj, Activity activity)
    {
        inject(obj, activity, Finder.ACTIVITY);
    }

    public static void inject(Object obj, View view)
    {
        inject(obj, view, Finder.VIEW);
    }

    static void inject(Object obj, Object obj1, Finder finder)
    {
        Class class1 = obj.getClass();
        Method method;
        try
        {
            if(debug)
                Log.d("ButterKnife", (new StringBuilder()).append("Looking up view injector for ").append(class1.getName()).toString());
            method = findInjectorForClass(class1);
        }
        catch(RuntimeException runtimeexception)
        {
            throw runtimeexception;
        }
        catch(Exception exception)
        {
            throw new UnableToInjectException((new StringBuilder()).append("Unable to inject views for ").append(obj).toString(), exception);
        }
        if(method == null)
            break MISSING_BLOCK_LABEL_73;
        method.invoke(null, new Object[] {
            finder, obj, obj1
        });
    }

    public static void reset(Object obj)
    {
        Class class1 = obj.getClass();
        Method method;
        try
        {
            if(debug)
                Log.d("ButterKnife", (new StringBuilder()).append("Looking up view injector for ").append(class1.getName()).toString());
            method = findResettersForClass(class1);
        }
        catch(RuntimeException runtimeexception)
        {
            throw runtimeexception;
        }
        catch(Exception exception)
        {
            throw new UnableToResetException((new StringBuilder()).append("Unable to reset views for ").append(obj).toString(), exception);
        }
        if(method == null)
            break MISSING_BLOCK_LABEL_65;
        method.invoke(null, new Object[] {
            obj
        });
    }

    public static void setDebug(boolean flag)
    {
        debug = flag;
    }

    static final Map INJECTORS = new LinkedHashMap();
    static final Method NO_OP = null;
    static final Map RESETTERS = new LinkedHashMap();
    private static final String TAG = "ButterKnife";
    private static boolean debug = false;

}
