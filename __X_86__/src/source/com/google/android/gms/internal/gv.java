// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;


// Referenced classes of package com.google.android.gms.internal:
//            fz

public final class gv
{

    public static String aW(int i)
    {
        switch(i)
        {
        default:
            fz.h("MatchTurnStatus", (new StringBuilder()).append("Unknown match turn status: ").append(i).toString());
            return "TURN_STATUS_UNKNOWN";

        case 0: // '\0'
            return "TURN_STATUS_INVITED";

        case 1: // '\001'
            return "TURN_STATUS_MY_TURN";

        case 2: // '\002'
            return "TURN_STATUS_THEIR_TURN";

        case 3: // '\003'
            return "TURN_STATUS_COMPLETE";
        }
    }
}
