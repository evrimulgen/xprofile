// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import java.util.HashMap;
import java.util.Set;

// Referenced classes of package com.google.android.gms.internal:
//            er, gt

public final class hb
{
    public static final class a
    {

        public a aZ(int i)
        {
            yJ = i;
            return this;
        }

        public hb fV()
        {
            return new hb(yJ, II);
        }

        public a p(String s, int i)
        {
            if(gt.isValid(i))
                II.put(s, Integer.valueOf(i));
            return this;
        }

        private HashMap II;
        private int yJ;

        public a()
        {
            II = new HashMap();
            yJ = 0;
        }
    }


    private hb(int i, HashMap hashmap)
    {
        yJ = i;
        II = hashmap;
    }


    public static hb H(DataHolder dataholder)
    {
        a a1 = new a();
        a1.aZ(dataholder.getStatusCode());
        int i = dataholder.getCount();
        for(int j = 0; j < i; j++)
        {
            int k = dataholder.I(j);
            a1.p(dataholder.getString("requestId", j, k), dataholder.getInteger("outcome", j, k));
        }

        return a1.fV();
    }

    public Set getRequestIds()
    {
        return II.keySet();
    }

    public int getRequestOutcome(String s)
    {
        er.b(II.containsKey(s), (new StringBuilder()).append("Request ").append(s).append(" was not part of the update operation!").toString());
        return ((Integer)II.get(s)).intValue();
    }

    private static final String IH[] = {
        "requestId", "outcome"
    };
    private final HashMap II;
    private final int yJ;

}
