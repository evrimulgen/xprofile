// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.common.api.*;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.leaderboard.*;

// Referenced classes of package com.google.android.gms.internal:
//            fx

public final class gj
    implements Leaderboards
{
    private static abstract class a extends com.google.android.gms.games.Games.a
    {

        public Result d(Status status)
        {
            return w(status);
        }

        public com.google.android.gms.games.leaderboard.Leaderboards.LeaderboardMetadataResult w(Status status)
        {
            return new com.google.android.gms.games.leaderboard.Leaderboards.LeaderboardMetadataResult(this, status) {

                public LeaderboardBuffer getLeaderboards()
                {
                    return new LeaderboardBuffer(DataHolder.empty(14));
                }

                public Status getStatus()
                {
                    return vb;
                }

                public void release()
                {
                }

                final a Ib;
                final Status vb;

            
            {
                Ib = a1;
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

    private static abstract class b extends com.google.android.gms.games.Games.a
    {

        public Result d(Status status)
        {
            return x(status);
        }

        public com.google.android.gms.games.leaderboard.Leaderboards.LoadPlayerScoreResult x(Status status)
        {
            return new com.google.android.gms.games.leaderboard.Leaderboards.LoadPlayerScoreResult(this, status) {

                public LeaderboardScore getScore()
                {
                    return null;
                }

                public Status getStatus()
                {
                    return vb;
                }

                final b Ic;
                final Status vb;

            
            {
                Ic = b1;
                vb = status;
                super();
            }
            }
;
        }

        private b()
        {
        }

    }

    private static abstract class c extends com.google.android.gms.games.Games.a
    {

        public Result d(Status status)
        {
            return y(status);
        }

        public com.google.android.gms.games.leaderboard.Leaderboards.LoadScoresResult y(Status status)
        {
            return new com.google.android.gms.games.leaderboard.Leaderboards.LoadScoresResult(this, status) {

                public Leaderboard getLeaderboard()
                {
                    return null;
                }

                public LeaderboardScoreBuffer getScores()
                {
                    return new LeaderboardScoreBuffer(DataHolder.empty(14));
                }

                public Status getStatus()
                {
                    return vb;
                }

                public void release()
                {
                }

                final c Id;
                final Status vb;

            
            {
                Id = c1;
                vb = status;
                super();
            }
            }
;
        }

        private c()
        {
        }

    }

    protected static abstract class d extends com.google.android.gms.games.Games.a
    {

        public Result d(Status status)
        {
            return z(status);
        }

        public com.google.android.gms.games.leaderboard.Leaderboards.SubmitScoreResult z(Status status)
        {
            return new com.google.android.gms.games.leaderboard.Leaderboards.SubmitScoreResult(this, status) {

                public ScoreSubmissionData getScoreData()
                {
                    return new ScoreSubmissionData(DataHolder.empty(14));
                }

                public Status getStatus()
                {
                    return vb;
                }

                public void release()
                {
                }

                final d Ie;
                final Status vb;

            
            {
                Ie = d1;
                vb = status;
                super();
            }
            }
;
        }

        protected d()
        {
        }
    }


    public gj()
    {
    }

    public Intent getAllLeaderboardsIntent(GoogleApiClient googleapiclient)
    {
        return Games.c(googleapiclient).fr();
    }

    public Intent getLeaderboardIntent(GoogleApiClient googleapiclient, String s)
    {
        return Games.c(googleapiclient).au(s);
    }

    public PendingResult loadCurrentPlayerLeaderboardScore(GoogleApiClient googleapiclient, String s, int i, int j)
    {
        return googleapiclient.a(new b(s, i, j) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((fx)a1);
            }

            protected void a(fx fx1)
            {
                fx1.a(this, null, HT, HU, HV);
            }

            final gj HS;
            final String HT;
            final int HU;
            final int HV;

            
            {
                HS = gj.this;
                HT = s;
                HU = i;
                HV = j;
                super();
            }
        }
);
    }

    public PendingResult loadLeaderboardMetadata(GoogleApiClient googleapiclient, String s, boolean flag)
    {
        return googleapiclient.a(new a(s, flag) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((fx)a1);
            }

            protected void a(fx fx1)
            {
                fx1.a(this, HT, HH);
            }

            final boolean HH;
            final gj HS;
            final String HT;

            
            {
                HS = gj.this;
                HT = s;
                HH = flag;
                super();
            }
        }
);
    }

    public PendingResult loadLeaderboardMetadata(GoogleApiClient googleapiclient, boolean flag)
    {
        return googleapiclient.a(new a(flag) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((fx)a1);
            }

            protected void a(fx fx1)
            {
                fx1.b(this, HH);
            }

            final boolean HH;
            final gj HS;

            
            {
                HS = gj.this;
                HH = flag;
                super();
            }
        }
);
    }

    public PendingResult loadMoreScores(GoogleApiClient googleapiclient, LeaderboardScoreBuffer leaderboardscorebuffer, int i, int j)
    {
        return googleapiclient.a(new c(leaderboardscorebuffer, i, j) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((fx)a1);
            }

            protected void a(fx fx1)
            {
                fx1.a(this, HX, HW, HY);
            }

            final gj HS;
            final int HW;
            final LeaderboardScoreBuffer HX;
            final int HY;

            
            {
                HS = gj.this;
                HX = leaderboardscorebuffer;
                HW = i;
                HY = j;
                super();
            }
        }
);
    }

    public PendingResult loadPlayerCenteredScores(GoogleApiClient googleapiclient, String s, int i, int j, int k)
    {
        return loadPlayerCenteredScores(googleapiclient, s, i, j, k, false);
    }

    public PendingResult loadPlayerCenteredScores(GoogleApiClient googleapiclient, String s, int i, int j, int k, boolean flag)
    {
        return googleapiclient.a(new c(s, i, j, k, flag) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((fx)a1);
            }

            protected void a(fx fx1)
            {
                fx1.b(this, HT, HU, HV, HW, HH);
            }

            final boolean HH;
            final gj HS;
            final String HT;
            final int HU;
            final int HV;
            final int HW;

            
            {
                HS = gj.this;
                HT = s;
                HU = i;
                HV = j;
                HW = k;
                HH = flag;
                super();
            }
        }
);
    }

    public PendingResult loadTopScores(GoogleApiClient googleapiclient, String s, int i, int j, int k)
    {
        return loadTopScores(googleapiclient, s, i, j, k, false);
    }

    public PendingResult loadTopScores(GoogleApiClient googleapiclient, String s, int i, int j, int k, boolean flag)
    {
        return googleapiclient.a(new c(s, i, j, k, flag) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((fx)a1);
            }

            protected void a(fx fx1)
            {
                fx1.a(this, HT, HU, HV, HW, HH);
            }

            final boolean HH;
            final gj HS;
            final String HT;
            final int HU;
            final int HV;
            final int HW;

            
            {
                HS = gj.this;
                HT = s;
                HU = i;
                HV = j;
                HW = k;
                HH = flag;
                super();
            }
        }
);
    }

    public void submitScore(GoogleApiClient googleapiclient, String s, long l)
    {
        submitScore(googleapiclient, s, l, null);
    }

    public void submitScore(GoogleApiClient googleapiclient, String s, long l, String s1)
    {
        Games.c(googleapiclient).a(null, s, l, s1);
    }

    public PendingResult submitScoreImmediate(GoogleApiClient googleapiclient, String s, long l)
    {
        return submitScoreImmediate(googleapiclient, s, l, null);
    }

    public PendingResult submitScoreImmediate(GoogleApiClient googleapiclient, String s, long l, String s1)
    {
        return googleapiclient.b(new d(s, l, s1) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((fx)a1);
            }

            protected void a(fx fx1)
            {
                fx1.a(this, HT, HZ, Ia);
            }

            final gj HS;
            final String HT;
            final long HZ;
            final String Ia;

            
            {
                HS = gj.this;
                HT = s;
                HZ = l;
                Ia = s1;
                super();
            }
        }
);
    }
}
