// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.os.*;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.DataHolderCreator;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;

public interface ga
    extends IInterface
{
    public static abstract class a extends Binder
        implements ga
    {

        public static ga I(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.games.internal.IGamesCallbacks");
            if(iinterface != null && (iinterface instanceof ga))
                return (ga)iinterface;
            else
                return new a(ibinder);
        }

        public IBinder asBinder()
        {
            return this;
        }

        public boolean onTransact(int i1, Parcel parcel, Parcel parcel1, int j1)
            throws RemoteException
        {
            int k1;
            DataHolder dataholder;
            switch(i1)
            {
            default:
                return super.onTransact(i1, parcel, parcel1, j1);

            case 1598968902: 
                parcel1.writeString("com.google.android.gms.games.internal.IGamesCallbacks");
                return true;

            case 5001: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                d(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5002: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int j12 = parcel.readInt();
                DataHolder dataholder38 = null;
                if(j12 != 0)
                    dataholder38 = DataHolder.CREATOR.createFromParcel(parcel);
                b(dataholder38);
                parcel1.writeNoException();
                return true;

            case 5003: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                e(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5004: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int i12 = parcel.readInt();
                DataHolder dataholder37 = null;
                if(i12 != 0)
                    dataholder37 = DataHolder.CREATOR.createFromParcel(parcel);
                c(dataholder37);
                parcel1.writeNoException();
                return true;

            case 5005: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder dataholder35;
                int l11;
                DataHolder dataholder36;
                if(parcel.readInt() != 0)
                    dataholder35 = DataHolder.CREATOR.createFromParcel(parcel);
                else
                    dataholder35 = null;
                l11 = parcel.readInt();
                dataholder36 = null;
                if(l11 != 0)
                    dataholder36 = DataHolder.CREATOR.createFromParcel(parcel);
                a(dataholder35, dataholder36);
                parcel1.writeNoException();
                return true;

            case 5006: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int k11 = parcel.readInt();
                DataHolder dataholder34 = null;
                if(k11 != 0)
                    dataholder34 = DataHolder.CREATOR.createFromParcel(parcel);
                d(dataholder34);
                parcel1.writeNoException();
                return true;

            case 5007: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int j11 = parcel.readInt();
                DataHolder dataholder33 = null;
                if(j11 != 0)
                    dataholder33 = DataHolder.CREATOR.createFromParcel(parcel);
                e(dataholder33);
                parcel1.writeNoException();
                return true;

            case 5008: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int i11 = parcel.readInt();
                DataHolder dataholder32 = null;
                if(i11 != 0)
                    dataholder32 = DataHolder.CREATOR.createFromParcel(parcel);
                f(dataholder32);
                parcel1.writeNoException();
                return true;

            case 5009: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int l10 = parcel.readInt();
                DataHolder dataholder31 = null;
                if(l10 != 0)
                    dataholder31 = DataHolder.CREATOR.createFromParcel(parcel);
                g(dataholder31);
                parcel1.writeNoException();
                return true;

            case 5010: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int k10 = parcel.readInt();
                DataHolder dataholder30 = null;
                if(k10 != 0)
                    dataholder30 = DataHolder.CREATOR.createFromParcel(parcel);
                h(dataholder30);
                parcel1.writeNoException();
                return true;

            case 5011: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int j10 = parcel.readInt();
                DataHolder dataholder29 = null;
                if(j10 != 0)
                    dataholder29 = DataHolder.CREATOR.createFromParcel(parcel);
                i(dataholder29);
                parcel1.writeNoException();
                return true;

            case 5016: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                cM();
                parcel1.writeNoException();
                return true;

            case 5017: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int i10 = parcel.readInt();
                DataHolder dataholder28 = null;
                if(i10 != 0)
                    dataholder28 = DataHolder.CREATOR.createFromParcel(parcel);
                k(dataholder28);
                parcel1.writeNoException();
                return true;

            case 5037: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int l9 = parcel.readInt();
                DataHolder dataholder27 = null;
                if(l9 != 0)
                    dataholder27 = DataHolder.CREATOR.createFromParcel(parcel);
                l(dataholder27);
                parcel1.writeNoException();
                return true;

            case 5018: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int k9 = parcel.readInt();
                DataHolder dataholder26 = null;
                if(k9 != 0)
                    dataholder26 = DataHolder.CREATOR.createFromParcel(parcel);
                s(dataholder26);
                parcel1.writeNoException();
                return true;

            case 5019: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int j9 = parcel.readInt();
                DataHolder dataholder25 = null;
                if(j9 != 0)
                    dataholder25 = DataHolder.CREATOR.createFromParcel(parcel);
                t(dataholder25);
                parcel1.writeNoException();
                return true;

            case 5020: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                onLeftRoom(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5021: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int i9 = parcel.readInt();
                DataHolder dataholder24 = null;
                if(i9 != 0)
                    dataholder24 = DataHolder.CREATOR.createFromParcel(parcel);
                u(dataholder24);
                parcel1.writeNoException();
                return true;

            case 5022: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int l8 = parcel.readInt();
                DataHolder dataholder23 = null;
                if(l8 != 0)
                    dataholder23 = DataHolder.CREATOR.createFromParcel(parcel);
                v(dataholder23);
                parcel1.writeNoException();
                return true;

            case 5023: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int k8 = parcel.readInt();
                DataHolder dataholder22 = null;
                if(k8 != 0)
                    dataholder22 = DataHolder.CREATOR.createFromParcel(parcel);
                w(dataholder22);
                parcel1.writeNoException();
                return true;

            case 5024: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int j8 = parcel.readInt();
                DataHolder dataholder21 = null;
                if(j8 != 0)
                    dataholder21 = DataHolder.CREATOR.createFromParcel(parcel);
                x(dataholder21);
                parcel1.writeNoException();
                return true;

            case 5025: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int i8 = parcel.readInt();
                DataHolder dataholder20 = null;
                if(i8 != 0)
                    dataholder20 = DataHolder.CREATOR.createFromParcel(parcel);
                y(dataholder20);
                parcel1.writeNoException();
                return true;

            case 5026: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int l7 = parcel.readInt();
                DataHolder dataholder19 = null;
                if(l7 != 0)
                    dataholder19 = DataHolder.CREATOR.createFromParcel(parcel);
                a(dataholder19, parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 5027: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int k7 = parcel.readInt();
                DataHolder dataholder18 = null;
                if(k7 != 0)
                    dataholder18 = DataHolder.CREATOR.createFromParcel(parcel);
                b(dataholder18, parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 5028: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int j7 = parcel.readInt();
                DataHolder dataholder17 = null;
                if(j7 != 0)
                    dataholder17 = DataHolder.CREATOR.createFromParcel(parcel);
                c(dataholder17, parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 5029: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int i7 = parcel.readInt();
                DataHolder dataholder16 = null;
                if(i7 != 0)
                    dataholder16 = DataHolder.CREATOR.createFromParcel(parcel);
                d(dataholder16, parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 5030: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int l6 = parcel.readInt();
                DataHolder dataholder15 = null;
                if(l6 != 0)
                    dataholder15 = DataHolder.CREATOR.createFromParcel(parcel);
                e(dataholder15, parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 5031: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int k6 = parcel.readInt();
                DataHolder dataholder14 = null;
                if(k6 != 0)
                    dataholder14 = DataHolder.CREATOR.createFromParcel(parcel);
                f(dataholder14, parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 5032: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int j6 = parcel.readInt();
                RealTimeMessage realtimemessage = null;
                if(j6 != 0)
                    realtimemessage = (RealTimeMessage)RealTimeMessage.CREATOR.createFromParcel(parcel);
                onRealTimeMessageReceived(realtimemessage);
                parcel1.writeNoException();
                return true;

            case 5033: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                b(parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5034: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int i6 = parcel.readInt();
                String s1 = parcel.readString();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                a(i6, s1, flag);
                parcel1.writeNoException();
                return true;

            case 5038: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int l5 = parcel.readInt();
                DataHolder dataholder13 = null;
                if(l5 != 0)
                    dataholder13 = DataHolder.CREATOR.createFromParcel(parcel);
                z(dataholder13);
                parcel1.writeNoException();
                return true;

            case 5035: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int k5 = parcel.readInt();
                DataHolder dataholder12 = null;
                if(k5 != 0)
                    dataholder12 = DataHolder.CREATOR.createFromParcel(parcel);
                A(dataholder12);
                parcel1.writeNoException();
                return true;

            case 5036: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                aR(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 5039: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int j5 = parcel.readInt();
                DataHolder dataholder11 = null;
                if(j5 != 0)
                    dataholder11 = DataHolder.CREATOR.createFromParcel(parcel);
                B(dataholder11);
                parcel1.writeNoException();
                return true;

            case 5040: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                aS(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 6001: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                onP2PConnected(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 6002: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                onP2PDisconnected(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8001: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int i5 = parcel.readInt();
                DataHolder dataholder10 = null;
                if(i5 != 0)
                    dataholder10 = DataHolder.CREATOR.createFromParcel(parcel);
                C(dataholder10);
                parcel1.writeNoException();
                return true;

            case 8002: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int k4 = parcel.readInt();
                int l4 = parcel.readInt();
                Bundle bundle1 = null;
                if(l4 != 0)
                    bundle1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                a(k4, bundle1);
                parcel1.writeNoException();
                return true;

            case 8003: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int j4 = parcel.readInt();
                DataHolder dataholder9 = null;
                if(j4 != 0)
                    dataholder9 = DataHolder.CREATOR.createFromParcel(parcel);
                n(dataholder9);
                parcel1.writeNoException();
                return true;

            case 8004: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int i4 = parcel.readInt();
                DataHolder dataholder8 = null;
                if(i4 != 0)
                    dataholder8 = DataHolder.CREATOR.createFromParcel(parcel);
                o(dataholder8);
                parcel1.writeNoException();
                return true;

            case 8005: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int l3 = parcel.readInt();
                DataHolder dataholder7 = null;
                if(l3 != 0)
                    dataholder7 = DataHolder.CREATOR.createFromParcel(parcel);
                p(dataholder7);
                parcel1.writeNoException();
                return true;

            case 8006: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int k3 = parcel.readInt();
                DataHolder dataholder6 = null;
                if(k3 != 0)
                    dataholder6 = DataHolder.CREATOR.createFromParcel(parcel);
                q(dataholder6);
                parcel1.writeNoException();
                return true;

            case 8007: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                f(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8008: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int j3 = parcel.readInt();
                DataHolder dataholder5 = null;
                if(j3 != 0)
                    dataholder5 = DataHolder.CREATOR.createFromParcel(parcel);
                r(dataholder5);
                parcel1.writeNoException();
                return true;

            case 8009: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                onTurnBasedMatchRemoved(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8010: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                onInvitationRemoved(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 9001: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int i3 = parcel.readInt();
                DataHolder dataholder4 = null;
                if(i3 != 0)
                    dataholder4 = DataHolder.CREATOR.createFromParcel(parcel);
                j(dataholder4);
                parcel1.writeNoException();
                return true;

            case 10001: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int l2 = parcel.readInt();
                DataHolder dataholder3 = null;
                if(l2 != 0)
                    dataholder3 = DataHolder.CREATOR.createFromParcel(parcel);
                m(dataholder3);
                parcel1.writeNoException();
                return true;

            case 10002: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                onRequestRemoved(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 10003: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int k2 = parcel.readInt();
                DataHolder dataholder2 = null;
                if(k2 != 0)
                    dataholder2 = DataHolder.CREATOR.createFromParcel(parcel);
                D(dataholder2);
                parcel1.writeNoException();
                return true;

            case 10004: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int j2 = parcel.readInt();
                DataHolder dataholder1 = null;
                if(j2 != 0)
                    dataholder1 = DataHolder.CREATOR.createFromParcel(parcel);
                E(dataholder1);
                parcel1.writeNoException();
                return true;

            case 10005: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                int l1 = parcel.readInt();
                int i2 = parcel.readInt();
                Bundle bundle = null;
                if(i2 != 0)
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                b(l1, bundle);
                parcel1.writeNoException();
                return true;

            case 10006: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                k1 = parcel.readInt();
                dataholder = null;
                break;
            }
            if(k1 != 0)
                dataholder = DataHolder.CREATOR.createFromParcel(parcel);
            F(dataholder);
            parcel1.writeNoException();
            return true;
        }

        public a()
        {
            attachInterface(this, "com.google.android.gms.games.internal.IGamesCallbacks");
        }
    }

    private static class a.a
        implements ga
    {

        public void A(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            ky.transact(5035, parcel, parcel1, 0);
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

        public void B(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            ky.transact(5039, parcel, parcel1, 0);
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

        public void C(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            ky.transact(8001, parcel, parcel1, 0);
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

        public void D(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            ky.transact(10003, parcel, parcel1, 0);
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

        public void E(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            ky.transact(10004, parcel, parcel1, 0);
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

        public void F(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            ky.transact(10006, parcel, parcel1, 0);
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

        public void a(int i1, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeInt(i1);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_67;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            ky.transact(8002, parcel, parcel1, 0);
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

        public void a(int i1, String s1, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeInt(i1);
            parcel.writeString(s1);
            int j1;
            j1 = 0;
            if(flag)
                j1 = 1;
            parcel.writeInt(j1);
            ky.transact(5034, parcel, parcel1, 0);
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

        public void a(DataHolder dataholder, DataHolder dataholder1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L3:
            if(dataholder1 == null)
                break MISSING_BLOCK_LABEL_99;
            parcel.writeInt(1);
            dataholder1.writeToParcel(parcel, 0);
_L4:
            ky.transact(5005, parcel, parcel1, 0);
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

        public void a(DataHolder dataholder, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_67;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            parcel.writeStringArray(as);
            ky.transact(5026, parcel, parcel1, 0);
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

        public void aR(int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeInt(i1);
            ky.transact(5036, parcel, parcel1, 0);
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

        public void aS(int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeInt(i1);
            ky.transact(5040, parcel, parcel1, 0);
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

        public IBinder asBinder()
        {
            return ky;
        }

        public void b(int i1, int j1, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            parcel.writeString(s1);
            ky.transact(5033, parcel, parcel1, 0);
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

        public void b(int i1, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeInt(i1);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_67;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            ky.transact(10005, parcel, parcel1, 0);
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

        public void b(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            ky.transact(5002, parcel, parcel1, 0);
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

        public void b(DataHolder dataholder, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_67;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            parcel.writeStringArray(as);
            ky.transact(5027, parcel, parcel1, 0);
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

        public void c(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            ky.transact(5004, parcel, parcel1, 0);
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

        public void c(DataHolder dataholder, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_67;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            parcel.writeStringArray(as);
            ky.transact(5028, parcel, parcel1, 0);
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

        public void cM()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            ky.transact(5016, parcel, parcel1, 0);
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

        public void d(int i1, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeInt(i1);
            parcel.writeString(s1);
            ky.transact(5001, parcel, parcel1, 0);
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

        public void d(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            ky.transact(5006, parcel, parcel1, 0);
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

        public void d(DataHolder dataholder, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_67;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            parcel.writeStringArray(as);
            ky.transact(5029, parcel, parcel1, 0);
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

        public void e(int i1, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeInt(i1);
            parcel.writeString(s1);
            ky.transact(5003, parcel, parcel1, 0);
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

        public void e(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            ky.transact(5007, parcel, parcel1, 0);
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

        public void e(DataHolder dataholder, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_67;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            parcel.writeStringArray(as);
            ky.transact(5030, parcel, parcel1, 0);
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

        public void f(int i1, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeInt(i1);
            parcel.writeString(s1);
            ky.transact(8007, parcel, parcel1, 0);
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

        public void f(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            ky.transact(5008, parcel, parcel1, 0);
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

        public void f(DataHolder dataholder, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_67;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            parcel.writeStringArray(as);
            ky.transact(5031, parcel, parcel1, 0);
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

        public void g(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            ky.transact(5009, parcel, parcel1, 0);
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

        public void h(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            ky.transact(5010, parcel, parcel1, 0);
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

        public void i(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            ky.transact(5011, parcel, parcel1, 0);
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

        public void j(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            ky.transact(9001, parcel, parcel1, 0);
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

        public void k(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            ky.transact(5017, parcel, parcel1, 0);
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

        public void l(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            ky.transact(5037, parcel, parcel1, 0);
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

        public void m(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            ky.transact(10001, parcel, parcel1, 0);
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

        public void n(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            ky.transact(8003, parcel, parcel1, 0);
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

        public void o(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            ky.transact(8004, parcel, parcel1, 0);
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

        public void onInvitationRemoved(String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeString(s1);
            ky.transact(8010, parcel, parcel1, 0);
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

        public void onLeftRoom(int i1, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeInt(i1);
            parcel.writeString(s1);
            ky.transact(5020, parcel, parcel1, 0);
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

        public void onP2PConnected(String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeString(s1);
            ky.transact(6001, parcel, parcel1, 0);
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

        public void onP2PDisconnected(String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeString(s1);
            ky.transact(6002, parcel, parcel1, 0);
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

        public void onRealTimeMessageReceived(RealTimeMessage realtimemessage)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(realtimemessage == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            realtimemessage.writeToParcel(parcel, 0);
_L1:
            ky.transact(5032, parcel, parcel1, 0);
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

        public void onRequestRemoved(String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeString(s1);
            ky.transact(10002, parcel, parcel1, 0);
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

        public void onTurnBasedMatchRemoved(String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            parcel.writeString(s1);
            ky.transact(8009, parcel, parcel1, 0);
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

        public void p(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            ky.transact(8005, parcel, parcel1, 0);
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

        public void q(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            ky.transact(8006, parcel, parcel1, 0);
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

        public void r(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            ky.transact(8008, parcel, parcel1, 0);
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

        public void s(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            ky.transact(5018, parcel, parcel1, 0);
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

        public void t(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            ky.transact(5019, parcel, parcel1, 0);
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

        public void u(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            ky.transact(5021, parcel, parcel1, 0);
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

        public void v(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            ky.transact(5022, parcel, parcel1, 0);
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

        public void w(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            ky.transact(5023, parcel, parcel1, 0);
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

        public void x(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            ky.transact(5024, parcel, parcel1, 0);
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

        public void y(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            ky.transact(5025, parcel, parcel1, 0);
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

        public void z(DataHolder dataholder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
            if(dataholder == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            dataholder.writeToParcel(parcel, 0);
_L1:
            ky.transact(5038, parcel, parcel1, 0);
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


    public abstract void A(DataHolder dataholder)
        throws RemoteException;

    public abstract void B(DataHolder dataholder)
        throws RemoteException;

    public abstract void C(DataHolder dataholder)
        throws RemoteException;

    public abstract void D(DataHolder dataholder)
        throws RemoteException;

    public abstract void E(DataHolder dataholder)
        throws RemoteException;

    public abstract void F(DataHolder dataholder)
        throws RemoteException;

    public abstract void a(int i1, Bundle bundle)
        throws RemoteException;

    public abstract void a(int i1, String s1, boolean flag)
        throws RemoteException;

    public abstract void a(DataHolder dataholder, DataHolder dataholder1)
        throws RemoteException;

    public abstract void a(DataHolder dataholder, String as[])
        throws RemoteException;

    public abstract void aR(int i1)
        throws RemoteException;

    public abstract void aS(int i1)
        throws RemoteException;

    public abstract void b(int i1, int j1, String s1)
        throws RemoteException;

    public abstract void b(int i1, Bundle bundle)
        throws RemoteException;

    public abstract void b(DataHolder dataholder)
        throws RemoteException;

    public abstract void b(DataHolder dataholder, String as[])
        throws RemoteException;

    public abstract void c(DataHolder dataholder)
        throws RemoteException;

    public abstract void c(DataHolder dataholder, String as[])
        throws RemoteException;

    public abstract void cM()
        throws RemoteException;

    public abstract void d(int i1, String s1)
        throws RemoteException;

    public abstract void d(DataHolder dataholder)
        throws RemoteException;

    public abstract void d(DataHolder dataholder, String as[])
        throws RemoteException;

    public abstract void e(int i1, String s1)
        throws RemoteException;

    public abstract void e(DataHolder dataholder)
        throws RemoteException;

    public abstract void e(DataHolder dataholder, String as[])
        throws RemoteException;

    public abstract void f(int i1, String s1)
        throws RemoteException;

    public abstract void f(DataHolder dataholder)
        throws RemoteException;

    public abstract void f(DataHolder dataholder, String as[])
        throws RemoteException;

    public abstract void g(DataHolder dataholder)
        throws RemoteException;

    public abstract void h(DataHolder dataholder)
        throws RemoteException;

    public abstract void i(DataHolder dataholder)
        throws RemoteException;

    public abstract void j(DataHolder dataholder)
        throws RemoteException;

    public abstract void k(DataHolder dataholder)
        throws RemoteException;

    public abstract void l(DataHolder dataholder)
        throws RemoteException;

    public abstract void m(DataHolder dataholder)
        throws RemoteException;

    public abstract void n(DataHolder dataholder)
        throws RemoteException;

    public abstract void o(DataHolder dataholder)
        throws RemoteException;

    public abstract void onInvitationRemoved(String s1)
        throws RemoteException;

    public abstract void onLeftRoom(int i1, String s1)
        throws RemoteException;

    public abstract void onP2PConnected(String s1)
        throws RemoteException;

    public abstract void onP2PDisconnected(String s1)
        throws RemoteException;

    public abstract void onRealTimeMessageReceived(RealTimeMessage realtimemessage)
        throws RemoteException;

    public abstract void onRequestRemoved(String s1)
        throws RemoteException;

    public abstract void onTurnBasedMatchRemoved(String s1)
        throws RemoteException;

    public abstract void p(DataHolder dataholder)
        throws RemoteException;

    public abstract void q(DataHolder dataholder)
        throws RemoteException;

    public abstract void r(DataHolder dataholder)
        throws RemoteException;

    public abstract void s(DataHolder dataholder)
        throws RemoteException;

    public abstract void t(DataHolder dataholder)
        throws RemoteException;

    public abstract void u(DataHolder dataholder)
        throws RemoteException;

    public abstract void v(DataHolder dataholder)
        throws RemoteException;

    public abstract void w(DataHolder dataholder)
        throws RemoteException;

    public abstract void x(DataHolder dataholder)
        throws RemoteException;

    public abstract void y(DataHolder dataholder)
        throws RemoteException;

    public abstract void z(DataHolder dataholder)
        throws RemoteException;
}
