// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit.client;

import java.util.*;
import retrofit.mime.TypedOutput;

public final class Request
{

    public Request(String s, String s1, List list, TypedOutput typedoutput)
    {
        if(s == null)
            throw new NullPointerException("Method must not be null.");
        if(s1 == null)
            throw new NullPointerException("URL must not be null.");
        method = s;
        url = s1;
        if(list == null)
            headers = Collections.emptyList();
        else
            headers = Collections.unmodifiableList(new ArrayList(list));
        body = typedoutput;
    }

    public TypedOutput getBody()
    {
        return body;
    }

    public List getHeaders()
    {
        return headers;
    }

    public String getMethod()
    {
        return method;
    }

    public String getUrl()
    {
        return url;
    }

    private final TypedOutput body;
    private final List headers;
    private final String method;
    private final String url;
}
