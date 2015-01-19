// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.er;

// Referenced classes of package com.google.android.gms.drive.internal:
//            h

public class CreateFileRequest
    implements SafeParcelable
{

    CreateFileRequest(int i, DriveId driveid, MetadataBundle metadatabundle, Contents contents)
    {
        wj = i;
        Dt = (DriveId)er.f(driveid);
        Ds = (MetadataBundle)er.f(metadatabundle);
        Dq = (Contents)er.f(contents);
    }

    public CreateFileRequest(DriveId driveid, MetadataBundle metadatabundle, Contents contents)
    {
        this(1, driveid, metadatabundle, contents);
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        h.a(this, parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new h();
    final Contents Dq;
    final MetadataBundle Ds;
    final DriveId Dt;
    final int wj;

}
