// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.identity.intents;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.identity.intents.model.CountrySpecification;
import java.util.*;

// Referenced classes of package com.google.android.gms.identity.intents:
//            a

public final class UserAddressRequest
    implements SafeParcelable
{
    public final class Builder
    {

        public Builder addAllowedCountrySpecification(CountrySpecification countryspecification)
        {
            if(Kz.Ky == null)
                Kz.Ky = new ArrayList();
            Kz.Ky.add(countryspecification);
            return this;
        }

        public Builder addAllowedCountrySpecifications(Collection collection)
        {
            if(Kz.Ky == null)
                Kz.Ky = new ArrayList();
            Kz.Ky.addAll(collection);
            return this;
        }

        public UserAddressRequest build()
        {
            if(Kz.Ky != null)
                Kz.Ky = Collections.unmodifiableList(Kz.Ky);
            return Kz;
        }

        final UserAddressRequest Kz;

        private Builder()
        {
            Kz = UserAddressRequest.this;
            super();
        }

    }


    UserAddressRequest()
    {
        wj = 1;
    }

    UserAddressRequest(int i, List list)
    {
        wj = i;
        Ky = list;
    }

    public static Builder newBuilder()
    {
        UserAddressRequest useraddressrequest = new UserAddressRequest();
        useraddressrequest.getClass();
        return useraddressrequest. new Builder();
    }

    public int describeContents()
    {
        return 0;
    }

    public int getVersionCode()
    {
        return wj;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        a.a(this, parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new a();
    List Ky;
    private final int wj;

}
