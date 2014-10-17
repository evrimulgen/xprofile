// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.tapstream.sdk;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

// Referenced classes of package com.tapstream.sdk:
//            Logging, Response

public class Hit
{
    public static interface CompletionHandler
    {

        public abstract void complete(Response response);
    }


    public Hit(String s)
    {
        tags = null;
        trackerName = s;
        try
        {
            encodedTrackerName = URLEncoder.encode(s, "UTF-8").replace("+", "%20");
            return;
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            Object aobj[] = new Object[1];
            aobj[0] = unsupportedencodingexception.getMessage();
            Logging.log(4, "Tapstream Error: Could not encode hit tracker name, exception=%s", aobj);
            return;
        }
    }

    public void addTag(String s)
    {
        if(s.length() > 255)
        {
            Logging.log(5, "Tapstream Warning: Hit tag exceeds 255 characters, it will not be included in the post (tag=%s)", new Object[] {
                s
            });
            return;
        }
        String s1;
        try
        {
            s1 = URLEncoder.encode(s, "UTF-8").replace("+", "%20");
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            Object aobj[] = new Object[2];
            aobj[0] = s;
            aobj[1] = unsupportedencodingexception.getMessage();
            Logging.log(4, "Tapstream Error: Could not encode hit tracker tag %s, exception=%s", aobj);
            return;
        }
        if(tags == null)
            tags = new StringBuilder("__ts=");
        else
            tags.append(",");
        tags.append(s1);
    }

    public String getEncodedTrackerName()
    {
        return encodedTrackerName;
    }

    public String getPostData()
    {
        if(tags == null)
            return "";
        else
            return tags.toString();
    }

    public String getTrackerName()
    {
        return trackerName;
    }

    private String encodedTrackerName;
    private StringBuilder tags;
    private String trackerName;
}
