// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.flush;

import io.segment.android.utils.IThreadedLayer;

public interface IFlushLayer
    extends IThreadedLayer
{
    public static interface FlushCallback
    {

        public abstract void onFlushCompleted(boolean flag);
    }


    public abstract void flush(FlushCallback flushcallback);
}
