// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.SerializableString;
import java.io.Serializable;
import java.util.Arrays;

// Referenced classes of package com.fasterxml.jackson.core.io:
//            CharTypes

public abstract class CharacterEscapes
    implements Serializable
{

    public CharacterEscapes()
    {
    }

    public static int[] standardAsciiEscapesForJSON()
    {
        int ai[] = CharTypes.get7BitOutputEscapes();
        return Arrays.copyOf(ai, ai.length);
    }

    public abstract int[] getEscapeCodesForAscii();

    public abstract SerializableString getEscapeSequence(int i);

    public static final int ESCAPE_CUSTOM = -2;
    public static final int ESCAPE_NONE = 0;
    public static final int ESCAPE_STANDARD = -1;
    private static final long serialVersionUID = 1L;
}
