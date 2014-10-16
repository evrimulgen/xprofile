// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit;

import retrofit.client.Response;

final class ResponseWrapper
{

    ResponseWrapper(Response response1, Object obj)
    {
        response = response1;
        responseBody = obj;
    }

    final Response response;
    final Object responseBody;
}
