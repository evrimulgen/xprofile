// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.wallet;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

// Referenced classes of package com.google.android.gms.wallet:
//            i

public final class LineItem
    implements SafeParcelable
{
    public final class Builder
    {

        public LineItem build()
        {
            return YB;
        }

        public Builder setCurrencyCode(String s)
        {
            YB.Yg = s;
            return this;
        }

        public Builder setDescription(String s)
        {
            YB.description = s;
            return this;
        }

        public Builder setQuantity(String s)
        {
            YB.Yy = s;
            return this;
        }

        public Builder setRole(int j)
        {
            YB.YA = j;
            return this;
        }

        public Builder setTotalPrice(String s)
        {
            YB.Yf = s;
            return this;
        }

        public Builder setUnitPrice(String s)
        {
            YB.Yz = s;
            return this;
        }

        final LineItem YB;

        private Builder()
        {
            YB = LineItem.this;
            super();
        }

    }

    public static interface Role
    {

        public static final int REGULAR = 0;
        public static final int SHIPPING = 2;
        public static final int TAX = 1;
    }


    LineItem()
    {
        wj = 1;
        YA = 0;
    }

    LineItem(int j, String s, String s1, String s2, String s3, int k, String s4)
    {
        wj = j;
        description = s;
        Yy = s1;
        Yz = s2;
        Yf = s3;
        YA = k;
        Yg = s4;
    }

    public static Builder newBuilder()
    {
        LineItem lineitem = new LineItem();
        lineitem.getClass();
        return lineitem. new Builder();
    }

    public int describeContents()
    {
        return 0;
    }

    public String getCurrencyCode()
    {
        return Yg;
    }

    public String getDescription()
    {
        return description;
    }

    public String getQuantity()
    {
        return Yy;
    }

    public int getRole()
    {
        return YA;
    }

    public String getTotalPrice()
    {
        return Yf;
    }

    public String getUnitPrice()
    {
        return Yz;
    }

    public int getVersionCode()
    {
        return wj;
    }

    public void writeToParcel(Parcel parcel, int j)
    {
        i.a(this, parcel, j);
    }

    public static final android.os.Parcelable.Creator CREATOR = new i();
    int YA;
    String Yf;
    String Yg;
    String Yy;
    String Yz;
    String description;
    private final int wj;

}
