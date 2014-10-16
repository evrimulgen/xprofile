// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import java.io.Serializable;

public abstract class NopAnnotationIntrospector extends AnnotationIntrospector
    implements Serializable
{

    public NopAnnotationIntrospector()
    {
    }

    public Version version()
    {
        return Version.unknownVersion();
    }

    public static final NopAnnotationIntrospector instance = new NopAnnotationIntrospector() {

        public Version version()
        {
            return PackageVersion.VERSION;
        }

        private static final long serialVersionUID = 1L;

    }
;
    private static final long serialVersionUID = 1L;

}
