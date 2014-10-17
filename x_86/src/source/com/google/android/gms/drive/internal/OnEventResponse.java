// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.ConflictEvent;

// Referenced classes of package com.google.android.gms.drive.internal:
//            ac

public class OnEventResponse
    implements SafeParcelable
{

    OnEventResponse(int i, int j, ChangeEvent changeevent, ConflictEvent conflictevent)
    {
        wj = i;
        Dm = j;
        Eb = changeevent;
        Ec = conflictevent;
    }

    public int describeContents()
    {
        return 0;
    }

    public ChangeEvent fa()
    {
        return Eb;
    }

    public ConflictEvent fb()
    {
        return Ec;
    }

    public int getEventType()
    {
        return Dm;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        ac.a(this, parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new ac();
    final int Dm;
    final ChangeEvent Eb;
    final ConflictEvent Ec;
    final int wj;

}
