// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.plugins;


// Referenced classes of package rx.plugins:
//            RxJavaErrorHandler

public class RxJavaErrorHandlerDefault extends RxJavaErrorHandler
{

    public RxJavaErrorHandlerDefault()
    {
    }

    public static RxJavaErrorHandler getInstance()
    {
        return INSTANCE;
    }

    private static RxJavaErrorHandlerDefault INSTANCE = new RxJavaErrorHandlerDefault();

}
