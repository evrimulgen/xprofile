// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit;


// Referenced classes of package retrofit:
//            Endpoint

public class ChangeableServer
    implements Endpoint
{

    public ChangeableServer(String s)
    {
        url = s;
        name = "default";
    }

    public ChangeableServer(String s, String s1)
    {
        url = s;
        name = s1;
    }

    public String getName()
    {
        return name;
    }

    public String getUrl()
    {
        return url;
    }

    public void update(String s)
    {
        url = s;
    }

    public void update(String s, String s1)
    {
        url = s;
        name = s1;
    }

    private String name;
    private String url;
}
