// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

// Referenced classes of package com.google.android.gms.drive.internal:
//            ag

public class OpenContentsRequest
    implements SafeParcelable
{

    OpenContentsRequest(int i, DriveId driveid, int j)
    {
        wj = i;
        Do = driveid;
        CR = j;
    }

    public OpenContentsRequest(DriveId driveid, int i)
    {
        this(1, driveid, i);
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        ag.a(this, parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new ag();
    final int CR;
    final DriveId Do;
    final int wj;

}
