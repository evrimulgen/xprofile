// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;


// Referenced classes of package com.google.tagmanager:
//            SimpleCache, LRUCache, Cache

class CacheFactory
{
    public static interface CacheSizeManager
    {

        public abstract int sizeOf(Object obj, Object obj1);
    }


    public CacheFactory()
    {
    }

    public Cache createCache(int i)
    {
        return createCache(i, mDefaultSizeManager);
    }

    public Cache createCache(int i, CacheSizeManager cachesizemanager)
    {
        if(i <= 0)
            throw new IllegalArgumentException("maxSize <= 0");
        if(getSdkVersion() < 12)
            return new SimpleCache(i, cachesizemanager);
        else
            return new LRUCache(i, cachesizemanager);
    }

    int getSdkVersion()
    {
        return android.os.Build.VERSION.SDK_INT;
    }

    final CacheSizeManager mDefaultSizeManager = new CacheSizeManager() {

        public int sizeOf(Object obj, Object obj1)
        {
            return 1;
        }

        final CacheFactory this$0;

            
            {
                this$0 = CacheFactory.this;
                super();
            }
    }
;
}
