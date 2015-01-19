// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.facebook;

import android.content.Context;
import android.os.Bundle;
import com.facebook.internal.PlatformServiceClient;

final class GetTokenClient extends PlatformServiceClient
{

    GetTokenClient(Context context, String s)
    {
        super(context, 0x10000, 0x10001, 0x133060d, s);
    }

    protected void populateRequestBundle(Bundle bundle)
    {
    }
}
