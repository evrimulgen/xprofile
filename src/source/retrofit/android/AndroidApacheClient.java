// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit.android;

import android.net.http.AndroidHttpClient;
import retrofit.client.ApacheClient;

public final class AndroidApacheClient extends ApacheClient
{

    public AndroidApacheClient()
    {
        super(AndroidHttpClient.newInstance("Retrofit"));
    }
}
