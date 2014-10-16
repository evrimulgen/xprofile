// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package nl.qbusict.cupboard;


// Referenced classes of package nl.qbusict.cupboard:
//            Cupboard

public final class CupboardFactory
{

    public CupboardFactory()
    {
    }

    public static Cupboard cupboard()
    {
        return INSTANCE;
    }

    public static Cupboard getInstance()
    {
        return INSTANCE;
    }

    public static void setCupboard(Cupboard cupboard1)
    {
        INSTANCE = cupboard1;
    }

    private static Cupboard INSTANCE = new Cupboard();

}
