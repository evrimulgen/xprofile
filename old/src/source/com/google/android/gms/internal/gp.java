// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.api.*;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.turnbased.*;
import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            fx

public final class gp
    implements TurnBasedMultiplayer
{
    private static abstract class a extends com.google.android.gms.games.Games.a
    {

        static String a(a a1)
        {
            return a1.uS;
        }

        public com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.CancelMatchResult D(Status status)
        {
            return new com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.CancelMatchResult(this, status) {

                public String getMatchId()
                {
                    return a.a(Iy);
                }

                public Status getStatus()
                {
                    return vb;
                }

                final a Iy;
                final Status vb;

            
            {
                Iy = a1;
                vb = status;
                super();
            }
            }
;
        }

        public Result d(Status status)
        {
            return D(status);
        }

        private final String uS;

        public a(String s)
        {
            uS = s;
        }
    }

    private static abstract class b extends com.google.android.gms.games.Games.a
    {

        public com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.InitiateMatchResult E(Status status)
        {
            return new com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.InitiateMatchResult(this, status) {

                public TurnBasedMatch getMatch()
                {
                    return null;
                }

                public Status getStatus()
                {
                    return vb;
                }

                final b Iz;
                final Status vb;

            
            {
                Iz = b1;
                vb = status;
                super();
            }
            }
;
        }

        public Result d(Status status)
        {
            return E(status);
        }

        private b()
        {
        }

    }

    private static abstract class c extends com.google.android.gms.games.Games.a
    {

        public com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LeaveMatchResult F(Status status)
        {
            return new com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LeaveMatchResult(this, status) {

                public TurnBasedMatch getMatch()
                {
                    return null;
                }

                public Status getStatus()
                {
                    return vb;
                }

                final c IA;
                final Status vb;

            
            {
                IA = c1;
                vb = status;
                super();
            }
            }
;
        }

        public Result d(Status status)
        {
            return F(status);
        }

        private c()
        {
        }

    }

    private static abstract class d extends com.google.android.gms.games.Games.a
    {

        public com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LoadMatchResult G(Status status)
        {
            return new com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LoadMatchResult(this, status) {

                public TurnBasedMatch getMatch()
                {
                    return null;
                }

                public Status getStatus()
                {
                    return vb;
                }

                final d IB;
                final Status vb;

            
            {
                IB = d1;
                vb = status;
                super();
            }
            }
;
        }

        public Result d(Status status)
        {
            return G(status);
        }

        private d()
        {
        }

    }

    private static abstract class e extends com.google.android.gms.games.Games.a
    {

        public com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LoadMatchesResult H(Status status)
        {
            return new com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LoadMatchesResult(this, status) {

                public LoadMatchesResponse getMatches()
                {
                    return new LoadMatchesResponse(new Bundle());
                }

                public Status getStatus()
                {
                    return vb;
                }

                public void release()
                {
                }

                final e IC;
                final Status vb;

            
            {
                IC = e1;
                vb = status;
                super();
            }
            }
;
        }

        public Result d(Status status)
        {
            return H(status);
        }

        private e()
        {
        }

    }

    private static abstract class f extends com.google.android.gms.games.Games.a
    {

        public com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.UpdateMatchResult I(Status status)
        {
            return new com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.UpdateMatchResult(this, status) {

                public TurnBasedMatch getMatch()
                {
                    return null;
                }

                public Status getStatus()
                {
                    return vb;
                }

                final f IE;
                final Status vb;

            
            {
                IE = f1;
                vb = status;
                super();
            }
            }
;
        }

        public Result d(Status status)
        {
            return I(status);
        }

        private f()
        {
        }

    }


    public gp()
    {
    }

    public PendingResult acceptInvitation(GoogleApiClient googleapiclient, String s)
    {
        return googleapiclient.b(new b(s) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((fx)a1);
            }

            protected void a(fx fx1)
            {
                fx1.e(this, Is);
            }

            final gp Iq;
            final String Is;

            
            {
                Iq = gp.this;
                Is = s;
                super();
            }
        }
);
    }

    public PendingResult cancelMatch(GoogleApiClient googleapiclient, String s)
    {
        return googleapiclient.b(new a(s, s) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((fx)a1);
            }

            protected void a(fx fx1)
            {
                fx1.g(this, Ir);
            }

            final gp Iq;
            final String Ir;

            
            {
                Iq = gp.this;
                Ir = s1;
                super(s);
            }
        }
);
    }

    public PendingResult createMatch(GoogleApiClient googleapiclient, TurnBasedMatchConfig turnbasedmatchconfig)
    {
        return googleapiclient.b(new b(turnbasedmatchconfig) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((fx)a1);
            }

            protected void a(fx fx1)
            {
                fx1.a(this, Ip);
            }

            final TurnBasedMatchConfig Ip;
            final gp Iq;

            
            {
                Iq = gp.this;
                Ip = turnbasedmatchconfig;
                super();
            }
        }
);
    }

    public void declineInvitation(GoogleApiClient googleapiclient, String s)
    {
        Games.c(googleapiclient).m(s, 1);
    }

    public void dismissInvitation(GoogleApiClient googleapiclient, String s)
    {
        Games.c(googleapiclient).l(s, 1);
    }

    public void dismissMatch(GoogleApiClient googleapiclient, String s)
    {
        Games.c(googleapiclient).av(s);
    }

    public PendingResult finishMatch(GoogleApiClient googleapiclient, String s)
    {
        return finishMatch(googleapiclient, s, null, (ParticipantResult[])null);
    }

    public PendingResult finishMatch(GoogleApiClient googleapiclient, String s, byte abyte0[], List list)
    {
        ParticipantResult aparticipantresult[];
        if(list == null)
            aparticipantresult = null;
        else
            aparticipantresult = (ParticipantResult[])list.toArray(new ParticipantResult[list.size()]);
        return finishMatch(googleapiclient, s, abyte0, aparticipantresult);
    }

    public transient PendingResult finishMatch(GoogleApiClient googleapiclient, String s, byte abyte0[], ParticipantResult aparticipantresult[])
    {
        return googleapiclient.b(new f(s, abyte0, aparticipantresult) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((fx)a1);
            }

            protected void a(fx fx1)
            {
                fx1.a(this, Ir, It, Iv);
            }

            final gp Iq;
            final String Ir;
            final byte It[];
            final ParticipantResult Iv[];

            
            {
                Iq = gp.this;
                Ir = s;
                It = abyte0;
                Iv = aparticipantresult;
                super();
            }
        }
);
    }

    public Intent getInboxIntent(GoogleApiClient googleapiclient)
    {
        return Games.c(googleapiclient).ft();
    }

    public int getMaxMatchDataSize(GoogleApiClient googleapiclient)
    {
        return Games.c(googleapiclient).fC();
    }

    public Intent getSelectOpponentsIntent(GoogleApiClient googleapiclient, int i, int j)
    {
        return Games.c(googleapiclient).a(i, j, true);
    }

    public Intent getSelectOpponentsIntent(GoogleApiClient googleapiclient, int i, int j, boolean flag)
    {
        return Games.c(googleapiclient).a(i, j, flag);
    }

    public PendingResult leaveMatch(GoogleApiClient googleapiclient, String s)
    {
        return googleapiclient.b(new c(s) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((fx)a1);
            }

            protected void a(fx fx1)
            {
                fx1.f(this, Ir);
            }

            final gp Iq;
            final String Ir;

            
            {
                Iq = gp.this;
                Ir = s;
                super();
            }
        }
);
    }

    public PendingResult leaveMatchDuringTurn(GoogleApiClient googleapiclient, String s, String s1)
    {
        return googleapiclient.b(new c(s, s1) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((fx)a1);
            }

            protected void a(fx fx1)
            {
                fx1.a(this, Ir, Iu);
            }

            final gp Iq;
            final String Ir;
            final String Iu;

            
            {
                Iq = gp.this;
                Ir = s;
                Iu = s1;
                super();
            }
        }
);
    }

    public PendingResult loadMatch(GoogleApiClient googleapiclient, String s)
    {
        return googleapiclient.a(new d(s) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((fx)a1);
            }

            protected void a(fx fx1)
            {
                fx1.h(this, Ir);
            }

            final gp Iq;
            final String Ir;

            
            {
                Iq = gp.this;
                Ir = s;
                super();
            }
        }
);
    }

    public PendingResult loadMatchesByStatus(GoogleApiClient googleapiclient, int i, int ai[])
    {
        return googleapiclient.a(new e(i, ai) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((fx)a1);
            }

            protected void a(fx fx1)
            {
                fx1.a(this, Iw, Ix);
            }

            final gp Iq;
            final int Iw;
            final int Ix[];

            
            {
                Iq = gp.this;
                Iw = i;
                Ix = ai;
                super();
            }
        }
);
    }

    public PendingResult loadMatchesByStatus(GoogleApiClient googleapiclient, int ai[])
    {
        return loadMatchesByStatus(googleapiclient, 0, ai);
    }

    public void registerMatchUpdateListener(GoogleApiClient googleapiclient, OnTurnBasedMatchUpdateReceivedListener onturnbasedmatchupdatereceivedlistener)
    {
        Games.c(googleapiclient).a(onturnbasedmatchupdatereceivedlistener);
    }

    public PendingResult rematch(GoogleApiClient googleapiclient, String s)
    {
        return googleapiclient.b(new b(s) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((fx)a1);
            }

            protected void a(fx fx1)
            {
                fx1.d(this, Ir);
            }

            final gp Iq;
            final String Ir;

            
            {
                Iq = gp.this;
                Ir = s;
                super();
            }
        }
);
    }

    public PendingResult takeTurn(GoogleApiClient googleapiclient, String s, byte abyte0[], String s1)
    {
        return takeTurn(googleapiclient, s, abyte0, s1, (ParticipantResult[])null);
    }

    public PendingResult takeTurn(GoogleApiClient googleapiclient, String s, byte abyte0[], String s1, List list)
    {
        ParticipantResult aparticipantresult[];
        if(list == null)
            aparticipantresult = null;
        else
            aparticipantresult = (ParticipantResult[])list.toArray(new ParticipantResult[list.size()]);
        return takeTurn(googleapiclient, s, abyte0, s1, aparticipantresult);
    }

    public transient PendingResult takeTurn(GoogleApiClient googleapiclient, String s, byte abyte0[], String s1, ParticipantResult aparticipantresult[])
    {
        return googleapiclient.b(new f(s, abyte0, s1, aparticipantresult) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((fx)a1);
            }

            protected void a(fx fx1)
            {
                fx1.a(this, Ir, It, Iu, Iv);
            }

            final gp Iq;
            final String Ir;
            final byte It[];
            final String Iu;
            final ParticipantResult Iv[];

            
            {
                Iq = gp.this;
                Ir = s;
                It = abyte0;
                Iu = s1;
                Iv = aparticipantresult;
                super();
            }
        }
);
    }

    public void unregisterMatchUpdateListener(GoogleApiClient googleapiclient)
    {
        Games.c(googleapiclient).fw();
    }
}
