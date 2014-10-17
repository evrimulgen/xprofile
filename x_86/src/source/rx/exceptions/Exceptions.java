// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.exceptions;

import java.util.HashSet;
import java.util.Set;

// Referenced classes of package rx.exceptions:
//            OnErrorNotImplementedException, OnErrorFailedException

public class Exceptions
{

    private Exceptions()
    {
    }

    public static void addCause(Throwable throwable, Throwable throwable1)
    {
        HashSet hashset = new HashSet();
        int i = 0;
        do
        {
            int j;
label0:
            {
                if(throwable.getCause() != null)
                {
                    j = i + 1;
                    if(i >= 25)
                        return;
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
            i = j;
        } while(true);
    }

    public static Throwable getFinalCause(Throwable throwable)
    {
        int i = 0;
        do
        {
            int j;
label0:
            {
                if(throwable.getCause() != null)
                {
                    j = i + 1;
                    if(i < 25)
                        break label0;
                    throwable = new RuntimeException("Stack too deep to get final cause");
                }
                return throwable;
            }
            throwable = throwable.getCause();
            i = j;
        } while(true);
    }

    public static RuntimeException propagate(Throwable throwable)
    {
        if(throwable instanceof RuntimeException)
            throw (RuntimeException)throwable;
        if(throwable instanceof Error)
            throw (Error)throwable;
        else
            throw new RuntimeException(throwable);
    }

    public static void throwIfFatal(Throwable throwable)
    {
        if(throwable instanceof OnErrorNotImplementedException)
            throw (OnErrorNotImplementedException)throwable;
        if(throwable instanceof OnErrorFailedException)
        {
            Throwable throwable1 = ((OnErrorFailedException)throwable).getCause();
            if(throwable1 instanceof RuntimeException)
                throw (RuntimeException)throwable1;
            else
                throw (OnErrorFailedException)throwable;
        }
        if(throwable instanceof StackOverflowError)
            throw (StackOverflowError)throwable;
        if(throwable instanceof VirtualMachineError)
            throw (VirtualMachineError)throwable;
        if(throwable instanceof ThreadDeath)
            throw (ThreadDeath)throwable;
        if(throwable instanceof LinkageError)
            throw (LinkageError)throwable;
        else
            return;
    }

    private static final int MAX_DEPTH = 25;
}
