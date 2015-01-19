// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments.base;

import android.os.Bundle;
import com.roadtrippers.api.Roadtrippers;
import com.roadtrippers.util.Persistence;
import dagger.Lazy;
import rx.Observable;

// Referenced classes of package com.roadtrippers.fragments.base:
//            BaseProgressFragment

public abstract class RetainedProgressFragment extends BaseProgressFragment
{

    public RetainedProgressFragment()
    {
    }

    protected Observable getBucketsFromServer()
    {
        return ((Roadtrippers)roadtrippers.get()).getBuckets(((Persistence)persistence.get()).getUserId());
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    Lazy persistence;
    Lazy roadtrippers;
}
