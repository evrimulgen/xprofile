// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

// Referenced classes of package com.fasterxml.jackson.databind.ser.impl:
//            ObjectIdWriter

public final class WritableObjectId
{

    public WritableObjectId(ObjectIdGenerator objectidgenerator)
    {
        idWritten = false;
        generator = objectidgenerator;
    }

    public Object generateId(Object obj)
    {
        Object obj1 = generator.generateId(obj);
        id = obj1;
        return obj1;
    }

    public void writeAsField(JsonGenerator jsongenerator, SerializerProvider serializerprovider, ObjectIdWriter objectidwriter)
        throws IOException, JsonGenerationException
    {
        idWritten = true;
        if(jsongenerator.canWriteObjectId())
        {
            jsongenerator.writeObjectId(String.valueOf(id));
        } else
        {
            com.fasterxml.jackson.core.io.SerializedString serializedstring = objectidwriter.propertyName;
            if(serializedstring != null)
            {
                jsongenerator.writeFieldName(serializedstring);
                objectidwriter.serializer.serialize(id, jsongenerator, serializerprovider);
                return;
            }
        }
    }

    public boolean writeAsId(JsonGenerator jsongenerator, SerializerProvider serializerprovider, ObjectIdWriter objectidwriter)
        throws IOException, JsonGenerationException
    {
        if(id != null && (idWritten || objectidwriter.alwaysAsId))
        {
            if(jsongenerator.canWriteObjectId())
                jsongenerator.writeObjectRef(String.valueOf(id));
            else
                objectidwriter.serializer.serialize(id, jsongenerator, serializerprovider);
            return true;
        } else
        {
            return false;
        }
    }

    public final ObjectIdGenerator generator;
    public Object id;
    protected boolean idWritten;
}
