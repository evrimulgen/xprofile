// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments.base;

import android.os.Bundle;

// Referenced classes of package com.roadtrippers.fragments.base:
//            BaseFragment

public abstract class RetainedFragment extends BaseFragment
{

    public RetainedFragment()
    {
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setRetainInstance(true);
    }
}
