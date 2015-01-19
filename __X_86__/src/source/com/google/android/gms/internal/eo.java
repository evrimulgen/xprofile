// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.os.*;
import com.google.android.gms.dynamic.b;

public interface eo
    extends IInterface
{
    public static abstract class a extends Binder
        implements eo
    {

        public static eo A(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.common.internal.ISignInButtonCreator");
            if(iinterface != null && (iinterface instanceof eo))
                return (eo)iinterface;
            else
                return new a(ibinder);
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            b b1;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.google.android.gms.common.internal.ISignInButtonCreator");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.google.android.gms.common.internal.ISignInButtonCreator");
                b1 = a(com.google.android.gms.dynamic.b.a.G(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                break;
            }
            IBinder ibinder;
            if(b1 != null)
                ibinder = b1.asBinder();
            else
                ibinder = null;
            parcel1.writeStrongBinder(ibinder);
            return true;
        }
    }

    private static class a.a
        implements eo
    {

        public b a(b b1, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.common.internal.ISignInButtonCreator");
            if(b1 == null)
                break MISSING_BLOCK_LABEL_92;
            IBinder ibinder = b1.asBinder();
_L1:
            b b2;
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeInt(j);
            ky.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            b2 = com.google.android.gms.dynamic.b.a.G(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return b2;
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

        a.a(IBinder ibinder)
        {
            ky = ibinder;
        }
    }


    public abstract b a(b b, int i, int j)
        throws RemoteException;
}
