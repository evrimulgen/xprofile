// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import java.util.*;

// Referenced classes of package com.google.android.gms.internal:
//            er

public final class ep
{
    public static final class a
    {

        public a a(String s, Object obj)
        {
            Ce.add((new StringBuilder()).append((String)er.f(s)).append("=").append(String.valueOf(obj)).toString());
            return this;
        }

        public String toString()
        {
            StringBuilder stringbuilder = (new StringBuilder(100)).append(Cf.getClass().getSimpleName()).append('{');
            int i = Ce.size();
            for(int j = 0; j < i; j++)
            {
                stringbuilder.append((String)Ce.get(j));
                if(j < i - 1)
                    stringbuilder.append(", ");
            }

            return stringbuilder.append('}').toString();
        }

        private final List Ce;
        private final Object Cf;

        private a(Object obj)
        {
            Cf = er.f(obj);
            Ce = new ArrayList();
        }

    }


    public static a e(Object obj)
    {
        return new a(obj);
    }

    public static boolean equal(Object obj, Object obj1)
    {
        return obj == obj1 || obj != null && obj.equals(obj1);
    }

    public static transient int hashCode(Object aobj[])
    {
        return Arrays.hashCode(aobj);
    }
}
