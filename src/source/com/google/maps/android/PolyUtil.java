// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.maps.android;

import com.google.android.gms.maps.model.LatLng;
import java.util.*;

// Referenced classes of package com.google.maps.android:
//            MathUtil

public class PolyUtil
{

    private PolyUtil()
    {
    }

    public static boolean containsLocation(LatLng latlng, List list, boolean flag)
    {
        int i = list.size();
        if(i == 0)
            return false;
        double d = Math.toRadians(latlng.latitude);
        double d1 = Math.toRadians(latlng.longitude);
        LatLng latlng1 = (LatLng)list.get(i - 1);
        double d2 = Math.toRadians(latlng1.latitude);
        double d3 = Math.toRadians(latlng1.longitude);
        int j = 0;
        for(Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            LatLng latlng2 = (LatLng)iterator.next();
            double d4 = MathUtil.wrap(d1 - d3, -3.1415926535897931D, 3.1415926535897931D);
            if(d == d2 && d4 == 0.0D)
                return true;
            double d5 = Math.toRadians(latlng2.latitude);
            double d6 = Math.toRadians(latlng2.longitude);
            double d7 = MathUtil.wrap(d6 - d3, -3.1415926535897931D, 3.1415926535897931D);
            if(intersects(d2, d5, d7, d, d4, flag))
                j++;
            d2 = d5;
            d3 = d6;
        }

        return (j & 1) != 0;
    }

    public static List decode(String s)
    {
        int i;
        ArrayList arraylist;
        int j;
        int k;
        int l;
        i = s.length();
        arraylist = new ArrayList();
        j = 0;
        k = 0;
        l = 0;
_L5:
        if(j >= i) goto _L2; else goto _L1
_L1:
        int i1;
        int j1;
        i1 = 1;
        j1 = 0;
_L7:
        int k1;
        int j2;
        int l2;
        int i3;
        k1 = j + 1;
        int l1 = -1 + (-63 + s.charAt(j));
        i1 += l1 << j1;
        j1 += 5;
        if(l1 >= 31)
            break MISSING_BLOCK_LABEL_223;
        int i2;
        int k2;
        int j3;
        if((i1 & 1) != 0)
            i2 = -1 ^ i1 >> 1;
        else
            i2 = i1 >> 1;
        k += i2;
        j2 = 1;
        k2 = 0;
        l2 = k1;
_L6:
        i3 = l2 + 1;
        j3 = -1 + (-63 + s.charAt(l2));
        j2 += j3 << k2;
        k2 += 5;
        if(j3 >= 31) goto _L4; else goto _L3
_L3:
        int k3;
        if((j2 & 1) != 0)
            k3 = -1 ^ j2 >> 1;
        else
            k3 = j2 >> 1;
        l += k3;
        arraylist.add(new LatLng(1.0000000000000001E-005D * (double)k, 1.0000000000000001E-005D * (double)l));
        j = i3;
          goto _L5
_L2:
        return arraylist;
_L4:
        l2 = i3;
          goto _L6
        j = k1;
          goto _L7
    }

    public static String encode(List list)
    {
        long l = 0L;
        long l1 = 0L;
        StringBuffer stringbuffer = new StringBuffer();
        for(Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            LatLng latlng = (LatLng)iterator.next();
            long l2 = Math.round(100000D * latlng.latitude);
            long l3 = Math.round(100000D * latlng.longitude);
            long l4 = l2 - l;
            long l5 = l3 - l1;
            encode(l4, stringbuffer);
            encode(l5, stringbuffer);
            l = l2;
            l1 = l3;
        }

        return stringbuffer.toString();
    }

    private static void encode(long l, StringBuffer stringbuffer)
    {
        long l1;
        if(l < 0L)
            l1 = -1L ^ l << 1;
        else
            l1 = l << 1;
        for(; l1 >= 32L; l1 >>= 5)
            stringbuffer.append(Character.toChars((int)(63L + (32L | 31L & l1))));

        stringbuffer.append(Character.toChars((int)(l1 + 63L)));
    }

    private static boolean intersects(double d, double d1, double d2, double d3, 
            double d4, boolean flag)
    {
        if(d4 >= 0.0D && d4 >= d2 || d4 < 0.0D && d4 < d2)
            return false;
        if(d3 <= -1.5707963267948966D)
            return false;
        if(d <= -1.5707963267948966D || d1 <= -1.5707963267948966D || d >= 1.5707963267948966D || d1 >= 1.5707963267948966D)
            return false;
        if(d2 <= -3.1415926535897931D)
            return false;
        double d5 = (d * (d2 - d4) + d1 * d4) / d2;
        if(d >= 0.0D && d1 >= 0.0D && d3 < d5)
            return false;
        if(d <= 0.0D && d1 <= 0.0D && d3 >= d5)
            return true;
        if(d3 >= 1.5707963267948966D)
            return true;
        if(flag)
            return Math.tan(d3) >= tanLatGC(d, d1, d2, d4);
        return MathUtil.mercator(d3) >= mercatorLatRhumb(d, d1, d2, d4);
    }

    public static boolean isLocationOnEdge(LatLng latlng, List list, boolean flag)
    {
        return isLocationOnEdge(latlng, list, flag, 0.10000000000000001D);
    }

    public static boolean isLocationOnEdge(LatLng latlng, List list, boolean flag, double d)
    {
        return isLocationOnEdgeOrPath(latlng, list, true, flag, d);
    }

    private static boolean isLocationOnEdgeOrPath(LatLng latlng, List list, boolean flag, boolean flag1, double d)
    {
        int i = list.size();
        if(i == 0)
            return false;
        double d1 = d / 6371009D;
        double d2 = MathUtil.hav(d1);
        double d3 = Math.toRadians(latlng.latitude);
        double d4 = Math.toRadians(latlng.longitude);
        int j;
        LatLng latlng1;
        double d5;
        double d6;
        if(flag)
            j = i - 1;
        else
            j = 0;
        latlng1 = (LatLng)list.get(j);
        d5 = Math.toRadians(latlng1.latitude);
        d6 = Math.toRadians(latlng1.longitude);
        if(flag1)
        {
            for(Iterator iterator1 = list.iterator(); iterator1.hasNext();)
            {
                LatLng latlng3 = (LatLng)iterator1.next();
                double d21 = Math.toRadians(latlng3.latitude);
                double d22 = Math.toRadians(latlng3.longitude);
                if(isOnSegmentGC(d5, d6, d21, d22, d3, d4, d2))
                    return true;
                d5 = d21;
                d6 = d22;
            }

        } else
        {
            double d7 = d3 - d1;
            double d8 = d3 + d1;
            double d9 = MathUtil.mercator(d5);
            double d10 = MathUtil.mercator(d3);
            double ad[] = new double[3];
            for(Iterator iterator = list.iterator(); iterator.hasNext();)
            {
                LatLng latlng2 = (LatLng)iterator.next();
                double d11 = Math.toRadians(latlng2.latitude);
                double d12 = MathUtil.mercator(d11);
                double d13 = Math.toRadians(latlng2.longitude);
                if(Math.max(d5, d11) >= d7 && Math.min(d5, d11) <= d8)
                {
                    double d14 = MathUtil.wrap(d13 - d6, -3.1415926535897931D, 3.1415926535897931D);
                    double d15 = MathUtil.wrap(d4 - d6, -3.1415926535897931D, 3.1415926535897931D);
                    ad[0] = d15;
                    ad[1] = 6.2831853071795862D + d15;
                    ad[2] = d15 - 6.2831853071795862D;
                    int k = ad.length;
                    double d16;
                    double d17;
                    double d18;
                    double d19;
                    for(int l = 0; l < k; l++)
                    {
                        d16 = ad[l];
                        d17 = d12 - d9;
                        d18 = d14 * d14 + d17 * d17;
                        double d20;
                        if(d18 <= 0.0D)
                            d19 = 0.0D;
                        else
                            d19 = MathUtil.clamp((d16 * d14 + d17 * (d10 - d9)) / d18, 0.0D, 1.0D);
                        d20 = d19 * d14;
                        if(MathUtil.havDistance(d3, MathUtil.inverseMercator(d9 + d19 * d17), d16 - d20) < d2)
                            return true;
                    }

                }
                d5 = d11;
                d6 = d13;
                d9 = d12;
            }

        }
        return false;
    }

    public static boolean isLocationOnPath(LatLng latlng, List list, boolean flag)
    {
        return isLocationOnPath(latlng, list, flag, 0.10000000000000001D);
    }

    public static boolean isLocationOnPath(LatLng latlng, List list, boolean flag, double d)
    {
        return isLocationOnEdgeOrPath(latlng, list, false, flag, d);
    }

    private static boolean isOnSegmentGC(double d, double d1, double d2, double d3, 
            double d4, double d5, double d6)
    {
        double d7 = MathUtil.havDistance(d, d4, d1 - d5);
        if(d7 <= d6)
            return true;
        double d8 = MathUtil.havDistance(d2, d4, d3 - d5);
        if(d8 <= d6)
            return true;
        double d9 = MathUtil.havFromSin(sinDeltaBearing(d, d1, d2, d3, d4, d5) * MathUtil.sinFromHav(d7));
        if(d9 > d6)
            return false;
        double d10 = MathUtil.havDistance(d, d2, d1 - d3);
        double d11 = d10 + d9 * (1.0D - 2D * d10);
        if(d7 > d11 || d8 > d11)
            return false;
        if(d10 < 0.73999999999999999D)
            return true;
        double d12 = 1.0D - 2D * d9;
        return MathUtil.sinSumFromHav((d7 - d9) / d12, (d8 - d9) / d12) > 0.0D;
    }

    private static double mercatorLatRhumb(double d, double d1, double d2, double d3)
    {
        return (MathUtil.mercator(d) * (d2 - d3) + d3 * MathUtil.mercator(d1)) / d2;
    }

    private static double sinDeltaBearing(double d, double d1, double d2, double d3, 
            double d4, double d5)
    {
        double d6 = Math.sin(d);
        double d7 = Math.cos(d2);
        double d8 = Math.cos(d4);
        double d9 = d4 - d;
        double d10 = d5 - d1;
        double d11 = d2 - d;
        double d12 = d3 - d1;
        double d13 = d8 * Math.sin(d10);
        double d14 = d7 * Math.sin(d12);
        double d15 = Math.sin(d9) + d8 * (2D * d6) * MathUtil.hav(d10);
        double d16 = Math.sin(d11) + d7 * (2D * d6) * MathUtil.hav(d12);
        double d17 = (d13 * d13 + d15 * d15) * (d14 * d14 + d16 * d16);
        if(d17 <= 0.0D)
            return 1.0D;
        else
            return (d13 * d16 - d15 * d14) / Math.sqrt(d17);
    }

    private static double tanLatGC(double d, double d1, double d2, double d3)
    {
        return (Math.tan(d) * Math.sin(d2 - d3) + Math.tan(d1) * Math.sin(d3)) / Math.sin(d2);
    }

    private static final double DEFAULT_TOLERANCE = 0.10000000000000001D;
}
