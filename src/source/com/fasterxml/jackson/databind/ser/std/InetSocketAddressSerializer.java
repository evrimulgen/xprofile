// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.net.*;

// Referenced classes of package com.fasterxml.jackson.databind.ser.std:
//            StdScalarSerializer

public class InetSocketAddressSerializer extends StdScalarSerializer
{

    public InetSocketAddressSerializer()
    {
        super(java/net/InetSocketAddress);
    }

    public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        serialize((InetSocketAddress)obj, jsongenerator, serializerprovider);
    }

    public void serialize(InetSocketAddress inetsocketaddress, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        InetAddress inetaddress = inetsocketaddress.getAddress();
        String s;
        int i;
        if(inetaddress == null)
            s = inetsocketaddress.getHostName();
        else
            s = inetaddress.toString().trim();
        i = s.indexOf('/');
        if(i >= 0)
            if(i == 0)
            {
                if(inetaddress instanceof Inet6Address)
                    s = (new StringBuilder()).append("[").append(s.substring(1)).append("]").toString();
                else
                    s = s.substring(1);
            } else
            {
                s = s.substring(0, i);
            }
        jsongenerator.writeString((new StringBuilder()).append(s).append(":").append(inetsocketaddress.getPort()).toString());
    }

    public volatile void serializeWithType(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonGenerationException
    {
        serializeWithType((InetSocketAddress)obj, jsongenerator, serializerprovider, typeserializer);
    }

    public void serializeWithType(InetSocketAddress inetsocketaddress, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonGenerationException
    {
        typeserializer.writeTypePrefixForScalar(inetsocketaddress, jsongenerator, java/net/InetSocketAddress);
        serialize(inetsocketaddress, jsongenerator, serializerprovider);
        typeserializer.writeTypeSuffixForScalar(inetsocketaddress, jsongenerator);
    }

    public static final InetSocketAddressSerializer instance = new InetSocketAddressSerializer();

}
