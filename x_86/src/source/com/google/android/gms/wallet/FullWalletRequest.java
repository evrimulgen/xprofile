// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.wallet;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

// Referenced classes of package com.google.android.gms.wallet:
//            g, Cart

public final class FullWalletRequest
    implements SafeParcelable
{
    public final class Builder
    {

        public FullWalletRequest build()
        {
            return Yv;
        }

        public Builder setCart(Cart cart)
        {
            Yv.Yu = cart;
            return this;
        }

        public Builder setGoogleTransactionId(String s)
        {
            Yv.Yk = s;
            return this;
        }

        public Builder setMerchantTransactionId(String s)
        {
            Yv.Yl = s;
            return this;
        }

        final FullWalletRequest Yv;

        private Builder()
        {
            Yv = FullWalletRequest.this;
            super();
        }

    }


    FullWalletRequest()
    {
        wj = 1;
    }

    FullWalletRequest(int i, String s, String s1, Cart cart)
    {
        wj = i;
        Yk = s;
        Yl = s1;
        Yu = cart;
    }

    public static Builder newBuilder()
    {
        FullWalletRequest fullwalletrequest = new FullWalletRequest();
        fullwalletrequest.getClass();
        return fullwalletrequest. new Builder();
    }

    public int describeContents()
    {
        return 0;
    }

    public Cart getCart()
    {
        return Yu;
    }

    public String getGoogleTransactionId()
    {
        return Yk;
    }

    public String getMerchantTransactionId()
    {
        return Yl;
    }

    public int getVersionCode()
    {
        return wj;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        g.a(this, parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new g();
    String Yk;
    String Yl;
    Cart Yu;
    private final int wj;

}
