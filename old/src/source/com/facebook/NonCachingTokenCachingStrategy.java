// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.facebook;

import android.os.Bundle;

// Referenced classes of package com.facebook:
//            TokenCachingStrategy

public class NonCachingTokenCachingStrategy extends TokenCachingStrategy
{

    public NonCachingTokenCachingStrategy()
    {
    }

    public void clear()
    {
    }

    public Bundle load()
    {
        return null;
    }

    public void save(Bundle bundle)
    {
    }
}
