// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.tagmanager;

import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

// Referenced classes of package com.google.android.gms.tagmanager:
//            ContainerHolder, TagManager, Container, bh, 
//            n

class n
    implements ContainerHolder
{
    public static interface a extends Binder
        extends n
    {
        private static class a
            implements n
        {

            public IBinder asBinder()
            {
                return ky;
            }

            public void d(com.google.android.gms.dynamic.b b1)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IOnMyLocationChangeListener");
                if(b1 == null)
                    break MISSING_BLOCK_LABEL_59;
                IBinder ibinder = b1.asBinder();
_L1:
                parcel.writeStrongBinder(ibinder);
                ky.transact(1, parcel, parcel1, 0);
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

            private IBinder ky;

            a(IBinder ibinder)
            {
                ky = ibinder;
            }
        }


        public static n aj(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            android.os.IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.maps.internal.IOnMyLocationChangeListener");
            if(iinterface != null && (iinterface instanceof n))
                return (n)iinterface;
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
                parcel1.writeString("com.google.android.gms.maps.internal.IOnMyLocationChangeListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IOnMyLocationChangeListener");
                d(com.google.android.gms.dynamic.b.a.G(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
        }

        public a()
        {
            attachInterface(this, "com.google.android.gms.maps.internal.IOnMyLocationChangeListener");
        }
    }

    private class b extends Handler
    {

        public void bd(String s)
        {
            sendMessage(obtainMessage(1, s));
        }

        protected void be(String s)
        {
            TZ.onContainerAvailable(Ua, s);
        }

        public void handleMessage(Message message)
        {
            switch(message.what)
            {
            default:
                bh.t("Don't know how to handle this message.");
                return;

            case 1: // '\001'
                be((String)message.obj);
                break;
            }
        }

        private final ContainerHolder.ContainerAvailableListener TZ;
        final n Ua;

        public b(ContainerHolder.ContainerAvailableListener containeravailablelistener, Looper looper)
        {
            Ua = n.this;
            super(looper);
            TZ = containeravailablelistener;
        }
    }


    public n(Status status)
    {
        vl = status;
        zs = null;
    }

    public n(TagManager tagmanager, Looper looper, Container container, a a1)
    {
        TY = tagmanager;
        if(looper == null)
            looper = Looper.getMainLooper();
        zs = looper;
        TU = container;
        TX = a1;
        vl = Status.zQ;
        tagmanager.a(this);
    }

    private void iG()
    {
        if(TW != null)
            TW.bd(TV.iD());
    }

    public void a(Container container)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = zk;
        if(!flag) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if(container != null)
            break MISSING_BLOCK_LABEL_31;
        bh.t("Unexpected null container.");
          goto _L1
        Exception exception;
        exception;
        throw exception;
        TV = container;
        iG();
          goto _L1
    }

    public void ba(String s)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = zk;
        if(!flag) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        TU.ba(s);
        if(true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    void bc(String s)
    {
        if(zk)
        {
            bh.t("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
            return;
        } else
        {
            TX.bc(s);
            return;
        }
    }

    public Container getContainer()
    {
        Container container = null;
        this;
        JVM INSTR monitorenter ;
        if(!zk)
            break MISSING_BLOCK_LABEL_20;
        bh.t("ContainerHolder is released.");
_L1:
        this;
        JVM INSTR monitorexit ;
        return container;
        if(TV != null)
        {
            TU = TV;
            TV = null;
        }
        container = TU;
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    String getContainerId()
    {
        if(zk)
        {
            bh.t("getContainerId called on a released ContainerHolder.");
            return "";
        } else
        {
            return TU.getContainerId();
        }
    }

    public Status getStatus()
    {
        return vl;
    }

    String iF()
    {
        if(zk)
        {
            bh.t("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
            return "";
        } else
        {
            return TX.iF();
        }
    }

    public void refresh()
    {
        this;
        JVM INSTR monitorenter ;
        if(!zk)
            break MISSING_BLOCK_LABEL_17;
        bh.t("Refreshing a released ContainerHolder.");
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        TX.iH();
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public void release()
    {
        this;
        JVM INSTR monitorenter ;
        if(!zk)
            break MISSING_BLOCK_LABEL_17;
        bh.t("Releasing a released ContainerHolder.");
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        zk = true;
        TY.b(this);
        TU.release();
        TU = null;
        TV = null;
        TX = null;
        TW = null;
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public void setContainerAvailableListener(ContainerHolder.ContainerAvailableListener containeravailablelistener)
    {
        this;
        JVM INSTR monitorenter ;
        if(!zk) goto _L2; else goto _L1
_L1:
        bh.t("ContainerHolder is released.");
_L3:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if(containeravailablelistener != null)
            break MISSING_BLOCK_LABEL_34;
        TW = null;
          goto _L3
        Exception exception;
        exception;
        throw exception;
        TW = new b(containeravailablelistener, zs);
        if(TV != null)
            iG();
          goto _L3
    }

    private Container TU;
    private Container TV;
    private b TW;
    private a TX;
    private TagManager TY;
    private Status vl;
    private boolean zk;
    private final Looper zs;
}
