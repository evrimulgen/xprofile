// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.db;

import io.segment.android.models.BasePayload;

public interface IPayloadSerializer
{

    public abstract BasePayload deseralize(String s);

    public abstract String serialize(BasePayload basepayload);
}
