// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.api.models;


// Referenced classes of package com.roadtrippers.api.models:
//            Geometry

public class GooglePlaceDetail
{
    public static class Response
    {

        public GooglePlaceDetail result;

        public Response()
        {
        }
    }


    public GooglePlaceDetail()
    {
    }

    public String formatted_address;
    public Geometry geometry;
    public String id;
    public String name;
}
