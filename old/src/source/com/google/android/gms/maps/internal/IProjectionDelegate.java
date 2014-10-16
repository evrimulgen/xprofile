// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.maps.internal;

import android.os.*;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.maps.model.*;

public interface IProjectionDelegate
    extends IInterface
{
    public static abstract class a extends Binder
        implements IProjectionDelegate
    {

        public static IProjectionDelegate ak(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
            if(iinterface != null && (iinterface instanceof IProjectionDelegate))
                return (IProjectionDelegate)iinterface;
            else
                return new a(ibinder);
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            VisibleRegion visibleregion;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.google.android.gms.maps.internal.IProjectionDelegate");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
                LatLng latlng1 = fromScreenLocation(com.google.android.gms.dynamic.b.a.G(parcel.readStrongBinder()));
                parcel1.writeNoException();
                if(latlng1 != null)
                {
                    parcel1.writeInt(1);
                    latlng1.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
                LatLng latlng;
                b b1;
                IBinder ibinder;
                if(parcel.readInt() != 0)
                    latlng = LatLng.CREATOR.createFromParcel(parcel);
                else
                    latlng = null;
                b1 = toScreenLocation(latlng);
                parcel1.writeNoException();
                ibinder = null;
                if(b1 != null)
                    ibinder = b1.asBinder();
                parcel1.writeStrongBinder(ibinder);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
                visibleregion = getVisibleRegion();
                parcel1.writeNoException();
                break;
            }
            if(visibleregion != null)
            {
                parcel1.writeInt(1);
                visibleregion.writeToParcel(parcel1, 1);
            } else
            {
                parcel1.writeInt(0);
            }
            return true;
        }
    }

    private static class a.a
        implements IProjectionDelegate
    {

        public IBinder asBinder()
        {
            return ky;
        }

        public LatLng fromScreenLocation(b b1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IProjectionDelegate");
            if(b1 == null)
                break MISSING_BLOCK_LABEL_88;
            IBinder ibinder = b1.asBinder();
_L1:
            int i;
            parcel.writeStrongBinder(ibinder);
            ky.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            LatLng latlng;
            latlng = null;
            if(i == 0)
                break MISSING_BLOCK_LABEL_77;
            LatLng latlng1 = LatLng.CREATOR.createFromParcel(parcel1);
            latlng = latlng1;
            parcel1.recycle();
            parcel.recycle();
            return latlng;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public VisibleRegion getVisibleRegion()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IProjectionDelegate");
            ky.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            VisibleRegion visibleregion1 = VisibleRegion.CREATOR.createFromParcel(parcel1);
            VisibleRegion visibleregion = visibleregion1;
_L4:
            parcel1.recycle();
            parcel.recycle();
            return visibleregion;
_L2:
            visibleregion = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public b toScreenLocation(LatLng latlng)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IProjectionDelegate");
            if(latlng == null)
                break MISSING_BLOCK_LABEL_67;
            parcel.writeInt(1);
            latlng.writeToParcel(parcel, 0);
_L1:
            b b1;
            ky.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            b1 = com.google.android.gms.dynamic.b.a.G(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return b1;
            parcel.writeInt(0);
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        private IBinder ky;

        a.a(IBinder ibinder)
        {
            ky = ibinder;
        }
    }


    public abstract LatLng fromScreenLocation(b b)
        throws RemoteException;

    public abstract VisibleRegion getVisibleRegion()
        throws RemoteException;

    public abstract b toScreenLocation(LatLng latlng)
        throws RemoteException;
}
