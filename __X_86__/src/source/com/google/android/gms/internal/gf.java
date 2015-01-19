// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.common.api.*;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.achievement.Achievements;

// Referenced classes of package com.google.android.gms.internal:
//            fx

public final class gf
    implements Achievements
{
    private static abstract class a extends com.google.android.gms.games.Games.a
    {

        public Result d(Status status)
        {
            return s(status);
        }

        public com.google.android.gms.games.achievement.Achievements.LoadAchievementsResult s(Status status)
        {
            return new com.google.android.gms.games.achievement.Achievements.LoadAchievementsResult(this, status) {

                public AchievementBuffer getAchievements()
                {
                    return new AchievementBuffer(DataHolder.empty(14));
                }

                public Status getStatus()
                {
                    return vb;
                }

                public void release()
                {
                }

                final a HL;
                final Status vb;

            
            {
                HL = a1;
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

        static String a(b b1)
        {
            return b1.uS;
        }

        public Result d(Status status)
        {
            return t(status);
        }

        public com.google.android.gms.games.achievement.Achievements.UpdateAchievementResult t(Status status)
        {
            return new com.google.android.gms.games.achievement.Achievements.UpdateAchievementResult(this, status) {

                public String getAchievementId()
                {
                    return b.a(HM);
                }

                public Status getStatus()
                {
                    return vb;
                }

                final b HM;
                final Status vb;

            
            {
                HM = b1;
                vb = status;
                super();
            }
            }
;
        }

        private final String uS;

        public b(String s)
        {
            uS = s;
        }
    }


    public gf()
    {
    }

    public Intent getAchievementsIntent(GoogleApiClient googleapiclient)
    {
        return Games.c(googleapiclient).fs();
    }

    public void increment(GoogleApiClient googleapiclient, String s, int i)
    {
        googleapiclient.b(new b(s, s, i) {

            public volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((fx)a1);
            }

            public void a(fx fx1)
            {
                fx1.a(null, HJ, HK);
            }

            final gf HI;
            final String HJ;
            final int HK;

            
            {
                HI = gf.this;
                HJ = s1;
                HK = i;
                super(s);
            }
        }
);
    }

    public PendingResult incrementImmediate(GoogleApiClient googleapiclient, String s, int i)
    {
        return googleapiclient.b(new b(s, s, i) {

            public volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((fx)a1);
            }

            public void a(fx fx1)
            {
                fx1.a(this, HJ, HK);
            }

            final gf HI;
            final String HJ;
            final int HK;

            
            {
                HI = gf.this;
                HJ = s1;
                HK = i;
                super(s);
            }
        }
);
    }

    public PendingResult load(GoogleApiClient googleapiclient, boolean flag)
    {
        return googleapiclient.a(new a(flag) {

            public volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((fx)a1);
            }

            public void a(fx fx1)
            {
                fx1.c(this, HH);
            }

            final boolean HH;
            final gf HI;

            
            {
                HI = gf.this;
                HH = flag;
                super();
            }
        }
);
    }

    public void reveal(GoogleApiClient googleapiclient, String s)
    {
        googleapiclient.b(new b(s, s) {

            public volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((fx)a1);
            }

            public void a(fx fx1)
            {
                fx1.b(null, HJ);
            }

            final gf HI;
            final String HJ;

            
            {
                HI = gf.this;
                HJ = s1;
                super(s);
            }
        }
);
    }

    public PendingResult revealImmediate(GoogleApiClient googleapiclient, String s)
    {
        return googleapiclient.b(new b(s, s) {

            public volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((fx)a1);
            }

            public void a(fx fx1)
            {
                fx1.b(this, HJ);
            }

            final gf HI;
            final String HJ;

            
            {
                HI = gf.this;
                HJ = s1;
                super(s);
            }
        }
);
    }

    public void setSteps(GoogleApiClient googleapiclient, String s, int i)
    {
        googleapiclient.b(new b(s, s, i) {

            public volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((fx)a1);
            }

            public void a(fx fx1)
            {
                fx1.b(null, HJ, HK);
            }

            final gf HI;
            final String HJ;
            final int HK;

            
            {
                HI = gf.this;
                HJ = s1;
                HK = i;
                super(s);
            }
        }
);
    }

    public PendingResult setStepsImmediate(GoogleApiClient googleapiclient, String s, int i)
    {
        return googleapiclient.b(new b(s, s, i) {

            public volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((fx)a1);
            }

            public void a(fx fx1)
            {
                fx1.b(this, HJ, HK);
            }

            final gf HI;
            final String HJ;
            final int HK;

            
            {
                HI = gf.this;
                HJ = s1;
                HK = i;
                super(s);
            }
        }
);
    }

    public void unlock(GoogleApiClient googleapiclient, String s)
    {
        googleapiclient.b(new b(s, s) {

            public volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((fx)a1);
            }

            public void a(fx fx1)
            {
                fx1.c(null, HJ);
            }

            final gf HI;
            final String HJ;

            
            {
                HI = gf.this;
                HJ = s1;
                super(s);
            }
        }
);
    }

    public PendingResult unlockImmediate(GoogleApiClient googleapiclient, String s)
    {
        return googleapiclient.b(new b(s, s) {

            public volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((fx)a1);
            }

            public void a(fx fx1)
            {
                fx1.c(this, HJ);
            }

            final gf HI;
            final String HJ;

            
            {
                HI = gf.this;
                HJ = s1;
                super(s);
            }
        }
);
    }
}
