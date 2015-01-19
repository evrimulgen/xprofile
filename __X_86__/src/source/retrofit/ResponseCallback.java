// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit;

import retrofit.client.Response;

// Referenced classes of package retrofit:
//            Callback

public abstract class ResponseCallback
    implements Callback
{

    public ResponseCallback()
    {
    }

    public volatile void success(Object obj, Response response)
    {
        success((Response)obj, response);
    }

    public abstract void success(Response response);

    public void success(Response response, Response response1)
    {
        success(response);
    }
}
