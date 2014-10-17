// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import java.util.*;

// Referenced classes of package com.squareup.picasso:
//            Cache, Utils

public class LruCache
    implements Cache
{

    public LruCache(int i)
    {
        if(i <= 0)
        {
            throw new IllegalArgumentException("Max size must be positive.");
        } else
        {
            maxSize = i;
            map = new LinkedHashMap(0, 0.75F, true);
            return;
        }
    }

    public LruCache(Context context)
    {
        this(Utils.calculateMemoryCacheSize(context));
    }

    private void trimToSize(int i)
    {
_L1:
        this;
        JVM INSTR monitorenter ;
        if(size < 0 || map.isEmpty() && size != 0)
            throw new IllegalStateException((new StringBuilder()).append(getClass().getName()).append(".sizeOf() is reporting inconsistent results!").toString());
        break MISSING_BLOCK_LABEL_64;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        if(size > i && !map.isEmpty())
            break MISSING_BLOCK_LABEL_85;
        this;
        JVM INSTR monitorexit ;
        return;
        java.util.Map.Entry entry = (java.util.Map.Entry)map.entrySet().iterator().next();
        String s = (String)entry.getKey();
        Bitmap bitmap = (Bitmap)entry.getValue();
        map.remove(s);
        size = size - Utils.getBitmapBytes(bitmap);
        evictionCount = 1 + evictionCount;
        this;
        JVM INSTR monitorexit ;
          goto _L1
    }

    public final void clear()
    {
        this;
        JVM INSTR monitorenter ;
        evictAll();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void evictAll()
    {
        trimToSize(-1);
    }

    public final int evictionCount()
    {
        this;
        JVM INSTR monitorenter ;
        int i = evictionCount;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public Bitmap get(String s)
    {
        if(s == null)
            throw new NullPointerException("key == null");
        this;
        JVM INSTR monitorenter ;
        Bitmap bitmap = (Bitmap)map.get(s);
        if(bitmap == null)
            break MISSING_BLOCK_LABEL_46;
        hitCount = 1 + hitCount;
        this;
        JVM INSTR monitorexit ;
        return bitmap;
        missCount = 1 + missCount;
        this;
        JVM INSTR monitorexit ;
        return null;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final int hitCount()
    {
        this;
        JVM INSTR monitorenter ;
        int i = hitCount;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public final int maxSize()
    {
        this;
        JVM INSTR monitorenter ;
        int i = maxSize;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public final int missCount()
    {
        this;
        JVM INSTR monitorenter ;
        int i = missCount;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public final int putCount()
    {
        this;
        JVM INSTR monitorenter ;
        int i = putCount;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public void set(String s, Bitmap bitmap)
    {
        if(s == null || bitmap == null)
            throw new NullPointerException("key == null || bitmap == null");
        this;
        JVM INSTR monitorenter ;
        Bitmap bitmap1;
        putCount = 1 + putCount;
        size = size + Utils.getBitmapBytes(bitmap);
        bitmap1 = (Bitmap)map.put(s, bitmap);
        if(bitmap1 == null)
            break MISSING_BLOCK_LABEL_76;
        size = size - Utils.getBitmapBytes(bitmap1);
        this;
        JVM INSTR monitorexit ;
        trimToSize(maxSize);
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final int size()
    {
        this;
        JVM INSTR monitorenter ;
        int i = size;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    private int evictionCount;
    private int hitCount;
    final LinkedHashMap map;
    private final int maxSize;
    private int missCount;
    private int putCount;
    private int size;
}
