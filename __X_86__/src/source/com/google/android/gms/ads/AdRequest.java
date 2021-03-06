// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.ads;

import android.content.Context;
import android.location.Location;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.internal.aj;
import java.util.Date;
import java.util.Set;

public final class AdRequest
{
    public static final class Builder
    {

        static com.google.android.gms.internal.aj.a a(Builder builder)
        {
            return builder.kB;
        }

        public Builder addKeyword(String s)
        {
            kB.g(s);
            return this;
        }

        public Builder addNetworkExtras(NetworkExtras networkextras)
        {
            kB.a(networkextras);
            return this;
        }

        public Builder addTestDevice(String s)
        {
            kB.h(s);
            return this;
        }

        public AdRequest build()
        {
            return new AdRequest(this);
        }

        public Builder setBirthday(Date date)
        {
            kB.a(date);
            return this;
        }

        public Builder setGender(int i)
        {
            kB.d(i);
            return this;
        }

        public Builder setLocation(Location location)
        {
            kB.a(location);
            return this;
        }

        public Builder tagForChildDirectedTreatment(boolean flag)
        {
            kB.e(flag);
            return this;
        }

        private final com.google.android.gms.internal.aj.a kB = new com.google.android.gms.internal.aj.a();

        public Builder()
        {
        }
    }


    private AdRequest(Builder builder)
    {
        kA = new aj(Builder.a(builder));
    }


    aj N()
    {
        return kA;
    }

    public Date getBirthday()
    {
        return kA.getBirthday();
    }

    public int getGender()
    {
        return kA.getGender();
    }

    public Set getKeywords()
    {
        return kA.getKeywords();
    }

    public Location getLocation()
    {
        return kA.getLocation();
    }

    public NetworkExtras getNetworkExtras(Class class1)
    {
        return kA.getNetworkExtras(class1);
    }

    public boolean isTestDevice(Context context)
    {
        return kA.isTestDevice(context);
    }

    public static final String DEVICE_ID_EMULATOR;
    public static final int ERROR_CODE_INTERNAL_ERROR = 0;
    public static final int ERROR_CODE_INVALID_REQUEST = 1;
    public static final int ERROR_CODE_NETWORK_ERROR = 2;
    public static final int ERROR_CODE_NO_FILL = 3;
    public static final int GENDER_FEMALE = 2;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_UNKNOWN;
    private final aj kA;

    static 
    {
        DEVICE_ID_EMULATOR = aj.DEVICE_ID_EMULATOR;
    }
}
