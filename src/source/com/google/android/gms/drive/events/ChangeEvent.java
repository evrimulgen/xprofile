// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.drive.events;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

// Referenced classes of package com.google.android.gms.drive.events:
//            ResourceEvent, a

public final class ChangeEvent
    implements SafeParcelable, ResourceEvent
{

    ChangeEvent(int i, DriveId driveid, int j)
    {
        wj = i;
        CS = driveid;
        Dl = j;
    }

    public int describeContents()
    {
        return 0;
    }

    public DriveId getDriveId()
    {
        return CS;
    }

    public int getType()
    {
        return 1;
    }

    public boolean hasContentChanged()
    {
        return (2 & Dl) != 0;
    }

    public boolean hasMetadataChanged()
    {
        return (1 & Dl) != 0;
    }

    public String toString()
    {
        Object aobj[] = new Object[2];
        aobj[0] = CS;
        aobj[1] = Integer.valueOf(Dl);
        return String.format("ChangeEvent [id=%s,changeFlags=%x]", aobj);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        a.a(this, parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new a();
    final DriveId CS;
    final int Dl;
    final int wj;

}
