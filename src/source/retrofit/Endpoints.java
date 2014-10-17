// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit;


// Referenced classes of package retrofit:
//            Server, Endpoint

public final class Endpoints
{

    private Endpoints()
    {
    }

    public static Endpoint newFixedEndpoint(String s)
    {
        return new Server(s);
    }

    public static Endpoint newFixedEndpoint(String s, String s1)
    {
        return new Server(s, s1);
    }
}
