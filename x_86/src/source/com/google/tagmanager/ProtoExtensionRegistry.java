// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import com.google.analytics.containertag.proto.Serving;
import com.google.tagmanager.protobuf.ExtensionRegistryLite;

class ProtoExtensionRegistry
{

    ProtoExtensionRegistry()
    {
    }

    public static ExtensionRegistryLite getRegistry()
    {
        com/google/tagmanager/ProtoExtensionRegistry;
        JVM INSTR monitorenter ;
        ExtensionRegistryLite extensionregistrylite;
        if(registry == null)
        {
            registry = ExtensionRegistryLite.newInstance();
            Serving.registerAllExtensions(registry);
        }
        extensionregistrylite = registry;
        com/google/tagmanager/ProtoExtensionRegistry;
        JVM INSTR monitorexit ;
        return extensionregistrylite;
        Exception exception;
        exception;
        throw exception;
    }

    private static ExtensionRegistryLite registry;
}
