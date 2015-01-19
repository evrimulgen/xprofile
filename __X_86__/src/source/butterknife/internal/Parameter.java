// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package butterknife.internal;


final class Parameter
{

    Parameter(int i, String s)
    {
        listenerPosition = i;
        type = s;
    }

    int getListenerPosition()
    {
        return listenerPosition;
    }

    String getType()
    {
        return type;
    }

    static final Parameter NONE[] = new Parameter[0];
    private final int listenerPosition;
    private final String type;

}
