// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.common.api;

import android.content.Context;
import android.os.*;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.internal.ee;
import com.google.android.gms.internal.er;
import java.util.*;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.gms.common.api:
//            Api, Scope, b

public interface GoogleApiClient
{
    public static interface ApiOptions
    {
    }

    public static final class Builder
    {

        public Builder addApi(Api api)
        {
            return addApi(api, null);
        }

        public Builder addApi(Api api, ApiOptions apioptions)
        {
            zr.put(api, apioptions);
            List list = api.dq();
            int i = list.size();
            for(int j = 0; j < i; j++)
                zn.add(((Scope)list.get(j)).dD());

            return this;
        }

        public Builder addConnectionCallbacks(ConnectionCallbacks connectioncallbacks)
        {
            zt.add(connectioncallbacks);
            return this;
        }

        public Builder addOnConnectionFailedListener(OnConnectionFailedListener onconnectionfailedlistener)
        {
            zu.add(onconnectionfailedlistener);
            return this;
        }

        public Builder addScope(Scope scope)
        {
            zn.add(scope.dD());
            return this;
        }

        public GoogleApiClient build()
        {
            return new b(mContext, zs, dx(), zr, zt, zu);
        }

        public ee dx()
        {
            return new ee(vi, zn, zo, zp, zq);
        }

        public Builder setAccountName(String s)
        {
            vi = s;
            return this;
        }

        public Builder setGravityForPopups(int i)
        {
            zo = i;
            return this;
        }

        public Builder setHandler(Handler handler)
        {
            er.b(handler, "Handler must not be null");
            zs = handler.getLooper();
            return this;
        }

        public Builder setViewForPopups(View view)
        {
            zp = view;
            return this;
        }

        public Builder useDefaultAccount()
        {
            return setAccountName("<<default account>>");
        }

        private final Context mContext;
        private String vi;
        private final Set zn;
        private int zo;
        private View zp;
        private String zq;
        private final Map zr;
        private Looper zs;
        private final Set zt;
        private final Set zu;

        public Builder(Context context)
        {
            zn = new HashSet();
            zr = new HashMap();
            zt = new HashSet();
            zu = new HashSet();
            mContext = context;
            zs = context.getMainLooper();
            zq = context.getPackageName();
        }

        public Builder(Context context, ConnectionCallbacks connectioncallbacks, OnConnectionFailedListener onconnectionfailedlistener)
        {
            this(context);
            er.b(connectioncallbacks, "Must provide a connected listener");
            zt.add(connectioncallbacks);
            er.b(onconnectionfailedlistener, "Must provide a connection failed listener");
            zu.add(onconnectionfailedlistener);
        }
    }

    public static interface ConnectionCallbacks
    {

        public abstract void onConnected(Bundle bundle);

        public abstract void onConnectionSuspended(int i);

        public static final int CAUSE_NETWORK_LOST = 2;
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;
    }

    public static interface OnConnectionFailedListener
        extends com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener
    {
    }


    public abstract Api.a a(Api.b b1);

    public abstract a.a a(a.a a1);

    public abstract a.a b(a.a a1);

    public abstract ConnectionResult blockingConnect(long l, TimeUnit timeunit);

    public abstract void connect();

    public abstract void disconnect();

    public abstract boolean isConnected();

    public abstract boolean isConnecting();

    public abstract boolean isConnectionCallbacksRegistered(ConnectionCallbacks connectioncallbacks);

    public abstract boolean isConnectionFailedListenerRegistered(OnConnectionFailedListener onconnectionfailedlistener);

    public abstract void reconnect();

    public abstract void registerConnectionCallbacks(ConnectionCallbacks connectioncallbacks);

    public abstract void registerConnectionFailedListener(OnConnectionFailedListener onconnectionfailedlistener);

    public abstract void unregisterConnectionCallbacks(ConnectionCallbacks connectioncallbacks);

    public abstract void unregisterConnectionFailedListener(OnConnectionFailedListener onconnectionfailedlistener);
}
