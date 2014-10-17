// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

// Referenced classes of package com.google.android.gms.drive.internal:
//            ad

public class OnListEntriesResponse
    implements SafeParcelable
{

    OnListEntriesResponse(int i, DataHolder dataholder)
    {
        wj = i;
        Ed = dataholder;
    }

    public int describeContents()
    {
        return 0;
    }

    public DataHolder fc()
    {
        return Ed;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        ad.a(this, parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new ad();
    final DataHolder Ed;
    final int wj;

}
