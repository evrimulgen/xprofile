// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.games.multiplayer;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

// Referenced classes of package com.google.android.gms.games.multiplayer:
//            b, Invitation

public final class InvitationBuffer extends d
{

    public InvitationBuffer(DataHolder dataholder)
    {
        super(dataholder);
    }

    protected Object c(int i, int j)
    {
        return getEntry(i, j);
    }

    protected Invitation getEntry(int i, int j)
    {
        return new b(zU, i, j);
    }

    protected String getPrimaryDataMarkerColumn()
    {
        return "external_invitation_id";
    }
}