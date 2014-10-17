// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.handmark.pulltorefresh.library;

import android.util.Log;
import android.view.View;

// Referenced classes of package com.handmark.pulltorefresh.library:
//            PullToRefreshBase

public final class OverscrollHelper
{

    public OverscrollHelper()
    {
    }

    static boolean isAndroidOverScrollEnabled(View view)
    {
        return view.getOverScrollMode() != 2;
    }

    public static void overScrollBy(PullToRefreshBase pulltorefreshbase, int i, int j, int k, int l, int i1, int j1, float f, 
            boolean flag)
    {
        static class _cls1
        {

            static final int $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[];

            static 
            {
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation = new int[PullToRefreshBase.Orientation.values().length];
                try
                {
                    $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[PullToRefreshBase.Orientation.HORIZONTAL.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[PullToRefreshBase.Orientation.VERTICAL.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1)
                {
                    return;
                }
            }
        }

        _cls1..SwitchMap.com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation[pulltorefreshbase.getPullToRefreshScrollDirection().ordinal()];
        JVM INSTR tableswitch 1 1: default 28
    //                   1 216;
           goto _L1 _L2
_L1:
        int k1;
        int l1;
        int i2;
        k1 = k;
        l1 = l;
        i2 = pulltorefreshbase.getScrollY();
_L13:
        if(!pulltorefreshbase.isPullToRefreshOverScrollEnabled() || pulltorefreshbase.isRefreshing()) goto _L4; else goto _L3
_L3:
        PullToRefreshBase.Mode mode = pulltorefreshbase.getMode();
        if(!mode.permitsPullToRefresh() || flag || k1 == 0) goto _L6; else goto _L5
_L5:
        int j2;
        j2 = k1 + l1;
        Log.d("OverscrollHelper", (new StringBuilder()).append("OverScroll. DeltaX: ").append(i).append(", ScrollX: ").append(j).append(", DeltaY: ").append(k).append(", ScrollY: ").append(l).append(", NewY: ").append(j2).append(", ScrollRange: ").append(i1).append(", CurrentScroll: ").append(i2).toString());
        if(j2 >= 0 - j1) goto _L8; else goto _L7
_L7:
        if(mode.showHeaderLoadingLayout())
        {
            if(i2 == 0)
                pulltorefreshbase.setState(PullToRefreshBase.State.OVERSCROLLING, new boolean[0]);
            pulltorefreshbase.setHeaderScroll((int)(f * (float)(i2 + j2)));
        }
_L4:
        return;
_L2:
        k1 = i;
        l1 = j;
        i2 = pulltorefreshbase.getScrollX();
        continue; /* Loop/switch isn't completed */
_L8:
        if(j2 <= i1 + j1)
            continue; /* Loop/switch isn't completed */
        if(!mode.showFooterLoadingLayout()) goto _L4; else goto _L9
_L9:
        if(i2 == 0)
            pulltorefreshbase.setState(PullToRefreshBase.State.OVERSCROLLING, new boolean[0]);
        pulltorefreshbase.setHeaderScroll((int)(f * (float)((i2 + j2) - i1)));
        return;
        if(Math.abs(j2) > j1 && Math.abs(j2 - i1) > j1) goto _L4; else goto _L10
_L10:
        pulltorefreshbase.setState(PullToRefreshBase.State.RESET, new boolean[0]);
        return;
_L6:
        if(!flag || PullToRefreshBase.State.OVERSCROLLING != pulltorefreshbase.getState()) goto _L4; else goto _L11
_L11:
        pulltorefreshbase.setState(PullToRefreshBase.State.RESET, new boolean[0]);
        return;
        if(true) goto _L13; else goto _L12
_L12:
    }

    public static void overScrollBy(PullToRefreshBase pulltorefreshbase, int i, int j, int k, int l, int i1, boolean flag)
    {
        overScrollBy(pulltorefreshbase, i, j, k, l, i1, 0, 1.0F, flag);
    }

    public static void overScrollBy(PullToRefreshBase pulltorefreshbase, int i, int j, int k, int l, boolean flag)
    {
        overScrollBy(pulltorefreshbase, i, j, k, l, 0, flag);
    }

    static final float DEFAULT_OVERSCROLL_SCALE = 1F;
    static final String LOG_TAG = "OverscrollHelper";
}
