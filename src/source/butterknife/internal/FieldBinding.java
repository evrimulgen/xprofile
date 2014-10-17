// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package butterknife.internal;


// Referenced classes of package butterknife.internal:
//            Binding

final class FieldBinding
    implements Binding
{

    FieldBinding(String s, String s1, boolean flag)
    {
        name = s;
        type = s1;
        required = flag;
    }

    public String getDescription()
    {
        return (new StringBuilder()).append("field '").append(name).append("'").toString();
    }

    public String getName()
    {
        return name;
    }

    public String getType()
    {
        return type;
    }

    public boolean isRequired()
    {
        return required;
    }

    private final String name;
    private final boolean required;
    private final String type;
}
