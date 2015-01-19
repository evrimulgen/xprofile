// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.adobe.adms.measurement;

import java.util.Hashtable;

final class ADMS_RequestProperties
{

    protected ADMS_RequestProperties(String s)
    {
        _headers = new Hashtable();
        String as[] = s.split("\t");
        if(as.length > 0 && as[0].length() > 0)
            _url = as[0];
        int i = 1;
        do
        {
            if(i >= as.length || i + 1 > as.length)
                return;
            String s1 = as[i];
            String s2 = as[i + 1];
            if(s1.trim().length() > 0 && s2.trim().length() > 0)
                _headers.put(s1, s2);
            i += 2;
        } while(true);
    }

    protected Hashtable getHeaders()
    {
        return _headers;
    }

    protected String getUrl()
    {
        return _url;
    }

    private Hashtable _headers;
    private String _url;
}
