// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.panorama;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.*;
import com.google.android.gms.internal.ee;
import com.google.android.gms.internal.ih;

public final class Panorama
{
    public static interface PanoramaResult
        extends Result
    {

        public abstract Intent getViewerIntent();
    }

    public static interface a
        extends PanoramaResult
    {
    }

    private static abstract class b extends com.google.android.gms.common.api.a.a
    {

        public PanoramaResult J(Status status)
        {
            return new PanoramaResult(this, status) {

                public Status getStatus()
                {
                    return vb;
                }

                public Intent getViewerIntent()
                {
                    return null;
                }

                final b QA;
                final Status vb;

            
            {
                QA = b1;
                vb = status;
                super();
            }
            }
;
        }

        public Result d(Status status)
        {
            return J(status);
        }

        public b()
        {
            super(Panorama.va);
        }
    }


    private Panorama()
    {
    }

    public static PendingResult loadPanoramaInfo(GoogleApiClient googleapiclient, Uri uri)
    {
        return googleapiclient.a(new b(uri) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((ih)a1);
            }

            protected void a(ih ih1)
            {
                ih1.a(this, Qz, false);
            }

            final Uri Qz;

            
            {
                Qz = uri;
                super();
            }
        }
);
    }

    public static PendingResult loadPanoramaInfoAndGrantAccess(GoogleApiClient googleapiclient, Uri uri)
    {
        return googleapiclient.a(new b(uri) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((ih)a1);
            }

            protected void a(ih ih1)
            {
                ih1.a(this, Qz, true);
            }

            final Uri Qz;

            
            {
                Qz = uri;
                super();
            }
        }
);
    }

    public static final Api API;
    static final com.google.android.gms.common.api.Api.b va;

    static 
    {
        va = new com.google.android.gms.common.api.Api.b() {

            public com.google.android.gms.common.api.Api.a b(Context context, Looper looper, ee ee, com.google.android.gms.common.api.GoogleApiClient.ApiOptions apioptions, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
            {
                return g(context, looper, ee, apioptions, connectioncallbacks, onconnectionfailedlistener);
            }

            public ih g(Context context, Looper looper, ee ee, com.google.android.gms.common.api.GoogleApiClient.ApiOptions apioptions, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
            {
                return new ih(context, looper, connectioncallbacks, onconnectionfailedlistener);
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
