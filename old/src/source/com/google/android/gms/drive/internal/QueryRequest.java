// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.query.Query;

// Referenced classes of package com.google.android.gms.drive.internal:
//            ai

public class QueryRequest
    implements SafeParcelable
{

    QueryRequest(int i, Query query)
    {
        wj = i;
        Ef = query;
    }

    public QueryRequest(Query query)
    {
        this(1, query);
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        ai.a(this, parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new ai();
    final Query Ef;
    final int wj;

}
