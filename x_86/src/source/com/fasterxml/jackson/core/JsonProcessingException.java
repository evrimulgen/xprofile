// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core;

import java.io.IOException;

// Referenced classes of package com.fasterxml.jackson.core:
//            JsonLocation

public class JsonProcessingException extends IOException
{

    protected JsonProcessingException(String s)
    {
        super(s);
    }

    protected JsonProcessingException(String s, JsonLocation jsonlocation)
    {
        this(s, jsonlocation, null);
    }

    protected JsonProcessingException(String s, JsonLocation jsonlocation, Throwable throwable)
    {
        super(s);
        if(throwable != null)
            initCause(throwable);
        _location = jsonlocation;
    }

    protected JsonProcessingException(String s, Throwable throwable)
    {
        this(s, null, throwable);
    }

    protected JsonProcessingException(Throwable throwable)
    {
        this(null, null, throwable);
    }

    public JsonLocation getLocation()
    {
        return _location;
    }

    public String getMessage()
    {
        String s = super.getMessage();
        if(s == null)
            s = "N/A";
        JsonLocation jsonlocation = getLocation();
        String s1 = getMessageSuffix();
        if(jsonlocation != null || s1 != null)
        {
            StringBuilder stringbuilder = new StringBuilder(100);
            stringbuilder.append(s);
            if(s1 != null)
                stringbuilder.append(s1);
            if(jsonlocation != null)
            {
                stringbuilder.append('\n');
                stringbuilder.append(" at ");
                stringbuilder.append(jsonlocation.toString());
            }
            s = stringbuilder.toString();
        }
        return s;
    }

    protected String getMessageSuffix()
    {
        return null;
    }

    public String getOriginalMessage()
    {
        return super.getMessage();
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getName()).append(": ").append(getMessage()).toString();
    }

    static final long serialVersionUID = 123L;
    protected JsonLocation _location;
}
