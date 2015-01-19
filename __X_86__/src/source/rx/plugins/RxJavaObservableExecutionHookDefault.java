// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.plugins;


// Referenced classes of package rx.plugins:
//            RxJavaObservableExecutionHook

class RxJavaObservableExecutionHookDefault extends RxJavaObservableExecutionHook
{

    RxJavaObservableExecutionHookDefault()
    {
    }

    public static RxJavaObservableExecutionHook getInstance()
    {
        return INSTANCE;
    }

    private static RxJavaObservableExecutionHookDefault INSTANCE = new RxJavaObservableExecutionHookDefault();

}
