// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.stats;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package io.segment.android.stats:
//            Statistic

public class Statistics extends ConcurrentHashMap
{

    public Statistics()
    {
    }

    public Statistic ensure(String s)
    {
        if(containsKey(s))
        {
            return (Statistic)get(s);
        } else
        {
            Statistic statistic = new Statistic();
            put(s, statistic);
            return statistic;
        }
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("\n-------- Safe Client Statistics --------\n");
        Iterator iterator = entrySet().iterator();
        do
        {
            if(!iterator.hasNext())
            {
                stringbuilder.append("----------------------------------------\n");
                return stringbuilder.toString();
            }
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            String s = (String)entry.getKey();
            Statistic statistic = (Statistic)entry.getValue();
            Object aobj[] = new Object[2];
            aobj[0] = s;
            aobj[1] = statistic.toString();
            stringbuilder.append(String.format("%s : %s\n", aobj));
        } while(true);
    }

    public void update(String s, double d)
    {
        if(!containsKey(s))
            putIfAbsent(s, new Statistic());
        ((Statistic)get(s)).update(d);
    }

    private static final long serialVersionUID = 0x855ca52e58fa317aL;
}
