// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager.protobuf;

import java.util.*;

// Referenced classes of package com.google.tagmanager.protobuf:
//            MessageLite

public class ExtensionRegistryLite
{
    private static final class ObjectIntPair
    {

        public boolean equals(Object obj)
        {
            ObjectIntPair objectintpair;
            if(obj instanceof ObjectIntPair)
                if(object == (objectintpair = (ObjectIntPair)obj).object && number == objectintpair.number)
                    return true;
            return false;
        }

        public int hashCode()
        {
            return 65535 * System.identityHashCode(object) + number;
        }

        private final int number;
        private final Object object;

        ObjectIntPair(Object obj, int i)
        {
            object = obj;
            number = i;
        }
    }


    ExtensionRegistryLite()
    {
        extensionsByNumber = new HashMap();
    }

    ExtensionRegistryLite(ExtensionRegistryLite extensionregistrylite)
    {
        if(extensionregistrylite == EMPTY)
        {
            extensionsByNumber = Collections.emptyMap();
            return;
        } else
        {
            extensionsByNumber = Collections.unmodifiableMap(extensionregistrylite.extensionsByNumber);
            return;
        }
    }

    private ExtensionRegistryLite(boolean flag)
    {
        extensionsByNumber = Collections.emptyMap();
    }

    public static ExtensionRegistryLite getEmptyRegistry()
    {
        return EMPTY;
    }

    public static boolean isEagerlyParseMessageSets()
    {
        return eagerlyParseMessageSets;
    }

    public static ExtensionRegistryLite newInstance()
    {
        return new ExtensionRegistryLite();
    }

    public static void setEagerlyParseMessageSets(boolean flag)
    {
        eagerlyParseMessageSets = flag;
    }

    public final void add(GeneratedMessageLite.GeneratedExtension generatedextension)
    {
        extensionsByNumber.put(new ObjectIntPair(generatedextension.getContainingTypeDefaultInstance(), generatedextension.getNumber()), generatedextension);
    }

    public GeneratedMessageLite.GeneratedExtension findLiteExtensionByNumber(MessageLite messagelite, int i)
    {
        return (GeneratedMessageLite.GeneratedExtension)extensionsByNumber.get(new ObjectIntPair(messagelite, i));
    }

    public ExtensionRegistryLite getUnmodifiable()
    {
        return new ExtensionRegistryLite(this);
    }

    private static final ExtensionRegistryLite EMPTY = new ExtensionRegistryLite(true);
    private static volatile boolean eagerlyParseMessageSets = false;
    private final Map extensionsByNumber;

}
