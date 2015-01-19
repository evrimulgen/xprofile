// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit;


// Referenced classes of package retrofit:
//            Endpoint

public class Server
    implements Endpoint
{

    public Server(String s)
    {
        this(s, "default");
    }

    public Server(String s, String s1)
    {
        apiUrl = s;
        name = s1;
    }

    public String getName()
    {
        return name;
    }

    public String getUrl()
    {
        return apiUrl;
    }

    public static final String DEFAULT_NAME = "default";
    private final String apiUrl;
    private final String name;
}
