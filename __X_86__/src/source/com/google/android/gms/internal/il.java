// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.*;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.PersonBuffer;
import java.util.Collection;

public final class il
    implements People
{
    private static abstract class a extends com.google.android.gms.plus.Plus.a
    {

        public com.google.android.gms.plus.People.LoadPeopleResult N(Status status)
        {
            return new com.google.android.gms.plus.People.LoadPeopleResult(this, status) {

                public String getNextPageToken()
                {
                    return null;
                }

                public PersonBuffer getPersonBuffer()
                {
                    return null;
                }

                public Status getStatus()
                {
                    return vb;
                }

                public void release()
                {
                }

                final a RK;
                final Status vb;

            
            {
                RK = a1;
                vb = status;
                super();
            }
            }
;
        }

        public Result d(Status status)
        {
            return N(status);
        }

        a(com.google.android.gms.common.api.Api.b b)
        {
            super(b);
        }
    }


    public il(com.google.android.gms.common.api.Api.b b)
    {
        Rw = b;
    }

    public Person getCurrentPerson(GoogleApiClient googleapiclient)
    {
        return Plus.a(googleapiclient, Rw).getCurrentPerson();
    }

    public PendingResult load(GoogleApiClient googleapiclient, Collection collection)
    {
        return googleapiclient.a(new a(Rw, collection) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((e)a1);
            }

            protected void a(e e1)
            {
                e1.a(this, RI);
            }

            final il RH;
            final Collection RI;

            
            {
                RH = il.this;
                RI = collection;
                super(b);
            }
        }
);
    }

    public transient PendingResult load(GoogleApiClient googleapiclient, String as[])
    {
        return googleapiclient.a(new a(Rw, as) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((e)a1);
            }

            protected void a(e e1)
            {
                e1.c(this, RJ);
            }

            final il RH;
            final String RJ[];

            
            {
                RH = il.this;
                RJ = as;
                super(b);
            }
        }
);
    }

    public PendingResult loadConnected(GoogleApiClient googleapiclient)
    {
        return googleapiclient.a(new a(Rw) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((e)a1);
            }

            protected void a(e e1)
            {
                e1.j(this);
            }

            final il RH;

            
            {
                RH = il.this;
                super(b);
            }
        }
);
    }

    public PendingResult loadVisible(GoogleApiClient googleapiclient, int i, String s)
    {
        return googleapiclient.a(new a(Rw, i, s) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((e)a1);
            }

            protected void a(e e1)
            {
                e1.a(this, RG, Rz);
            }

            final int RG;
            final il RH;
            final String Rz;

            
            {
                RH = il.this;
                RG = i;
                Rz = s;
                super(b);
            }
        }
);
    }

    public PendingResult loadVisible(GoogleApiClient googleapiclient, String s)
    {
        return googleapiclient.a(new a(Rw, s) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((e)a1);
            }

            protected void a(e e1)
            {
                e1.i(this, Rz);
            }

            final il RH;
            final String Rz;

            
            {
                RH = il.this;
                Rz = s;
                super(b);
            }
        }
);
    }

    private final com.google.android.gms.common.api.Api.b Rw;
}
