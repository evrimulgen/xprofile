// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.picasso;

import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;

public class StatsSnapshot
{

    public StatsSnapshot(int i, int j, long l, long l1, long l2, long l3, long l4, long l5, 
            int k, int i1, long l6)
    {
        maxSize = i;
        size = j;
        cacheHits = l;
        cacheMisses = l1;
        totalOriginalBitmapSize = l2;
        totalTransformedBitmapSize = l3;
        averageOriginalBitmapSize = l4;
        averageTransformedBitmapSize = l5;
        originalBitmapCount = k;
        transformedBitmapCount = i1;
        timeStamp = l6;
    }

    public void dump()
    {
        StringWriter stringwriter = new StringWriter();
        dump(new PrintWriter(stringwriter));
        Log.i("Picasso", stringwriter.toString());
    }

    public void dump(PrintWriter printwriter)
    {
        printwriter.println("===============BEGIN PICASSO STATS ===============");
        printwriter.println("Memory Cache Stats");
        printwriter.print("  Max Cache Size: ");
        printwriter.println(maxSize);
        printwriter.print("  Cache Size: ");
        printwriter.println(size);
        printwriter.print("  Cache % Full: ");
        printwriter.println((int)Math.ceil(100F * ((float)size / (float)maxSize)));
        printwriter.print("  Cache Hits: ");
        printwriter.println(cacheHits);
        printwriter.print("  Cache Misses: ");
        printwriter.println(cacheMisses);
        printwriter.println("Bitmap Stats");
        printwriter.print("  Total Bitmaps Decoded: ");
        printwriter.println(originalBitmapCount);
        printwriter.print("  Total Bitmap Size: ");
        printwriter.println(totalOriginalBitmapSize);
        printwriter.print("  Total Transformed Bitmaps: ");
        printwriter.println(transformedBitmapCount);
        printwriter.print("  Total Transformed Bitmap Size: ");
        printwriter.println(totalTransformedBitmapSize);
        printwriter.print("  Average Bitmap Size: ");
        printwriter.println(averageOriginalBitmapSize);
        printwriter.print("  Average Transformed Bitmap Size: ");
        printwriter.println(averageTransformedBitmapSize);
        printwriter.println("===============END PICASSO STATS ===============");
        printwriter.flush();
    }

    public String toString()
    {
        return (new StringBuilder()).append("StatsSnapshot{maxSize=").append(maxSize).append(", size=").append(size).append(", cacheHits=").append(cacheHits).append(", cacheMisses=").append(cacheMisses).append(", totalOriginalBitmapSize=").append(totalOriginalBitmapSize).append(", totalTransformedBitmapSize=").append(totalTransformedBitmapSize).append(", averageOriginalBitmapSize=").append(averageOriginalBitmapSize).append(", averageTransformedBitmapSize=").append(averageTransformedBitmapSize).append(", originalBitmapCount=").append(originalBitmapCount).append(", transformedBitmapCount=").append(transformedBitmapCount).append(", timeStamp=").append(timeStamp).append('}').toString();
    }

    private static final String TAG = "Picasso";
    public final long averageOriginalBitmapSize;
    public final long averageTransformedBitmapSize;
    public final long cacheHits;
    public final long cacheMisses;
    public final int maxSize;
    public final int originalBitmapCount;
    public final int size;
    public final long timeStamp;
    public final long totalOriginalBitmapSize;
    public final long totalTransformedBitmapSize;
    public final int transformedBitmapCount;
}
