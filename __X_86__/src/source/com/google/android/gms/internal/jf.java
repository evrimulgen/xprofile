// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.os.*;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.MaskedWallet;

public interface jf
    extends IInterface
{
    public static abstract class a extends Binder
        implements jf
    {

        public static jf aD(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
            if(iinterface != null && (iinterface instanceof jf))
                return (jf)iinterface;
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
                parcel1.writeString("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
                int j1 = parcel.readInt();
                MaskedWallet maskedwallet;
                Bundle bundle3;
                if(parcel.readInt() != 0)
                    maskedwallet = (MaskedWallet)MaskedWallet.CREATOR.createFromParcel(parcel);
                else
                    maskedwallet = null;
                if(parcel.readInt() != 0)
                    bundle3 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle3 = null;
                a(j1, maskedwallet, bundle3);
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
                int i1 = parcel.readInt();
                FullWallet fullwallet;
                Bundle bundle2;
                if(parcel.readInt() != 0)
                    fullwallet = (FullWallet)FullWallet.CREATOR.createFromParcel(parcel);
                else
                    fullwallet = null;
                if(parcel.readInt() != 0)
                    bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle2 = null;
                a(i1, fullwallet, bundle2);
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
                int l = parcel.readInt();
                boolean flag;
                Bundle bundle1;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                if(parcel.readInt() != 0)
                    bundle1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle1 = null;
                a(l, flag, bundle1);
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
                k = parcel.readInt();
                break;
            }
            Bundle bundle;
            if(parcel.readInt() != 0)
                bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
            else
                bundle = null;
            e(k, bundle);
            parcel1.writeNoException();
            return true;
        }

        public a()
        {
            attachInterface(this, "com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
        }
    }

    private static class a.a
        implements jf
    {

        public void a(int i, FullWallet fullwallet, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
            parcel.writeInt(i);
            if(fullwallet == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            fullwallet.writeToParcel(parcel, 0);
_L3:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_113;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
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

        public void a(int i, MaskedWallet maskedwallet, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
            parcel.writeInt(i);
            if(maskedwallet == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            maskedwallet.writeToParcel(parcel, 0);
_L3:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_113;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
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

        public void a(int i, boolean flag, Bundle bundle)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            Parcel parcel1;
            j = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
            parcel.writeInt(i);
            if(!flag)
                j = 0;
            parcel.writeInt(j);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_92;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            ky.transact(3, parcel, parcel1, 0);
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

        public IBinder asBinder()
        {
            return ky;
        }

        public void e(int i, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
            parcel.writeInt(i);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
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

        private IBinder ky;

        a.a(IBinder ibinder)
        {
            ky = ibinder;
        }
    }


    public abstract void a(int i, FullWallet fullwallet, Bundle bundle)
        throws RemoteException;

    public abstract void a(int i, MaskedWallet maskedwallet, Bundle bundle)
        throws RemoteException;

    public abstract void a(int i, boolean flag, Bundle bundle)
        throws RemoteException;

    public abstract void e(int i, Bundle bundle)
        throws RemoteException;
}
