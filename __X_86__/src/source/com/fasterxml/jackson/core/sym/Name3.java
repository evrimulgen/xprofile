// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core.sym;


// Referenced classes of package com.fasterxml.jackson.core.sym:
//            Name

public final class Name3 extends Name
{

    Name3(String s, int i, int j, int k, int l)
    {
        super(s, i);
        mQuad1 = j;
        mQuad2 = k;
        mQuad3 = l;
    }

    public boolean equals(int i)
    {
        return false;
    }

    public boolean equals(int i, int j)
    {
        return false;
    }

    public boolean equals(int ai[], int i)
    {
        return i == 3 && ai[0] == mQuad1 && ai[1] == mQuad2 && ai[2] == mQuad3;
    }

    final int mQuad1;
    final int mQuad2;
    final int mQuad3;
}
