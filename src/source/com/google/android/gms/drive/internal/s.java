// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.drive.internal;

import android.os.*;
import android.util.Log;
import com.google.android.gms.internal.er;

// Referenced classes of package com.google.android.gms.drive.internal:
//            OnEventResponse, s, i

public class s extends w.a
{
    private static class a extends Binder
        implements s
    {
        private static class a
            implements s
        {

            public Bundle a(String s1, Bundle bundle)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.auth.IAuthManagerService");
                parcel.writeString(s1);
                if(bundle == null) goto _L2; else goto _L1
_L1:
                parcel.writeInt(1);
                bundle.writeToParcel(parcel, 0);
_L3:
                Bundle bundle1;
                ky.transact(2, parcel, parcel1, 0);
                parcel1.readException();
                if(parcel1.readInt() == 0)
                    break MISSING_BLOCK_LABEL_112;
                bundle1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel1);
_L4:
                parcel1.recycle();
                parcel.recycle();
                return bundle1;
_L2:
                parcel.writeInt(0);
                  goto _L3
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
                bundle1 = null;
                  goto _L4
            }

            public Bundle a(String s1, String s2, Bundle bundle)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.auth.IAuthManagerService");
                parcel.writeString(s1);
                parcel.writeString(s2);
                if(bundle == null) goto _L2; else goto _L1
_L1:
                parcel.writeInt(1);
                bundle.writeToParcel(parcel, 0);
_L3:
                Bundle bundle1;
                ky.transact(1, parcel, parcel1, 0);
                parcel1.readException();
                if(parcel1.readInt() == 0)
                    break MISSING_BLOCK_LABEL_127;
                bundle1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel1);
_L4:
                parcel1.recycle();
                parcel.recycle();
                return bundle1;
_L2:
                parcel.writeInt(0);
                  goto _L3
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
                bundle1 = null;
                  goto _L4
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


        public static s a(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            android.os.IInterface iinterface = ibinder.queryLocalInterface("com.google.android.auth.IAuthManagerService");
            if(iinterface != null && (iinterface instanceof s))
                return (s)iinterface;
            else
                return new a(ibinder);
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            String s1;
            int k;
            Bundle bundle;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.google.android.auth.IAuthManagerService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.google.android.auth.IAuthManagerService");
                String s2 = parcel.readString();
                String s3 = parcel.readString();
                int l = parcel.readInt();
                Bundle bundle2 = null;
                if(l != 0)
                    bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                Bundle bundle3 = a(s2, s3, bundle2);
                parcel1.writeNoException();
                if(bundle3 != null)
                {
                    parcel1.writeInt(1);
                    bundle3.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.google.android.auth.IAuthManagerService");
                s1 = parcel.readString();
                k = parcel.readInt();
                bundle = null;
                break;
            }
            if(k != 0)
                bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
            Bundle bundle1 = a(s1, bundle);
            parcel1.writeNoException();
            if(bundle1 != null)
            {
                parcel1.writeInt(1);
                bundle1.writeToParcel(parcel1, 1);
            } else
            {
                parcel1.writeInt(0);
            }
            return true;
        }
    }


    public s(Looper looper, int i, com.google.android.gms.drive.events.DriveEvent.Listener listener)
    {
        Dm = i;
        DR = listener;
        DS = new a(looper);
    }

    public void a(OnEventResponse oneventresponse)
        throws RemoteException
    {
        boolean flag;
        if(Dm == oneventresponse.getEventType())
            flag = true;
        else
            flag = false;
        er.v(flag);
        switch(oneventresponse.getEventType())
        {
        default:
            Log.w("EventCallback", (new StringBuilder()).append("Unexpected event type:").append(oneventresponse.getEventType()).toString());
            return;

        case 1: // '\001'
            DS.a(DR, oneventresponse.fa());
            return;

        case 2: // '\002'
            DS.a(DR, oneventresponse.fb());
            break;
        }
    }

    private final com.google.android.gms.drive.events.DriveEvent.Listener DR;
    private final a DS;
    private final int Dm;

    // Unreferenced inner class com/google/android/gms/analytics/s$1

/* anonymous class */
    static class _cls1
        implements i
    {

        public long currentTimeMillis()
        {
            return System.currentTimeMillis();
        }

        final s rL;

            
            {
                rL = s1;
                super();
            }
    }

}
