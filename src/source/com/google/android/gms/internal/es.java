// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import com.google.android.gms.dynamic.c;
import com.google.android.gms.dynamic.e;

// Referenced classes of package com.google.android.gms.internal:
//            eo

public final class es extends e
{

    private es()
    {
        super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
    }

    public static View d(Context context, int i, int j)
        throws com.google.android.gms.dynamic.e.a
    {
        return Cg.e(context, i, j);
    }

    private View e(Context context, int i, int j)
        throws com.google.android.gms.dynamic.e.a
    {
        View view;
        try
        {
            com.google.android.gms.dynamic.b b = c.h(context);
            view = (View)c.b(((eo)z(context)).a(b, i, j));
        }
        catch(Exception exception)
        {
            throw new com.google.android.gms.dynamic.e.a((new StringBuilder()).append("Could not get button with size ").append(i).append(" and color ").append(j).toString(), exception);
        }
        return view;
    }

    public eo B(IBinder ibinder)
    {
        return eo.a.A(ibinder);
    }

    public Object d(IBinder ibinder)
    {
        return B(ibinder);
    }

    private static final es Cg = new es();

}
