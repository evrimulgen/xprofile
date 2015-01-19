// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;

// Referenced classes of package com.google.android.gms.internal:
//            jk, fj

public final class jj
    implements SafeParcelable
{

    jj()
    {
        wj = 1;
        ZC = fj.eH();
    }

    jj(int i, String s, String s1, ArrayList arraylist)
    {
        wj = i;
        ZA = s;
        ZB = s1;
        ZC = arraylist;
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
        jk.a(this, parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new jk();
    String ZA;
    String ZB;
    ArrayList ZC;
    private final int wj;

}
