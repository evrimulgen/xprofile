// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.NoClass;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.impl.WritableObjectId;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.RootNameLookup;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package com.fasterxml.jackson.databind.ser:
//            SerializerCache, SerializerFactory

public abstract class DefaultSerializerProvider extends SerializerProvider
    implements Serializable
{
    public static final class Impl extends DefaultSerializerProvider
    {

        public Impl createInstance(SerializationConfig serializationconfig, SerializerFactory serializerfactory)
        {
            return new Impl(this, serializationconfig, serializerfactory);
        }

        public volatile DefaultSerializerProvider createInstance(SerializationConfig serializationconfig, SerializerFactory serializerfactory)
        {
            return createInstance(serializationconfig, serializerfactory);
        }

        private static final long serialVersionUID = 1L;

        public Impl()
        {
        }

        protected Impl(SerializerProvider serializerprovider, SerializationConfig serializationconfig, SerializerFactory serializerfactory)
        {
            super(serializerprovider, serializationconfig, serializerfactory);
        }
    }


    protected DefaultSerializerProvider()
    {
    }

    protected DefaultSerializerProvider(SerializerProvider serializerprovider, SerializationConfig serializationconfig, SerializerFactory serializerfactory)
    {
        super(serializerprovider, serializationconfig, serializerfactory);
    }

    protected Map _createObjectIdMap()
    {
        if(isEnabled(SerializationFeature.USE_EQUALITY_FOR_OBJECT_ID))
            return new HashMap();
        else
            return new IdentityHashMap();
    }

    protected void _serializeNull(JsonGenerator jsongenerator)
        throws IOException, JsonGenerationException
    {
        JsonSerializer jsonserializer = getDefaultNullValueSerializer();
        try
        {
            jsonserializer.serialize(null, jsongenerator, this);
            return;
        }
        catch(IOException ioexception)
        {
            throw ioexception;
        }
        catch(Exception exception)
        {
            String s = exception.getMessage();
            if(s == null)
                s = (new StringBuilder()).append("[no message for ").append(exception.getClass().getName()).append("]").toString();
            throw new JsonMappingException(s, exception);
        }
    }

    public void acceptJsonFormatVisitor(JavaType javatype, JsonFormatVisitorWrapper jsonformatvisitorwrapper)
        throws JsonMappingException
    {
        if(javatype == null)
        {
            throw new IllegalArgumentException("A class must be provided");
        } else
        {
            jsonformatvisitorwrapper.setProvider(this);
            findValueSerializer(javatype, null).acceptJsonFormatVisitor(jsonformatvisitorwrapper, javatype);
            return;
        }
    }

    public int cachedSerializersCount()
    {
        return _serializerCache.size();
    }

    public abstract DefaultSerializerProvider createInstance(SerializationConfig serializationconfig, SerializerFactory serializerfactory);

    public WritableObjectId findObjectId(Object obj, ObjectIdGenerator objectidgenerator)
    {
        ObjectIdGenerator objectidgenerator1;
        if(_seenObjectIds == null)
        {
            _seenObjectIds = _createObjectIdMap();
        } else
        {
            WritableObjectId writableobjectid = (WritableObjectId)_seenObjectIds.get(obj);
            if(writableobjectid != null)
                return writableobjectid;
        }
        if(_objectIdGenerators != null) goto _L2; else goto _L1
_L1:
        _objectIdGenerators = new ArrayList(8);
        objectidgenerator1 = null;
_L4:
        if(objectidgenerator1 == null)
        {
            objectidgenerator1 = objectidgenerator.newForSerialization(this);
            _objectIdGenerators.add(objectidgenerator1);
        }
        WritableObjectId writableobjectid1 = new WritableObjectId(objectidgenerator1);
        _seenObjectIds.put(obj, writableobjectid1);
        return writableobjectid1;
_L2:
        int i = _objectIdGenerators.size();
        for(int j = 0; j < i; j++)
        {
            objectidgenerator1 = (ObjectIdGenerator)_objectIdGenerators.get(j);
            if(objectidgenerator1.canUseFor(objectidgenerator))
                continue; /* Loop/switch isn't completed */
        }

        objectidgenerator1 = null;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void flushCachedSerializers()
    {
        _serializerCache.flush();
    }

    public JsonSchema generateJsonSchema(Class class1)
        throws JsonMappingException
    {
        if(class1 == null)
            throw new IllegalArgumentException("A class must be provided");
        JsonSerializer jsonserializer = findValueSerializer(class1, null);
        com.fasterxml.jackson.databind.JsonNode jsonnode;
        if(jsonserializer instanceof SchemaAware)
            jsonnode = ((SchemaAware)jsonserializer).getSchema(this, null);
        else
            jsonnode = JsonSchema.getDefaultSchemaNode();
        if(!(jsonnode instanceof ObjectNode))
            throw new IllegalArgumentException((new StringBuilder()).append("Class ").append(class1.getName()).append(" would not be serialized as a JSON object and therefore has no schema").toString());
        else
            return new JsonSchema((ObjectNode)jsonnode);
    }

    public boolean hasSerializerFor(Class class1)
    {
        return hasSerializerFor(class1, null);
    }

    public boolean hasSerializerFor(Class class1, AtomicReference atomicreference)
    {
        JsonSerializer jsonserializer = _findExplicitUntypedSerializer(class1);
        boolean flag;
        flag = false;
        if(jsonserializer != null)
            flag = true;
_L2:
        return flag;
        JsonMappingException jsonmappingexception;
        jsonmappingexception;
        flag = false;
        if(atomicreference != null)
        {
            atomicreference.set(jsonmappingexception);
            return false;
        }
        if(true) goto _L2; else goto _L1
_L1:
        RuntimeException runtimeexception;
        runtimeexception;
        if(atomicreference == null)
        {
            throw runtimeexception;
        } else
        {
            atomicreference.set(runtimeexception);
            return false;
        }
    }

    public void serializeValue(JsonGenerator jsongenerator, Object obj)
        throws IOException, JsonGenerationException
    {
        boolean flag = true;
        if(obj != null) goto _L2; else goto _L1
_L1:
        _serializeNull(jsongenerator);
_L4:
        return;
_L2:
        JsonSerializer jsonserializer = findTypedValueSerializer(obj.getClass(), flag, null);
        String s = _config.getRootName();
        if(s == null)
        {
            flag = _config.isEnabled(SerializationFeature.WRAP_ROOT_VALUE);
            if(flag)
            {
                jsongenerator.writeStartObject();
                jsongenerator.writeFieldName(_rootNames.findRootName(obj.getClass(), _config));
            }
        } else
        if(s.length() == 0)
        {
            flag = false;
        } else
        {
            jsongenerator.writeStartObject();
            jsongenerator.writeFieldName(s);
        }
        try
        {
            jsonserializer.serialize(obj, jsongenerator, this);
        }
        catch(IOException ioexception)
        {
            throw ioexception;
        }
        catch(Exception exception)
        {
            String s1 = exception.getMessage();
            if(s1 == null)
                s1 = (new StringBuilder()).append("[no message for ").append(exception.getClass().getName()).append("]").toString();
            throw new JsonMappingException(s1, exception);
        }
        if(!flag) goto _L4; else goto _L3
_L3:
        jsongenerator.writeEndObject();
        return;
    }

    public void serializeValue(JsonGenerator jsongenerator, Object obj, JavaType javatype)
        throws IOException, JsonGenerationException
    {
        boolean flag = true;
        if(obj != null) goto _L2; else goto _L1
_L1:
        _serializeNull(jsongenerator);
_L4:
        return;
_L2:
        if(!javatype.getRawClass().isAssignableFrom(obj.getClass()))
            _reportIncompatibleRootType(obj, javatype);
        JsonSerializer jsonserializer = findTypedValueSerializer(javatype, flag, null);
        String s = _config.getRootName();
        if(s == null)
        {
            flag = _config.isEnabled(SerializationFeature.WRAP_ROOT_VALUE);
            if(flag)
            {
                jsongenerator.writeStartObject();
                jsongenerator.writeFieldName(_rootNames.findRootName(obj.getClass(), _config));
            }
        } else
        if(s.length() == 0)
        {
            flag = false;
        } else
        {
            jsongenerator.writeStartObject();
            jsongenerator.writeFieldName(s);
        }
        try
        {
            jsonserializer.serialize(obj, jsongenerator, this);
        }
        catch(IOException ioexception)
        {
            throw ioexception;
        }
        catch(Exception exception)
        {
            String s1 = exception.getMessage();
            if(s1 == null)
                s1 = (new StringBuilder()).append("[no message for ").append(exception.getClass().getName()).append("]").toString();
            throw new JsonMappingException(s1, exception);
        }
        if(!flag) goto _L4; else goto _L3
_L3:
        jsongenerator.writeEndObject();
        return;
    }

    public void serializeValue(JsonGenerator jsongenerator, Object obj, JavaType javatype, JsonSerializer jsonserializer)
        throws IOException, JsonGenerationException
    {
        boolean flag = true;
        if(obj != null) goto _L2; else goto _L1
_L1:
        _serializeNull(jsongenerator);
_L4:
        return;
_L2:
        if(javatype != null && !javatype.getRawClass().isAssignableFrom(obj.getClass()))
            _reportIncompatibleRootType(obj, javatype);
        if(jsonserializer == null)
            jsonserializer = findTypedValueSerializer(javatype, flag, null);
        String s = _config.getRootName();
        if(s == null)
        {
            flag = _config.isEnabled(SerializationFeature.WRAP_ROOT_VALUE);
            if(flag)
            {
                jsongenerator.writeStartObject();
                jsongenerator.writeFieldName(_rootNames.findRootName(obj.getClass(), _config));
            }
        } else
        if(s.length() == 0)
        {
            flag = false;
        } else
        {
            jsongenerator.writeStartObject();
            jsongenerator.writeFieldName(s);
        }
        try
        {
            jsonserializer.serialize(obj, jsongenerator, this);
        }
        catch(IOException ioexception)
        {
            throw ioexception;
        }
        catch(Exception exception)
        {
            String s1 = exception.getMessage();
            if(s1 == null)
                s1 = (new StringBuilder()).append("[no message for ").append(exception.getClass().getName()).append("]").toString();
            throw new JsonMappingException(s1, exception);
        }
        if(!flag) goto _L4; else goto _L3
_L3:
        jsongenerator.writeEndObject();
        return;
    }

    public JsonSerializer serializerInstance(Annotated annotated, Object obj)
        throws JsonMappingException
    {
        if(obj != null) goto _L2; else goto _L1
_L1:
        return null;
_L2:
        JsonSerializer jsonserializer1;
        if(obj instanceof JsonSerializer)
        {
            jsonserializer1 = (JsonSerializer)obj;
        } else
        {
            if(!(obj instanceof Class))
                throw new IllegalStateException((new StringBuilder()).append("AnnotationIntrospector returned serializer definition of type ").append(obj.getClass().getName()).append("; expected type JsonSerializer or Class<JsonSerializer> instead").toString());
            Class class1 = (Class)obj;
            if(class1 == com/fasterxml/jackson/databind/JsonSerializer$None || class1 == com/fasterxml/jackson/databind/annotation/NoClass)
                continue; /* Loop/switch isn't completed */
            if(!com/fasterxml/jackson/databind/JsonSerializer.isAssignableFrom(class1))
                throw new IllegalStateException((new StringBuilder()).append("AnnotationIntrospector returned Class ").append(class1.getName()).append("; expected Class<JsonSerializer>").toString());
            HandlerInstantiator handlerinstantiator = _config.getHandlerInstantiator();
            JsonSerializer jsonserializer = null;
            if(handlerinstantiator != null)
                jsonserializer = handlerinstantiator.serializerInstance(_config, annotated, class1);
            if(jsonserializer == null)
                jsonserializer1 = (JsonSerializer)ClassUtil.createInstance(class1, _config.canOverrideAccessModifiers());
            else
                jsonserializer1 = jsonserializer;
        }
        return _handleResolvable(jsonserializer1);
        if(true) goto _L1; else goto _L3
_L3:
    }

    private static final long serialVersionUID = 1L;
    protected transient ArrayList _objectIdGenerators;
    protected transient Map _seenObjectIds;
}
