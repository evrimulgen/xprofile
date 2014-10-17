// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.cast;

import android.content.Context;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.api.*;
import com.google.android.gms.internal.*;
import java.io.IOException;

// Referenced classes of package com.google.android.gms.cast:
//            ApplicationMetadata, CastDevice

public final class Cast
{
    public static interface ApplicationConnectionResult
        extends Result
    {

        public abstract ApplicationMetadata getApplicationMetadata();

        public abstract String getApplicationStatus();

        public abstract String getSessionId();

        public abstract boolean getWasLaunched();
    }

    public static interface CastApi
    {

        public abstract ApplicationMetadata getApplicationMetadata(GoogleApiClient googleapiclient)
            throws IllegalStateException;

        public abstract String getApplicationStatus(GoogleApiClient googleapiclient)
            throws IllegalStateException;

        public abstract double getVolume(GoogleApiClient googleapiclient)
            throws IllegalStateException;

        public abstract boolean isMute(GoogleApiClient googleapiclient)
            throws IllegalStateException;

        public abstract PendingResult joinApplication(GoogleApiClient googleapiclient);

        public abstract PendingResult joinApplication(GoogleApiClient googleapiclient, String s);

        public abstract PendingResult joinApplication(GoogleApiClient googleapiclient, String s, String s1);

        public abstract PendingResult launchApplication(GoogleApiClient googleapiclient, String s);

        public abstract PendingResult launchApplication(GoogleApiClient googleapiclient, String s, boolean flag);

        public abstract PendingResult leaveApplication(GoogleApiClient googleapiclient);

        public abstract void removeMessageReceivedCallbacks(GoogleApiClient googleapiclient, String s)
            throws IOException, IllegalArgumentException;

        public abstract void requestStatus(GoogleApiClient googleapiclient)
            throws IOException, IllegalStateException;

        public abstract PendingResult sendMessage(GoogleApiClient googleapiclient, String s, String s1);

        public abstract void setMessageReceivedCallbacks(GoogleApiClient googleapiclient, String s, MessageReceivedCallback messagereceivedcallback)
            throws IOException, IllegalStateException;

        public abstract void setMute(GoogleApiClient googleapiclient, boolean flag)
            throws IOException, IllegalStateException;

        public abstract void setVolume(GoogleApiClient googleapiclient, double d)
            throws IOException, IllegalArgumentException, IllegalStateException;

        public abstract PendingResult stopApplication(GoogleApiClient googleapiclient);

        public abstract PendingResult stopApplication(GoogleApiClient googleapiclient, String s);
    }

    public static final class CastApi.a
        implements CastApi
    {

        public ApplicationMetadata getApplicationMetadata(GoogleApiClient googleapiclient)
            throws IllegalStateException
        {
            return ((dq)googleapiclient.a(Cast.va)).getApplicationMetadata();
        }

        public String getApplicationStatus(GoogleApiClient googleapiclient)
            throws IllegalStateException
        {
            return ((dq)googleapiclient.a(Cast.va)).getApplicationStatus();
        }

        public double getVolume(GoogleApiClient googleapiclient)
            throws IllegalStateException
        {
            return ((dq)googleapiclient.a(Cast.va)).da();
        }

        public boolean isMute(GoogleApiClient googleapiclient)
            throws IllegalStateException
        {
            return ((dq)googleapiclient.a(Cast.va)).isMute();
        }

        public PendingResult joinApplication(GoogleApiClient googleapiclient)
        {
            return googleapiclient.b(new c(this) {

                protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                    throws RemoteException
                {
                    a((dq)a1);
                }

                protected void a(dq dq1)
                    throws RemoteException
                {
                    try
                    {
                        dq1.b(null, null, this);
                        return;
                    }
                    catch(IllegalStateException illegalstateexception)
                    {
                        x(2001);
                    }
                }

                final CastApi.a wr;

            
            {
                wr = a1;
                super();
            }
            }
);
        }

        public PendingResult joinApplication(GoogleApiClient googleapiclient, String s)
        {
            return googleapiclient.b(new c(this, s) {

                protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                    throws RemoteException
                {
                    a((dq)a1);
                }

                protected void a(dq dq1)
                    throws RemoteException
                {
                    try
                    {
                        dq1.b(ws, null, this);
                        return;
                    }
                    catch(IllegalStateException illegalstateexception)
                    {
                        x(2001);
                    }
                }

                final CastApi.a wr;
                final String ws;

            
            {
                wr = a1;
                ws = s;
                super();
            }
            }
);
        }

        public PendingResult joinApplication(GoogleApiClient googleapiclient, String s, String s1)
        {
            return googleapiclient.b(new c(this, s, s1) {

                protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                    throws RemoteException
                {
                    a((dq)a1);
                }

                protected void a(dq dq1)
                    throws RemoteException
                {
                    try
                    {
                        dq1.b(ws, wu, this);
                        return;
                    }
                    catch(IllegalStateException illegalstateexception)
                    {
                        x(2001);
                    }
                }

                final CastApi.a wr;
                final String ws;
                final String wu;

            
            {
                wr = a1;
                ws = s;
                wu = s1;
                super();
            }
            }
);
        }

        public PendingResult launchApplication(GoogleApiClient googleapiclient, String s)
        {
            return googleapiclient.b(new c(this, s) {

                protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                    throws RemoteException
                {
                    a((dq)a1);
                }

                protected void a(dq dq1)
                    throws RemoteException
                {
                    try
                    {
                        dq1.a(ws, false, this);
                        return;
                    }
                    catch(IllegalStateException illegalstateexception)
                    {
                        x(2001);
                    }
                }

                final CastApi.a wr;
                final String ws;

            
            {
                wr = a1;
                ws = s;
                super();
            }
            }
);
        }

        public PendingResult launchApplication(GoogleApiClient googleapiclient, String s, boolean flag)
        {
            return googleapiclient.b(new c(this, s, flag) {

                protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                    throws RemoteException
                {
                    a((dq)a1);
                }

                protected void a(dq dq1)
                    throws RemoteException
                {
                    try
                    {
                        dq1.a(ws, wt, this);
                        return;
                    }
                    catch(IllegalStateException illegalstateexception)
                    {
                        x(2001);
                    }
                }

                final CastApi.a wr;
                final String ws;
                final boolean wt;

            
            {
                wr = a1;
                ws = s;
                wt = flag;
                super();
            }
            }
);
        }

        public PendingResult leaveApplication(GoogleApiClient googleapiclient)
        {
            return googleapiclient.b(new b(this) {

                protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                    throws RemoteException
                {
                    a((dq)a1);
                }

                protected void a(dq dq1)
                    throws RemoteException
                {
                    try
                    {
                        dq1.e(this);
                        return;
                    }
                    catch(IllegalStateException illegalstateexception)
                    {
                        x(2001);
                    }
                }

                final CastApi.a wr;

            
            {
                wr = a1;
                super();
            }
            }
);
        }

        public void removeMessageReceivedCallbacks(GoogleApiClient googleapiclient, String s)
            throws IOException, IllegalArgumentException
        {
            try
            {
                ((dq)googleapiclient.a(Cast.va)).Q(s);
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw new IOException("service error");
            }
        }

        public void requestStatus(GoogleApiClient googleapiclient)
            throws IOException, IllegalStateException
        {
            try
            {
                ((dq)googleapiclient.a(Cast.va)).cZ();
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw new IOException("service error");
            }
        }

        public PendingResult sendMessage(GoogleApiClient googleapiclient, String s, String s1)
        {
            return googleapiclient.b(new b(this, s, s1) {

                protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                    throws RemoteException
                {
                    a((dq)a1);
                }

                protected void a(dq dq1)
                    throws RemoteException
                {
                    try
                    {
                        dq1.a(wp, wq, this);
                        return;
                    }
                    catch(IllegalArgumentException illegalargumentexception)
                    {
                        x(2001);
                        return;
                    }
                    catch(IllegalStateException illegalstateexception)
                    {
                        x(2001);
                    }
                }

                final String wp;
                final String wq;
                final CastApi.a wr;

            
            {
                wr = a1;
                wp = s;
                wq = s1;
                super();
            }
            }
);
        }

        public void setMessageReceivedCallbacks(GoogleApiClient googleapiclient, String s, MessageReceivedCallback messagereceivedcallback)
            throws IOException, IllegalStateException
        {
            try
            {
                ((dq)googleapiclient.a(Cast.va)).a(s, messagereceivedcallback);
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw new IOException("service error");
            }
        }

        public void setMute(GoogleApiClient googleapiclient, boolean flag)
            throws IOException, IllegalStateException
        {
            try
            {
                ((dq)googleapiclient.a(Cast.va)).t(flag);
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw new IOException("service error");
            }
        }

        public void setVolume(GoogleApiClient googleapiclient, double d)
            throws IOException, IllegalArgumentException, IllegalStateException
        {
            try
            {
                ((dq)googleapiclient.a(Cast.va)).a(d);
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw new IOException("service error");
            }
        }

        public PendingResult stopApplication(GoogleApiClient googleapiclient)
        {
            return googleapiclient.b(new b(this) {

                protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                    throws RemoteException
                {
                    a((dq)a1);
                }

                protected void a(dq dq1)
                    throws RemoteException
                {
                    try
                    {
                        dq1.a("", this);
                        return;
                    }
                    catch(IllegalStateException illegalstateexception)
                    {
                        x(2001);
                    }
                }

                final CastApi.a wr;

            
            {
                wr = a1;
                super();
            }
            }
);
        }

        public PendingResult stopApplication(GoogleApiClient googleapiclient, String s)
        {
            return googleapiclient.b(new b(this, s) {

                protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                    throws RemoteException
                {
                    a((dq)a1);
                }

                protected void a(dq dq1)
                    throws RemoteException
                {
                    if(TextUtils.isEmpty(wu))
                    {
                        c(2001, "IllegalArgument: sessionId cannot be null or empty");
                        return;
                    }
                    try
                    {
                        dq1.a(wu, this);
                        return;
                    }
                    catch(IllegalStateException illegalstateexception)
                    {
                        x(2001);
                    }
                }

                final CastApi.a wr;
                final String wu;

            
            {
                wr = a1;
                wu = s;
                super();
            }
            }
);
        }

        public CastApi.a()
        {
        }
    }

    public static final class CastOptions
        implements com.google.android.gms.common.api.GoogleApiClient.ApiOptions
    {

        static int a(CastOptions castoptions)
        {
            return castoptions.wx;
        }

        public static Builder builder(CastDevice castdevice, Listener listener)
        {
            return new Builder(castdevice, listener);
        }

        final CastDevice wv;
        final Listener ww;
        private final int wx;

        private CastOptions(Builder builder1)
        {
            wv = builder1.wy;
            ww = builder1.wz;
            wx = Builder.a(builder1);
        }

    }

    public static final class CastOptions.Builder
    {

        static int a(CastOptions.Builder builder)
        {
            return builder.wA;
        }

        public CastOptions build()
        {
            return new CastOptions(this);
        }

        public CastOptions.Builder setVerboseLoggingEnabled(boolean flag)
        {
            if(flag)
            {
                wA = 1 | wA;
                return this;
            } else
            {
                wA = -2 & wA;
                return this;
            }
        }

        private int wA;
        CastDevice wy;
        Listener wz;

        private CastOptions.Builder(CastDevice castdevice, Listener listener)
        {
            er.b(castdevice, "CastDevice parameter cannot be null");
            er.b(listener, "CastListener parameter cannot be null");
            wy = castdevice;
            wz = listener;
            wA = 0;
        }

    }

    public static abstract class Listener
    {

        public void onApplicationDisconnected(int i)
        {
        }

        public void onApplicationStatusChanged()
        {
        }

        public void onVolumeChanged()
        {
        }

        public Listener()
        {
        }
    }

    public static interface MessageReceivedCallback
    {

        public abstract void onMessageReceived(CastDevice castdevice, String s, String s1);
    }

    protected static abstract class a extends com.google.android.gms.common.api.a.a
        implements PendingResult
    {

        public void c(int i, String s)
        {
            a(d(new Status(i, s, null)));
        }

        public void x(int i)
        {
            a(d(new Status(i)));
        }

        public a()
        {
            super(Cast.va);
        }
    }

    private static abstract class b extends a
    {

        public Result d(Status status)
        {
            return f(status);
        }

        public Status f(Status status)
        {
            return status;
        }

        private b()
        {
        }

    }

    private static abstract class c extends a
    {

        public Result d(Status status)
        {
            return h(status);
        }

        public ApplicationConnectionResult h(Status status)
        {
            return new ApplicationConnectionResult(this, status) {

                public ApplicationMetadata getApplicationMetadata()
                {
                    return null;
                }

                public String getApplicationStatus()
                {
                    return null;
                }

                public String getSessionId()
                {
                    return null;
                }

                public Status getStatus()
                {
                    return vb;
                }

                public boolean getWasLaunched()
                {
                    return false;
                }

                final Status vb;
                final c wB;

            
            {
                wB = c1;
                vb = status;
                super();
            }
            }
;
        }

        private c()
        {
        }

    }


    private Cast()
    {
    }

    public static final Api API;
    public static final CastApi CastApi = new CastApi.a();
    public static final String EXTRA_APP_NO_LONGER_RUNNING = "com.google.android.gms.cast.EXTRA_APP_NO_LONGER_RUNNING";
    public static final int MAX_MESSAGE_LENGTH = 0x10000;
    public static final int MAX_NAMESPACE_LENGTH = 128;
    static final com.google.android.gms.common.api.Api.b va;

    static 
    {
        va = new com.google.android.gms.common.api.Api.b() {

            public com.google.android.gms.common.api.Api.a b(Context context, Looper looper, ee ee, com.google.android.gms.common.api.GoogleApiClient.ApiOptions apioptions, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
            {
                return c(context, looper, ee, apioptions, connectioncallbacks, onconnectionfailedlistener);
            }

            public dq c(Context context, Looper looper, ee ee, com.google.android.gms.common.api.GoogleApiClient.ApiOptions apioptions, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
            {
                er.b(apioptions, "Setting the API options is required.");
                er.b(apioptions instanceof CastOptions, "Must provide valid CastOptions!");
                CastOptions castoptions = (CastOptions)apioptions;
                return new dq(context, looper, castoptions.wv, CastOptions.a(castoptions), castoptions.ww, connectioncallbacks, onconnectionfailedlistener);
            }

            public int getPriority()
            {
                return 0x7fffffff;
            }

        }
;
        API = new Api(va, new Scope[0]);
    }
}
