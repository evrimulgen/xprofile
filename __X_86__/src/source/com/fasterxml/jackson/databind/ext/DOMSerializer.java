// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.lang.reflect.Type;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

public class DOMSerializer extends StdSerializer
{

    public DOMSerializer()
    {
        super(org/w3c/dom/Node);
        DOMImplementationRegistry domimplementationregistry;
        try
        {
            domimplementationregistry = DOMImplementationRegistry.newInstance();
        }
        catch(Exception exception)
        {
            throw new IllegalStateException((new StringBuilder()).append("Could not instantiate DOMImplementationRegistry: ").append(exception.getMessage()).toString(), exception);
        }
        _domImpl = (DOMImplementationLS)domimplementationregistry.getDOMImplementation("LS");
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonformatvisitorwrapper, JavaType javatype)
        throws JsonMappingException
    {
        if(jsonformatvisitorwrapper != null)
            jsonformatvisitorwrapper.expectAnyFormat(javatype);
    }

    public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
    {
        return createSchemaNode("string", true);
    }

    public volatile void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        serialize((Node)obj, jsongenerator, serializerprovider);
    }

    public void serialize(Node node, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        if(_domImpl == null)
        {
            throw new IllegalStateException("Could not find DOM LS");
        } else
        {
            jsongenerator.writeString(_domImpl.createLSSerializer().writeToString(node));
            return;
        }
    }

    protected final DOMImplementationLS _domImpl;
}
