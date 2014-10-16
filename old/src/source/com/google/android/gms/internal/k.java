// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.content.Context;
import android.os.*;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.maps.model.internal.d;
import java.io.IOException;

// Referenced classes of package com.google.android.gms.internal:
//            j, e, q, n, 
//            o, k

public class k extends j
{
    class a extends Binder
        implements k
    {
        private static class a
            implements k
        {

            public boolean a(d d1)
                throws RemoteException
            {
                boolean flag;
                Parcel parcel;
                Parcel parcel1;
                flag = true;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IOnMarkerClickListener");
                if(d1 == null)
                    break MISSING_BLOCK_LABEL_78;
                IBinder ibinder = d1.asBinder();
_L1:
                int i;
                parcel.writeStrongBinder(ibinder);
                ky.transact(1, parcel, parcel1, 0);
                parcel1.readException();
                i = parcel1.readInt();
                if(i == 0)
                    flag = false;
                parcel1.recycle();
                parcel.recycle();
                return flag;
                ibinder = null;
                  goto _L1
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

            private IBinder ky;

            a(IBinder ibinder)
            {
                ky = ibinder;
            }
        }


        public static k ag(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            android.os.IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.maps.internal.IOnMarkerClickListener");
            if(iinterface != null && (iinterface instanceof k))
                return (k)iinterface;
            else
                return new a(ibinder);
        }

        public IBinder asBinder()
        {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int l)
            throws RemoteException
        {
            boolean flag;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, l);

            case 1598968902: 
                parcel1.writeString("com.google.android.gms.maps.internal.IOnMarkerClickListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IOnMarkerClickListener");
                flag = a(com.google.android.gms.maps.model.internal.d.a.aq(parcel.readStrongBinder()));
                parcel1.writeNoException();
                break;
            }
            int i1;
            if(flag)
                i1 = 1;
            else
                i1 = 0;
            parcel1.writeInt(i1);
            return true;
        }

        public a()
        {
            attachInterface(this, "com.google.android.gms.maps.internal.IOnMarkerClickListener");
        }
    }


    private k(Context context, n n1, o o)
    {
        super(context, n1, o);
    }

    public static k a(String s, Context context)
    {
        e e1 = new e();
        a(s, context, ((n) (e1)));
        return new k(context, e1, new q(239));
    }

    protected void b(Context context)
    {
        long l;
        l = 1L;
        super.b(context);
        a a1 = f(context);
        String s;
        if(!a1.isLimitAdTrackingEnabled())
            l = 0L;
        a(28, l);
        s = a1.getId();
        IOException ioexception;
        if(s != null)
            try
            {
                a(30, s);
                return;
            }
            catch(IOException ioexception1)
            {
                return;
            }
        break MISSING_BLOCK_LABEL_70;
        ioexception;
        a(28, 1L);
        return;
        GooglePlayServicesNotAvailableException googleplayservicesnotavailableexception;
        googleplayservicesnotavailableexception;
    }

    a f(Context context)
        throws IOException, GooglePlayServicesNotAvailableException
    {
        int i = 0;
        com.google.android.gms.ads.identifier.AdvertisingIdClient.Info info;
        String s;
        try
        {
            info = AdvertisingIdClient.getAdvertisingIdInfo(context);
        }
        catch(GooglePlayServicesRepairableException googleplayservicesrepairableexception)
        {
            throw new IOException(googleplayservicesrepairableexception);
        }
        s = info.getId();
        String s1;
        if(s != null && s.matches("^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$"))
        {
            byte abyte0[] = new byte[16];
            int l = 0;
            for(; i < s.length(); i += 2)
            {
                if(i == 8 || i == 13 || i == 18 || i == 23)
                    i++;
                abyte0[l] = (byte)((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
                l++;
            }

            s1 = ka.a(abyte0, true);
        } else
        {
            s1 = s;
        }
        return new a(this, s1, info.isLimitAdTrackingEnabled());
    }
}
