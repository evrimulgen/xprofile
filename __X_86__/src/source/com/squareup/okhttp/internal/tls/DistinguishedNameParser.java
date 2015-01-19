// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.tls;

import javax.security.auth.x500.X500Principal;

final class DistinguishedNameParser
{

    public DistinguishedNameParser(X500Principal x500principal)
    {
        dn = x500principal.getName("RFC2253");
        length = dn.length();
    }

    private String escapedAV()
    {
        beg = pos;
        end = pos;
        do
        {
label0:
            do
            {
                if(pos >= length)
                    return new String(chars, beg, end - beg);
                switch(chars[pos])
                {
                default:
                    char ac3[] = chars;
                    int l = end;
                    end = l + 1;
                    ac3[l] = chars[pos];
                    pos = 1 + pos;
                    break;

                case 43: // '+'
                case 44: // ','
                case 59: // ';'
                    return new String(chars, beg, end - beg);

                case 92: // '\\'
                    char ac2[] = chars;
                    int k = end;
                    end = k + 1;
                    ac2[k] = getEscaped();
                    pos = 1 + pos;
                    break;

                case 32: // ' '
                    cur = end;
                    pos = 1 + pos;
                    char ac[] = chars;
                    int i = end;
                    end = i + 1;
                    ac[i] = ' ';
                    break label0;
                }
            } while(true);
            for(; pos < length && chars[pos] == ' '; pos = 1 + pos)
            {
                char ac1[] = chars;
                int j = end;
                end = j + 1;
                ac1[j] = ' ';
            }

        } while(pos != length && chars[pos] != ',' && chars[pos] != '+' && chars[pos] != ';');
        return new String(chars, beg, cur - beg);
    }

    private int getByte(int i)
    {
        char c1;
        int k;
        if(i + 1 >= length)
            throw new IllegalStateException((new StringBuilder()).append("Malformed DN: ").append(dn).toString());
        char c = chars[i];
        int j;
        if(c >= '0' && c <= '9')
            j = c - 48;
        else
        if(c >= 'a' && c <= 'f')
            j = c - 87;
        else
        if(c >= 'A' && c <= 'F')
            j = c - 55;
        else
            throw new IllegalStateException((new StringBuilder()).append("Malformed DN: ").append(dn).toString());
_L6:
        c1 = chars[i + 1];
        if(c1 < '0' || c1 > '9') goto _L2; else goto _L1
_L1:
        k = c1 - 48;
_L4:
        return k + (j << 4);
_L2:
        if(c1 >= 'a' && c1 <= 'f')
        {
            k = c1 - 87;
            continue; /* Loop/switch isn't completed */
        }
        if(c1 < 'A' || c1 > 'F')
            break; /* Loop/switch isn't completed */
        k = c1 - 55;
        if(true) goto _L4; else goto _L3
_L3:
        throw new IllegalStateException((new StringBuilder()).append("Malformed DN: ").append(dn).toString());
        if(true) goto _L6; else goto _L5
_L5:
    }

    private char getEscaped()
    {
        pos = 1 + pos;
        if(pos == length)
            throw new IllegalStateException((new StringBuilder()).append("Unexpected end of DN: ").append(dn).toString());
        switch(chars[pos])
        {
        default:
            return getUTF8();

        case 32: // ' '
        case 34: // '"'
        case 35: // '#'
        case 37: // '%'
        case 42: // '*'
        case 43: // '+'
        case 44: // ','
        case 59: // ';'
        case 60: // '<'
        case 61: // '='
        case 62: // '>'
        case 92: // '\\'
        case 95: // '_'
            return chars[pos];
        }
    }

    private char getUTF8()
    {
        char c;
        int i;
        c = '?';
        i = getByte(pos);
        pos = 1 + pos;
        if(i >= 128) goto _L2; else goto _L1
_L1:
        c = (char)i;
_L4:
        return c;
_L2:
        if(i < 192 || i > 247) goto _L4; else goto _L3
_L3:
        int k;
        int j;
        int l;
        int i1;
        if(i <= 223)
        {
            j = 1;
            k = i & 0x1f;
        } else
        if(i <= 239)
        {
            j = 2;
            k = i & 0xf;
        } else
        {
            j = 3;
            k = i & 7;
        }
        l = 0;
        if(l >= j)
            break MISSING_BLOCK_LABEL_197;
        pos = 1 + pos;
        if(pos == length || chars[pos] != '\\') goto _L4; else goto _L5
_L5:
        pos = 1 + pos;
        i1 = getByte(pos);
        pos = 1 + pos;
        if((i1 & 0xc0) != 128) goto _L4; else goto _L6
_L6:
        k = (k << 6) + (i1 & 0x3f);
        l++;
        break MISSING_BLOCK_LABEL_66;
        return (char)k;
    }

    private String hexAV()
    {
        if(4 + pos >= length)
            throw new IllegalStateException((new StringBuilder()).append("Unexpected end of DN: ").append(dn).toString());
        beg = pos;
        pos = 1 + pos;
_L6:
        if(pos != length && chars[pos] != '+' && chars[pos] != ',' && chars[pos] != ';') goto _L2; else goto _L1
_L1:
        end = pos;
_L4:
        int i;
        i = end - beg;
        if(i < 5 || (i & 1) == 0)
            throw new IllegalStateException((new StringBuilder()).append("Unexpected end of DN: ").append(dn).toString());
        break; /* Loop/switch isn't completed */
_L2:
        if(chars[pos] != ' ')
            break; /* Loop/switch isn't completed */
        end = pos;
        pos = 1 + pos;
        while(pos < length && chars[pos] == ' ') 
            pos = 1 + pos;
        if(true) goto _L4; else goto _L3
_L3:
        if(chars[pos] >= 'A' && chars[pos] <= 'F')
        {
            char ac[] = chars;
            int l = pos;
            ac[l] = (char)(32 + ac[l]);
        }
        pos = 1 + pos;
        if(true) goto _L6; else goto _L5
_L5:
        byte abyte0[] = new byte[i / 2];
        int j = 0;
        int k = 1 + beg;
        for(; j < abyte0.length; j++)
        {
            abyte0[j] = (byte)getByte(k);
            k += 2;
        }

        return new String(chars, beg, i);
    }

    private String nextAT()
    {
        for(; pos < length && chars[pos] == ' '; pos = 1 + pos);
        if(pos == length)
            return null;
        beg = pos;
        for(pos = 1 + pos; pos < length && chars[pos] != '=' && chars[pos] != ' '; pos = 1 + pos);
        if(pos >= length)
            throw new IllegalStateException((new StringBuilder()).append("Unexpected end of DN: ").append(dn).toString());
        end = pos;
        if(chars[pos] == ' ')
        {
            for(; pos < length && chars[pos] != '=' && chars[pos] == ' '; pos = 1 + pos);
            if(chars[pos] != '=' || pos == length)
                throw new IllegalStateException((new StringBuilder()).append("Unexpected end of DN: ").append(dn).toString());
        }
        for(pos = 1 + pos; pos < length && chars[pos] == ' '; pos = 1 + pos);
        if(end - beg > 4 && chars[3 + beg] == '.' && (chars[beg] == 'O' || chars[beg] == 'o') && (chars[1 + beg] == 'I' || chars[1 + beg] == 'i') && (chars[2 + beg] == 'D' || chars[2 + beg] == 'd'))
            beg = 4 + beg;
        return new String(chars, beg, end - beg);
    }

    private String quotedAV()
    {
        pos = 1 + pos;
        beg = pos;
        end = beg;
        do
        {
            if(pos == length)
                throw new IllegalStateException((new StringBuilder()).append("Unexpected end of DN: ").append(dn).toString());
            if(chars[pos] == '"')
            {
                for(pos = 1 + pos; pos < length && chars[pos] == ' '; pos = 1 + pos);
                break;
            }
            if(chars[pos] == '\\')
                chars[end] = getEscaped();
            else
                chars[end] = chars[pos];
            pos = 1 + pos;
            end = 1 + end;
        } while(true);
        return new String(chars, beg, end - beg);
    }

    public String findMostSpecific(String s)
    {
        String s1;
        pos = 0;
        beg = 0;
        end = 0;
        cur = 0;
        chars = dn.toCharArray();
        s1 = nextAT();
        if(s1 != null) goto _L2; else goto _L1
_L1:
        String s2 = null;
_L8:
        return s2;
_L2:
        s2 = "";
        if(pos == length)
            return null;
        chars[pos];
        JVM INSTR lookupswitch 5: default 120
    //                   34: 146
    //                   35: 154
    //                   43: 125
    //                   44: 125
    //                   59: 125;
           goto _L3 _L4 _L5 _L6 _L6 _L6
_L3:
        s2 = escapedAV();
_L6:
        if(s.equalsIgnoreCase(s1)) goto _L8; else goto _L7
_L4:
        s2 = quotedAV();
          goto _L6
_L5:
        s2 = hexAV();
          goto _L6
          goto _L8
_L7:
        if(pos >= length)
            return null;
        if(chars[pos] == ',' || chars[pos] == ';' || chars[pos] == '+')
        {
            pos = 1 + pos;
            s1 = nextAT();
            if(s1 == null)
                throw new IllegalStateException((new StringBuilder()).append("Malformed DN: ").append(dn).toString());
        } else
        {
            throw new IllegalStateException((new StringBuilder()).append("Malformed DN: ").append(dn).toString());
        }
          goto _L2
    }

    private int beg;
    private char chars[];
    private int cur;
    private final String dn;
    private int end;
    private final int length;
    private int pos;
}
