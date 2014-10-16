// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.maps.internal;

import android.os.*;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.GoogleMapOptionsCreator;

// Referenced classes of package com.google.android.gms.maps.internal:
//            IGoogleMapDelegate

public interface IMapFragmentDelegate
    extends IInterface
{
    public static abstract class a extends Binder
        implements IMapFragmentDelegate
    {

        public static IMapFragmentDelegate Y(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.maps.internal.IMapFragmentDelegate");
            if(iinterface != null && (iinterface instanceof IMapFragmentDelegate))
                return (IMapFragmentDelegate)iinterface;
            else
                return new a(ibinder);
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            boolean flag;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.google.android.gms.maps.internal.IMapFragmentDelegate");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IMapFragmentDelegate");
                IGoogleMapDelegate igooglemapdelegate = getMap();
                parcel1.writeNoException();
                IBinder ibinder1;
                if(igooglemapdelegate != null)
                    ibinder1 = igooglemapdelegate.asBinder();
                else
                    ibinder1 = null;
                parcel1.writeStrongBinder(ibinder1);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IMapFragmentDelegate");
                b b4 = com.google.android.gms.dynamic.b.a.G(parcel.readStrongBinder());
                GoogleMapOptions googlemapoptions;
                Bundle bundle3;
                if(parcel.readInt() != 0)
                    googlemapoptions = GoogleMapOptions.CREATOR.createFromParcel(parcel);
                else
                    googlemapoptions = null;
                if(parcel.readInt() != 0)
                    bundle3 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle3 = null;
                onInflate(b4, googlemapoptions, bundle3);
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IMapFragmentDelegate");
                Bundle bundle2;
                if(parcel.readInt() != 0)
                    bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle2 = null;
                onCreate(bundle2);
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IMapFragmentDelegate");
                b b1 = com.google.android.gms.dynamic.b.a.G(parcel.readStrongBinder());
                b b2 = com.google.android.gms.dynamic.b.a.G(parcel.readStrongBinder());
                Bundle bundle1;
                b b3;
                IBinder ibinder;
                if(parcel.readInt() != 0)
                    bundle1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle1 = null;
                b3 = onCreateView(b1, b2, bundle1);
                parcel1.writeNoException();
                ibinder = null;
                if(b3 != null)
                    ibinder = b3.asBinder();
                parcel1.writeStrongBinder(ibinder);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IMapFragmentDelegate");
                onResume();
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IMapFragmentDelegate");
                onPause();
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IMapFragmentDelegate");
                onDestroyView();
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IMapFragmentDelegate");
                onDestroy();
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IMapFragmentDelegate");
                onLowMemory();
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IMapFragmentDelegate");
                Bundle bundle;
                if(parcel.readInt() != 0)
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle = null;
                onSaveInstanceState(bundle);
                parcel1.writeNoException();
                if(bundle != null)
                {
                    parcel1.writeInt(1);
                    bundle.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 11: // '\013'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IMapFragmentDelegate");
                flag = isReady();
                parcel1.writeNoException();
                break;
            }
            int k;
            if(flag)
                k = 1;
            else
                k = 0;
            parcel1.writeInt(k);
            return true;
        }
    }

    private static class a.a
        implements IMapFragmentDelegate
    {

        public IBinder asBinder()
        {
            return ky;
        }

        public IGoogleMapDelegate getMap()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            IGoogleMapDelegate igooglemapdelegate;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IMapFragmentDelegate");
            ky.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            igooglemapdelegate = IGoogleMapDelegate.a.V(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return igooglemapdelegate;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isReady()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IMapFragmentDelegate");
            ky.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag = false;
            if(i != 0)
                flag = true;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void onCreate(Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IMapFragmentDelegate");
            if(bundle == null)
                break MISSING_BLOCK_LABEL_56;
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

        public b onCreateView(b b1, b b2, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IMapFragmentDelegate");
            if(b1 == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder = b1.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder);
            IBinder ibinder1;
            ibinder1 = null;
            if(b2 == null)
                break MISSING_BLOCK_LABEL_51;
            ibinder1 = b2.asBinder();
            parcel.writeStrongBinder(ibinder1);
            if(bundle == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L6:
            b b3;
            ky.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            b3 = com.google.android.gms.dynamic.b.a.G(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return b3;
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
              goto _L5
        }

        public void onDestroy()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IMapFragmentDelegate");
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

        public void onDestroyView()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IMapFragmentDelegate");
            ky.transact(7, parcel, parcel1, 0);
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

        public void onInflate(b b1, GoogleMapOptions googlemapoptions, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IMapFragmentDelegate");
            if(b1 == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder = b1.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder);
            if(googlemapoptions == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            googlemapoptions.writeToParcel(parcel, 0);
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

        public void onLowMemory()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IMapFragmentDelegate");
            ky.transact(9, parcel, parcel1, 0);
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

        public void onPause()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IMapFragmentDelegate");
            ky.transact(6, parcel, parcel1, 0);
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

        public void onResume()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IMapFragmentDelegate");
            ky.transact(5, parcel, parcel1, 0);
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

        public void onSaveInstanceState(Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IMapFragmentDelegate");
            if(bundle == null)
                break MISSING_BLOCK_LABEL_69;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            ky.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() != 0)
                bundle.readFromParcel(parcel1);
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


    public abstract IGoogleMapDelegate getMap()
        throws RemoteException;

    public abstract boolean isReady()
        throws RemoteException;

    public abstract void onCreate(Bundle bundle)
        throws RemoteException;

    public abstract b onCreateView(b b, b b1, Bundle bundle)
        throws RemoteException;

    public abstract void onDestroy()
        throws RemoteException;

    public abstract void onDestroyView()
        throws RemoteException;

    public abstract void onInflate(b b, GoogleMapOptions googlemapoptions, Bundle bundle)
        throws RemoteException;

    public abstract void onLowMemory()
        throws RemoteException;

    public abstract void onPause()
        throws RemoteException;

    public abstract void onResume()
        throws RemoteException;

    public abstract void onSaveInstanceState(Bundle bundle)
        throws RemoteException;
}
