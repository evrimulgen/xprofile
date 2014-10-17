// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit;

import retrofit.client.Response;

// Referenced classes of package retrofit:
//            RetrofitError

public interface Callback
{

    public abstract void failure(RetrofitError retrofiterror);

    public abstract void success(Object obj, Response response);
}
