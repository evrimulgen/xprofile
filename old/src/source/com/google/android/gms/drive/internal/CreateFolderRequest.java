// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.er;

// Referenced classes of package com.google.android.gms.drive.internal:
//            i

public class CreateFolderRequest
    implements SafeParcelable
{

    CreateFolderRequest(int j, DriveId driveid, MetadataBundle metadatabundle)
    {
        wj = j;
        Dt = (DriveId)er.f(driveid);
        Ds = (MetadataBundle)er.f(metadatabundle);
    }

    public CreateFolderRequest(DriveId driveid, MetadataBundle metadatabundle)
    {
        this(1, driveid, metadatabundle);
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int j)
    {
        i.a(this, parcel, j);
    }

    public static final android.os.Parcelable.Creator CREATOR = new i();
    final MetadataBundle Ds;
    final DriveId Dt;
    final int wj;

}
