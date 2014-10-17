// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.games.achievement;

import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.data.DataHolder;

// Referenced classes of package com.google.android.gms.games.achievement:
//            a, Achievement

public final class AchievementBuffer extends DataBuffer
{

    public AchievementBuffer(DataHolder dataholder)
    {
        super(dataholder);
    }

    public Achievement get(int i)
    {
        return new a(zU, i);
    }

    public volatile Object get(int i)
    {
        return get(i);
    }
}
