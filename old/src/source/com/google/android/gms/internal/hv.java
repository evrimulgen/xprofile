// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.os.*;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.DataHolderCreator;

public interface hv
    extends IInterface
{
    public static abstract class a extends Binder
        implements hv
    {

        public static hv R(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.location.places.internal.IPlacesCallbacks");
            if(iinterface != null && (iinterface instanceof hv))
                return (hv)iinterface;
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
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.google.android.gms.location.places.internal.IPlacesCallbacks");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.google.android.gms.location.places.internal.IPlacesCallbacks");
                break;
            }
            DataHolder dataholder;
            if(parcel.readInt() != 0)
                dataholder = DataHolder.CREATOR.createFromParcel(parcel);
            else
                dataholder = null;
            I(dataholder);
            return true;
        }
    }

    private static class a.a
        implements hv
    {

        public void I(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.location.places.internal.IPlacesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            ky.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
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


    public abstract void I(DataHolder dataholder)
        throws RemoteException;
}
