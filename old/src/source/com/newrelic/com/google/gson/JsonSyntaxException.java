// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.com.google.gson;


// Referenced classes of package com.newrelic.com.google.gson:
//            JsonParseException

public final class JsonSyntaxException extends JsonParseException
{

    public JsonSyntaxException(String s)
    {
        super(s);
    }

    public JsonSyntaxException(String s, Throwable throwable)
    {
        super(s, throwable);
    }

    public JsonSyntaxException(Throwable throwable)
    {
        super(throwable);
    }

    private static final long serialVersionUID = 1L;
}
