// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.*;
import com.google.android.gms.common.api.Status;

// Referenced classes of package com.google.android.gms.internal:
//            eh, en, ig

public class ih extends eh
{
    final class a extends eh.b
        implements com.google.android.gms.panorama.Panorama.a
    {

        protected void a(Object obj)
        {
            c((com.google.android.gms.common.api.a.c)obj);
        }

        protected void c(com.google.android.gms.common.api.a.c c1)
        {
            c1.b(this);
        }

        protected void cP()
        {
        }

        public Status getStatus()
        {
            return QE;
        }

        public Intent getViewerIntent()
        {
            return QF;
        }

        public final Status QE;
        public final Intent QF;
        final ih QG;
        public final int type;

        public a(com.google.android.gms.common.api.a.c c1, Status status, int i, Intent intent)
        {
            QG = ih.this;
            super(ih.this, c1);
            QE = status;
            type = i;
            QF = intent;
        }
    }

    final class b extends if.a
    {

        public void a(int i, Bundle bundle, int j, Intent intent)
        {
            if(QJ != null)
                QG.getContext().revokeUriPermission(QJ, 1);
            PendingIntent pendingintent;
            Status status;
            if(bundle != null)
                pendingintent = (PendingIntent)bundle.getParcelable("pendingIntent");
            else
                pendingintent = null;
            status = new Status(i, null, pendingintent);
            if(QI != null)
                QG.a(QG. new c(QI, status, intent));
            else
            if(QH != null)
            {
                QG.a(QG. new a(QH, status, j, intent));
                return;
            }
        }

        final ih QG;
        private final com.google.android.gms.common.api.a.c QH;
        private final com.google.android.gms.common.api.a.c QI;
        private final Uri QJ;

        public b(com.google.android.gms.common.api.a.c c1, com.google.android.gms.common.api.a.c c2, Uri uri)
        {
            QG = ih.this;
            super();
            QH = c1;
            QI = c2;
            QJ = uri;
        }
    }

    final class c extends eh.b
        implements com.google.android.gms.panorama.Panorama.PanoramaResult
    {

        protected void a(Object obj)
        {
            c((com.google.android.gms.common.api.a.c)obj);
        }

        protected void c(com.google.android.gms.common.api.a.c c1)
        {
            c1.b(this);
        }

        protected void cP()
        {
        }

        public Status getStatus()
        {
            return QE;
        }

        public Intent getViewerIntent()
        {
            return QF;
        }

        private final Status QE;
        private final Intent QF;
        final ih QG;

        public c(com.google.android.gms.common.api.a.c c1, Status status, Intent intent)
        {
            QG = ih.this;
            super(ih.this, c1);
            QE = status;
            QF = intent;
        }
    }


    public ih(Context context, Looper looper, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
    {
        super(context, looper, connectioncallbacks, onconnectionfailedlistener, (String[])null);
    }

    public ih(Context context, com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener onconnectionfailedlistener)
    {
        this(context, context.getMainLooper(), ((com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks) (new eh.c(connectioncallbacks))), ((com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener) (new eh.g(onconnectionfailedlistener))));
    }

    public void a(com.google.android.gms.common.api.a.c c1, Uri uri, boolean flag)
    {
        Uri uri1;
        if(flag)
            uri1 = uri;
        else
            uri1 = null;
        a(new b(null, c1, uri1), uri, null, flag);
    }

    protected void a(en en1, eh.e e)
        throws RemoteException
    {
        Bundle bundle = new Bundle();
        en1.a(e, 0x41f6b8, getContext().getPackageName(), bundle);
    }

    public void a(b b1, Uri uri, Bundle bundle, boolean flag)
    {
        bm();
        if(flag)
            getContext().grantUriPermission("com.google.android.gms", uri, 1);
        try
        {
            ((ig)eb()).a(b1, uri, bundle, flag);
            return;
        }
        catch(RemoteException remoteexception)
        {
            b1.a(8, null, 0, null);
        }
    }

    protected String aF()
    {
        return "com.google.android.gms.panorama.service.START";
    }

    protected String aG()
    {
        return "com.google.android.gms.panorama.internal.IPanoramaService";
    }

    public ig ax(IBinder ibinder)
    {
        return ig.a.aw(ibinder);
    }

    public IInterface p(IBinder ibinder)
    {
        return ax(ibinder);
    }
}
