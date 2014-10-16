// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.containertag.common.Key;
import java.io.UnsupportedEncodingException;
import java.util.*;

// Referenced classes of package com.google.tagmanager:
//            FunctionCallImplementation, ValueEscapeUtil, Log, Types

class JoinerMacro extends FunctionCallImplementation
{
    private static final class EscapeType extends Enum
    {

        public static EscapeType valueOf(String s)
        {
            return (EscapeType)Enum.valueOf(com/google/tagmanager/JoinerMacro$EscapeType, s);
        }

        public static EscapeType[] values()
        {
            return (EscapeType[])$VALUES.clone();
        }

        private static final EscapeType $VALUES[];
        public static final EscapeType BACKSLASH;
        public static final EscapeType NONE;
        public static final EscapeType URL;

        static 
        {
            NONE = new EscapeType("NONE", 0);
            URL = new EscapeType("URL", 1);
            BACKSLASH = new EscapeType("BACKSLASH", 2);
            EscapeType aescapetype[] = new EscapeType[3];
            aescapetype[0] = NONE;
            aescapetype[1] = URL;
            aescapetype[2] = BACKSLASH;
            $VALUES = aescapetype;
        }

        private EscapeType(String s, int i)
        {
            super(s, i);
        }
    }


    public JoinerMacro()
    {
        String s = ID;
        String as[] = new String[1];
        as[0] = ARG0;
        super(s, as);
    }

    private void addTo(Set set, String s)
    {
        for(int i = 0; i < s.length(); i++)
            set.add(Character.valueOf(s.charAt(i)));

    }

    private void append(StringBuilder stringbuilder, String s, EscapeType escapetype, Set set)
    {
        stringbuilder.append(escape(s, escapetype, set));
    }

    private String escape(String s, EscapeType escapetype, Set set)
    {
        static class _cls1
        {

            static final int $SwitchMap$com$google$analytics$midtier$proto$containertag$TypeSystem$Value$Type[];
            static final int $SwitchMap$com$google$tagmanager$JoinerMacro$EscapeType[];

            static 
            {
                $SwitchMap$com$google$tagmanager$JoinerMacro$EscapeType = new int[EscapeType.values().length];
                try
                {
                    $SwitchMap$com$google$tagmanager$JoinerMacro$EscapeType[EscapeType.URL.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$google$tagmanager$JoinerMacro$EscapeType[EscapeType.BACKSLASH.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$google$tagmanager$JoinerMacro$EscapeType[EscapeType.NONE.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                $SwitchMap$com$google$analytics$midtier$proto$containertag$TypeSystem$Value$Type = new int[com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Type.values().length];
                try
                {
                    $SwitchMap$com$google$analytics$midtier$proto$containertag$TypeSystem$Value$Type[com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Type.LIST.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$com$google$analytics$midtier$proto$containertag$TypeSystem$Value$Type[com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Type.MAP.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror4)
                {
                    return;
                }
            }
        }

        String s1;
        Iterator iterator;
        switch(_cls1..SwitchMap.com.google.tagmanager.JoinerMacro.EscapeType[escapetype.ordinal()])
        {
        default:
            return s;

        case 1: // '\001'
            String s3;
            try
            {
                s3 = ValueEscapeUtil.urlEncode(s);
            }
            catch(UnsupportedEncodingException unsupportedencodingexception)
            {
                Log.e("Joiner: unsupported encoding", unsupportedencodingexception);
                return s;
            }
            return s3;

        case 2: // '\002'
            s1 = s.replace("\\", "\\\\");
            iterator = set.iterator();
            break;
        }
        while(iterator.hasNext()) 
        {
            String s2 = ((Character)iterator.next()).toString();
            s1 = s1.replace(s2, (new StringBuilder()).append("\\").append(s2).toString());
        }
        return s1;
    }

    public static String getFunctionId()
    {
        return ID;
    }

    public com.google.analytics.midtier.proto.containertag.TypeSystem.Value evaluate(Map map)
    {
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value value;
        String s;
        String s1;
        EscapeType escapetype;
        Object obj;
        StringBuilder stringbuilder;
        value = (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(ARG0);
        if(value == null)
            return Types.getDefaultValue();
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value value1 = (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(ITEM_SEPARATOR);
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value value2;
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value value3;
        if(value1 != null)
            s = Types.valueToString(value1);
        else
            s = "";
        value2 = (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(KEY_VALUE_SEPARATOR);
        if(value2 != null)
            s1 = Types.valueToString(value2);
        else
            s1 = "=";
        escapetype = EscapeType.NONE;
        value3 = (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(ESCAPE);
        obj = null;
        if(value3 != null)
        {
            String s4 = Types.valueToString(value3);
            if("url".equals(s4))
                escapetype = EscapeType.URL;
            else
            if("backslash".equals(s4))
            {
                escapetype = EscapeType.BACKSLASH;
                obj = new HashSet();
                addTo(((Set) (obj)), s);
                addTo(((Set) (obj)), s1);
                ((Set) (obj)).remove(Character.valueOf('\\'));
            } else
            {
                Log.e((new StringBuilder()).append("Joiner: unsupported escape type: ").append(s4).toString());
                return Types.getDefaultValue();
            }
        }
        stringbuilder = new StringBuilder();
        _cls1..SwitchMap.com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Type[value.getType().ordinal()];
        JVM INSTR tableswitch 1 2: default 160
    //                   1 280
    //                   2 350;
           goto _L1 _L2 _L3
_L1:
        append(stringbuilder, Types.valueToString(value), escapetype, ((Set) (obj)));
_L5:
        return Types.objectToValue(stringbuilder.toString());
_L2:
        boolean flag = true;
        Iterator iterator = value.getListItemList().iterator();
        while(iterator.hasNext()) 
        {
            com.google.analytics.midtier.proto.containertag.TypeSystem.Value value4 = (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)iterator.next();
            if(!flag)
                stringbuilder.append(s);
            append(stringbuilder, Types.valueToString(value4), escapetype, ((Set) (obj)));
            flag = false;
        }
        if(true)
            continue; /* Loop/switch isn't completed */
_L3:
        int i = 0;
        while(i < value.getMapKeyCount()) 
        {
            if(i > 0)
                stringbuilder.append(s);
            String s2 = Types.valueToString(value.getMapKey(i));
            String s3 = Types.valueToString(value.getMapValue(i));
            append(stringbuilder, s2, escapetype, ((Set) (obj)));
            stringbuilder.append(s1);
            append(stringbuilder, s3, escapetype, ((Set) (obj)));
            i++;
        }
        if(true) goto _L5; else goto _L4
_L4:
    }

    public boolean isCacheable()
    {
        return true;
    }

    private static final String ARG0;
    private static final String DEFAULT_ITEM_SEPARATOR = "";
    private static final String DEFAULT_KEY_VALUE_SEPARATOR = "=";
    private static final String ESCAPE;
    private static final String ID;
    private static final String ITEM_SEPARATOR;
    private static final String KEY_VALUE_SEPARATOR;

    static 
    {
        ID = FunctionType.JOINER.toString();
        ARG0 = Key.ARG0.toString();
        ITEM_SEPARATOR = Key.ITEM_SEPARATOR.toString();
        KEY_VALUE_SEPARATOR = Key.KEY_VALUE_SEPARATOR.toString();
        ESCAPE = Key.ESCAPE.toString();
    }
}
