// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.handmark.pulltorefresh.library;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import com.handmark.pulltorefresh.library.internal.LoadingLayout;
import java.util.HashSet;
import java.util.Iterator;

// Referenced classes of package com.handmark.pulltorefresh.library:
//            ILoadingLayout

public class LoadingLayoutProxy
    implements ILoadingLayout
{

    LoadingLayoutProxy()
    {
    }

    public void addLayout(LoadingLayout loadinglayout)
    {
        if(loadinglayout != null)
            mLoadingLayouts.add(loadinglayout);
    }

    public void setLastUpdatedLabel(CharSequence charsequence)
    {
        for(Iterator iterator = mLoadingLayouts.iterator(); iterator.hasNext(); ((LoadingLayout)iterator.next()).setLastUpdatedLabel(charsequence));
    }

    public void setLoadingDrawable(Drawable drawable)
    {
        for(Iterator iterator = mLoadingLayouts.iterator(); iterator.hasNext(); ((LoadingLayout)iterator.next()).setLoadingDrawable(drawable));
    }

    public void setPullLabel(CharSequence charsequence)
    {
        for(Iterator iterator = mLoadingLayouts.iterator(); iterator.hasNext(); ((LoadingLayout)iterator.next()).setPullLabel(charsequence));
    }

    public void setRefreshingLabel(CharSequence charsequence)
    {
        for(Iterator iterator = mLoadingLayouts.iterator(); iterator.hasNext(); ((LoadingLayout)iterator.next()).setRefreshingLabel(charsequence));
    }

    public void setReleaseLabel(CharSequence charsequence)
    {
        for(Iterator iterator = mLoadingLayouts.iterator(); iterator.hasNext(); ((LoadingLayout)iterator.next()).setReleaseLabel(charsequence));
    }

    public void setTextTypeface(Typeface typeface)
    {
        for(Iterator iterator = mLoadingLayouts.iterator(); iterator.hasNext(); ((LoadingLayout)iterator.next()).setTextTypeface(typeface));
    }

    private final HashSet mLoadingLayouts = new HashSet();
}
