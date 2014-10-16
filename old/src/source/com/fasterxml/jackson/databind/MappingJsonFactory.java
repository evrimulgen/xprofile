// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.format.InputAccessor;
import com.fasterxml.jackson.core.format.MatchStrength;
import java.io.IOException;

// Referenced classes of package com.fasterxml.jackson.databind:
//            ObjectMapper

public class MappingJsonFactory extends JsonFactory
{

    public MappingJsonFactory()
    {
        this(null);
    }

    public MappingJsonFactory(ObjectMapper objectmapper)
    {
        super(objectmapper);
        if(objectmapper == null)
            setCodec(new ObjectMapper(this));
    }

    public JsonFactory copy()
    {
        _checkInvalidCopy(com/fasterxml/jackson/databind/MappingJsonFactory);
        return new MappingJsonFactory(null);
    }

    public volatile ObjectCodec getCodec()
    {
        return getCodec();
    }

    public final ObjectMapper getCodec()
    {
        return (ObjectMapper)_objectCodec;
    }

    public String getFormatName()
    {
        return "JSON";
    }

    public MatchStrength hasFormat(InputAccessor inputaccessor)
        throws IOException
    {
        if(getClass() == com/fasterxml/jackson/databind/MappingJsonFactory)
            return hasJSONFormat(inputaccessor);
        else
            return null;
    }

    private static final long serialVersionUID = 0xa268216511ccb687L;
}
