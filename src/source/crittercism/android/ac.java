// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package crittercism.android;

import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executor;

// Referenced classes of package crittercism.android:
//            ae, cc, j, b, 
//            c, ba, ca, cd, 
//            d, x, ce, w, 
//            i, ac

public final class ac extends SocketImpl
    implements ae
{

    public ac(d d1, c c1, SocketImpl socketimpl)
    {
        if(d1 == null)
            throw new NullPointerException("dispatch was null");
        if(socketimpl == null)
        {
            throw new NullPointerException("delegate was null");
        } else
        {
            i = d1;
            j = c1;
            k = socketimpl;
            f();
            return;
        }
    }

    private b a(boolean flag)
    {
        b b1 = new b();
        InetAddress inetaddress = getInetAddress();
        if(inetaddress != null)
            b1.a(inetaddress);
        int i1 = getPort();
        if(i1 > 0)
            b1.a(i1);
        if(flag)
            b1.a(k.a.a);
        if(j != null)
            b1.j = j.a();
        if(ba.b())
            b1.a(ba.a());
        return b1;
    }

    private transient Object a(int i1, Object aobj[])
    {
        Object obj;
        try
        {
            a.set(k, address);
            b.set(k, fd);
            c.setInt(k, localport);
            d.setInt(k, port);
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            throw new ca(cc.L);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            throw new ca(cc.M);
        }
        obj = e[i1].invoke(k, aobj);
        f();
        return obj;
        IllegalArgumentException illegalargumentexception1;
        illegalargumentexception1;
        throw new ca(cc.E);
        Exception exception1;
        exception1;
        f();
        throw exception1;
        IllegalAccessException illegalaccessexception1;
        illegalaccessexception1;
        throw new ca(cc.F);
        InvocationTargetException invocationtargetexception;
        invocationtargetexception;
        Throwable throwable = invocationtargetexception.getCause();
        if(throwable != null)
            break MISSING_BLOCK_LABEL_163;
        throw new ca(cc.G);
        if(throwable instanceof Exception)
            throw (Exception)throwable;
        if(throwable instanceof Error)
            throw (Error)throwable;
        else
            throw new ca(cc.H);
        ClassCastException classcastexception;
        classcastexception;
        throw new ca(cc.K);
        Exception exception;
        exception;
        throw new ca(cc.T);
    }

    private transient Object b(int i1, Object aobj[])
    {
        Object obj;
        try
        {
            obj = a(i1, aobj);
        }
        catch(ca ca1)
        {
            throw ca1;
        }
        catch(Exception exception)
        {
            throw new ca(new cd(cc.I, (new StringBuilder("Unexpected exception: ")).append(exception.getClass().getName()).toString()));
        }
        return obj;
    }

    private transient Object c(int i1, Object aobj[])
    {
        Object obj;
        try
        {
            obj = a(i1, aobj);
        }
        catch(IOException ioexception)
        {
            throw ioexception;
        }
        catch(ca ca1)
        {
            throw ca1;
        }
        catch(Exception exception)
        {
            throw new ca(new cd(cc.I, (new StringBuilder("Unexpected exception: ")).append(exception.getClass().getName()).toString()));
        }
        return obj;
    }

    public static boolean c()
    {
        return f;
    }

    public static cc d()
    {
        return g;
    }

    public static void e()
    {
        if(!f)
            throw new ca(g);
        com.google.android.gms.analytics.i i1 = new i() {

            public long currentTimeMillis()
            {
                return System.currentTimeMillis();
            }

            final ac uh;

            
            {
                uh = ac1;
                super();
            }
        }
;
        ac ac1 = new ac(new d(new Executor() {

            public final void execute(Runnable runnable)
            {
            }

        }
), null, i1);
        Object obj = new Object();
        try
        {
            ac1.setOption(0, obj);
            ac1.getOption(0);
            ac1.sendUrgentData(0);
            ac1.listen(0);
            ac1.getOutputStream();
            ac1.getInputStream();
            ac1.create(false);
            ac1.connect(((SocketAddress) (null)), 0);
            ac1.connect(((InetAddress) (null)), 0);
            ac1.connect(((String) (null)), 0);
            ac1.close();
            ac1.bind(null, 0);
            ac1.available();
            ac1.accept(ac1);
            ac1.getFileDescriptor();
            ac1.getInetAddress();
            ac1.getLocalPort();
            ac1.getPort();
            ac1.setPerformancePreferences(0, 0, 0);
            ac1.shutdownInput();
            ac1.shutdownOutput();
            ac1.supportsUrgentData();
            return;
        }
        catch(IOException ioexception)
        {
            return;
        }
        catch(ca ca1)
        {
            throw ca1;
        }
        catch(Throwable throwable)
        {
            throw new ca(cc.U);
        }
    }

    private void f()
    {
        try
        {
            address = (InetAddress)a.get(k);
            fd = (FileDescriptor)b.get(k);
            localport = c.getInt(k);
            port = d.getInt(k);
            return;
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            throw new ca(cc.N);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            throw new ca(cc.O);
        }
    }

    public final b a()
    {
        return a(true);
    }

    public final void a(b b1)
    {
        synchronized(h)
        {
            h.add(b1);
        }
    }

    public final void accept(SocketImpl socketimpl)
    {
        c(0, new Object[] {
            socketimpl
        });
    }

    public final int available()
    {
        Integer integer = (Integer)c(1, new Object[0]);
        if(integer == null)
            throw new ca(cc.J);
        else
            return integer.intValue();
    }

    public final b b()
    {
        b b1;
        synchronized(h)
        {
            b1 = (b)h.poll();
        }
        return b1;
    }

    public final void bind(InetAddress inetaddress, int i1)
    {
        Object aobj[] = new Object[2];
        aobj[0] = inetaddress;
        aobj[1] = Integer.valueOf(i1);
        c(2, aobj);
    }

    public final void close()
    {
        c(3, new Object[0]);
        try
        {
            if(m != null)
                m.d();
            return;
        }
        catch(ThreadDeath threaddeath)
        {
            throw threaddeath;
        }
        catch(Throwable throwable)
        {
            return;
        }
    }

    public final void connect(String s, int i1)
    {
        try
        {
            Object aobj[] = new Object[2];
            aobj[0] = s;
            aobj[1] = Integer.valueOf(i1);
            c(6, aobj);
            return;
        }
        catch(IOException ioexception)
        {
            if(s != null)
                try
                {
                    b b1 = a(false);
                    b1.e();
                    b1.a(s);
                    b1.a(i1);
                    b1.g = ce.a(ioexception);
                    i.a(b1, b.a.i);
                }
                catch(ThreadDeath threaddeath)
                {
                    throw threaddeath;
                }
                catch(Throwable throwable) { }
        }
        throw ioexception;
    }

    public final void connect(InetAddress inetaddress, int i1)
    {
        try
        {
            Object aobj[] = new Object[2];
            aobj[0] = inetaddress;
            aobj[1] = Integer.valueOf(i1);
            c(4, aobj);
            return;
        }
        catch(IOException ioexception)
        {
            if(inetaddress != null)
                try
                {
                    b b1 = a(false);
                    b1.e();
                    b1.a(inetaddress);
                    b1.a(i1);
                    b1.g = ce.a(ioexception);
                    i.a(b1, b.a.i);
                }
                catch(ThreadDeath threaddeath)
                {
                    throw threaddeath;
                }
                catch(Throwable throwable) { }
        }
        throw ioexception;
    }

    public final void connect(SocketAddress socketaddress, int i1)
    {
        try
        {
            Object aobj[] = new Object[2];
            aobj[0] = socketaddress;
            aobj[1] = Integer.valueOf(i1);
            c(5, aobj);
            return;
        }
        catch(IOException ioexception)
        {
            if(socketaddress != null)
                try
                {
                    if(socketaddress instanceof InetSocketAddress)
                    {
                        b b1 = a(false);
                        InetSocketAddress inetsocketaddress = (InetSocketAddress)socketaddress;
                        b1.e();
                        b1.a(inetsocketaddress.getAddress());
                        b1.a(inetsocketaddress.getPort());
                        b1.g = ce.a(ioexception);
                        i.a(b1, b.a.i);
                    }
                }
                catch(ThreadDeath threaddeath)
                {
                    throw threaddeath;
                }
                catch(Throwable throwable) { }
        }
        throw ioexception;
    }

    public final void create(boolean flag)
    {
        Object aobj[] = new Object[1];
        aobj[0] = Boolean.valueOf(flag);
        c(7, aobj);
    }

    public final FileDescriptor getFileDescriptor()
    {
        return (FileDescriptor)b(8, new Object[0]);
    }

    public final InetAddress getInetAddress()
    {
        return (InetAddress)b(9, new Object[0]);
    }

    public final InputStream getInputStream()
    {
        InputStream inputstream;
        inputstream = (InputStream)c(10, new Object[0]);
        if(inputstream == null)
            break MISSING_BLOCK_LABEL_71;
        x x1;
        if(m != null && m.a(inputstream))
            return m;
        m = new x(this, inputstream, i);
        x1 = m;
        return x1;
        ThreadDeath threaddeath;
        threaddeath;
        throw threaddeath;
        Throwable throwable;
        throwable;
        return inputstream;
    }

    public final int getLocalPort()
    {
        return ((Integer)b(11, new Object[0])).intValue();
    }

    public final Object getOption(int i1)
    {
        return k.getOption(i1);
    }

    public final OutputStream getOutputStream()
    {
        OutputStream outputstream;
        outputstream = (OutputStream)c(12, new Object[0]);
        if(outputstream == null)
            break MISSING_BLOCK_LABEL_67;
        w w1;
        if(l != null && l.a(outputstream))
            return l;
        l = new w(this, outputstream);
        w1 = l;
        return w1;
        ThreadDeath threaddeath;
        threaddeath;
        throw threaddeath;
        Throwable throwable;
        throwable;
        return outputstream;
    }

    public final int getPort()
    {
        return ((Integer)b(13, new Object[0])).intValue();
    }

    public final void listen(int i1)
    {
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i1);
        c(14, aobj);
    }

    public final void sendUrgentData(int i1)
    {
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i1);
        c(15, aobj);
    }

    public final void setOption(int i1, Object obj)
    {
        k.setOption(i1, obj);
    }

    public final void setPerformancePreferences(int i1, int j1, int k1)
    {
        Object aobj[] = new Object[3];
        aobj[0] = Integer.valueOf(i1);
        aobj[1] = Integer.valueOf(j1);
        aobj[2] = Integer.valueOf(k1);
        b(16, aobj);
    }

    public final void shutdownInput()
    {
        c(17, new Object[0]);
    }

    public final void shutdownOutput()
    {
        c(18, new Object[0]);
    }

    public final boolean supportsUrgentData()
    {
        return ((Boolean)b(19, new Object[0])).booleanValue();
    }

    public final String toString()
    {
        return k.toString();
    }

    private static Field a;
    private static Field b;
    private static Field c;
    private static Field d;
    private static Method e[];
    private static boolean f;
    private static cc g;
    private final Queue h = new LinkedList();
    private d i;
    private c j;
    private SocketImpl k;
    private w l;
    private x m;

    static 
    {
        int i1;
        i1 = 0;
        e = new Method[20];
        f = false;
        g = cc.a;
        Field field;
        AccessibleObject aaccessibleobject[];
        a = java/net/SocketImpl.getDeclaredField("address");
        b = java/net/SocketImpl.getDeclaredField("fd");
        c = java/net/SocketImpl.getDeclaredField("localport");
        d = java/net/SocketImpl.getDeclaredField("port");
        field = a;
        aaccessibleobject = new AccessibleObject[3];
        aaccessibleobject[0] = b;
        aaccessibleobject[1] = c;
        aaccessibleobject[2] = d;
        if(field == null)
            break MISSING_BLOCK_LABEL_103;
        field.setAccessible(true);
        if(aaccessibleobject.length > 0)
            crittercism.android.j.a(aaccessibleobject);
        e[0] = java/net/SocketImpl.getDeclaredMethod("accept", new Class[] {
            java/net/SocketImpl
        });
        e[1] = java/net/SocketImpl.getDeclaredMethod("available", new Class[0]);
        Method amethod[] = e;
        Class aclass[] = new Class[2];
        aclass[0] = java/net/InetAddress;
        aclass[1] = Integer.TYPE;
        amethod[2] = java/net/SocketImpl.getDeclaredMethod("bind", aclass);
        e[3] = java/net/SocketImpl.getDeclaredMethod("close", new Class[0]);
        Method amethod1[] = e;
        Class aclass1[] = new Class[2];
        aclass1[0] = java/net/InetAddress;
        aclass1[1] = Integer.TYPE;
        amethod1[4] = java/net/SocketImpl.getDeclaredMethod("connect", aclass1);
        Method amethod2[] = e;
        Class aclass2[] = new Class[2];
        aclass2[0] = java/net/SocketAddress;
        aclass2[1] = Integer.TYPE;
        amethod2[5] = java/net/SocketImpl.getDeclaredMethod("connect", aclass2);
        Method amethod3[] = e;
        Class aclass3[] = new Class[2];
        aclass3[0] = java/lang/String;
        aclass3[1] = Integer.TYPE;
        amethod3[6] = java/net/SocketImpl.getDeclaredMethod("connect", aclass3);
        Method amethod4[] = e;
        Class aclass4[] = new Class[1];
        aclass4[0] = Boolean.TYPE;
        amethod4[7] = java/net/SocketImpl.getDeclaredMethod("create", aclass4);
        e[8] = java/net/SocketImpl.getDeclaredMethod("getFileDescriptor", new Class[0]);
        e[9] = java/net/SocketImpl.getDeclaredMethod("getInetAddress", new Class[0]);
        e[10] = java/net/SocketImpl.getDeclaredMethod("getInputStream", new Class[0]);
        e[11] = java/net/SocketImpl.getDeclaredMethod("getLocalPort", new Class[0]);
        e[12] = java/net/SocketImpl.getDeclaredMethod("getOutputStream", new Class[0]);
        e[13] = java/net/SocketImpl.getDeclaredMethod("getPort", new Class[0]);
        Method amethod5[] = e;
        Class aclass5[] = new Class[1];
        aclass5[0] = Integer.TYPE;
        amethod5[14] = java/net/SocketImpl.getDeclaredMethod("listen", aclass5);
        Method amethod6[] = e;
        Class aclass6[] = new Class[1];
        aclass6[0] = Integer.TYPE;
        amethod6[15] = java/net/SocketImpl.getDeclaredMethod("sendUrgentData", aclass6);
        Method amethod7[] = e;
        Class aclass7[] = new Class[3];
        aclass7[0] = Integer.TYPE;
        aclass7[1] = Integer.TYPE;
        aclass7[2] = Integer.TYPE;
        amethod7[16] = java/net/SocketImpl.getDeclaredMethod("setPerformancePreferences", aclass7);
        e[17] = java/net/SocketImpl.getDeclaredMethod("shutdownInput", new Class[0]);
        e[18] = java/net/SocketImpl.getDeclaredMethod("shutdownOutput", new Class[0]);
        e[19] = java/net/SocketImpl.getDeclaredMethod("supportsUrgentData", new Class[0]);
        crittercism.android.j.a(e);
        f = true;
        return;
        SecurityException securityexception;
        securityexception;
        f = false;
        g = cc.P;
        return;
        NoSuchMethodException nosuchmethodexception;
        nosuchmethodexception;
        f = false;
        g = cc.Q;
_L3:
        if(i1 >= 20)
            break MISSING_BLOCK_LABEL_763;
        if(e[i1] != null) goto _L2; else goto _L1
_L1:
        g.a(i1);
        return;
_L2:
        i1++;
          goto _L3
        NoSuchFieldException nosuchfieldexception;
        nosuchfieldexception;
        f = false;
        g = cc.R;
        if(a == null)
        {
            g.a(1);
            return;
        }
        if(b == null)
        {
            g.a(2);
            return;
        }
        if(c == null)
        {
            g.a(3);
            return;
        }
        if(d == null)
        {
            g.a(4);
            return;
        } else
        {
            g.a(9999);
            return;
        }
        Throwable throwable;
        throwable;
        f = false;
        g = cc.S;
        return;
        i1 = 20;
          goto _L1
    }
}
