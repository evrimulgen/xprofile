// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.exceptions;

import java.util.*;

public final class CompositeException extends RuntimeException
{
    static final class CompositeExceptionCausalChain extends RuntimeException
    {

        public String getMessage()
        {
            return MESSAGE;
        }

        static String MESSAGE = "Chain of Causes for CompositeException In Order Received =>";
        private static final long serialVersionUID = 0x35c7853e403cebd2L;


        CompositeExceptionCausalChain()
        {
        }
    }


    public CompositeException(String s, Collection collection)
    {
        ArrayList arraylist = new ArrayList();
        CompositeExceptionCausalChain compositeexceptioncausalchain = new CompositeExceptionCausalChain();
        int i = 0;
        Throwable throwable;
        for(Iterator iterator = collection.iterator(); iterator.hasNext(); arraylist.add(throwable))
        {
            throwable = (Throwable)iterator.next();
            i++;
            attachCallingThreadStack(compositeexceptioncausalchain, throwable);
        }

        exceptions = Collections.unmodifiableList(arraylist);
        message = (new StringBuilder()).append(i).append(" exceptions occurred. See them in causal chain below.").toString();
        cause = compositeexceptioncausalchain;
    }

    public CompositeException(Collection collection)
    {
        this(null, collection);
    }

    static void attachCallingThreadStack(Throwable throwable, Throwable throwable1)
    {
        HashSet hashset = new HashSet();
        do
        {
label0:
            {
                if(throwable.getCause() != null)
                {
                    throwable = throwable.getCause();
                    if(!hashset.contains(throwable.getCause()))
                        break label0;
                }
                try
                {
                    throwable.initCause(throwable1);
                    return;
                }
                catch(Throwable throwable2)
                {
                    return;
                }
            }
            hashset.add(throwable.getCause());
        } while(true);
    }

    private static String getStackTraceAsString(StackTraceElement astacktraceelement[])
    {
        StringBuilder stringbuilder = new StringBuilder();
        boolean flag = true;
        int i = astacktraceelement.length;
        int j = 0;
        while(j < i) 
        {
            StackTraceElement stacktraceelement = astacktraceelement[j];
            if(!stacktraceelement.toString().startsWith("java.lang.Thread.getStackTrace"))
            {
                if(!flag)
                    stringbuilder.append("\n\t");
                stringbuilder.append(stacktraceelement.toString());
                flag = false;
            }
            j++;
        }
        return stringbuilder.toString();
    }

    public Throwable getCause()
    {
        this;
        JVM INSTR monitorenter ;
        Throwable throwable = cause;
        this;
        JVM INSTR monitorexit ;
        return throwable;
        Exception exception;
        exception;
        throw exception;
    }

    public List getExceptions()
    {
        return exceptions;
    }

    public String getMessage()
    {
        return message;
    }

    private static final long serialVersionUID = 0x29ffcc6947b49592L;
    private final Throwable cause;
    private final List exceptions;
    private final String message;
}
