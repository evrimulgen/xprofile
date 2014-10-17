// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.content.Intent;
import android.net.Uri;
import android.os.*;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.DataHolderCreator;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;

// Referenced classes of package com.google.android.gms.internal:
//            ga, gy, gz, ha, 
//            gx

public interface gb
    extends IInterface
{
    public static abstract class a extends Binder
        implements gb
    {

        public static gb J(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.games.internal.IGamesService");
            if(iinterface != null && (iinterface instanceof gb))
                return (gb)iinterface;
            else
                return new a(ibinder);
        }

        public boolean onTransact(int i1, Parcel parcel, Parcel parcel1, int j1)
            throws RemoteException
        {
            switch(i1)
            {
            default:
                return super.onTransact(i1, parcel, parcel1, j1);

            case 1598968902: 
                parcel1.writeString("com.google.android.gms.games.internal.IGamesService");
                return true;

            case 5001: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                n(parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 5002: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 5003: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                String s34 = fB();
                parcel1.writeNoException();
                parcel1.writeString(s34);
                return true;

            case 5004: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                Bundle bundle9 = cY();
                parcel1.writeNoException();
                if(bundle9 != null)
                {
                    parcel1.writeInt(1);
                    bundle9.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 5005: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                IBinder ibinder6 = parcel.readStrongBinder();
                Bundle bundle8;
                if(parcel.readInt() != 0)
                    bundle8 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle8 = null;
                a(ibinder6, bundle8);
                parcel1.writeNoException();
                return true;

            case 5006: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                fH();
                parcel1.writeNoException();
                return true;

            case 5007: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                String s33 = fn();
                parcel1.writeNoException();
                parcel1.writeString(s33);
                return true;

            case 5064: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                String s32 = ax(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(s32);
                return true;

            case 5065: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                j(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5012: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                String s31 = fo();
                parcel1.writeNoException();
                parcel1.writeString(s31);
                return true;

            case 5013: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                DataHolder dataholder2 = fI();
                parcel1.writeNoException();
                if(dataholder2 != null)
                {
                    parcel1.writeInt(1);
                    dataholder2.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 5014: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5015: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga35 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                int i19 = parcel.readInt();
                boolean flag46;
                int j19;
                boolean flag47;
                if(parcel.readInt() != 0)
                    flag46 = true;
                else
                    flag46 = false;
                j19 = parcel.readInt();
                flag47 = false;
                if(j19 != 0)
                    flag47 = true;
                a(ga35, i19, flag46, flag47);
                parcel1.writeNoException();
                return true;

            case 5016: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString(), parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 5017: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                b(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 5018: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                b(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5019: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga34 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                String s30 = parcel.readString();
                int j18 = parcel.readInt();
                int k18 = parcel.readInt();
                int l18 = parcel.readInt();
                boolean flag45;
                if(parcel.readInt() != 0)
                    flag45 = true;
                else
                    flag45 = false;
                a(ga34, s30, j18, k18, l18, flag45);
                parcel1.writeNoException();
                return true;

            case 5020: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga33 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                String s29 = parcel.readString();
                int k17 = parcel.readInt();
                int l17 = parcel.readInt();
                int i18 = parcel.readInt();
                boolean flag44;
                if(parcel.readInt() != 0)
                    flag44 = true;
                else
                    flag44 = false;
                b(ga33, s29, k17, l17, i18, flag44);
                parcel1.writeNoException();
                return true;

            case 5021: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga32 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                Bundle bundle7;
                if(parcel.readInt() != 0)
                    bundle7 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle7 = null;
                a(ga32, bundle7, parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 5022: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                c(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 5023: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga31 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                String s28 = parcel.readString();
                IBinder ibinder5 = parcel.readStrongBinder();
                Bundle bundle6;
                if(parcel.readInt() != 0)
                    bundle6 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle6 = null;
                a(ga31, s28, ibinder5, bundle6);
                parcel1.writeNoException();
                return true;

            case 5024: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga30 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                String s27 = parcel.readString();
                IBinder ibinder4 = parcel.readStrongBinder();
                Bundle bundle5;
                if(parcel.readInt() != 0)
                    bundle5 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle5 = null;
                b(ga30, s27, ibinder4, bundle5);
                parcel1.writeNoException();
                return true;

            case 5025: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga29 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                String s26 = parcel.readString();
                int i17 = parcel.readInt();
                IBinder ibinder3 = parcel.readStrongBinder();
                int j17 = parcel.readInt();
                Bundle bundle4 = null;
                if(j17 != 0)
                    bundle4 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                a(ga29, s26, i17, ibinder3, bundle4);
                parcel1.writeNoException();
                return true;

            case 5026: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                d(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 5027: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                e(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 5028: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                m(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 5029: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                l(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 5058: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 5059: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                o(parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 5030: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga28 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                IBinder ibinder2 = parcel.readStrongBinder();
                int k16 = parcel.readInt();
                String as1[] = parcel.createStringArray();
                int l16 = parcel.readInt();
                Bundle bundle3 = null;
                if(l16 != 0)
                    bundle3 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                boolean flag43;
                if(parcel.readInt() != 0)
                    flag43 = true;
                else
                    flag43 = false;
                a(ga28, ibinder2, k16, as1, bundle3, flag43, parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 5031: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga27 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                IBinder ibinder1 = parcel.readStrongBinder();
                String s25 = parcel.readString();
                boolean flag42;
                if(parcel.readInt() != 0)
                    flag42 = true;
                else
                    flag42 = false;
                a(ga27, ibinder1, s25, flag42, parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 5032: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                c(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5033: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                int j16 = a(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.createByteArray(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(j16);
                return true;

            case 5034: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                int i16 = b(parcel.createByteArray(), parcel.readString(), parcel.createStringArray());
                parcel1.writeNoException();
                parcel1.writeInt(i16);
                return true;

            case 5035: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                String s24 = ay(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(s24);
                return true;

            case 5036: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                aU(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 5037: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                d(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5038: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5039: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga26 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                String s22 = parcel.readString();
                String s23 = parcel.readString();
                int i15 = parcel.readInt();
                int j15 = parcel.readInt();
                int k15 = parcel.readInt();
                int l15 = parcel.readInt();
                boolean flag41 = false;
                if(l15 != 0)
                    flag41 = true;
                a(ga26, s22, s23, i15, j15, k15, flag41);
                parcel1.writeNoException();
                return true;

            case 5040: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga25 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                String s20 = parcel.readString();
                String s21 = parcel.readString();
                int i14 = parcel.readInt();
                int j14 = parcel.readInt();
                int k14 = parcel.readInt();
                int l14 = parcel.readInt();
                boolean flag40 = false;
                if(l14 != 0)
                    flag40 = true;
                b(ga25, s20, s21, i14, j14, k14, flag40);
                parcel1.writeNoException();
                return true;

            case 5041: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                b(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5042: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                e(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5043: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                f(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5044: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga24 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                int k13 = parcel.readInt();
                int l13 = parcel.readInt();
                boolean flag38;
                boolean flag39;
                if(parcel.readInt() != 0)
                    flag38 = true;
                else
                    flag38 = false;
                if(parcel.readInt() != 0)
                    flag39 = true;
                else
                    flag39 = false;
                a(ga24, k13, l13, flag38, flag39);
                parcel1.writeNoException();
                return true;

            case 5045: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga23 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                String s19 = parcel.readString();
                int j13 = parcel.readInt();
                boolean flag36;
                boolean flag37;
                if(parcel.readInt() != 0)
                    flag36 = true;
                else
                    flag36 = false;
                if(parcel.readInt() != 0)
                    flag37 = true;
                else
                    flag37 = false;
                a(ga23, s19, j13, flag36, flag37);
                parcel1.writeNoException();
                return true;

            case 5046: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga22 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                int l12 = parcel.readInt();
                boolean flag34;
                int i13;
                boolean flag35;
                if(parcel.readInt() != 0)
                    flag34 = true;
                else
                    flag34 = false;
                i13 = parcel.readInt();
                flag35 = false;
                if(i13 != 0)
                    flag35 = true;
                b(ga22, l12, flag34, flag35);
                parcel1.writeNoException();
                return true;

            case 5047: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                f(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 5048: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga21 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                int j12 = parcel.readInt();
                boolean flag32;
                int k12;
                boolean flag33;
                if(parcel.readInt() != 0)
                    flag32 = true;
                else
                    flag32 = false;
                k12 = parcel.readInt();
                flag33 = false;
                if(k12 != 0)
                    flag33 = true;
                c(ga21, j12, flag32, flag33);
                parcel1.writeNoException();
                return true;

            case 5049: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                g(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 5050: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                az(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5051: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                b(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 5052: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                g(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5053: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                DataHolder dataholder1 = h(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                if(dataholder1 != null)
                {
                    parcel1.writeInt(1);
                    dataholder1.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 5060: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                int i12 = aA(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i12);
                return true;

            case 5054: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga20 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                String s18 = parcel.readString();
                int l11 = parcel.readInt();
                boolean flag31 = false;
                if(l11 != 0)
                    flag31 = true;
                a(ga20, s18, flag31);
                parcel1.writeNoException();
                return true;

            case 5061: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                i(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5055: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                n(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 5067: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                boolean flag30 = fJ();
                parcel1.writeNoException();
                int k11 = 0;
                if(flag30)
                    k11 = 1;
                parcel1.writeInt(k11);
                return true;

            case 5068: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                int j11 = parcel.readInt();
                boolean flag29 = false;
                if(j11 != 0)
                    flag29 = true;
                y(flag29);
                parcel1.writeNoException();
                return true;

            case 5056: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                h(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 5057: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                j(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5062: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                i(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 5063: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga19 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                int i11 = parcel.readInt();
                boolean flag28 = false;
                if(i11 != 0)
                    flag28 = true;
                Bundle bundle2;
                if(parcel.readInt() != 0)
                    bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle2 = null;
                a(ga19, flag28, bundle2);
                parcel1.writeNoException();
                return true;

            case 5066: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                Uri uri3 = aB(parcel.readString());
                parcel1.writeNoException();
                if(uri3 != null)
                {
                    parcel1.writeInt(1);
                    uri3.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 5501: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga18 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                String s17 = parcel.readString();
                int l10 = parcel.readInt();
                boolean flag26;
                boolean flag27;
                if(parcel.readInt() != 0)
                    flag26 = true;
                else
                    flag26 = false;
                if(parcel.readInt() != 0)
                    flag27 = true;
                else
                    flag27 = false;
                b(ga18, s17, l10, flag26, flag27);
                parcel1.writeNoException();
                return true;

            case 5502: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                DataHolder dataholder = fK();
                parcel1.writeNoException();
                if(dataholder != null)
                {
                    parcel1.writeInt(1);
                    dataholder.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 6001: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga17 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                int k10 = parcel.readInt();
                boolean flag25 = false;
                if(k10 != 0)
                    flag25 = true;
                a(ga17, flag25);
                parcel1.writeNoException();
                return true;

            case 6002: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga16 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                String s15 = parcel.readString();
                String s16 = parcel.readString();
                int j10 = parcel.readInt();
                boolean flag24 = false;
                if(j10 != 0)
                    flag24 = true;
                a(ga16, s15, s16, flag24);
                parcel1.writeNoException();
                return true;

            case 6003: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga15 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                int l9 = parcel.readInt();
                boolean flag22;
                int i10;
                boolean flag23;
                if(parcel.readInt() != 0)
                    flag22 = true;
                else
                    flag22 = false;
                i10 = parcel.readInt();
                flag23 = false;
                if(i10 != 0)
                    flag23 = true;
                d(ga15, l9, flag22, flag23);
                parcel1.writeNoException();
                return true;

            case 6004: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga14 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                int j9 = parcel.readInt();
                boolean flag20;
                int k9;
                boolean flag21;
                if(parcel.readInt() != 0)
                    flag20 = true;
                else
                    flag20 = false;
                k9 = parcel.readInt();
                flag21 = false;
                if(k9 != 0)
                    flag21 = true;
                e(ga14, j9, flag20, flag21);
                parcel1.writeNoException();
                return true;

            case 6501: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga13 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                String s14 = parcel.readString();
                int l8 = parcel.readInt();
                boolean flag16;
                boolean flag17;
                boolean flag18;
                int i9;
                boolean flag19;
                if(parcel.readInt() != 0)
                    flag16 = true;
                else
                    flag16 = false;
                if(parcel.readInt() != 0)
                    flag17 = true;
                else
                    flag17 = false;
                if(parcel.readInt() != 0)
                    flag18 = true;
                else
                    flag18 = false;
                i9 = parcel.readInt();
                flag19 = false;
                if(i9 != 0)
                    flag19 = true;
                a(ga13, s14, l8, flag16, flag17, flag18, flag19);
                parcel1.writeNoException();
                return true;

            case 6502: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga12 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                String s13 = parcel.readString();
                int k8 = parcel.readInt();
                boolean flag15 = false;
                if(k8 != 0)
                    flag15 = true;
                b(ga12, s13, flag15);
                parcel1.writeNoException();
                return true;

            case 6503: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga11 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                int j8 = parcel.readInt();
                boolean flag14 = false;
                if(j8 != 0)
                    flag14 = true;
                b(ga11, flag14);
                parcel1.writeNoException();
                return true;

            case 6504: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga10 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                String s12 = parcel.readString();
                int i8 = parcel.readInt();
                boolean flag13 = false;
                if(i8 != 0)
                    flag13 = true;
                c(ga10, s12, flag13);
                parcel1.writeNoException();
                return true;

            case 6505: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga9 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                String s11 = parcel.readString();
                int l7 = parcel.readInt();
                boolean flag12 = false;
                if(l7 != 0)
                    flag12 = true;
                d(ga9, s11, flag12);
                parcel1.writeNoException();
                return true;

            case 6506: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga8 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                String s9 = parcel.readString();
                String s10 = parcel.readString();
                int k7 = parcel.readInt();
                boolean flag11 = false;
                if(k7 != 0)
                    flag11 = true;
                b(ga8, s9, s10, flag11);
                parcel1.writeNoException();
                return true;

            case 6507: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                Uri uri2;
                ParcelFileDescriptor parcelfiledescriptor1;
                if(parcel.readInt() != 0)
                    uri2 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    uri2 = null;
                parcelfiledescriptor1 = e(uri2);
                parcel1.writeNoException();
                if(parcelfiledescriptor1 != null)
                {
                    parcel1.writeInt(1);
                    parcelfiledescriptor1.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 7001: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                k(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 7002: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString(), parcel.readLong(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 7003: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga7 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                String s8 = parcel.readString();
                int i7 = parcel.readInt();
                IBinder ibinder = parcel.readStrongBinder();
                int j7 = parcel.readInt();
                Bundle bundle1 = null;
                if(j7 != 0)
                    bundle1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                b(ga7, s8, i7, ibinder, bundle1);
                parcel1.writeNoException();
                return true;

            case 8001: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 8002: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                aC(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8003: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.createIntArray());
                parcel1.writeNoException();
                return true;

            case 8004: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga6 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                int j6 = parcel.readInt();
                int k6 = parcel.readInt();
                String as[] = parcel.createStringArray();
                int l6 = parcel.readInt();
                Bundle bundle = null;
                if(l6 != 0)
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                a(ga6, j6, k6, as, bundle);
                parcel1.writeNoException();
                return true;

            case 8005: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                l(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8006: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                m(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8007: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString(), parcel.createByteArray(), parcel.readString(), (ParticipantResult[])parcel.createTypedArray(ParticipantResult.CREATOR));
                parcel1.writeNoException();
                return true;

            case 8008: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString(), parcel.createByteArray(), (ParticipantResult[])parcel.createTypedArray(ParticipantResult.CREATOR));
                parcel1.writeNoException();
                return true;

            case 8009: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                n(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8010: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                o(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8011: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                c(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8012: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                b(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 8013: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                p(parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 8014: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                p(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8024: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                int i6 = fC();
                parcel1.writeNoException();
                parcel1.writeInt(i6);
                return true;

            case 8025: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                k(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8015: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                d(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8016: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                e(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8017: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString(), parcel.createIntArray());
                parcel1.writeNoException();
                return true;

            case 8026: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                c(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 8018: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readLong(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8019: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(parcel.readLong(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8020: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                b(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readLong(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8021: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                b(parcel.readLong(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8022: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                fL();
                parcel1.writeNoException();
                return true;

            case 8023: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga5 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                String s7 = parcel.readString();
                int k5 = parcel.readInt();
                int l5 = parcel.readInt();
                boolean flag10 = false;
                if(l5 != 0)
                    flag10 = true;
                a(ga5, s7, k5, flag10);
                parcel1.writeNoException();
                return true;

            case 8027: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga4 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                int j5 = parcel.readInt();
                boolean flag9 = false;
                if(j5 != 0)
                    flag9 = true;
                c(ga4, flag9);
                parcel1.writeNoException();
                return true;

            case 9001: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga3 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                String s6 = parcel.readString();
                int i5 = parcel.readInt();
                boolean flag7;
                boolean flag8;
                if(parcel.readInt() != 0)
                    flag7 = true;
                else
                    flag7 = false;
                if(parcel.readInt() != 0)
                    flag8 = true;
                else
                    flag8 = false;
                c(ga3, s6, i5, flag7, flag8);
                parcel1.writeNoException();
                return true;

            case 9002: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                q(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 9003: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                Intent intent15 = fr();
                parcel1.writeNoException();
                if(intent15 != null)
                {
                    parcel1.writeInt(1);
                    intent15.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 9004: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                Intent intent14 = au(parcel.readString());
                parcel1.writeNoException();
                if(intent14 != null)
                {
                    parcel1.writeInt(1);
                    intent14.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 9005: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                Intent intent13 = fs();
                parcel1.writeNoException();
                if(intent13 != null)
                {
                    parcel1.writeInt(1);
                    intent13.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 9006: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                Intent intent12 = ft();
                parcel1.writeNoException();
                if(intent12 != null)
                {
                    parcel1.writeInt(1);
                    intent12.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 9007: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                Intent intent11 = fu();
                parcel1.writeNoException();
                if(intent11 != null)
                {
                    parcel1.writeInt(1);
                    intent11.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 9008: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                int k4 = parcel.readInt();
                int l4 = parcel.readInt();
                boolean flag6;
                Intent intent10;
                if(parcel.readInt() != 0)
                    flag6 = true;
                else
                    flag6 = false;
                intent10 = a(k4, l4, flag6);
                parcel1.writeNoException();
                if(intent10 != null)
                {
                    parcel1.writeInt(1);
                    intent10.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 9009: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                int i4 = parcel.readInt();
                int j4 = parcel.readInt();
                boolean flag5;
                Intent intent9;
                if(parcel.readInt() != 0)
                    flag5 = true;
                else
                    flag5 = false;
                intent9 = b(i4, j4, flag5);
                parcel1.writeNoException();
                if(intent9 != null)
                {
                    parcel1.writeInt(1);
                    intent9.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 9010: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                Intent intent8 = fy();
                parcel1.writeNoException();
                if(intent8 != null)
                {
                    parcel1.writeInt(1);
                    intent8.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 9011: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                RoomEntity roomentity;
                Intent intent7;
                if(parcel.readInt() != 0)
                    roomentity = (RoomEntity)RoomEntity.CREATOR.createFromParcel(parcel);
                else
                    roomentity = null;
                intent7 = a(roomentity, parcel.readInt());
                parcel1.writeNoException();
                if(intent7 != null)
                {
                    parcel1.writeInt(1);
                    intent7.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 9012: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                Intent intent6 = fz();
                parcel1.writeNoException();
                if(intent6 != null)
                {
                    parcel1.writeInt(1);
                    intent6.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 9013: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                Intent intent5 = fM();
                parcel1.writeNoException();
                if(intent5 != null)
                {
                    parcel1.writeInt(1);
                    intent5.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 9031: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ParticipantEntity aparticipantentity[] = (ParticipantEntity[])parcel.createTypedArray(ParticipantEntity.CREATOR);
                String s4 = parcel.readString();
                String s5 = parcel.readString();
                Uri uri;
                int l3;
                Uri uri1;
                Intent intent4;
                if(parcel.readInt() != 0)
                    uri = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    uri = null;
                l3 = parcel.readInt();
                uri1 = null;
                if(l3 != 0)
                    uri1 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                intent4 = a(aparticipantentity, s4, s5, uri, uri1);
                parcel1.writeNoException();
                if(intent4 != null)
                {
                    parcel1.writeInt(1);
                    intent4.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 9019: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                int k3 = fA();
                parcel1.writeNoException();
                parcel1.writeInt(k3);
                return true;

            case 9020: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga2 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                String s3 = parcel.readString();
                int j3 = parcel.readInt();
                boolean flag3;
                boolean flag4;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                d(ga2, s3, j3, flag3, flag4);
                parcel1.writeNoException();
                return true;

            case 9028: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga1 = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                String s1 = parcel.readString();
                String s2 = parcel.readString();
                int i3 = parcel.readInt();
                boolean flag1;
                boolean flag2;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                a(ga1, s1, s2, i3, flag1, flag2);
                parcel1.writeNoException();
                return true;

            case 9030: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ParcelFileDescriptor parcelfiledescriptor = aD(parcel.readString());
                parcel1.writeNoException();
                if(parcelfiledescriptor != null)
                {
                    parcel1.writeInt(1);
                    parcelfiledescriptor.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 10001: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                c(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 10002: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                q(parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 10003: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                c(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readLong(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 10004: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                c(parcel.readLong(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 10005: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString(), parcel.createStringArray(), parcel.readInt(), parcel.createByteArray(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 10006: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 10007: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                b(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 10008: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 10009: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 10010: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 10011: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 10012: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                Intent intent3 = a(parcel.readInt(), parcel.createByteArray(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                if(intent3 != null)
                {
                    parcel1.writeInt(1);
                    intent3.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 10013: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                int l2 = fE();
                parcel1.writeNoException();
                parcel1.writeInt(l2);
                return true;

            case 10023: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                int k2 = fF();
                parcel1.writeNoException();
                parcel1.writeInt(k2);
                return true;

            case 10015: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                Intent intent2 = fD();
                parcel1.writeNoException();
                if(intent2 != null)
                {
                    parcel1.writeInt(1);
                    intent2.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 10022: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                int j2 = parcel.readInt();
                gz gz1 = null;
                if(j2 != 0)
                    gz1 = gz.CREATOR.am(parcel);
                Intent intent1 = a(gz1, parcel.readString());
                parcel1.writeNoException();
                if(intent1 != null)
                {
                    parcel1.writeInt(1);
                    intent1.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 10014: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                o(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 10016: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 10017: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ga ga = com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder());
                String s = parcel.readString();
                int l1 = parcel.readInt();
                int i2 = parcel.readInt();
                boolean flag = false;
                if(i2 != 0)
                    flag = true;
                b(ga, s, l1, flag);
                parcel1.writeNoException();
                return true;

            case 10021: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                int k1 = parcel.readInt();
                gy gy1 = null;
                if(k1 != 0)
                    gy1 = gy.CREATOR.al(parcel);
                Intent intent = a(gy1, parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                if(intent != null)
                {
                    parcel1.writeInt(1);
                    intent.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 10018: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readInt(), parcel.createIntArray());
                parcel1.writeNoException();
                return true;

            case 10019: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                a(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.readString(), parcel.readInt(), parcel.createIntArray());
                parcel1.writeNoException();
                return true;

            case 10020: 
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                c(com.google.android.gms.internal.ga.a.I(parcel.readStrongBinder()), parcel.createStringArray());
                parcel1.writeNoException();
                return true;
            }
        }
    }

    private static class a.a
        implements gb
    {

        public int a(ga ga1, byte abyte0[], String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_98;
            IBinder ibinder = ga1.asBinder();
_L1:
            int i1;
            parcel.writeStrongBinder(ibinder);
            parcel.writeByteArray(abyte0);
            parcel.writeString(s);
            parcel.writeString(s1);
            ky.transact(5033, parcel, parcel1, 0);
            parcel1.readException();
            i1 = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i1;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Intent a(int i1, int j1, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            int k1;
            k1 = 0;
            if(flag)
                k1 = 1;
            parcel.writeInt(k1);
            ky.transact(9008, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            intent = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Intent a(int i1, byte abyte0[], int j1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeInt(i1);
            parcel.writeByteArray(abyte0);
            parcel.writeInt(j1);
            parcel.writeString(s);
            ky.transact(10012, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            intent = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Intent a(RoomEntity roomentity, int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(roomentity == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            roomentity.writeToParcel(parcel, 0);
_L3:
            Intent intent;
            parcel.writeInt(i1);
            ky.transact(9011, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_114;
            intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            parcel.writeInt(0);
              goto _L3
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
            intent = null;
              goto _L4
        }

        public Intent a(gy gy1, String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(gy1 == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            gy1.writeToParcel(parcel, 0);
_L3:
            Intent intent;
            parcel.writeString(s);
            parcel.writeString(s1);
            ky.transact(10021, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_129;
            intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            parcel.writeInt(0);
              goto _L3
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
            intent = null;
              goto _L4
        }

        public Intent a(gz gz1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(gz1 == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            gz1.writeToParcel(parcel, 0);
_L3:
            Intent intent;
            parcel.writeString(s);
            ky.transact(10022, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_114;
            intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            parcel.writeInt(0);
              goto _L3
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
            intent = null;
              goto _L4
        }

        public Intent a(ParticipantEntity aparticipantentity[], String s, String s1, Uri uri, Uri uri1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeTypedArray(aparticipantentity, 0);
            parcel.writeString(s);
            parcel.writeString(s1);
            if(uri == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L5:
            if(uri1 == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            uri1.writeToParcel(parcel, 0);
_L6:
            Intent intent;
            ky.transact(9031, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_166;
            intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L7:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            parcel.writeInt(0);
              goto _L5
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
_L4:
            parcel.writeInt(0);
              goto _L6
            intent = null;
              goto _L7
        }

        public void a(long l1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeLong(l1);
            parcel.writeString(s);
            ky.transact(8019, parcel, parcel1, 0);
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

        public void a(IBinder ibinder, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeStrongBinder(ibinder);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_67;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            ky.transact(5005, parcel, parcel1, 0);
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

        public void a(ga ga1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_61;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            ky.transact(5002, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ga ga1, int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_70;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i1);
            ky.transact(10016, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ga ga1, int i1, int j1, int k1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_89;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            parcel.writeInt(k1);
            ky.transact(10009, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ga ga1, int i1, int j1, boolean flag, boolean flag1)
            throws RemoteException
        {
            int k1;
            Parcel parcel;
            Parcel parcel1;
            k1 = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_113;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            int l1;
            if(flag)
                l1 = k1;
            else
                l1 = 0;
            parcel.writeInt(l1);
            if(!flag1)
                k1 = 0;
            parcel.writeInt(k1);
            ky.transact(5044, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ga ga1, int i1, int j1, String as[], Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder = ga1.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            parcel.writeStringArray(as);
            if(bundle == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L6:
            ky.transact(8004, parcel, parcel1, 0);
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
              goto _L5
        }

        public void a(ga ga1, int i1, boolean flag, boolean flag1)
            throws RemoteException
        {
            int j1;
            Parcel parcel;
            Parcel parcel1;
            j1 = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_106;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i1);
            int k1;
            if(flag)
                k1 = j1;
            else
                k1 = 0;
            parcel.writeInt(k1);
            if(!flag1)
                j1 = 0;
            parcel.writeInt(j1);
            ky.transact(5015, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ga ga1, int i1, int ai[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_82;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i1);
            parcel.writeIntArray(ai);
            ky.transact(10018, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ga ga1, long l1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_76;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeLong(l1);
            ky.transact(5058, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ga ga1, long l1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_83;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeLong(l1);
            parcel.writeString(s);
            ky.transact(8018, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ga ga1, Bundle bundle, int i1, int j1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder = ga1.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder);
            if(bundle == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L6:
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            ky.transact(5021, parcel, parcel1, 0);
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
              goto _L5
        }

        public void a(ga ga1, IBinder ibinder, int i1, String as[], Bundle bundle, boolean flag, long l1)
            throws RemoteException
        {
            int j1;
            Parcel parcel;
            Parcel parcel1;
            j1 = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder1 = ga1.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder1);
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i1);
            parcel.writeStringArray(as);
            if(bundle == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
            break MISSING_BLOCK_LABEL_158;
_L6:
            parcel.writeInt(j1);
            parcel.writeLong(l1);
            ky.transact(5030, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            ibinder1 = null;
              goto _L5
_L4:
            parcel.writeInt(0);
            break MISSING_BLOCK_LABEL_158;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
            while(!flag) 
            {
                j1 = 0;
                break;
            }
              goto _L6
        }

        public void a(ga ga1, IBinder ibinder, String s, boolean flag, long l1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_107;
            IBinder ibinder1 = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder1);
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            int i1;
            i1 = 0;
            if(flag)
                i1 = 1;
            parcel.writeInt(i1);
            parcel.writeLong(l1);
            ky.transact(5031, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder1 = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ga ga1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_70;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            ky.transact(5014, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ga ga1, String s, int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_82;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeInt(i1);
            ky.transact(10011, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ga ga1, String s, int i1, int j1, int k1, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_114;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            parcel.writeInt(k1);
            int l1;
            l1 = 0;
            if(flag)
                l1 = 1;
            parcel.writeInt(l1);
            ky.transact(5019, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ga ga1, String s, int i1, IBinder ibinder, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder1 = ga1.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder1);
            parcel.writeString(s);
            parcel.writeInt(i1);
            parcel.writeStrongBinder(ibinder);
            if(bundle == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L6:
            ky.transact(5025, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            ibinder1 = null;
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

        public void a(ga ga1, String s, int i1, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_100;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeInt(i1);
            int j1;
            j1 = 0;
            if(flag)
                j1 = 1;
            parcel.writeInt(j1);
            ky.transact(8023, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ga ga1, String s, int i1, boolean flag, boolean flag1)
            throws RemoteException
        {
            int j1;
            Parcel parcel;
            Parcel parcel1;
            j1 = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_113;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeInt(i1);
            int k1;
            if(flag)
                k1 = j1;
            else
                k1 = 0;
            parcel.writeInt(k1);
            if(!flag1)
                j1 = 0;
            parcel.writeInt(j1);
            ky.transact(5045, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ga ga1, String s, int i1, boolean flag, boolean flag1, boolean flag2, boolean flag3)
            throws RemoteException
        {
            int j1;
            Parcel parcel;
            Parcel parcel1;
            j1 = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_145;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeInt(i1);
            int k1;
            int l1;
            int i2;
            if(flag)
                k1 = j1;
            else
                k1 = 0;
            parcel.writeInt(k1);
            if(flag1)
                l1 = j1;
            else
                l1 = 0;
            parcel.writeInt(l1);
            if(flag2)
                i2 = j1;
            else
                i2 = 0;
            parcel.writeInt(i2);
            if(!flag3)
                j1 = 0;
            parcel.writeInt(j1);
            ky.transact(6501, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ga ga1, String s, int i1, int ai[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_89;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeInt(i1);
            parcel.writeIntArray(ai);
            ky.transact(10019, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ga ga1, String s, long l1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_82;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeLong(l1);
            ky.transact(5016, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ga ga1, String s, long l1, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_89;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeLong(l1);
            parcel.writeString(s1);
            ky.transact(7002, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ga ga1, String s, IBinder ibinder, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder1 = ga1.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder1);
            parcel.writeString(s);
            parcel.writeStrongBinder(ibinder);
            if(bundle == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L6:
            ky.transact(5023, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            ibinder1 = null;
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

        public void a(ga ga1, String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_82;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeString(s1);
            ky.transact(5038, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ga ga1, String s, String s1, int i1, int j1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_96;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            ky.transact(8001, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ga ga1, String s, String s1, int i1, int j1, int k1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_103;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            parcel.writeInt(k1);
            ky.transact(10010, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ga ga1, String s, String s1, int i1, int j1, int k1, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_121;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            parcel.writeInt(k1);
            int l1;
            l1 = 0;
            if(flag)
                l1 = 1;
            parcel.writeInt(l1);
            ky.transact(5039, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ga ga1, String s, String s1, int i1, boolean flag, boolean flag1)
            throws RemoteException
        {
            int j1;
            Parcel parcel;
            Parcel parcel1;
            j1 = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_120;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i1);
            int k1;
            if(flag)
                k1 = j1;
            else
                k1 = 0;
            parcel.writeInt(k1);
            if(!flag1)
                j1 = 0;
            parcel.writeInt(j1);
            ky.transact(9028, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ga ga1, String s, String s1, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_100;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeString(s1);
            int i1;
            i1 = 0;
            if(flag)
                i1 = 1;
            parcel.writeInt(i1);
            ky.transact(6002, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ga ga1, String s, String s1, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_89;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeStringArray(as);
            ky.transact(10008, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ga ga1, String s, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_93;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            int i1;
            i1 = 0;
            if(flag)
                i1 = 1;
            parcel.writeInt(i1);
            ky.transact(5054, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ga ga1, String s, byte abyte0[], String s1, ParticipantResult aparticipantresult[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_97;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeByteArray(abyte0);
            parcel.writeString(s1);
            parcel.writeTypedArray(aparticipantresult, 0);
            ky.transact(8007, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ga ga1, String s, byte abyte0[], ParticipantResult aparticipantresult[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_90;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeByteArray(abyte0);
            parcel.writeTypedArray(aparticipantresult, 0);
            ky.transact(8008, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ga ga1, String s, int ai[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_82;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeIntArray(ai);
            ky.transact(8017, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ga ga1, String s, String as[], int i1, byte abyte0[], int j1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_103;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeStringArray(as);
            parcel.writeInt(i1);
            parcel.writeByteArray(abyte0);
            parcel.writeInt(j1);
            ky.transact(10005, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ga ga1, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_81;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            int i1;
            i1 = 0;
            if(flag)
                i1 = 1;
            parcel.writeInt(i1);
            ky.transact(6001, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ga ga1, boolean flag, Bundle bundle)
            throws RemoteException
        {
            int i1;
            Parcel parcel;
            Parcel parcel1;
            i1 = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder = ga1.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder);
            if(!flag)
                i1 = 0;
            parcel.writeInt(i1);
            if(bundle == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L6:
            ky.transact(5063, parcel, parcel1, 0);
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
              goto _L5
        }

        public void a(ga ga1, int ai[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_70;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeIntArray(ai);
            ky.transact(8003, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void a(ga ga1, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_70;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeStringArray(as);
            ky.transact(10006, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int aA(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i1;
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s);
            ky.transact(5060, parcel, parcel1, 0);
            parcel1.readException();
            i1 = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Uri aB(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s);
            ky.transact(5066, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Uri uri = (Uri)Uri.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return uri;
_L2:
            uri = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void aC(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s);
            ky.transact(8002, parcel, parcel1, 0);
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

        public ParcelFileDescriptor aD(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s);
            ky.transact(9030, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ParcelFileDescriptor parcelfiledescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return parcelfiledescriptor;
_L2:
            parcelfiledescriptor = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void aU(int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
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

        public IBinder asBinder()
        {
            return ky;
        }

        public Intent au(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s);
            ky.transact(9004, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            intent = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String ax(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s1;
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s);
            ky.transact(5064, parcel, parcel1, 0);
            parcel1.readException();
            s1 = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String ay(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s1;
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s);
            ky.transact(5035, parcel, parcel1, 0);
            parcel1.readException();
            s1 = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void az(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s);
            ky.transact(5050, parcel, parcel1, 0);
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

        public int b(byte abyte0[], String s, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i1;
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeByteArray(abyte0);
            parcel.writeString(s);
            parcel.writeStringArray(as);
            ky.transact(5034, parcel, parcel1, 0);
            parcel1.readException();
            i1 = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Intent b(int i1, int j1, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            int k1;
            k1 = 0;
            if(flag)
                k1 = 1;
            parcel.writeInt(k1);
            ky.transact(9009, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            intent = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void b(long l1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeLong(l1);
            parcel.writeString(s);
            ky.transact(8021, parcel, parcel1, 0);
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

        public void b(ga ga1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_61;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            ky.transact(5017, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void b(ga ga1, int i1, boolean flag, boolean flag1)
            throws RemoteException
        {
            int j1;
            Parcel parcel;
            Parcel parcel1;
            j1 = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_106;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i1);
            int k1;
            if(flag)
                k1 = j1;
            else
                k1 = 0;
            parcel.writeInt(k1);
            if(!flag1)
                j1 = 0;
            parcel.writeInt(j1);
            ky.transact(5046, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void b(ga ga1, long l1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_76;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeLong(l1);
            ky.transact(8012, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void b(ga ga1, long l1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_83;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeLong(l1);
            parcel.writeString(s);
            ky.transact(8020, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void b(ga ga1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_70;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            ky.transact(5018, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void b(ga ga1, String s, int i1, int j1, int k1, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_114;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            parcel.writeInt(k1);
            int l1;
            l1 = 0;
            if(flag)
                l1 = 1;
            parcel.writeInt(l1);
            ky.transact(5020, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void b(ga ga1, String s, int i1, IBinder ibinder, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder1 = ga1.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder1);
            parcel.writeString(s);
            parcel.writeInt(i1);
            parcel.writeStrongBinder(ibinder);
            if(bundle == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L6:
            ky.transact(7003, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            ibinder1 = null;
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

        public void b(ga ga1, String s, int i1, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_100;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeInt(i1);
            int j1;
            j1 = 0;
            if(flag)
                j1 = 1;
            parcel.writeInt(j1);
            ky.transact(10017, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void b(ga ga1, String s, int i1, boolean flag, boolean flag1)
            throws RemoteException
        {
            int j1;
            Parcel parcel;
            Parcel parcel1;
            j1 = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_113;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeInt(i1);
            int k1;
            if(flag)
                k1 = j1;
            else
                k1 = 0;
            parcel.writeInt(k1);
            if(!flag1)
                j1 = 0;
            parcel.writeInt(j1);
            ky.transact(5501, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void b(ga ga1, String s, IBinder ibinder, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null) goto _L2; else goto _L1
_L1:
            IBinder ibinder1 = ga1.asBinder();
_L5:
            parcel.writeStrongBinder(ibinder1);
            parcel.writeString(s);
            parcel.writeStrongBinder(ibinder);
            if(bundle == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L6:
            ky.transact(5024, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            ibinder1 = null;
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

        public void b(ga ga1, String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_82;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeString(s1);
            ky.transact(5041, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void b(ga ga1, String s, String s1, int i1, int j1, int k1, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_121;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            parcel.writeInt(k1);
            int l1;
            l1 = 0;
            if(flag)
                l1 = 1;
            parcel.writeInt(l1);
            ky.transact(5040, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void b(ga ga1, String s, String s1, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_100;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeString(s1);
            int i1;
            i1 = 0;
            if(flag)
                i1 = 1;
            parcel.writeInt(i1);
            ky.transact(6506, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void b(ga ga1, String s, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_93;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            int i1;
            i1 = 0;
            if(flag)
                i1 = 1;
            parcel.writeInt(i1);
            ky.transact(6502, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void b(ga ga1, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_81;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            int i1;
            i1 = 0;
            if(flag)
                i1 = 1;
            parcel.writeInt(i1);
            ky.transact(6503, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void b(ga ga1, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_70;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeStringArray(as);
            ky.transact(10007, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void b(String s, String s1, int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i1);
            ky.transact(5051, parcel, parcel1, 0);
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

        public void c(long l1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeLong(l1);
            parcel.writeString(s);
            ky.transact(10004, parcel, parcel1, 0);
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

        public void c(ga ga1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_61;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            ky.transact(5022, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void c(ga ga1, int i1, boolean flag, boolean flag1)
            throws RemoteException
        {
            int j1;
            Parcel parcel;
            Parcel parcel1;
            j1 = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_106;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i1);
            int k1;
            if(flag)
                k1 = j1;
            else
                k1 = 0;
            parcel.writeInt(k1);
            if(!flag1)
                j1 = 0;
            parcel.writeInt(j1);
            ky.transact(5048, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void c(ga ga1, long l1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_76;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeLong(l1);
            ky.transact(10001, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void c(ga ga1, long l1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_83;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeLong(l1);
            parcel.writeString(s);
            ky.transact(10003, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void c(ga ga1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_70;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            ky.transact(5032, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void c(ga ga1, String s, int i1, boolean flag, boolean flag1)
            throws RemoteException
        {
            int j1;
            Parcel parcel;
            Parcel parcel1;
            j1 = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_113;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeInt(i1);
            int k1;
            if(flag)
                k1 = j1;
            else
                k1 = 0;
            parcel.writeInt(k1);
            if(!flag1)
                j1 = 0;
            parcel.writeInt(j1);
            ky.transact(9001, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void c(ga ga1, String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_82;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeString(s1);
            ky.transact(8011, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void c(ga ga1, String s, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_93;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            int i1;
            i1 = 0;
            if(flag)
                i1 = 1;
            parcel.writeInt(i1);
            ky.transact(6504, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void c(ga ga1, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_81;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            int i1;
            i1 = 0;
            if(flag)
                i1 = 1;
            parcel.writeInt(i1);
            ky.transact(8027, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void c(ga ga1, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_70;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeStringArray(as);
            ky.transact(10020, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void c(String s, String s1, int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i1);
            ky.transact(8026, parcel, parcel1, 0);
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

        public Bundle cY()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            ky.transact(5004, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return bundle;
_L2:
            bundle = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void d(ga ga1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_61;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            ky.transact(5026, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void d(ga ga1, int i1, boolean flag, boolean flag1)
            throws RemoteException
        {
            int j1;
            Parcel parcel;
            Parcel parcel1;
            j1 = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_106;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i1);
            int k1;
            if(flag)
                k1 = j1;
            else
                k1 = 0;
            parcel.writeInt(k1);
            if(!flag1)
                j1 = 0;
            parcel.writeInt(j1);
            ky.transact(6003, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void d(ga ga1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_70;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            ky.transact(5037, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void d(ga ga1, String s, int i1, boolean flag, boolean flag1)
            throws RemoteException
        {
            int j1;
            Parcel parcel;
            Parcel parcel1;
            j1 = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_113;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeInt(i1);
            int k1;
            if(flag)
                k1 = j1;
            else
                k1 = 0;
            parcel.writeInt(k1);
            if(!flag1)
                j1 = 0;
            parcel.writeInt(j1);
            ky.transact(9020, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void d(ga ga1, String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_82;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeString(s1);
            ky.transact(8015, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void d(ga ga1, String s, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_93;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            int i1;
            i1 = 0;
            if(flag)
                i1 = 1;
            parcel.writeInt(i1);
            ky.transact(6505, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ParcelFileDescriptor e(Uri uri)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(uri == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L3:
            ParcelFileDescriptor parcelfiledescriptor;
            ky.transact(6507, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_102;
            parcelfiledescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return parcelfiledescriptor;
_L2:
            parcel.writeInt(0);
              goto _L3
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
            parcelfiledescriptor = null;
              goto _L4
        }

        public void e(ga ga1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_61;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            ky.transact(5027, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void e(ga ga1, int i1, boolean flag, boolean flag1)
            throws RemoteException
        {
            int j1;
            Parcel parcel;
            Parcel parcel1;
            j1 = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_106;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i1);
            int k1;
            if(flag)
                k1 = j1;
            else
                k1 = 0;
            parcel.writeInt(k1);
            if(!flag1)
                j1 = 0;
            parcel.writeInt(j1);
            ky.transact(6004, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void e(ga ga1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_70;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            ky.transact(5042, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void e(ga ga1, String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_82;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeString(s1);
            ky.transact(8016, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void f(ga ga1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_61;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            ky.transact(5047, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void f(ga ga1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_70;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            ky.transact(5043, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int fA()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i1;
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            ky.transact(9019, parcel, parcel1, 0);
            parcel1.readException();
            i1 = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String fB()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            ky.transact(5003, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int fC()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i1;
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            ky.transact(8024, parcel, parcel1, 0);
            parcel1.readException();
            i1 = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Intent fD()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            ky.transact(10015, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            intent = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int fE()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i1;
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            ky.transact(10013, parcel, parcel1, 0);
            parcel1.readException();
            i1 = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int fF()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i1;
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            ky.transact(10023, parcel, parcel1, 0);
            parcel1.readException();
            i1 = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void fH()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            ky.transact(5006, parcel, parcel1, 0);
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

        public DataHolder fI()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            ky.transact(5013, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            DataHolder dataholder1 = DataHolder.CREATOR.createFromParcel(parcel1);
            DataHolder dataholder = dataholder1;
_L4:
            parcel1.recycle();
            parcel.recycle();
            return dataholder;
_L2:
            dataholder = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean fJ()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i1;
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            ky.transact(5067, parcel, parcel1, 0);
            parcel1.readException();
            i1 = parcel1.readInt();
            boolean flag = false;
            if(i1 != 0)
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

        public DataHolder fK()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            ky.transact(5502, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            DataHolder dataholder1 = DataHolder.CREATOR.createFromParcel(parcel1);
            DataHolder dataholder = dataholder1;
_L4:
            parcel1.recycle();
            parcel.recycle();
            return dataholder;
_L2:
            dataholder = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void fL()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            ky.transact(8022, parcel, parcel1, 0);
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

        public Intent fM()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            ky.transact(9013, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            intent = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String fn()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            ky.transact(5007, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String fo()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            ky.transact(5012, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Intent fr()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            ky.transact(9003, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            intent = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Intent fs()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            ky.transact(9005, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            intent = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Intent ft()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            ky.transact(9006, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            intent = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Intent fu()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            ky.transact(9007, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            intent = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Intent fy()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            ky.transact(9010, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            intent = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Intent fz()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            ky.transact(9012, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            intent = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void g(ga ga1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_61;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            ky.transact(5049, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void g(ga ga1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_70;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            ky.transact(5052, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public DataHolder h(ga ga1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_101;
            IBinder ibinder = ga1.asBinder();
_L1:
            int i1;
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            ky.transact(5053, parcel, parcel1, 0);
            parcel1.readException();
            i1 = parcel1.readInt();
            DataHolder dataholder;
            dataholder = null;
            if(i1 == 0)
                break MISSING_BLOCK_LABEL_89;
            DataHolder dataholder1 = DataHolder.CREATOR.createFromParcel(parcel1);
            dataholder = dataholder1;
            parcel1.recycle();
            parcel.recycle();
            return dataholder;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void h(ga ga1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_61;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            ky.transact(5056, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void i(ga ga1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_61;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            ky.transact(5062, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void i(ga ga1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_70;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            ky.transact(5061, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void j(ga ga1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_70;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            ky.transact(5057, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void j(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s);
            parcel.writeString(s1);
            ky.transact(5065, parcel, parcel1, 0);
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

        public void k(ga ga1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_70;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            ky.transact(7001, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void k(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s);
            parcel.writeString(s1);
            ky.transact(8025, parcel, parcel1, 0);
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

        public void l(ga ga1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_70;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            ky.transact(8005, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void l(String s, int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s);
            parcel.writeInt(i1);
            ky.transact(5029, parcel, parcel1, 0);
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

        public void m(ga ga1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_70;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            ky.transact(8006, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void m(String s, int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s);
            parcel.writeInt(i1);
            ky.transact(5028, parcel, parcel1, 0);
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

        public void n(long l1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeLong(l1);
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

        public void n(ga ga1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_70;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            ky.transact(8009, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void n(String s, int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s);
            parcel.writeInt(i1);
            ky.transact(5055, parcel, parcel1, 0);
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

        public void o(long l1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeLong(l1);
            ky.transact(5059, parcel, parcel1, 0);
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

        public void o(ga ga1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_70;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            ky.transact(8010, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void o(String s, int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeString(s);
            parcel.writeInt(i1);
            ky.transact(10014, parcel, parcel1, 0);
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

        public void p(long l1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeLong(l1);
            ky.transact(8013, parcel, parcel1, 0);
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

        public void p(ga ga1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_70;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            ky.transact(8014, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void q(long l1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            parcel.writeLong(l1);
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

        public void q(ga ga1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if(ga1 == null)
                break MISSING_BLOCK_LABEL_70;
            IBinder ibinder = ga1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            ky.transact(9002, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void y(boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            int i1;
            i1 = 0;
            if(flag)
                i1 = 1;
            parcel.writeInt(i1);
            ky.transact(5068, parcel, parcel1, 0);
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

        private IBinder ky;

        a.a(IBinder ibinder)
        {
            ky = ibinder;
        }
    }


    public abstract int a(ga ga, byte abyte0[], String s, String s1)
        throws RemoteException;

    public abstract Intent a(int i1, int j1, boolean flag)
        throws RemoteException;

    public abstract Intent a(int i1, byte abyte0[], int j1, String s)
        throws RemoteException;

    public abstract Intent a(RoomEntity roomentity, int i1)
        throws RemoteException;

    public abstract Intent a(gy gy, String s, String s1)
        throws RemoteException;

    public abstract Intent a(gz gz, String s)
        throws RemoteException;

    public abstract Intent a(ParticipantEntity aparticipantentity[], String s, String s1, Uri uri, Uri uri1)
        throws RemoteException;

    public abstract void a(long l1, String s)
        throws RemoteException;

    public abstract void a(IBinder ibinder, Bundle bundle)
        throws RemoteException;

    public abstract void a(ga ga)
        throws RemoteException;

    public abstract void a(ga ga, int i1)
        throws RemoteException;

    public abstract void a(ga ga, int i1, int j1, int k1)
        throws RemoteException;

    public abstract void a(ga ga, int i1, int j1, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void a(ga ga, int i1, int j1, String as[], Bundle bundle)
        throws RemoteException;

    public abstract void a(ga ga, int i1, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void a(ga ga, int i1, int ai[])
        throws RemoteException;

    public abstract void a(ga ga, long l1)
        throws RemoteException;

    public abstract void a(ga ga, long l1, String s)
        throws RemoteException;

    public abstract void a(ga ga, Bundle bundle, int i1, int j1)
        throws RemoteException;

    public abstract void a(ga ga, IBinder ibinder, int i1, String as[], Bundle bundle, boolean flag, long l1)
        throws RemoteException;

    public abstract void a(ga ga, IBinder ibinder, String s, boolean flag, long l1)
        throws RemoteException;

    public abstract void a(ga ga, String s)
        throws RemoteException;

    public abstract void a(ga ga, String s, int i1)
        throws RemoteException;

    public abstract void a(ga ga, String s, int i1, int j1, int k1, boolean flag)
        throws RemoteException;

    public abstract void a(ga ga, String s, int i1, IBinder ibinder, Bundle bundle)
        throws RemoteException;

    public abstract void a(ga ga, String s, int i1, boolean flag)
        throws RemoteException;

    public abstract void a(ga ga, String s, int i1, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void a(ga ga, String s, int i1, boolean flag, boolean flag1, boolean flag2, boolean flag3)
        throws RemoteException;

    public abstract void a(ga ga, String s, int i1, int ai[])
        throws RemoteException;

    public abstract void a(ga ga, String s, long l1)
        throws RemoteException;

    public abstract void a(ga ga, String s, long l1, String s1)
        throws RemoteException;

    public abstract void a(ga ga, String s, IBinder ibinder, Bundle bundle)
        throws RemoteException;

    public abstract void a(ga ga, String s, String s1)
        throws RemoteException;

    public abstract void a(ga ga, String s, String s1, int i1, int j1)
        throws RemoteException;

    public abstract void a(ga ga, String s, String s1, int i1, int j1, int k1)
        throws RemoteException;

    public abstract void a(ga ga, String s, String s1, int i1, int j1, int k1, boolean flag)
        throws RemoteException;

    public abstract void a(ga ga, String s, String s1, int i1, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void a(ga ga, String s, String s1, boolean flag)
        throws RemoteException;

    public abstract void a(ga ga, String s, String s1, String as[])
        throws RemoteException;

    public abstract void a(ga ga, String s, boolean flag)
        throws RemoteException;

    public abstract void a(ga ga, String s, byte abyte0[], String s1, ParticipantResult aparticipantresult[])
        throws RemoteException;

    public abstract void a(ga ga, String s, byte abyte0[], ParticipantResult aparticipantresult[])
        throws RemoteException;

    public abstract void a(ga ga, String s, int ai[])
        throws RemoteException;

    public abstract void a(ga ga, String s, String as[], int i1, byte abyte0[], int j1)
        throws RemoteException;

    public abstract void a(ga ga, boolean flag)
        throws RemoteException;

    public abstract void a(ga ga, boolean flag, Bundle bundle)
        throws RemoteException;

    public abstract void a(ga ga, int ai[])
        throws RemoteException;

    public abstract void a(ga ga, String as[])
        throws RemoteException;

    public abstract int aA(String s)
        throws RemoteException;

    public abstract Uri aB(String s)
        throws RemoteException;

    public abstract void aC(String s)
        throws RemoteException;

    public abstract ParcelFileDescriptor aD(String s)
        throws RemoteException;

    public abstract void aU(int i1)
        throws RemoteException;

    public abstract Intent au(String s)
        throws RemoteException;

    public abstract String ax(String s)
        throws RemoteException;

    public abstract String ay(String s)
        throws RemoteException;

    public abstract void az(String s)
        throws RemoteException;

    public abstract int b(byte abyte0[], String s, String as[])
        throws RemoteException;

    public abstract Intent b(int i1, int j1, boolean flag)
        throws RemoteException;

    public abstract void b(long l1, String s)
        throws RemoteException;

    public abstract void b(ga ga)
        throws RemoteException;

    public abstract void b(ga ga, int i1, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void b(ga ga, long l1)
        throws RemoteException;

    public abstract void b(ga ga, long l1, String s)
        throws RemoteException;

    public abstract void b(ga ga, String s)
        throws RemoteException;

    public abstract void b(ga ga, String s, int i1, int j1, int k1, boolean flag)
        throws RemoteException;

    public abstract void b(ga ga, String s, int i1, IBinder ibinder, Bundle bundle)
        throws RemoteException;

    public abstract void b(ga ga, String s, int i1, boolean flag)
        throws RemoteException;

    public abstract void b(ga ga, String s, int i1, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void b(ga ga, String s, IBinder ibinder, Bundle bundle)
        throws RemoteException;

    public abstract void b(ga ga, String s, String s1)
        throws RemoteException;

    public abstract void b(ga ga, String s, String s1, int i1, int j1, int k1, boolean flag)
        throws RemoteException;

    public abstract void b(ga ga, String s, String s1, boolean flag)
        throws RemoteException;

    public abstract void b(ga ga, String s, boolean flag)
        throws RemoteException;

    public abstract void b(ga ga, boolean flag)
        throws RemoteException;

    public abstract void b(ga ga, String as[])
        throws RemoteException;

    public abstract void b(String s, String s1, int i1)
        throws RemoteException;

    public abstract void c(long l1, String s)
        throws RemoteException;

    public abstract void c(ga ga)
        throws RemoteException;

    public abstract void c(ga ga, int i1, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void c(ga ga, long l1)
        throws RemoteException;

    public abstract void c(ga ga, long l1, String s)
        throws RemoteException;

    public abstract void c(ga ga, String s)
        throws RemoteException;

    public abstract void c(ga ga, String s, int i1, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void c(ga ga, String s, String s1)
        throws RemoteException;

    public abstract void c(ga ga, String s, boolean flag)
        throws RemoteException;

    public abstract void c(ga ga, boolean flag)
        throws RemoteException;

    public abstract void c(ga ga, String as[])
        throws RemoteException;

    public abstract void c(String s, String s1, int i1)
        throws RemoteException;

    public abstract Bundle cY()
        throws RemoteException;

    public abstract void d(ga ga)
        throws RemoteException;

    public abstract void d(ga ga, int i1, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void d(ga ga, String s)
        throws RemoteException;

    public abstract void d(ga ga, String s, int i1, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void d(ga ga, String s, String s1)
        throws RemoteException;

    public abstract void d(ga ga, String s, boolean flag)
        throws RemoteException;

    public abstract ParcelFileDescriptor e(Uri uri)
        throws RemoteException;

    public abstract void e(ga ga)
        throws RemoteException;

    public abstract void e(ga ga, int i1, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void e(ga ga, String s)
        throws RemoteException;

    public abstract void e(ga ga, String s, String s1)
        throws RemoteException;

    public abstract void f(ga ga)
        throws RemoteException;

    public abstract void f(ga ga, String s)
        throws RemoteException;

    public abstract int fA()
        throws RemoteException;

    public abstract String fB()
        throws RemoteException;

    public abstract int fC()
        throws RemoteException;

    public abstract Intent fD()
        throws RemoteException;

    public abstract int fE()
        throws RemoteException;

    public abstract int fF()
        throws RemoteException;

    public abstract void fH()
        throws RemoteException;

    public abstract DataHolder fI()
        throws RemoteException;

    public abstract boolean fJ()
        throws RemoteException;

    public abstract DataHolder fK()
        throws RemoteException;

    public abstract void fL()
        throws RemoteException;

    public abstract Intent fM()
        throws RemoteException;

    public abstract String fn()
        throws RemoteException;

    public abstract String fo()
        throws RemoteException;

    public abstract Intent fr()
        throws RemoteException;

    public abstract Intent fs()
        throws RemoteException;

    public abstract Intent ft()
        throws RemoteException;

    public abstract Intent fu()
        throws RemoteException;

    public abstract Intent fy()
        throws RemoteException;

    public abstract Intent fz()
        throws RemoteException;

    public abstract void g(ga ga)
        throws RemoteException;

    public abstract void g(ga ga, String s)
        throws RemoteException;

    public abstract DataHolder h(ga ga, String s)
        throws RemoteException;

    public abstract void h(ga ga)
        throws RemoteException;

    public abstract void i(ga ga)
        throws RemoteException;

    public abstract void i(ga ga, String s)
        throws RemoteException;

    public abstract void j(ga ga, String s)
        throws RemoteException;

    public abstract void j(String s, String s1)
        throws RemoteException;

    public abstract void k(ga ga, String s)
        throws RemoteException;

    public abstract void k(String s, String s1)
        throws RemoteException;

    public abstract void l(ga ga, String s)
        throws RemoteException;

    public abstract void l(String s, int i1)
        throws RemoteException;

    public abstract void m(ga ga, String s)
        throws RemoteException;

    public abstract void m(String s, int i1)
        throws RemoteException;

    public abstract void n(long l1)
        throws RemoteException;

    public abstract void n(ga ga, String s)
        throws RemoteException;

    public abstract void n(String s, int i1)
        throws RemoteException;

    public abstract void o(long l1)
        throws RemoteException;

    public abstract void o(ga ga, String s)
        throws RemoteException;

    public abstract void o(String s, int i1)
        throws RemoteException;

    public abstract void p(long l1)
        throws RemoteException;

    public abstract void p(ga ga, String s)
        throws RemoteException;

    public abstract void q(long l1)
        throws RemoteException;

    public abstract void q(ga ga, String s)
        throws RemoteException;

    public abstract void y(boolean flag)
        throws RemoteException;
}
