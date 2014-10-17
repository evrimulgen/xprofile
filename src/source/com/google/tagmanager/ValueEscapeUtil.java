// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.tagmanager:
//            ObjectAndStatic, Log, Types

class ValueEscapeUtil
{

    private ValueEscapeUtil()
    {
    }

    private static ObjectAndStatic applyEscaping(ObjectAndStatic objectandstatic, com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Escaping escaping)
    {
        if(!isValidStringType((com.google.analytics.midtier.proto.containertag.TypeSystem.Value)objectandstatic.getObject()))
        {
            Log.e("Escaping can only be applied to strings.");
            return objectandstatic;
        }
        static class _cls1
        {

            static final int $SwitchMap$com$google$analytics$midtier$proto$containertag$TypeSystem$Value$Escaping[];

            static 
            {
                $SwitchMap$com$google$analytics$midtier$proto$containertag$TypeSystem$Value$Escaping = new int[com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Escaping.values().length];
                try
                {
                    $SwitchMap$com$google$analytics$midtier$proto$containertag$TypeSystem$Value$Escaping[com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Escaping.ESCAPE_URI.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
            }
        }

        switch(_cls1..SwitchMap.com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Escaping[escaping.ordinal()])
        {
        default:
            Log.e((new StringBuilder()).append("Unsupported Value Escaping: ").append(escaping).toString());
            return objectandstatic;

        case 1: // '\001'
            return escapeUri(objectandstatic);
        }
    }

    static ObjectAndStatic applyEscapings(ObjectAndStatic objectandstatic, List list)
    {
        ObjectAndStatic objectandstatic1 = objectandstatic;
        for(Iterator iterator = list.iterator(); iterator.hasNext();)
            objectandstatic1 = applyEscaping(objectandstatic1, (com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Escaping)iterator.next());

        return objectandstatic1;
    }

    private static ObjectAndStatic escapeUri(ObjectAndStatic objectandstatic)
    {
        ObjectAndStatic objectandstatic1;
        try
        {
            objectandstatic1 = new ObjectAndStatic(Types.objectToValue(urlEncode(((com.google.analytics.midtier.proto.containertag.TypeSystem.Value)objectandstatic.getObject()).getString())), objectandstatic.isStatic());
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            Log.e("Escape URI: unsupported encoding", unsupportedencodingexception);
            return objectandstatic;
        }
        return objectandstatic1;
    }

    private static boolean isValidStringType(com.google.analytics.midtier.proto.containertag.TypeSystem.Value value)
    {
        return value.hasType() && value.getType().equals(com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Type.STRING) && value.hasString();
    }

    static String urlEncode(String s)
        throws UnsupportedEncodingException
    {
        return URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20");
    }
}
