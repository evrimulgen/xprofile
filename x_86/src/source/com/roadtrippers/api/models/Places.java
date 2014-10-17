// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.api.models;


// Referenced classes of package com.roadtrippers.api.models:
//            Poi

public class Places
{
    public static class Response
    {

        public Places response;

        public Response()
        {
        }
    }


    public Places()
    {
    }

    public Places(Poi poi)
    {
        if(poi == null)
        {
            throw new NullPointerException("place should not be null");
        } else
        {
            results = (new Poi[] {
                poi
            });
            return;
        }
    }

    public Poi results[];
}
