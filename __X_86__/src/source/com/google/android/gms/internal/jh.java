// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

// Referenced classes of package com.google.android.gms.internal:
//            ji

public final class jh
    implements SafeParcelable
{

    jh()
    {
        wj = 1;
    }

    jh(int i, String s, String s1)
    {
        wj = i;
        label = s;
        value = s1;
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
        ji.a(this, parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new ji();
    String label;
    String value;
    private final int wj;

}
