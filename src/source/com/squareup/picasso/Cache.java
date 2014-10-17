// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.picasso;

import android.graphics.Bitmap;

public interface Cache
{

    public abstract void clear();

    public abstract Bitmap get(String s);

    public abstract int maxSize();

    public abstract void set(String s, Bitmap bitmap);

    public abstract int size();

    public static final Cache NONE = new Cache() {

        public void clear()
        {
        }

        public Bitmap get(String s)
        {
            return null;
        }

        public int maxSize()
        {
            return 0;
        }

        public void set(String s, Bitmap bitmap)
        {
        }

        public int size()
        {
            return 0;
        }

    }
;

}
