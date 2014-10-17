// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.Contents;

// Referenced classes of package com.google.android.gms.drive.internal:
//            z

public class OnContentsResponse
    implements SafeParcelable
{

    OnContentsResponse(int i, Contents contents)
    {
        wj = i;
        CW = contents;
    }

    public int describeContents()
    {
        return 0;
    }

    public Contents eX()
    {
        return CW;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        z.a(this, parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new z();
    final Contents CW;
    final int wj;

}
