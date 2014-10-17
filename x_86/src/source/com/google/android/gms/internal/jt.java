// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

// Referenced classes of package com.google.android.gms.internal:
//            ju

public final class jt
    implements SafeParcelable
{

    jt()
    {
        wj = 1;
    }

    jt(int i, String s, String s1)
    {
        wj = i;
        ZN = s;
        description = s1;
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
        ju.a(this, parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new ju();
    String ZN;
    String description;
    private final int wj;

}
