// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.os.*;
import com.google.android.gms.cast.ApplicationMetadata;

public interface dt
    extends IInterface
{
    public static abstract class a extends Binder
        implements dt
    {

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
                parcel1.writeString("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                z(parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                ApplicationMetadata applicationmetadata;
                String s1;
                String s2;
                int l;
                boolean flag1;
                if(parcel.readInt() != 0)
                    applicationmetadata = (ApplicationMetadata)ApplicationMetadata.CREATOR.createFromParcel(parcel);
                else
                    applicationmetadata = null;
                s1 = parcel.readString();
                s2 = parcel.readString();
                l = parcel.readInt();
                flag1 = false;
                if(l != 0)
                    flag1 = true;
                a(applicationmetadata, s1, s2, flag1);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                A(parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                String s = parcel.readString();
                double d1 = parcel.readDouble();
                int k = parcel.readInt();
                boolean flag = false;
                if(k != 0)
                    flag = true;
                b(s, d1, flag);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                d(parcel.readString(), parcel.readString());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                b(parcel.readString(), parcel.createByteArray());
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                C(parcel.readInt());
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                B(parcel.readInt());
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                onApplicationDisconnected(parcel.readInt());
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                a(parcel.readString(), parcel.readLong(), parcel.readInt());
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                a(parcel.readString(), parcel.readLong());
                return true;
            }
        }

        public a()
        {
            attachInterface(this, "com.google.android.gms.cast.internal.ICastDeviceControllerListener");
        }
    }


    public abstract void A(int i)
        throws RemoteException;

    public abstract void B(int i)
        throws RemoteException;

    public abstract void C(int i)
        throws RemoteException;

    public abstract void a(ApplicationMetadata applicationmetadata, String s, String s1, boolean flag)
        throws RemoteException;

    public abstract void a(String s, long l)
        throws RemoteException;

    public abstract void a(String s, long l, int i)
        throws RemoteException;

    public abstract void b(String s, double d1, boolean flag)
        throws RemoteException;

    public abstract void b(String s, byte abyte0[])
        throws RemoteException;

    public abstract void d(String s, String s1)
        throws RemoteException;

    public abstract void onApplicationDisconnected(int i)
        throws RemoteException;

    public abstract void z(int i)
        throws RemoteException;
}
