// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.*;
import com.google.android.gms.drive.*;

// Referenced classes of package com.google.android.gms.drive.internal:
//            n, m, GetMetadataRequest, u, 
//            c, OnListParentsResponse, j, OnMetadataResponse, 
//            ListParentsRequest, UpdateMetadataRequest

public class r
    implements DriveResource
{
    private abstract class a extends m
    {

        public Result d(Status status)
        {
            return r(status);
        }

        public com.google.android.gms.drive.DriveResource.MetadataResult r(Status status)
        {
            return new e(status, null);
        }

        final r DP;

        private a()
        {
            DP = r.this;
            super();
        }

    }

    private static class b extends com.google.android.gms.drive.internal.c
    {

        public void a(OnListParentsResponse onlistparentsresponse)
            throws RemoteException
        {
            MetadataBuffer metadatabuffer = new MetadataBuffer(onlistparentsresponse.fd(), null);
            vj.b(new l.e(Status.zQ, metadatabuffer));
        }

        public void l(Status status)
            throws RemoteException
        {
            vj.b(new l.e(status, null));
        }

        private final com.google.android.gms.common.api.a.c vj;

        public b(com.google.android.gms.common.api.a.c c1)
        {
            vj = c1;
        }
    }

    private abstract class c extends m
    {

        public Result d(Status status)
        {
            return o(status);
        }

        public com.google.android.gms.drive.DriveApi.MetadataBufferResult o(Status status)
        {
            return new l.e(status, null);
        }

        final r DP;

        private c()
        {
            DP = r.this;
            super();
        }

    }

    private static class d extends com.google.android.gms.drive.internal.c
    {

        public void a(OnMetadataResponse onmetadataresponse)
            throws RemoteException
        {
            vj.b(new e(Status.zQ, new j(onmetadataresponse.fe())));
        }

        public void l(Status status)
            throws RemoteException
        {
            vj.b(new e(status, null));
        }

        private final com.google.android.gms.common.api.a.c vj;

        public d(com.google.android.gms.common.api.a.c c1)
        {
            vj = c1;
        }
    }

    private static class e
        implements com.google.android.gms.drive.DriveResource.MetadataResult
    {

        public Metadata getMetadata()
        {
            return DQ;
        }

        public Status getStatus()
        {
            return vl;
        }

        private final Metadata DQ;
        private final Status vl;

        public e(Status status, Metadata metadata)
        {
            vl = status;
            DQ = metadata;
        }
    }

    private abstract class f extends m
    {

        public Result d(Status status)
        {
            return r(status);
        }

        public com.google.android.gms.drive.DriveResource.MetadataResult r(Status status)
        {
            return new e(status, null);
        }

        final r DP;

        private f()
        {
            DP = r.this;
            super();
        }

    }


    protected r(DriveId driveid)
    {
        CS = driveid;
    }

    public PendingResult addChangeListener(GoogleApiClient googleapiclient, com.google.android.gms.drive.events.DriveEvent.Listener listener)
    {
        return ((n)googleapiclient.a(Drive.va)).a(googleapiclient, CS, 1, listener);
    }

    public DriveId getDriveId()
    {
        return CS;
    }

    public PendingResult getMetadata(GoogleApiClient googleapiclient)
    {
        return googleapiclient.a(new a() {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((n)a1);
            }

            protected void a(n n1)
                throws RemoteException
            {
                n1.eT().a(new GetMetadataRequest(DP.CS), new d(this));
            }

            final r DP;

            
            {
                DP = r.this;
                super();
            }
        }
);
    }

    public PendingResult listParents(GoogleApiClient googleapiclient)
    {
        return googleapiclient.a(new c() {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((n)a1);
            }

            protected void a(n n1)
                throws RemoteException
            {
                n1.eT().a(new ListParentsRequest(DP.CS), new b(this));
            }

            final r DP;

            
            {
                DP = r.this;
                super();
            }
        }
);
    }

    public PendingResult removeChangeListener(GoogleApiClient googleapiclient, com.google.android.gms.drive.events.DriveEvent.Listener listener)
    {
        return ((n)googleapiclient.a(Drive.va)).b(googleapiclient, CS, 1, listener);
    }

    public PendingResult updateMetadata(GoogleApiClient googleapiclient, MetadataChangeSet metadatachangeset)
    {
        if(metadatachangeset == null)
            throw new IllegalArgumentException("ChangeSet must be provided.");
        else
            return googleapiclient.b(new f(metadatachangeset) {

                protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                    throws RemoteException
                {
                    a((n)a1);
                }

                protected void a(n n1)
                    throws RemoteException
                {
                    n1.eT().a(new UpdateMetadataRequest(DP.CS, DK.eS()), new d(this));
                }

                final MetadataChangeSet DK;
                final r DP;

            
            {
                DP = r.this;
                DK = metadatachangeset;
                super();
            }
            }
);
    }

    protected final DriveId CS;
}
