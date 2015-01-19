// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.request;

import io.segment.android.models.Batch;
import io.segment.android.models.EasyJSONObject;
import org.apache.http.HttpResponse;

public interface IRequester
{

    public abstract EasyJSONObject fetchSettings();

    public abstract HttpResponse send(Batch batch);
}
