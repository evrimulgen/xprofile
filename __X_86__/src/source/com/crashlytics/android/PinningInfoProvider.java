// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.crashlytics.android;

import java.io.InputStream;

public interface PinningInfoProvider
{

    public abstract String getKeyStorePassword();

    public abstract InputStream getKeyStoreStream();

    public abstract String[] getPins();
}
