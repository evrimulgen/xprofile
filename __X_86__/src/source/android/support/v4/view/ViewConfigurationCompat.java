// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.view.ViewConfiguration;

// Referenced classes of package android.support.v4.view:
//            ViewConfigurationCompatFroyo

public class ViewConfigurationCompat
{
    static class BaseViewConfigurationVersionImpl
        implements ViewConfigurationVersionImpl
    {

        public int getScaledPagingTouchSlop(ViewConfiguration viewconfiguration)
        {
            return viewconfiguration.getScaledTouchSlop();
        }

        BaseViewConfigurationVersionImpl()
        {
        }
    }

    static class FroyoViewConfigurationVersionImpl
        implements ViewConfigurationVersionImpl
    {

        public int getScaledPagingTouchSlop(ViewConfiguration viewconfiguration)
        {
            return ViewConfigurationCompatFroyo.getScaledPagingTouchSlop(viewconfiguration);
        }

        FroyoViewConfigurationVersionImpl()
        {
        }
    }

    static interface ViewConfigurationVersionImpl
    {

        public abstract int getScaledPagingTouchSlop(ViewConfiguration viewconfiguration);
    }


    public ViewConfigurationCompat()
    {
    }

    public static int getScaledPagingTouchSlop(ViewConfiguration viewconfiguration)
    {
        return IMPL.getScaledPagingTouchSlop(viewconfiguration);
    }

    static final ViewConfigurationVersionImpl IMPL;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 11)
            IMPL = new FroyoViewConfigurationVersionImpl();
        else
            IMPL = new BaseViewConfigurationVersionImpl();
    }
}
