// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.os.*;
import com.google.android.gms.identity.intents.UserAddressRequest;

// Referenced classes of package com.google.android.gms.internal:
//            hd

public interface he
    extends IInterface
{
    public static abstract class a extends Binder
        implements he
    {

        public static he M(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.identity.intents.internal.IAddressService");
            if(iinterface != null && (iinterface instanceof he))
                return (he)iinterface;
            else
                return new a(ibinder);
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            hd hd;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.google.android.gms.identity.intents.internal.IAddressService");
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.google.android.gms.identity.intents.internal.IAddressService");
                hd = com.google.android.gms.internal.hd.a.L(parcel.readStrongBinder());
                break;
            }
            UserAddressRequest useraddressrequest;
            Bundle bundle;
            if(parcel.readInt() != 0)
                useraddressrequest = (UserAddressRequest)UserAddressRequest.CREATOR.createFromParcel(parcel);
            else
                useraddressrequest = null;
            if(parcel.readInt() != 0)
                bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
            else
                bundle = null;
            a(hd, useraddressrequest, bundle);
            parcel1.writeNoException();
            return true;
        }
    }

    private static class a.a
        implements he
    {

        public void a(hd hd1, UserAddressRequest useraddressrequest, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.identity.intents.internal.IAddressService");
            if(hd1 == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder = hd1.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder);
            if(useraddressrequest == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            useraddressrequest.writeToParcel(parcel, 0);
_L6:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_132;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L7:
            ky.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            ibinder = null;
              goto _L5
_L4:
            parcel.writeInt(0);
              goto _L6
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
            parcel.writeInt(0);
              goto _L7
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


    public abstract void a(hd hd, UserAddressRequest useraddressrequest, Bundle bundle)
        throws RemoteException;
}
