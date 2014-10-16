// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

// Referenced classes of package com.google.android.gms.internal:
//            jw, jr, jt

public final class jv
    implements SafeParcelable
{

    jv()
    {
        wj = 1;
    }

    jv(int i, String s, String s1, jr jr, jt jt, jt jt1)
    {
        wj = i;
        ZK = s;
        oi = s1;
        ZO = jr;
        ZP = jt;
        ZQ = jt1;
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
        jw.a(this, parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new jw();
    String ZK;
    jr ZO;
    jt ZP;
    jt ZQ;
    String oi;
    private final int wj;

}
