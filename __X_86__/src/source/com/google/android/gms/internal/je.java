// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.os.*;
import com.google.android.gms.wallet.*;

// Referenced classes of package com.google.android.gms.internal:
//            jf

public interface je
    extends IInterface
{
    public static abstract class a extends Binder
        implements je
    {

        public static je aC(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.wallet.internal.IOwService");
            if(iinterface != null && (iinterface instanceof je))
                return (je)iinterface;
            else
                return new a(ibinder);
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.google.android.gms.wallet.internal.IOwService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
                MaskedWalletRequest maskedwalletrequest;
                Bundle bundle5;
                if(parcel.readInt() != 0)
                    maskedwalletrequest = (MaskedWalletRequest)MaskedWalletRequest.CREATOR.createFromParcel(parcel);
                else
                    maskedwalletrequest = null;
                if(parcel.readInt() != 0)
                    bundle5 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle5 = null;
                a(maskedwalletrequest, bundle5, jf.a.aD(parcel.readStrongBinder()));
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
                FullWalletRequest fullwalletrequest;
                Bundle bundle4;
                if(parcel.readInt() != 0)
                    fullwalletrequest = (FullWalletRequest)FullWalletRequest.CREATOR.createFromParcel(parcel);
                else
                    fullwalletrequest = null;
                if(parcel.readInt() != 0)
                    bundle4 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle4 = null;
                a(fullwalletrequest, bundle4, jf.a.aD(parcel.readStrongBinder()));
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
                String s = parcel.readString();
                String s1 = parcel.readString();
                Bundle bundle3;
                if(parcel.readInt() != 0)
                    bundle3 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle3 = null;
                a(s, s1, bundle3, jf.a.aD(parcel.readStrongBinder()));
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
                NotifyTransactionStatusRequest notifytransactionstatusrequest;
                Bundle bundle2;
                if(parcel.readInt() != 0)
                    notifytransactionstatusrequest = (NotifyTransactionStatusRequest)NotifyTransactionStatusRequest.CREATOR.createFromParcel(parcel);
                else
                    notifytransactionstatusrequest = null;
                if(parcel.readInt() != 0)
                    bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle2 = null;
                a(notifytransactionstatusrequest, bundle2);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
                Bundle bundle1;
                if(parcel.readInt() != 0)
                    bundle1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle1 = null;
                a(bundle1, jf.a.aD(parcel.readStrongBinder()));
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
                break;
            }
            d d1;
            Bundle bundle;
            if(parcel.readInt() != 0)
                d1 = (d)d.CREATOR.createFromParcel(parcel);
            else
                d1 = null;
            if(parcel.readInt() != 0)
                bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
            else
                bundle = null;
            a(d1, bundle, jf.a.aD(parcel.readStrongBinder()));
            return true;
        }
    }

    private static class a.a
        implements je
    {

        public void a(Bundle bundle, jf jf1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wallet.internal.IOwService");
            if(bundle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            IBinder ibinder;
            ibinder = null;
            if(jf1 == null)
                break MISSING_BLOCK_LABEL_40;
            ibinder = jf1.asBinder();
            parcel.writeStrongBinder(ibinder);
            ky.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void a(FullWalletRequest fullwalletrequest, Bundle bundle, jf jf1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wallet.internal.IOwService");
            if(fullwalletrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            fullwalletrequest.writeToParcel(parcel, 0);
_L3:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_108;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            IBinder ibinder;
            ibinder = null;
            if(jf1 == null)
                break MISSING_BLOCK_LABEL_61;
            ibinder = jf1.asBinder();
            parcel.writeStrongBinder(ibinder);
            ky.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
            parcel.writeInt(0);
              goto _L4
        }

        public void a(MaskedWalletRequest maskedwalletrequest, Bundle bundle, jf jf1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wallet.internal.IOwService");
            if(maskedwalletrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            maskedwalletrequest.writeToParcel(parcel, 0);
_L3:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_108;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            IBinder ibinder;
            ibinder = null;
            if(jf1 == null)
                break MISSING_BLOCK_LABEL_61;
            ibinder = jf1.asBinder();
            parcel.writeStrongBinder(ibinder);
            ky.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
            parcel.writeInt(0);
              goto _L4
        }

        public void a(NotifyTransactionStatusRequest notifytransactionstatusrequest, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wallet.internal.IOwService");
            if(notifytransactionstatusrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            notifytransactionstatusrequest.writeToParcel(parcel, 0);
_L3:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_76;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            ky.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
            parcel.writeInt(0);
              goto _L4
        }

        public void a(d d1, Bundle bundle, jf jf1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wallet.internal.IOwService");
            if(d1 == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            d1.writeToParcel(parcel, 0);
_L3:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_109;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            IBinder ibinder;
            ibinder = null;
            if(jf1 == null)
                break MISSING_BLOCK_LABEL_61;
            ibinder = jf1.asBinder();
            parcel.writeStrongBinder(ibinder);
            ky.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
            parcel.writeInt(0);
              goto _L4
        }

        public void a(String s, String s1, Bundle bundle, jf jf1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wallet.internal.IOwService");
            parcel.writeString(s);
            parcel.writeString(s1);
            if(bundle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            IBinder ibinder;
            ibinder = null;
            if(jf1 == null)
                break MISSING_BLOCK_LABEL_58;
            ibinder = jf1.asBinder();
            parcel.writeStrongBinder(ibinder);
            ky.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
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


    public abstract void a(Bundle bundle, jf jf)
        throws RemoteException;

    public abstract void a(FullWalletRequest fullwalletrequest, Bundle bundle, jf jf)
        throws RemoteException;

    public abstract void a(MaskedWalletRequest maskedwalletrequest, Bundle bundle, jf jf)
        throws RemoteException;

    public abstract void a(NotifyTransactionStatusRequest notifytransactionstatusrequest, Bundle bundle)
        throws RemoteException;

    public abstract void a(d d, Bundle bundle, jf jf)
        throws RemoteException;

    public abstract void a(String s, String s1, Bundle bundle, jf jf)
        throws RemoteException;
}
