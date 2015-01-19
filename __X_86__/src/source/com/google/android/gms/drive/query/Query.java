// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.drive.query;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.query.internal.LogicalFilter;
import com.google.android.gms.drive.query.internal.Operator;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.gms.drive.query:
//            a, Filter

public class Query
    implements SafeParcelable
{
    public static class Builder
    {

        public Builder addFilter(Filter filter)
        {
            EN.add(filter);
            return this;
        }

        public Query build()
        {
            return new Query(new LogicalFilter(Operator.Ff, EN), EM);
        }

        public Builder setPageToken(String s)
        {
            EM = s;
            return this;
        }

        private String EM;
        private final List EN = new ArrayList();

        public Builder()
        {
        }
    }


    Query(int i, LogicalFilter logicalfilter, String s)
    {
        wj = i;
        EL = logicalfilter;
        EM = s;
    }

    Query(LogicalFilter logicalfilter, String s)
    {
        this(1, logicalfilter, s);
    }

    public int describeContents()
    {
        return 0;
    }

    public Filter getFilter()
    {
        return EL;
    }

    public String getPageToken()
    {
        return EM;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        a.a(this, parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new a();
    LogicalFilter EL;
    String EM;
    final int wj;

}
