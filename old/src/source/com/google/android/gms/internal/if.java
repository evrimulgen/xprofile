// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.content.Intent;
import android.os.*;

public interface if
    extends IInterface
{
    public static abstract class a extends Binder
        implements if
    {

        public static if av(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.panorama.internal.IPanoramaCallbacks");
            if(iinterface != null && (iinterface instanceof if))
                return (if)iinterface;
            else
                return new a(ibinder);
        }

        public IBinder asBinder()
        {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            int k;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.google.android.gms.panorama.internal.IPanoramaCallbacks");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.google.android.gms.panorama.internal.IPanoramaCallbacks");
                k = parcel.readInt();
                break;
            }
            Bundle bundle;
            int l;
            Intent intent;
            if(parcel.readInt() != 0)
                bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
            else
                bundle = null;
            l = parcel.readInt();
            if(parcel.readInt() != 0)
                intent = (Intent)Intent.CREATOR.createFromParcel(parcel);
            else
                intent = null;
            a(k, bundle, l, intent);
            parcel1.writeNoException();
            return true;
        }

        public a()
        {
            attachInterface(this, "com.google.android.gms.panorama.internal.IPanoramaCallbacks");
        }
    }

    private static class a.a
        implements if
    {

        public void a(int i, Bundle bundle, int j, Intent intent)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.panorama.internal.IPanoramaCallbacks");
            parcel.writeInt(i);
            if(bundle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(j);
            if(intent == null)
                break MISSING_BLOCK_LABEL_121;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
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

        public IBinder asBinder()
        {
            return ky;
        }

        private IBinder ky;

        a.a(IBinder ibinder)
        {
            ky = ibinder;
        }
    }


    public abstract void a(int i, Bundle bundle, int j, Intent intent)
        throws RemoteException;
}
