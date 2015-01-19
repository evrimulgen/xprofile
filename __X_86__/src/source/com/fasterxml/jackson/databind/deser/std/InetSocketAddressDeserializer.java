// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import java.io.IOException;
import java.net.InetSocketAddress;

// Referenced classes of package com.fasterxml.jackson.databind.deser.std:
//            FromStringDeserializer

public class InetSocketAddressDeserializer extends FromStringDeserializer
{

    public InetSocketAddressDeserializer()
    {
        super(java/net/InetSocketAddress);
    }

    protected volatile Object _deserialize(String s, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return _deserialize(s, deserializationcontext);
    }

    protected InetSocketAddress _deserialize(String s, DeserializationContext deserializationcontext)
        throws IOException
    {
        if(s.startsWith("["))
        {
            int k = s.lastIndexOf(']');
            if(k == -1)
                throw new InvalidFormatException("Bracketed IPv6 address must contain closing bracket.", s, java/net/InetSocketAddress);
            int l = s.indexOf(':', k);
            int i1;
            if(l > -1)
                i1 = Integer.parseInt(s.substring(l + 1));
            else
                i1 = 0;
            return new InetSocketAddress(s.substring(0, k + 1), i1);
        }
        int i = s.indexOf(':');
        if(i != -1 && s.indexOf(':', i + 1) == -1)
        {
            int j = Integer.parseInt(s.substring(i));
            return new InetSocketAddress(s.substring(0, i), j);
        } else
        {
            return new InetSocketAddress(s, 0);
        }
    }

    public static final InetSocketAddressDeserializer instance = new InetSocketAddressDeserializer();
    private static final long serialVersionUID = 1L;

}
