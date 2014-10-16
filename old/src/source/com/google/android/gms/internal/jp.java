// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

// Referenced classes of package com.google.android.gms.internal:
//            jq

public final class jp
    implements SafeParcelable
{

    jp()
    {
        wj = 1;
    }

    jp(int i, String s, String s1)
    {
        wj = i;
        ZK = s;
        oi = s1;
    }

    public int describeContents()
    {
        return 0;
    }

    public int getVersionCode()
    {
        return wj;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        jq.a(this, parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new jq();
    String ZK;
    String oi;
    private final int wj;

}
