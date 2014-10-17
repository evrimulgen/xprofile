// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.panorama;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.ih;

public class PanoramaClient
    implements GooglePlayServicesClient
{
    public static interface OnPanoramaInfoLoadedListener
    {

        public abstract void onPanoramaInfoLoaded(ConnectionResult connectionresult, Intent intent);
    }


    public PanoramaClient(Context context, com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener onconnectionfailedlistener)
    {
        QB = new ih(context, connectioncallbacks, onconnectionfailedlistener);
    }

    public void connect()
    {
        QB.connect();
    }

    public void disconnect()
    {
        QB.disconnect();
    }

    public boolean isConnected()
    {
        return QB.isConnected();
    }

    public boolean isConnecting()
    {
        return QB.isConnecting();
    }

    public boolean isConnectionCallbacksRegistered(com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks connectioncallbacks)
    {
        return QB.isConnectionCallbacksRegistered(connectioncallbacks);
    }

    public boolean isConnectionFailedListenerRegistered(com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener onconnectionfailedlistener)
    {
        return QB.isConnectionFailedListenerRegistered(onconnectionfailedlistener);
    }

    public void loadPanoramaInfo(OnPanoramaInfoLoadedListener onpanoramainfoloadedlistener, Uri uri)
    {
        QB.a(new com.google.android.gms.common.api.a.c(onpanoramainfoloadedlistener) {

            public void a(Panorama.PanoramaResult panoramaresult)
            {
                QC.onPanoramaInfoLoaded(panoramaresult.getStatus().dG(), panoramaresult.getViewerIntent());
            }

            public void b(Object obj)
            {
                a((Panorama.PanoramaResult)obj);
            }

            final OnPanoramaInfoLoadedListener QC;
            final PanoramaClient QD;

            
            {
                QD = PanoramaClient.this;
                QC = onpanoramainfoloadedlistener;
                super();
            }
        }
, uri, false);
    }

    public void loadPanoramaInfoAndGrantAccess(OnPanoramaInfoLoadedListener onpanoramainfoloadedlistener, Uri uri)
    {
        QB.a(new com.google.android.gms.common.api.a.c(onpanoramainfoloadedlistener) {

            public void a(Panorama.PanoramaResult panoramaresult)
            {
                QC.onPanoramaInfoLoaded(panoramaresult.getStatus().dG(), panoramaresult.getViewerIntent());
            }

            public void b(Object obj)
            {
                a((Panorama.PanoramaResult)obj);
            }

            final OnPanoramaInfoLoadedListener QC;
            final PanoramaClient QD;

            
            {
                QD = PanoramaClient.this;
                QC = onpanoramainfoloadedlistener;
                super();
            }
        }
, uri, true);
    }

    public void registerConnectionCallbacks(com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks connectioncallbacks)
    {
        QB.registerConnectionCallbacks(connectioncallbacks);
    }

    public void registerConnectionFailedListener(com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener onconnectionfailedlistener)
    {
        QB.registerConnectionFailedListener(onconnectionfailedlistener);
    }

    public void unregisterConnectionCallbacks(com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks connectioncallbacks)
    {
        QB.unregisterConnectionCallbacks(connectioncallbacks);
    }

    public void unregisterConnectionFailedListener(com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener onconnectionfailedlistener)
    {
        QB.unregisterConnectionFailedListener(onconnectionfailedlistener);
    }

    private final ih QB;
}
