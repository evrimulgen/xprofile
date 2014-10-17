// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package crittercism.android;

import android.location.Location;
import android.os.*;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.StatusCreator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.DataHolderCreator;
import com.google.android.gms.internal.*;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import java.io.*;
import java.net.InetAddress;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import org.json.JSONArray;

// Referenced classes of package crittercism.android:
//            bf, ce, k, g, 
//            dp, b

public final class b extends bf
{
    public static final class a extends Binder
        implements b
    {
        private static class a
            implements b
        {

            public void L(Status status)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusCallbacks");
                if(status == null)
                    break MISSING_BLOCK_LABEL_57;
                parcel.writeInt(1);
                status.writeToParcel(parcel, 0);
_L1:
                ky.transact(10, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
                parcel.writeInt(0);
                  goto _L1
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
            }

            public void a(int i1, Bundle bundle, Bundle bundle1)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusCallbacks");
                parcel.writeInt(i1);
                if(bundle == null) goto _L2; else goto _L1
_L1:
                parcel.writeInt(1);
                bundle.writeToParcel(parcel, 0);
_L3:
                if(bundle1 == null)
                    break MISSING_BLOCK_LABEL_113;
                parcel.writeInt(1);
                bundle1.writeToParcel(parcel, 0);
_L4:
                ky.transact(1, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
_L2:
                parcel.writeInt(0);
                  goto _L3
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
                parcel.writeInt(0);
                  goto _L4
            }

            public void a(int i1, Bundle bundle, ParcelFileDescriptor parcelfiledescriptor)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusCallbacks");
                parcel.writeInt(i1);
                if(bundle == null) goto _L2; else goto _L1
_L1:
                parcel.writeInt(1);
                bundle.writeToParcel(parcel, 0);
_L3:
                if(parcelfiledescriptor == null)
                    break MISSING_BLOCK_LABEL_113;
                parcel.writeInt(1);
                parcelfiledescriptor.writeToParcel(parcel, 0);
_L4:
                ky.transact(2, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
_L2:
                parcel.writeInt(0);
                  goto _L3
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
                parcel.writeInt(0);
                  goto _L4
            }

            public void a(int i1, Bundle bundle, fh fh1)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusCallbacks");
                parcel.writeInt(i1);
                if(bundle == null) goto _L2; else goto _L1
_L1:
                parcel.writeInt(1);
                bundle.writeToParcel(parcel, 0);
_L3:
                if(fh1 == null)
                    break MISSING_BLOCK_LABEL_113;
                parcel.writeInt(1);
                fh1.writeToParcel(parcel, 0);
_L4:
                ky.transact(5, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
_L2:
                parcel.writeInt(0);
                  goto _L3
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
                parcel.writeInt(0);
                  goto _L4
            }

            public void a(int i1, ir ir1)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusCallbacks");
                parcel.writeInt(i1);
                if(ir1 == null)
                    break MISSING_BLOCK_LABEL_66;
                parcel.writeInt(1);
                ir1.writeToParcel(parcel, 0);
_L1:
                ky.transact(9, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
                parcel.writeInt(0);
                  goto _L1
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
            }

            public void a(DataHolder dataholder, String s)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusCallbacks");
                if(dataholder == null)
                    break MISSING_BLOCK_LABEL_65;
                parcel.writeInt(1);
                dataholder.writeToParcel(parcel, 0);
_L1:
                parcel.writeString(s);
                ky.transact(4, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
                parcel.writeInt(0);
                  goto _L1
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
            }

            public void a(DataHolder dataholder, String s, String s1)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusCallbacks");
                if(dataholder == null)
                    break MISSING_BLOCK_LABEL_79;
                parcel.writeInt(1);
                dataholder.writeToParcel(parcel, 0);
_L1:
                parcel.writeString(s);
                parcel.writeString(s1);
                ky.transact(6, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
                parcel.writeInt(0);
                  goto _L1
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
            }

            public void aP(String s)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusCallbacks");
                parcel.writeString(s);
                ky.transact(3, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
            }

            public void aQ(String s)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusCallbacks");
                parcel.writeString(s);
                ky.transact(8, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
            }

            public IBinder asBinder()
            {
                return ky;
            }

            public void d(int i1, Bundle bundle)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusCallbacks");
                parcel.writeInt(i1);
                if(bundle == null)
                    break MISSING_BLOCK_LABEL_66;
                parcel.writeInt(1);
                bundle.writeToParcel(parcel, 0);
_L1:
                ky.transact(7, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
                parcel.writeInt(0);
                  goto _L1
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
            }

            private IBinder ky;

            a(IBinder ibinder)
            {
                ky = ibinder;
            }
        }


        public static b ay(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            android.os.IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
            if(iinterface != null && (iinterface instanceof b))
                return (b)iinterface;
            else
                return new a(ibinder);
        }

        public IBinder asBinder()
        {
            return this;
        }

        public boolean onTransact(int i1, Parcel parcel, Parcel parcel1, int j1)
            throws RemoteException
        {
            int k1;
            Status status;
            switch(i1)
            {
            default:
                return super.onTransact(i1, parcel, parcel1, j1);

            case 1598968902: 
                parcel1.writeString("com.google.android.gms.plus.internal.IPlusCallbacks");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
                int l3 = parcel.readInt();
                Bundle bundle3;
                Bundle bundle4;
                if(parcel.readInt() != 0)
                    bundle3 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle3 = null;
                if(parcel.readInt() != 0)
                    bundle4 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle4 = null;
                a(l3, bundle3, bundle4);
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
                int k3 = parcel.readInt();
                Bundle bundle2;
                ParcelFileDescriptor parcelfiledescriptor;
                if(parcel.readInt() != 0)
                    bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle2 = null;
                if(parcel.readInt() != 0)
                    parcelfiledescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcelfiledescriptor = null;
                a(k3, bundle2, parcelfiledescriptor);
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
                aP(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
                int j3 = parcel.readInt();
                DataHolder dataholder1 = null;
                if(j3 != 0)
                    dataholder1 = DataHolder.CREATOR.createFromParcel(parcel);
                a(dataholder1, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
                int l2 = parcel.readInt();
                Bundle bundle1;
                int i3;
                fh fh1;
                if(parcel.readInt() != 0)
                    bundle1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle1 = null;
                i3 = parcel.readInt();
                fh1 = null;
                if(i3 != 0)
                    fh1 = fh.CREATOR.x(parcel);
                a(l2, bundle1, fh1);
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
                int k2 = parcel.readInt();
                DataHolder dataholder = null;
                if(k2 != 0)
                    dataholder = DataHolder.CREATOR.createFromParcel(parcel);
                a(dataholder, parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
                int j2 = parcel.readInt();
                Bundle bundle;
                if(parcel.readInt() != 0)
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle = null;
                d(j2, bundle);
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
                aQ(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
                int l1 = parcel.readInt();
                int i2 = parcel.readInt();
                ir ir1 = null;
                if(i2 != 0)
                    ir1 = ir.CREATOR.aI(parcel);
                a(l1, ir1);
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
                k1 = parcel.readInt();
                status = null;
                break;
            }
            if(k1 != 0)
                status = Status.CREATOR.createFromParcel(parcel);
            L(status);
            parcel1.writeNoException();
            return true;
        }

        public a()
        {
            attachInterface(this, "com.google.android.gms.plus.internal.IPlusCallbacks");
        }
    }


    public b()
    {
        a = 0x7fffffffffffffffL;
        k = 0x7fffffffffffffffL;
        l = false;
        m = false;
        b = false;
        c = a.a;
        n = 0L;
        d = 0L;
        o = false;
        p = false;
        e = 0;
        f = "";
        g = ce.a;
        h = new k();
        j = g.a;
    }

    public b(String s)
    {
        a = 0x7fffffffffffffffL;
        k = 0x7fffffffffffffffL;
        l = false;
        m = false;
        b = false;
        c = a.a;
        n = 0L;
        d = 0L;
        o = false;
        p = false;
        e = 0;
        f = "";
        g = ce.a;
        h = new k();
        j = g.a;
        if(s != null)
            i = s;
    }

    public b(URL url)
    {
        a = 0x7fffffffffffffffL;
        k = 0x7fffffffffffffffL;
        l = false;
        m = false;
        b = false;
        c = a.a;
        n = 0L;
        d = 0L;
        o = false;
        p = false;
        e = 0;
        f = "";
        g = ce.a;
        h = new k();
        j = g.a;
        if(url != null)
            i = url.toExternalForm();
    }

    private long f()
    {
        long l1 = 0x7fffffffffffffffL;
        if(a != l1 && k != l1)
            l1 = k - a;
        return l1;
    }

    public final String a()
    {
        boolean flag = true;
        String s = i;
        if(s == null)
        {
            k k1 = h;
            if(k1.b != null)
                s = k1.b;
            else
            if(k1.a != null)
                s = k1.a.getHostName();
            else
                s = "unknown-host";
            if(k1.f)
            {
                int i1 = k1.e;
                if(i1 > 0)
                {
                    String s6 = (new StringBuilder(":")).append(i1).toString();
                    if(!s.endsWith(s6))
                        s = (new StringBuilder()).append(s).append(s6).toString();
                }
            } else
            {
                String s1 = k1.c;
                if(s1 == null || !s1.regionMatches(flag, 0, "http:", 0, 5) && !s1.regionMatches(flag, 0, "https:", 0, 6))
                    flag = false;
                if(flag)
                {
                    s = s1;
                } else
                {
                    String s2;
                    if(k1.d != null)
                        s2 = (new StringBuilder()).append("").append(crittercism.android.k.a.a(k1.d)).append(":").toString();
                    else
                        s2 = "";
                    if(s1.startsWith("//"))
                    {
                        s = (new StringBuilder()).append(s2).append(s1).toString();
                    } else
                    {
                        String s3 = (new StringBuilder()).append(s2).append("//").toString();
                        if(s1.startsWith(s))
                        {
                            s = (new StringBuilder()).append(s3).append(s1).toString();
                        } else
                        {
                            String s4 = "";
                            if(k1.e > 0 && (k1.d == null || crittercism.android.k.a.b(k1.d) != k1.e))
                            {
                                String s5 = (new StringBuilder(":")).append(k1.e).toString();
                                if(!s.endsWith(s5))
                                    s4 = s5;
                            }
                            s = (new StringBuilder()).append(s3).append(s).append(s4).append(s1).toString();
                        }
                    }
                }
            }
            i = s;
        }
        return s;
    }

    public final void a(int i1)
    {
        k k1 = h;
        if(i1 > 0)
            k1.e = i1;
    }

    public final void a(long l1)
    {
        if(!o)
            n = l1 + n;
    }

    public final void a(Location location)
    {
        double ad[] = new double[2];
        ad[0] = location.getLatitude();
        ad[1] = location.getLongitude();
        q = ad;
    }

    public final void a(k.a a1)
    {
        h.d = a1;
    }

    public final void a(OutputStream outputstream)
    {
        JSONArray jsonarray = d();
        OutputStreamWriter outputstreamwriter = new OutputStreamWriter(outputstream);
        String s;
        if(!(jsonarray instanceof JSONArray))
            s = jsonarray.toString();
        else
            s = JSONArrayInstrumentation.toString((JSONArray)jsonarray);
        outputstreamwriter.write(s);
        outputstreamwriter.close();
    }

    public final void a(String s)
    {
        i = null;
        h.b = s;
    }

    public final void a(InetAddress inetaddress)
    {
        i = null;
        h.a = inetaddress;
    }

    public final void b()
    {
        if(!l && a == 0x7fffffffffffffffL)
            a = System.currentTimeMillis();
    }

    public final void b(long l1)
    {
        o = true;
        n = l1;
    }

    public final void c()
    {
        if(!m && k == 0x7fffffffffffffffL)
            k = System.currentTimeMillis();
    }

    public final void c(long l1)
    {
        if(!p)
            d = l1 + d;
    }

    public final JSONArray d()
    {
        JSONArray jsonarray = new JSONArray();
        try
        {
            jsonarray.put(f);
            jsonarray.put(a());
            jsonarray.put(dp.a.a(new Date(a)));
            jsonarray.put(f());
            jsonarray.put(j.a());
            jsonarray.put(n);
            jsonarray.put(d);
            jsonarray.put(e);
            jsonarray.put(3);
            jsonarray.put(Integer.toString(g.a()));
            if(q != null)
            {
                JSONArray jsonarray1 = new JSONArray();
                jsonarray1.put(q[0]);
                jsonarray1.put(q[1]);
                jsonarray.put(jsonarray1);
            }
        }
        catch(Exception exception)
        {
            System.out.println("Failed to create statsArray");
            exception.printStackTrace();
            return null;
        }
        return jsonarray;
    }

    public final void d(long l1)
    {
        p = true;
        d = l1;
    }

    public final void e()
    {
        h.f = true;
    }

    public final void e(long l1)
    {
        a = l1;
        l = true;
    }

    public final void f(long l1)
    {
        k = l1;
        m = true;
    }

    public final String toString()
    {
        String s = (new StringBuilder()).append("").append("URI            : ").append(i).append("\n").toString();
        String s1 = (new StringBuilder()).append(s).append("URI Builder    : ").append(h.toString()).append("\n").toString();
        String s2 = (new StringBuilder()).append(s1).append("\n").toString();
        String s3 = (new StringBuilder()).append(s2).append("Logged by      : ").append(c.toString()).append("\n").toString();
        String s4 = (new StringBuilder()).append(s3).append("Error:         : ").append(g).append("\n").toString();
        String s5 = (new StringBuilder()).append(s4).append("\n").toString();
        String s6 = (new StringBuilder()).append(s5).append("Response time  : ").append(f()).append("\n").toString();
        String s7 = (new StringBuilder()).append(s6).append("Start time     : ").append(a).append("\n").toString();
        String s8 = (new StringBuilder()).append(s7).append("End time       : ").append(k).append("\n").toString();
        String s9 = (new StringBuilder()).append(s8).append("\n").toString();
        String s10 = (new StringBuilder()).append(s9).append("Bytes out    : ").append(d).append("\n").toString();
        String s11 = (new StringBuilder()).append(s10).append("Bytes in     : ").append(n).append("\n").toString();
        String s12 = (new StringBuilder()).append(s11).append("\n").toString();
        String s13 = (new StringBuilder()).append(s12).append("Response code  : ").append(e).append("\n").toString();
        String s14 = (new StringBuilder()).append(s13).append("Request method : ").append(f).append("\n").toString();
        if(q != null)
            s14 = (new StringBuilder()).append(s14).append("Location       : ").append(Arrays.toString(q)).append("\n").toString();
        return s14;
    }

    public long a;
    public boolean b;
    a c;
    public long d;
    public int e;
    public String f;
    public ce g;
    public k h;
    public String i;
    public g j;
    private long k;
    private boolean l;
    private boolean m;
    private long n;
    private boolean o;
    private boolean p;
    private double q[];
}
