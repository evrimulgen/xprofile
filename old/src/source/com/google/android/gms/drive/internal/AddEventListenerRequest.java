// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

// Referenced classes of package com.google.android.gms.drive.internal:
//            a

public class AddEventListenerRequest
    implements SafeParcelable
{

    AddEventListenerRequest(int i, DriveId driveid, int j)
    {
        wj = i;
        CS = driveid;
        Dm = j;
    }

    public AddEventListenerRequest(DriveId driveid, int i)
    {
        this(1, driveid, i);
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        a.a(this, parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new a();
    final DriveId CS;
    final int Dm;
    final int wj;

}
