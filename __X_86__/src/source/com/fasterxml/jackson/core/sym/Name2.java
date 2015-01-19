// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core.sym;


// Referenced classes of package com.fasterxml.jackson.core.sym:
//            Name

public final class Name2 extends Name
{

    Name2(String s, int i, int j, int k)
    {
        super(s, i);
        mQuad1 = j;
        mQuad2 = k;
    }

    public boolean equals(int i)
    {
        return false;
    }

    public boolean equals(int i, int j)
    {
        return i == mQuad1 && j == mQuad2;
    }

    public boolean equals(int ai[], int i)
    {
        return i == 2 && ai[0] == mQuad1 && ai[1] == mQuad2;
    }

    final int mQuad1;
    final int mQuad2;
}
