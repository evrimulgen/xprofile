// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.tapstream.sdk;

import java.io.PrintStream;
import java.lang.reflect.Method;

// Referenced classes of package com.tapstream.sdk:
//            Logger

public class Logging
{
    private static class DefaultLogger
        implements Logger
    {

        public void log(int i, String s)
        {
            System.out.println(s);
        }

        private DefaultLogger()
        {
        }

    }


    public Logging()
    {
    }

    public static transient void log(int i, String s, Object aobj[])
    {
        com/tapstream/sdk/Logging;
        JVM INSTR monitorenter ;
        if(logger == null) goto _L2; else goto _L1
_L1:
        String s1 = (String)formatMethod.invoke(null, new Object[] {
            s, aobj
        });
_L3:
        logger.log(i, s1);
_L2:
        com/tapstream/sdk/Logging;
        JVM INSTR monitorexit ;
        return;
        Exception exception1;
        exception1;
        exception1.printStackTrace();
        s1 = "";
          goto _L3
        Exception exception;
        exception;
        throw exception;
    }

    public static void setLogger(Logger logger1)
    {
        com/tapstream/sdk/Logging;
        JVM INSTR monitorenter ;
        logger = logger1;
        com/tapstream/sdk/Logging;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int WARN = 5;
    private static Method formatMethod;
    private static Logger logger = new DefaultLogger();

    static 
    {
        try
        {
            formatMethod = java/lang/String.getDeclaredMethod("format", new Class[] {
                java/lang/String, [Ljava/lang/Object;
            });
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }
}
