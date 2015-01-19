// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsonFormatVisitors.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Time;

// Referenced classes of package com.fasterxml.jackson.databind.ser.std:
//            StdScalarSerializer

public class SqlTimeSerializer extends StdScalarSerializer
{

    public SqlTimeSerializer()
    {
        super(java/sql/Time);
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonformatvisitorwrapper, JavaType javatype)
        throws JsonMappingException
    {
        JsonStringFormatVisitor jsonstringformatvisitor;
        if(jsonformatvisitorwrapper == null)
            jsonstringformatvisitor = null;
        else
            jsonstringformatvisitor = jsonformatvisitorwrapper.expectStringFormat(javatype);
        if(jsonstringformatvisitor != null)
            jsonstringformatvisitor.format(JsonValueFormat.DATE_TIME);
    }

    public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
    {
        return createSchemaNode("string", true);
    }

    public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        serialize((Time)obj, jsongenerator, serializerprovider);
    }

    public void serialize(Time time, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        jsongenerator.writeString(time.toString());
    }
}
