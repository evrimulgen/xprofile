// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.common.api.*;
import com.google.android.gms.plus.Moments;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.plus.model.moments.Moment;
import com.google.android.gms.plus.model.moments.MomentBuffer;

public final class ik
    implements Moments
{
    private static abstract class a extends com.google.android.gms.plus.Plus.a
    {

        public com.google.android.gms.plus.Moments.LoadMomentsResult M(Status status)
        {
            return new com.google.android.gms.plus.Moments.LoadMomentsResult(this, status) {

                public MomentBuffer getMomentBuffer()
                {
                    return null;
                }

                public String getNextPageToken()
                {
                    return null;
                }

                public Status getStatus()
                {
                    return vb;
                }

                public String getUpdated()
                {
                    return null;
                }

                public void release()
                {
                }

                final a RF;
                final Status vb;

            
            {
                RF = a1;
                vb = status;
                super();
            }
            }
;
        }

        public Result d(Status status)
        {
            return M(status);
        }

        a(com.google.android.gms.common.api.Api.b b1)
        {
            super(b1);
        }
    }

    private static abstract class b extends com.google.android.gms.plus.Plus.a
    {

        public Result d(Status status)
        {
            return f(status);
        }

        public Status f(Status status)
        {
            return status;
        }

        b(com.google.android.gms.common.api.Api.b b1)
        {
            super(b1);
        }
    }

    private static abstract class c extends com.google.android.gms.plus.Plus.a
    {

        public Result d(Status status)
        {
            return f(status);
        }

        public Status f(Status status)
        {
            return status;
        }

        c(com.google.android.gms.common.api.Api.b b1)
        {
            super(b1);
        }
    }


    public ik(com.google.android.gms.common.api.Api.b b1)
    {
        Rw = b1;
    }

    public PendingResult load(GoogleApiClient googleapiclient)
    {
        return googleapiclient.a(new a(Rw) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((e)a1);
            }

            protected void a(e e1)
            {
                e1.i(this);
            }

            final ik Ry;

            
            {
                Ry = ik.this;
                super(b1);
            }
        }
);
    }

    public PendingResult load(GoogleApiClient googleapiclient, int i, String s, Uri uri, String s1, String s2)
    {
        return googleapiclient.a(new a(Rw, i, s, uri, s1, s2) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((e)a1);
            }

            protected void a(e e1)
            {
                e1.a(this, HW, Rz, RA, RB, RC);
            }

            final int HW;
            final Uri RA;
            final String RB;
            final String RC;
            final ik Ry;
            final String Rz;

            
            {
                Ry = ik.this;
                HW = i;
                Rz = s;
                RA = uri;
                RB = s1;
                RC = s2;
                super(b1);
            }
        }
);
    }

    public PendingResult remove(GoogleApiClient googleapiclient, String s)
    {
        return googleapiclient.b(new b(Rw, s) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((e)a1);
            }

            protected void a(e e1)
            {
                e1.removeMoment(RE);
                a(((Result) (Status.zQ)));
            }

            final String RE;
            final ik Ry;

            
            {
                Ry = ik.this;
                RE = s;
                super(b1);
            }
        }
);
    }

    public PendingResult write(GoogleApiClient googleapiclient, Moment moment)
    {
        return googleapiclient.b(new c(Rw, moment) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((e)a1);
            }

            protected void a(e e1)
            {
                e1.a(this, RD);
            }

            final Moment RD;
            final ik Ry;

            
            {
                Ry = ik.this;
                RD = moment;
                super(b1);
            }
        }
);
    }

    private final com.google.android.gms.common.api.Api.b Rw;
}
