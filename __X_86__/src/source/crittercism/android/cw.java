// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package crittercism.android;

import android.os.ConditionVariable;
import java.io.*;

// Referenced classes of package crittercism.android:
//            cv

public final class cw extends cv
{
    public static final class a extends Enum
    {

        public static a valueOf(String s)
        {
            return (a)Enum.valueOf(crittercism/android/cw$a, s);
        }

        public static a[] values()
        {
            return (a[])c.clone();
        }

        public static final a a;
        public static final a b;
        private static final a c[];

        static 
        {
            a = new a("STDOUT", 0);
            b = new a("STDERR", 1);
            a aa[] = new a[2];
            aa[0] = a;
            aa[1] = b;
            c = aa;
        }

        private a(String s, int i)
        {
            super(s, i);
        }
    }


    public cw(Process process, ConditionVariable conditionvariable, a a1)
    {
        a = null;
        b = null;
        c = conditionvariable;
        d = process;
        e = new StringBuilder();
        f = a1;
        g = false;
    }

    private void c()
    {
        this;
        JVM INSTR monitorenter ;
        g = true;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void a()
    {
        if(f != a.a) goto _L2; else goto _L1
_L1:
        InputStream inputstream1 = d.getInputStream();
_L4:
        b = inputstream1;
        a = new BufferedReader(new InputStreamReader(b));
_L3:
        String s = a.readLine();
        if(s == null)
            break MISSING_BLOCK_LABEL_81;
        e.append(s).append('\n');
          goto _L3
        IOException ioexception2;
        ioexception2;
_L5:
        Exception exception;
        Exception exception1;
        InputStream inputstream;
        try
        {
            a.close();
        }
        catch(IOException ioexception) { }
        try
        {
            b.close();
        }
        catch(IOException ioexception1) { }
        a = null;
        c();
        if(c != null)
            c.open();
        return;
_L2:
        inputstream = d.getErrorStream();
        inputstream1 = inputstream;
          goto _L4
        exception;
        (new StringBuilder("Exception when attempting to read stream: ")).append(exception.getClass().getName());
          goto _L5
        exception1;
        a = null;
        throw exception1;
          goto _L4
    }

    public final StringBuilder b()
    {
        this;
        JVM INSTR monitorenter ;
        StringBuilder stringbuilder;
        if(!g)
            break MISSING_BLOCK_LABEL_18;
        stringbuilder = e;
_L1:
        this;
        JVM INSTR monitorexit ;
        return stringbuilder;
        stringbuilder = null;
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    private BufferedReader a;
    private InputStream b;
    private ConditionVariable c;
    private Process d;
    private StringBuilder e;
    private a f;
    private boolean g;
}
