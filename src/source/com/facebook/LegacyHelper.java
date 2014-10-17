// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.facebook;

import android.os.Bundle;

// Referenced classes of package com.facebook:
//            Session

public class LegacyHelper
{

    public LegacyHelper()
    {
    }

    public static void extendTokenCompleted(Session session, Bundle bundle)
    {
        session.extendTokenCompleted(bundle);
    }
}
