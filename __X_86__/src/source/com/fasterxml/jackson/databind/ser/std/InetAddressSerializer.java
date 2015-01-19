// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.net.InetAddress;

// Referenced classes of package com.fasterxml.jackson.databind.ser.std:
//            StdScalarSerializer

public class InetAddressSerializer extends StdScalarSerializer
{

    public InetAddressSerializer()
    {
        super(java/net/InetAddress);
    }

    public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        serialize((InetAddress)obj, jsongenerator, serializerprovider);
    }

    public void serialize(InetAddress inetaddress, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        String s = inetaddress.toString().trim();
        int i = s.indexOf('/');
        if(i >= 0)
            if(i == 0)
                s = s.substring(1);
            else
                s = s.substring(0, i);
        jsongenerator.writeString(s);
    }

    public volatile void serializeWithType(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonGenerationException
    {
        serializeWithType((InetAddress)obj, jsongenerator, serializerprovider, typeserializer);
    }

    public void serializeWithType(InetAddress inetaddress, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonGenerationException
    {
        typeserializer.writeTypePrefixForScalar(inetaddress, jsongenerator, java/net/InetAddress);
        serialize(inetaddress, jsongenerator, serializerprovider);
        typeserializer.writeTypeSuffixForScalar(inetaddress, jsongenerator);
    }

    public static final InetAddressSerializer instance = new InetAddressSerializer();

}
