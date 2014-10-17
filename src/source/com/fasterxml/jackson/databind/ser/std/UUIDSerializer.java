// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.util.UUID;

// Referenced classes of package com.fasterxml.jackson.databind.ser.std:
//            StdScalarSerializer

public class UUIDSerializer extends StdScalarSerializer
{

    public UUIDSerializer()
    {
        super(java/util/UUID);
    }

    private static final void _appendInt(int i, byte abyte0[], int j)
    {
        abyte0[j] = (byte)(i >> 24);
        int k = j + 1;
        abyte0[k] = (byte)(i >> 16);
        int l = k + 1;
        abyte0[l] = (byte)(i >> 8);
        abyte0[l + 1] = (byte)i;
    }

    private static void _appendInt(int i, char ac[], int j)
    {
        _appendShort(i >> 16, ac, j);
        _appendShort(i, ac, j + 4);
    }

    private static void _appendShort(int i, char ac[], int j)
    {
        ac[j] = HEX_CHARS[0xf & i >> 12];
        int k = j + 1;
        ac[k] = HEX_CHARS[0xf & i >> 8];
        int l = k + 1;
        ac[l] = HEX_CHARS[0xf & i >> 4];
        ac[l + 1] = HEX_CHARS[i & 0xf];
    }

    private static final byte[] _asBytes(UUID uuid)
    {
        byte abyte0[] = new byte[16];
        long l = uuid.getMostSignificantBits();
        long l1 = uuid.getLeastSignificantBits();
        _appendInt((int)(l >> 32), abyte0, 0);
        _appendInt((int)l, abyte0, 4);
        _appendInt((int)(l1 >> 32), abyte0, 8);
        _appendInt((int)l1, abyte0, 12);
        return abyte0;
    }

    public volatile boolean isEmpty(Object obj)
    {
        return isEmpty((UUID)obj);
    }

    public boolean isEmpty(UUID uuid)
    {
        while(uuid == null || uuid.getLeastSignificantBits() == 0L && uuid.getMostSignificantBits() == 0L) 
            return true;
        return false;
    }

    public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        serialize((UUID)obj, jsongenerator, serializerprovider);
    }

    public void serialize(UUID uuid, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        if(jsongenerator.canWriteBinaryNatively() && !(jsongenerator instanceof TokenBuffer))
        {
            jsongenerator.writeBinary(_asBytes(uuid));
            return;
        } else
        {
            char ac[] = new char[36];
            long l = uuid.getMostSignificantBits();
            _appendInt((int)(l >> 32), ac, 0);
            ac[8] = '-';
            int i = (int)l;
            _appendShort(i >>> 16, ac, 9);
            ac[13] = '-';
            _appendShort(i, ac, 14);
            ac[18] = '-';
            long l1 = uuid.getLeastSignificantBits();
            _appendShort((int)(l1 >>> 48), ac, 19);
            ac[23] = '-';
            _appendShort((int)(l1 >>> 32), ac, 24);
            _appendInt((int)l1, ac, 28);
            jsongenerator.writeString(ac, 0, 36);
            return;
        }
    }

    static final char HEX_CHARS[] = "0123456789abcdef".toCharArray();

}
