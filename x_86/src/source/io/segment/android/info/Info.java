// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.info;

import android.content.Context;

public interface Info
{

    public abstract Object get(Context context);

    public abstract String getKey();
}
