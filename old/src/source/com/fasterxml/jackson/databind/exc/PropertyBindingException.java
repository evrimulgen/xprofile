// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.exc;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.util.*;

public abstract class PropertyBindingException extends JsonMappingException
{

    protected PropertyBindingException(String s, JsonLocation jsonlocation, Class class1, String s1, Collection collection)
    {
        super(s, jsonlocation);
        _referringClass = class1;
        _propertyName = s1;
        _propertyIds = collection;
    }

    public Collection getKnownPropertyIds()
    {
        if(_propertyIds == null)
            return null;
        else
            return Collections.unmodifiableCollection(_propertyIds);
    }

    public String getMessageSuffix()
    {
        String s = _propertiesAsString;
        if(s != null || _propertyIds == null) goto _L2; else goto _L1
_L1:
        StringBuilder stringbuilder;
        int i;
        stringbuilder = new StringBuilder(100);
        i = _propertyIds.size();
        if(i != 1) goto _L4; else goto _L3
_L3:
        stringbuilder.append(" (one known property: \"");
        stringbuilder.append(String.valueOf(_propertyIds.iterator().next()));
        stringbuilder.append('"');
_L6:
        stringbuilder.append("])");
        s = stringbuilder.toString();
        _propertiesAsString = s;
_L2:
        return s;
_L4:
        stringbuilder.append(" (").append(i).append(" known properties: ");
        Iterator iterator = _propertyIds.iterator();
        do
        {
            if(iterator.hasNext())
            {
label0:
                {
                    stringbuilder.append('"');
                    stringbuilder.append(String.valueOf(iterator.next()));
                    stringbuilder.append('"');
                    if(stringbuilder.length() <= 200)
                        break label0;
                    stringbuilder.append(" [truncated]");
                }
            }
            if(true)
                continue;
            if(iterator.hasNext())
                stringbuilder.append(", ");
        } while(true);
        if(true) goto _L6; else goto _L5
_L5:
    }

    public String getPropertyName()
    {
        return _propertyName;
    }

    public Class getReferringClass()
    {
        return _referringClass;
    }

    private static final int MAX_DESC_LENGTH = 200;
    protected transient String _propertiesAsString;
    protected final Collection _propertyIds;
    protected final String _propertyName;
    protected final Class _referringClass;
}
