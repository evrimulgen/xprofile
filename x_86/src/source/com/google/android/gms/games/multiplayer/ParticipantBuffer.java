// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.games.multiplayer;

import com.google.android.gms.common.data.DataBuffer;

// Referenced classes of package com.google.android.gms.games.multiplayer:
//            d, Participant

public final class ParticipantBuffer extends DataBuffer
{

    public Participant get(int i)
    {
        return new d(zU, i);
    }

    public volatile Object get(int i)
    {
        return get(i);
    }
}
