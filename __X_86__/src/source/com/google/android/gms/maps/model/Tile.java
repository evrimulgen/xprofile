// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.r;

// Referenced classes of package com.google.android.gms.maps.model:
//            TileCreator, i

public final class Tile
    implements SafeParcelable
{

    Tile(int j, int k, int l, byte abyte0[])
    {
        wj = j;
        width = k;
        height = l;
        data = abyte0;
    }

    public Tile(int j, int k, byte abyte0[])
    {
        this(1, j, k, abyte0);
    }

    public int describeContents()
    {
        return 0;
    }

    int getVersionCode()
    {
        return wj;
    }

    public void writeToParcel(Parcel parcel, int j)
    {
        if(r.hc())
        {
            i.a(this, parcel, j);
            return;
        } else
        {
            TileCreator.a(this, parcel, j);
            return;
        }
    }

    public static final TileCreator CREATOR = new TileCreator();
    public final byte data[];
    public final int height;
    public final int width;
    private final int wj;

}
