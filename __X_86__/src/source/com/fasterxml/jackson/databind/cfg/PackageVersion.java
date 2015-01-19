// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.util.VersionUtil;

public final class PackageVersion
    implements Versioned
{

    public PackageVersion()
    {
    }

    public Version version()
    {
        return VERSION;
    }

    public static final Version VERSION = VersionUtil.parseVersion("2.3.1", "com.fasterxml.jackson.core", "jackson-databind");

}
