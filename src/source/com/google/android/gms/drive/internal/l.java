// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.*;
import com.google.android.gms.drive.*;
import com.google.android.gms.drive.query.Query;

// Referenced classes of package com.google.android.gms.drive.internal:
//            n, q, o, c, 
//            j, OnMetadataResponse, m, OnContentsResponse, 
//            OnListEntriesResponse, QueryRequest, u, CreateContentsRequest, 
//            CloseContentsRequest, ak, GetMetadataRequest

public class com.google.android.gms.drive.internal.l
    implements DriveApi
{
    static class a
        implements com.google.android.gms.drive.DriveApi.ContentsResult
    {

        public Contents getContents()
        {
            return CW;
        }

        public Status getStatus()
        {
            return vl;
        }

        private final Contents CW;
        private final Status vl;

        public a(Status status, Contents contents)
        {
            vl = status;
            CW = contents;
        }
    }

    private static class b extends com.google.android.gms.drive.internal.c
    {

        public void a(OnMetadataResponse onmetadataresponse)
            throws RemoteException
        {
            vj.b(new c(Status.zQ, (new com.google.android.gms.drive.internal.j(onmetadataresponse.fe())).getDriveId()));
        }

        public void l(Status status)
            throws RemoteException
        {
            vj.b(new c(status, null));
        }

        private final com.google.android.gms.common.api.a.c vj;

        public b(com.google.android.gms.common.api.a.c c1)
        {
            vj = c1;
        }
    }

    static class c
        implements com.google.android.gms.drive.DriveApi.DriveIdResult
    {

        public DriveId getDriveId()
        {
            return CS;
        }

        public Status getStatus()
        {
            return vl;
        }

        private final DriveId CS;
        private final Status vl;

        public c(Status status, DriveId driveid)
        {
            vl = status;
            CS = driveid;
        }
    }

    abstract class d extends m
    {

        public Result d(Status status)
        {
            return m(status);
        }

        public com.google.android.gms.drive.DriveApi.DriveIdResult m(Status status)
        {
            return new c(status, null);
        }

        final com.google.android.gms.drive.internal.l Dv;

        d()
        {
            Dv = com.google.android.gms.drive.internal.l.this;
            super();
        }
    }

    static class e
        implements com.google.android.gms.drive.DriveApi.MetadataBufferResult
    {

        public MetadataBuffer getMetadataBuffer()
        {
            return Dy;
        }

        public Status getStatus()
        {
            return vl;
        }

        private final MetadataBuffer Dy;
        private final Status vl;

        public e(Status status, MetadataBuffer metadatabuffer)
        {
            vl = status;
            Dy = metadatabuffer;
        }
    }

    private static class f extends com.google.android.gms.drive.internal.c
    {

        public void a(OnContentsResponse oncontentsresponse)
            throws RemoteException
        {
            vj.b(new a(Status.zQ, oncontentsresponse.eX()));
        }

        public void l(Status status)
            throws RemoteException
        {
            vj.b(new a(status, null));
        }

        private final com.google.android.gms.common.api.a.c vj;

        public f(com.google.android.gms.common.api.a.c c1)
        {
            vj = c1;
        }
    }

    abstract class g extends m
    {

        public Result d(Status status)
        {
            return n(status);
        }

        public com.google.android.gms.drive.DriveApi.ContentsResult n(Status status)
        {
            return new a(status, null);
        }

        final com.google.android.gms.drive.internal.l Dv;

        g()
        {
            Dv = com.google.android.gms.drive.internal.l.this;
            super();
        }
    }

    static class h extends com.google.android.gms.drive.internal.c
    {

        public void a(OnListEntriesResponse onlistentriesresponse)
            throws RemoteException
        {
            MetadataBuffer metadatabuffer = new MetadataBuffer(onlistentriesresponse.fc(), null);
            vj.b(new e(Status.zQ, metadatabuffer));
        }

        public void l(Status status)
            throws RemoteException
        {
            vj.b(new e(status, null));
        }

        private final com.google.android.gms.common.api.a.c vj;

        public h(com.google.android.gms.common.api.a.c c1)
        {
            vj = c1;
        }
    }

    abstract class i extends m
    {

        public Result d(Status status)
        {
            return o(status);
        }

        public com.google.android.gms.drive.DriveApi.MetadataBufferResult o(Status status)
        {
            return new e(status, null);
        }

        final com.google.android.gms.drive.internal.l Dv;

        i()
        {
            Dv = com.google.android.gms.drive.internal.l.this;
            super();
        }
    }

    static abstract class j extends m
    {

        public Result d(Status status)
        {
            return f(status);
        }

        public Status f(Status status)
        {
            return status;
        }

        j()
        {
        }
    }

    static class k extends j
    {

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
            throws RemoteException
        {
            a((n)a1);
        }

        protected void a(n n1)
        {
        }

        k(Status status)
        {
            a(status);
        }
    }

    abstract class l extends m
    {

        public Result d(Status status)
        {
            return f(status);
        }

        public Status f(Status status)
        {
            return status;
        }

        final com.google.android.gms.drive.internal.l Dv;

        l()
        {
            Dv = com.google.android.gms.drive.internal.l.this;
            super();
        }
    }


    public com.google.android.gms.drive.internal.l()
    {
    }

    public PendingResult discardContents(GoogleApiClient googleapiclient, Contents contents)
    {
        return googleapiclient.b(new j(contents) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((n)a1);
            }

            protected void a(n n1)
                throws RemoteException
            {
                n1.eT().a(new CloseContentsRequest(Dw, false), new ak(this));
            }

            final com.google.android.gms.drive.internal.l Dv;
            final Contents Dw;

            
            {
                Dv = com.google.android.gms.drive.internal.l.this;
                Dw = contents;
                super();
            }
        }
);
    }

    public PendingResult fetchDriveId(GoogleApiClient googleapiclient, String s)
    {
        return googleapiclient.a(new d(s) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((n)a1);
            }

            protected void a(n n1)
                throws RemoteException
            {
                n1.eT().a(new GetMetadataRequest(DriveId.aq(Dx)), new b(this));
            }

            final com.google.android.gms.drive.internal.l Dv;
            final String Dx;

            
            {
                Dv = com.google.android.gms.drive.internal.l.this;
                Dx = s;
                super();
            }
        }
);
    }

    public DriveFolder getAppFolder(GoogleApiClient googleapiclient)
    {
        if(!googleapiclient.isConnected())
            throw new IllegalStateException("Client must be connected");
        DriveId driveid = ((n)googleapiclient.a(Drive.va)).eV();
        if(driveid != null)
            return new q(driveid);
        else
            return null;
    }

    public DriveFile getFile(GoogleApiClient googleapiclient, DriveId driveid)
    {
        if(driveid == null)
            throw new IllegalArgumentException("Id must be provided.");
        if(!googleapiclient.isConnected())
            throw new IllegalStateException("Client must be connected");
        else
            return new o(driveid);
    }

    public DriveFolder getFolder(GoogleApiClient googleapiclient, DriveId driveid)
    {
        if(driveid == null)
            throw new IllegalArgumentException("Id must be provided.");
        if(!googleapiclient.isConnected())
            throw new IllegalStateException("Client must be connected");
        else
            return new q(driveid);
    }

    public DriveFolder getRootFolder(GoogleApiClient googleapiclient)
    {
        if(!googleapiclient.isConnected())
            throw new IllegalStateException("Client must be connected");
        else
            return new q(((n)googleapiclient.a(Drive.va)).eU());
    }

    public PendingResult newContents(GoogleApiClient googleapiclient)
    {
        return googleapiclient.a(new g() {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((n)a1);
            }

            protected void a(n n1)
                throws RemoteException
            {
                n1.eT().a(new CreateContentsRequest(), new f(this));
            }

            final com.google.android.gms.drive.internal.l Dv;

            
            {
                Dv = com.google.android.gms.drive.internal.l.this;
                super();
            }
        }
);
    }

    public CreateFileActivityBuilder newCreateFileActivityBuilder()
    {
        return new CreateFileActivityBuilder();
    }

    public OpenFileActivityBuilder newOpenFileActivityBuilder()
    {
        return new OpenFileActivityBuilder();
    }

    public PendingResult query(GoogleApiClient googleapiclient, Query query1)
    {
        if(query1 == null)
            throw new IllegalArgumentException("Query must be provided.");
        else
            return googleapiclient.a(new i(query1) {

                protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                    throws RemoteException
                {
                    a((n)a1);
                }

                protected void a(n n1)
                    throws RemoteException
                {
                    n1.eT().a(new QueryRequest(Du), new h(this));
                }

                final Query Du;
                final com.google.android.gms.drive.internal.l Dv;

            
            {
                Dv = com.google.android.gms.drive.internal.l.this;
                Du = query1;
                super();
            }
            }
);
    }

    public PendingResult requestSync(GoogleApiClient googleapiclient)
    {
        return googleapiclient.b(new l() {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((n)a1);
            }

            protected void a(n n1)
                throws RemoteException
            {
                n1.eT().a(new ak(this));
            }

            final com.google.android.gms.drive.internal.l Dv;

            
            {
                Dv = com.google.android.gms.drive.internal.l.this;
                super();
            }
        }
);
    }
}
