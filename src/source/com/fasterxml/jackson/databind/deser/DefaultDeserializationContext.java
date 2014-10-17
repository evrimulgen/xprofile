// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.NoClass;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.util.LinkedHashMap;

// Referenced classes of package com.fasterxml.jackson.databind.deser:
//            ResolvableDeserializer, DeserializerFactory, DeserializerCache

public abstract class DefaultDeserializationContext extends DeserializationContext
    implements Serializable
{
    public static final class Impl extends DefaultDeserializationContext
    {

        public DefaultDeserializationContext createInstance(DeserializationConfig deserializationconfig, JsonParser jsonparser, InjectableValues injectablevalues)
        {
            return new Impl(this, deserializationconfig, jsonparser, injectablevalues);
        }

        public DefaultDeserializationContext with(DeserializerFactory deserializerfactory)
        {
            return new Impl(this, deserializerfactory);
        }

        private static final long serialVersionUID = 1L;

        protected Impl(Impl impl, DeserializationConfig deserializationconfig, JsonParser jsonparser, InjectableValues injectablevalues)
        {
            super(impl, deserializationconfig, jsonparser, injectablevalues);
        }

        protected Impl(Impl impl, DeserializerFactory deserializerfactory)
        {
            super(impl, deserializerfactory);
        }

        public Impl(DeserializerFactory deserializerfactory)
        {
            super(deserializerfactory, null);
        }
    }


    protected DefaultDeserializationContext(DefaultDeserializationContext defaultdeserializationcontext, DeserializationConfig deserializationconfig, JsonParser jsonparser, InjectableValues injectablevalues)
    {
        super(defaultdeserializationcontext, deserializationconfig, jsonparser, injectablevalues);
    }

    protected DefaultDeserializationContext(DefaultDeserializationContext defaultdeserializationcontext, DeserializerFactory deserializerfactory)
    {
        super(defaultdeserializationcontext, deserializerfactory);
    }

    protected DefaultDeserializationContext(DeserializerFactory deserializerfactory, DeserializerCache deserializercache)
    {
        super(deserializerfactory, deserializercache);
    }

    public abstract DefaultDeserializationContext createInstance(DeserializationConfig deserializationconfig, JsonParser jsonparser, InjectableValues injectablevalues);

    public JsonDeserializer deserializerInstance(Annotated annotated, Object obj)
        throws JsonMappingException
    {
        JsonDeserializer jsondeserializer = null;
        if(obj != null) goto _L2; else goto _L1
_L1:
        return jsondeserializer;
_L2:
        if(!(obj instanceof JsonDeserializer))
            break; /* Loop/switch isn't completed */
        jsondeserializer = (JsonDeserializer)obj;
_L6:
        if(jsondeserializer instanceof ResolvableDeserializer)
        {
            ((ResolvableDeserializer)jsondeserializer).resolve(this);
            return jsondeserializer;
        }
        if(true) goto _L1; else goto _L3
_L3:
        Class class1;
        if(!(obj instanceof Class))
            throw new IllegalStateException((new StringBuilder()).append("AnnotationIntrospector returned deserializer definition of type ").append(obj.getClass().getName()).append("; expected type JsonDeserializer or Class<JsonDeserializer> instead").toString());
        class1 = (Class)obj;
        jsondeserializer = null;
        if(class1 == com/fasterxml/jackson/databind/JsonDeserializer$None) goto _L1; else goto _L4
_L4:
        jsondeserializer = null;
        if(class1 == com/fasterxml/jackson/databind/annotation/NoClass) goto _L1; else goto _L5
_L5:
        if(!com/fasterxml/jackson/databind/JsonDeserializer.isAssignableFrom(class1))
            throw new IllegalStateException((new StringBuilder()).append("AnnotationIntrospector returned Class ").append(class1.getName()).append("; expected Class<JsonDeserializer>").toString());
        HandlerInstantiator handlerinstantiator = _config.getHandlerInstantiator();
        jsondeserializer = null;
        if(handlerinstantiator != null)
            jsondeserializer = handlerinstantiator.deserializerInstance(_config, annotated, class1);
        if(jsondeserializer == null)
            jsondeserializer = (JsonDeserializer)ClassUtil.createInstance(class1, _config.canOverrideAccessModifiers());
          goto _L6
    }

    public ReadableObjectId findObjectId(Object obj, ObjectIdGenerator objectidgenerator)
    {
        com.fasterxml.jackson.annotation.ObjectIdGenerator.IdKey idkey = objectidgenerator.key(obj);
        ReadableObjectId readableobjectid1;
        if(_objectIds == null)
        {
            _objectIds = new LinkedHashMap();
        } else
        {
            ReadableObjectId readableobjectid = (ReadableObjectId)_objectIds.get(idkey);
            if(readableobjectid != null)
                return readableobjectid;
        }
        readableobjectid1 = new ReadableObjectId(obj);
        _objectIds.put(idkey, readableobjectid1);
        return readableobjectid1;
    }

    public final KeyDeserializer keyDeserializerInstance(Annotated annotated, Object obj)
        throws JsonMappingException
    {
        KeyDeserializer keydeserializer = null;
        if(obj != null) goto _L2; else goto _L1
_L1:
        return keydeserializer;
_L2:
        if(!(obj instanceof KeyDeserializer))
            break; /* Loop/switch isn't completed */
        keydeserializer = (KeyDeserializer)obj;
_L6:
        if(keydeserializer instanceof ResolvableDeserializer)
        {
            ((ResolvableDeserializer)keydeserializer).resolve(this);
            return keydeserializer;
        }
        if(true) goto _L1; else goto _L3
_L3:
        Class class1;
        if(!(obj instanceof Class))
            throw new IllegalStateException((new StringBuilder()).append("AnnotationIntrospector returned key deserializer definition of type ").append(obj.getClass().getName()).append("; expected type KeyDeserializer or Class<KeyDeserializer> instead").toString());
        class1 = (Class)obj;
        keydeserializer = null;
        if(class1 == com/fasterxml/jackson/databind/KeyDeserializer$None) goto _L1; else goto _L4
_L4:
        keydeserializer = null;
        if(class1 == com/fasterxml/jackson/databind/annotation/NoClass) goto _L1; else goto _L5
_L5:
        if(!com/fasterxml/jackson/databind/KeyDeserializer.isAssignableFrom(class1))
            throw new IllegalStateException((new StringBuilder()).append("AnnotationIntrospector returned Class ").append(class1.getName()).append("; expected Class<KeyDeserializer>").toString());
        HandlerInstantiator handlerinstantiator = _config.getHandlerInstantiator();
        keydeserializer = null;
        if(handlerinstantiator != null)
            keydeserializer = handlerinstantiator.keyDeserializerInstance(_config, annotated, class1);
        if(keydeserializer == null)
            keydeserializer = (KeyDeserializer)ClassUtil.createInstance(class1, _config.canOverrideAccessModifiers());
          goto _L6
    }

    public abstract DefaultDeserializationContext with(DeserializerFactory deserializerfactory);

    private static final long serialVersionUID = 1L;
    protected transient LinkedHashMap _objectIds;
}
