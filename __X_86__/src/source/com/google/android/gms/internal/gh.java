// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.*;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.*;

// Referenced classes of package com.google.android.gms.internal:
//            fx

public final class gh
    implements GamesMetadata
{
    private static abstract class a extends com.google.android.gms.games.Games.a
    {

        public Result d(Status status)
        {
            return u(status);
        }

        public com.google.android.gms.games.GamesMetadata.LoadGamesResult u(Status status)
        {
            return new com.google.android.gms.games.GamesMetadata.LoadGamesResult(this, status) {

                public GameBuffer getGames()
                {
                    return new GameBuffer(DataHolder.empty(14));
                }

                public Status getStatus()
                {
                    return vb;
                }

                public void release()
                {
                }

                final a HO;
                final Status vb;

            
            {
                HO = a1;
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


    public gh()
    {
    }

    public Game getCurrentGame(GoogleApiClient googleapiclient)
    {
        return Games.c(googleapiclient).fq();
    }

    public PendingResult loadGame(GoogleApiClient googleapiclient)
    {
        return googleapiclient.a(new a() {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((fx)a1);
            }

            protected void a(fx fx1)
            {
                fx1.g(this);
            }

            final gh HN;

            
            {
                HN = gh.this;
                super();
            }
        }
);
    }
}
