// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.tagmanager;

import android.util.LruCache;

// Referenced classes of package com.google.android.gms.tagmanager:
//            k

class bb
    implements k
{

    bb(int i, l.a a)
    {
        Vw = new LruCache(i, a) {

            protected int sizeOf(Object obj, Object obj1)
            {
                return Vx.sizeOf(obj, obj1);
            }

            final l.a Vx;
            final bb Vy;

            
            {
                Vy = bb.this;
                Vx = a;
                super(i);
            }
        }
;
    }

    public void e(Object obj, Object obj1)
    {
        Vw.put(obj, obj1);
    }

    public Object get(Object obj)
    {
        return Vw.get(obj);
    }

    private LruCache Vw;
}
