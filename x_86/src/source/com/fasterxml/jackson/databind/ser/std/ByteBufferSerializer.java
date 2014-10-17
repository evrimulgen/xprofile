// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.util.ByteBufferBackedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

// Referenced classes of package com.fasterxml.jackson.databind.ser.std:
//            StdScalarSerializer

public class ByteBufferSerializer extends StdScalarSerializer
{

    public ByteBufferSerializer()
    {
        super(java/nio/ByteBuffer);
    }

    public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        serialize((ByteBuffer)obj, jsongenerator, serializerprovider);
    }

    public void serialize(ByteBuffer bytebuffer, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        if(bytebuffer.hasArray())
        {
            jsongenerator.writeBinary(bytebuffer.array(), 0, bytebuffer.limit());
            return;
        }
        ByteBuffer bytebuffer1 = bytebuffer.asReadOnlyBuffer();
        if(bytebuffer1.position() > 0)
            bytebuffer1.rewind();
        ByteBufferBackedInputStream bytebufferbackedinputstream = new ByteBufferBackedInputStream(bytebuffer1);
        jsongenerator.writeBinary(bytebufferbackedinputstream, bytebuffer1.remaining());
        bytebufferbackedinputstream.close();
    }

    public static final ByteBufferSerializer instance = new ByteBufferSerializer();

}
