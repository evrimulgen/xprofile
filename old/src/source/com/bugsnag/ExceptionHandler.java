// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.bugsnag;


// Referenced classes of package com.bugsnag:
//            Client

final class ExceptionHandler
    implements Thread.UncaughtExceptionHandler
{

    private ExceptionHandler(Thread.UncaughtExceptionHandler uncaughtexceptionhandler, Client client1)
    {
        originalHandler = uncaughtexceptionhandler;
        client = client1;
    }

    public static void install(Client client1)
    {
        Thread.UncaughtExceptionHandler uncaughtexceptionhandler = Thread.getDefaultUncaughtExceptionHandler();
        if(uncaughtexceptionhandler instanceof ExceptionHandler)
            uncaughtexceptionhandler = ((ExceptionHandler)uncaughtexceptionhandler).originalHandler;
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(uncaughtexceptionhandler, client1));
    }

    public final void uncaughtException(Thread thread, Throwable throwable)
    {
        client.autoNotify(throwable);
        originalHandler.uncaughtException(thread, throwable);
    }

    private Client client;
    private Thread.UncaughtExceptionHandler originalHandler;
}
