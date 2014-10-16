// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.data.DataHolder;

// Referenced classes of package com.google.android.gms.games.leaderboard:
//            c, e, LeaderboardScore

public final class LeaderboardScoreBuffer extends DataBuffer
{

    public LeaderboardScoreBuffer(DataHolder dataholder)
    {
        super(dataholder);
        IO = new c(dataholder.getMetadata());
    }

    public c fX()
    {
        return IO;
    }

    public LeaderboardScore get(int i)
    {
        return new e(zU, i);
    }

    public volatile Object get(int i)
    {
        return get(i);
    }

    private final c IO;
}
