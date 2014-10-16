// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package crittercism.android;

import java.util.*;

// Referenced classes of package crittercism.android:
//            n

public final class p extends n
{

    public p(Map map)
    {
        super(map);
        TreeMap treemap = new TreeMap(new Comparator() {

            public final volatile int compare(Object obj, Object obj1)
            {
                String s = (String)obj;
                String s1 = (String)obj1;
                if(s == s1)
                    return 0;
                if(s == null)
                    return -1;
                if(s1 == null)
                    return 1;
                else
                    return String.CASE_INSENSITIVE_ORDER.compare(s, s1);
            }

            final p a;

            
            {
                a = p.this;
                super();
            }
        }
);
        treemap.putAll(map);
        super.a = treemap;
    }
}
