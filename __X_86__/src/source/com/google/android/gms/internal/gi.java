// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.common.api.*;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.*;

// Referenced classes of package com.google.android.gms.internal:
//            fx

public final class gi
    implements Invitations
{
    private static abstract class a extends com.google.android.gms.games.Games.a
    {

        public Result d(Status status)
        {
            return v(status);
        }

        public com.google.android.gms.games.multiplayer.Invitations.LoadInvitationsResult v(Status status)
        {
            return new com.google.android.gms.games.multiplayer.Invitations.LoadInvitationsResult(this, status) {

                public InvitationBuffer getInvitations()
                {
                    return new InvitationBuffer(DataHolder.empty(14));
                }

                public Status getStatus()
                {
                    return vb;
                }

                public void release()
                {
                }

                final a HR;
                final Status vb;

            
            {
                HR = a1;
                vb = status;
                super();
            }
            }
;
        }

        private a()
        {
        }

    }


    public gi()
    {
    }

    public Intent getInvitationInboxIntent(GoogleApiClient googleapiclient)
    {
        return Games.c(googleapiclient).fu();
    }

    public PendingResult loadInvitations(GoogleApiClient googleapiclient)
    {
        return loadInvitations(googleapiclient, 0);
    }

    public PendingResult loadInvitations(GoogleApiClient googleapiclient, int i)
    {
        return googleapiclient.a(new a(i) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((fx)a1);
            }

            protected void a(fx fx1)
            {
                fx1.c(this, HP);
            }

            final int HP;
            final gi HQ;

            
            {
                HQ = gi.this;
                HP = i;
                super();
            }
        }
);
    }

    public void registerInvitationListener(GoogleApiClient googleapiclient, OnInvitationReceivedListener oninvitationreceivedlistener)
    {
        Games.c(googleapiclient).a(oninvitationreceivedlistener);
    }

    public void unregisterInvitationListener(GoogleApiClient googleapiclient)
    {
        Games.c(googleapiclient).fv();
    }
}
