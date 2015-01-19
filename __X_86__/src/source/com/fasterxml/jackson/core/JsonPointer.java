// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.io.NumberInput;

public class JsonPointer
{

    protected JsonPointer()
    {
        _nextSegment = null;
        _matchingPropertyName = "";
        _matchingElementIndex = -1;
        _asString = "";
    }

    protected JsonPointer(String s, String s1, JsonPointer jsonpointer)
    {
        _asString = s;
        _nextSegment = jsonpointer;
        _matchingPropertyName = s1;
        _matchingElementIndex = _parseInt(s1);
    }

    private static void _appendEscape(StringBuilder stringbuilder, char c)
    {
        if(c == '0')
            c = '~';
        else
        if(c == '1')
            c = '/';
        else
            stringbuilder.append('~');
        stringbuilder.append(c);
    }

    private static final int _parseInt(String s)
    {
        int i = s.length();
        if(i != 0) goto _L2; else goto _L1
_L1:
        return -1;
_L2:
        int j = 0;
label0:
        do
        {
label1:
            {
                if(j >= i)
                    break label1;
                int k = j + 1;
                char c = s.charAt(j);
                if(c > '9' || c < '0')
                    break label0;
                j = k + 1;
            }
        } while(true);
        if(true) goto _L1; else goto _L3
_L3:
        return NumberInput.parseInt(s);
    }

    protected static JsonPointer _parseQuotedTail(String s, int i)
    {
        int j = s.length();
        StringBuilder stringbuilder = new StringBuilder(Math.max(16, j));
        if(i > 2)
            stringbuilder.append(s, 1, i - 1);
        int k = i + 1;
        _appendEscape(stringbuilder, s.charAt(i));
        for(int l = k; l < j;)
        {
            char c = s.charAt(l);
            if(c == '/')
                return new JsonPointer(s, stringbuilder.toString(), _parseTail(s.substring(l)));
            l++;
            if(c == '~' && l < j)
            {
                int i1 = l + 1;
                _appendEscape(stringbuilder, s.charAt(l));
                l = i1;
            } else
            {
                stringbuilder.append(c);
            }
        }

        return new JsonPointer(s, stringbuilder.toString(), EMPTY);
    }

    protected static JsonPointer _parseTail(String s)
    {
        int i = s.length();
        int k;
        for(int j = 1; j < i; j = k)
        {
            char c = s.charAt(j);
            if(c == '/')
                return new JsonPointer(s, s.substring(1, j), _parseTail(s.substring(j)));
            k = j + 1;
            if(c == '~' && k < i)
                return _parseQuotedTail(s, k);
        }

        return new JsonPointer(s, s.substring(1), EMPTY);
    }

    public static JsonPointer compile(String s)
        throws IllegalArgumentException
    {
        if(s == null || s.length() == 0)
            return EMPTY;
        if(s.charAt(0) != '/')
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid input: JSON Pointer expression must start with '/': \"").append(s).append("\"").toString());
        else
            return _parseTail(s);
    }

    public static JsonPointer valueOf(String s)
    {
        return compile(s);
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        if(obj == this)
        {
            flag = true;
        } else
        {
            flag = false;
            if(obj != null)
            {
                boolean flag1 = obj instanceof JsonPointer;
                flag = false;
                if(flag1)
                    return _asString.equals(((JsonPointer)obj)._asString);
            }
        }
        return flag;
    }

    public int getMatchingIndex()
    {
        return _matchingElementIndex;
    }

    public String getMatchingProperty()
    {
        return _matchingPropertyName;
    }

    public int hashCode()
    {
        return _asString.hashCode();
    }

    public JsonPointer matchElement(int i)
    {
        if(i != _matchingElementIndex || i < 0)
            return null;
        else
            return _nextSegment;
    }

    public JsonPointer matchProperty(String s)
    {
        if(_nextSegment == null || !_matchingPropertyName.equals(s))
            return null;
        else
            return _nextSegment;
    }

    public boolean matches()
    {
        return _nextSegment == null;
    }

    public boolean mayMatchElement()
    {
        return _matchingElementIndex >= 0;
    }

    public boolean mayMatchProperty()
    {
        return _matchingPropertyName != null;
    }

    public JsonPointer tail()
    {
        return _nextSegment;
    }

    public String toString()
    {
        return _asString;
    }

    protected static final JsonPointer EMPTY = new JsonPointer();
    protected final String _asString;
    protected final int _matchingElementIndex;
    protected final String _matchingPropertyName;
    protected final JsonPointer _nextSegment;

}
