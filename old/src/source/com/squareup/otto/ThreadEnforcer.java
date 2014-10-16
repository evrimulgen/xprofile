// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.otto;

import android.os.Looper;

// Referenced classes of package com.squareup.otto:
//            Bus

public interface ThreadEnforcer
{

    public abstract void enforce(Bus bus);

    public static final ThreadEnforcer ANY = new ThreadEnforcer() {

        public void enforce(Bus bus)
        {
        }

    }
;
    public static final ThreadEnforcer MAIN = new ThreadEnforcer() {

        public void enforce(Bus bus)
        {
            if(Looper.myLooper() != Looper.getMainLooper())
                throw new IllegalStateException((new StringBuilder()).append("Event bus ").append(bus).append(" accessed from non-main thread ").append(Looper.myLooper()).toString());
            else
                return;
        }

    }
;

}
