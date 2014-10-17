// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.db;

import io.segment.android.models.BasePayload;
import io.segment.android.utils.IThreadedLayer;
import java.util.List;

public interface IPayloadDatabaseLayer
    extends IThreadedLayer
{
    public static interface EnqueueCallback
    {

        public abstract void onEnqueue(boolean flag, long l);
    }

    public static interface PayloadCallback
    {

        public abstract void onPayload(long l, long l1, List list);
    }

    public static interface RemoveCallback
    {

        public abstract void onRemoved(int i);
    }


    public abstract void enqueue(BasePayload basepayload, EnqueueCallback enqueuecallback);

    public abstract void nextPayload(PayloadCallback payloadcallback);

    public abstract void removePayloads(long l, long l1, RemoveCallback removecallback);
}
