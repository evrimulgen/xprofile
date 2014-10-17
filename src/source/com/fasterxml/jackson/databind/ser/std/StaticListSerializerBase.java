// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.lang.reflect.Type;
import java.util.Collection;

// Referenced classes of package com.fasterxml.jackson.databind.ser.std:
//            StdSerializer

public abstract class StaticListSerializerBase extends StdSerializer
{

    protected StaticListSerializerBase(Class class1)
    {
        super(class1, false);
    }

    protected abstract void acceptContentVisitor(JsonArrayFormatVisitor jsonarrayformatvisitor)
        throws JsonMappingException;

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonformatvisitorwrapper, JavaType javatype)
        throws JsonMappingException
    {
        acceptContentVisitor(jsonformatvisitorwrapper.expectArrayFormat(javatype));
    }

    protected abstract JsonNode contentSchema();

    public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
    {
        ObjectNode objectnode = createSchemaNode("array", true);
        objectnode.put("items", contentSchema());
        return objectnode;
    }

    public volatile boolean isEmpty(Object obj)
    {
        return isEmpty((Collection)obj);
    }

    public boolean isEmpty(Collection collection)
    {
        return collection == null || collection.size() == 0;
    }
}
