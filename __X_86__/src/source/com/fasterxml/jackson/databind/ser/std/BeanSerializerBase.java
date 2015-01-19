// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.*;
import com.fasterxml.jackson.databind.ser.impl.*;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;

// Referenced classes of package com.fasterxml.jackson.databind.ser.std:
//            StdSerializer, StdDelegatingSerializer

public abstract class BeanSerializerBase extends StdSerializer
    implements ContextualSerializer, ResolvableSerializer, JsonFormatVisitable, SchemaAware
{

    protected BeanSerializerBase(JavaType javatype, BeanSerializerBuilder beanserializerbuilder, BeanPropertyWriter abeanpropertywriter[], BeanPropertyWriter abeanpropertywriter1[])
    {
        super(javatype);
        _props = abeanpropertywriter;
        _filteredProps = abeanpropertywriter1;
        if(beanserializerbuilder == null)
        {
            _typeId = null;
            _anyGetterWriter = null;
            _propertyFilterId = null;
            _objectIdWriter = null;
            _serializationShape = null;
            return;
        }
        _typeId = beanserializerbuilder.getTypeId();
        _anyGetterWriter = beanserializerbuilder.getAnyGetter();
        _propertyFilterId = beanserializerbuilder.getFilterId();
        _objectIdWriter = beanserializerbuilder.getObjectIdWriter();
        com.fasterxml.jackson.annotation.JsonFormat.Value value = beanserializerbuilder.getBeanDescription().findExpectedFormat(null);
        com.fasterxml.jackson.annotation.JsonFormat.Shape shape = null;
        if(value != null)
            shape = value.getShape();
        _serializationShape = shape;
    }

    protected BeanSerializerBase(BeanSerializerBase beanserializerbase)
    {
        this(beanserializerbase, beanserializerbase._props, beanserializerbase._filteredProps);
    }

    protected BeanSerializerBase(BeanSerializerBase beanserializerbase, ObjectIdWriter objectidwriter)
    {
        this(beanserializerbase, objectidwriter, beanserializerbase._propertyFilterId);
    }

    protected BeanSerializerBase(BeanSerializerBase beanserializerbase, ObjectIdWriter objectidwriter, Object obj)
    {
        super(beanserializerbase._handledType);
        _props = beanserializerbase._props;
        _filteredProps = beanserializerbase._filteredProps;
        _typeId = beanserializerbase._typeId;
        _anyGetterWriter = beanserializerbase._anyGetterWriter;
        _objectIdWriter = objectidwriter;
        _propertyFilterId = obj;
        _serializationShape = beanserializerbase._serializationShape;
    }

    protected BeanSerializerBase(BeanSerializerBase beanserializerbase, NameTransformer nametransformer)
    {
        this(beanserializerbase, rename(beanserializerbase._props, nametransformer), rename(beanserializerbase._filteredProps, nametransformer));
    }

    public BeanSerializerBase(BeanSerializerBase beanserializerbase, BeanPropertyWriter abeanpropertywriter[], BeanPropertyWriter abeanpropertywriter1[])
    {
        super(beanserializerbase._handledType);
        _props = abeanpropertywriter;
        _filteredProps = abeanpropertywriter1;
        _typeId = beanserializerbase._typeId;
        _anyGetterWriter = beanserializerbase._anyGetterWriter;
        _objectIdWriter = beanserializerbase._objectIdWriter;
        _propertyFilterId = beanserializerbase._propertyFilterId;
        _serializationShape = beanserializerbase._serializationShape;
    }

    protected BeanSerializerBase(BeanSerializerBase beanserializerbase, String as[])
    {
        super(beanserializerbase._handledType);
        HashSet hashset = ArrayBuilders.arrayToSet(as);
        BeanPropertyWriter abeanpropertywriter[] = beanserializerbase._props;
        BeanPropertyWriter abeanpropertywriter1[] = beanserializerbase._filteredProps;
        int i = abeanpropertywriter.length;
        ArrayList arraylist = new ArrayList(i);
        ArrayList arraylist1;
        int j;
        if(abeanpropertywriter1 == null)
            arraylist1 = null;
        else
            arraylist1 = new ArrayList(i);
        j = 0;
        while(j < i) 
        {
            BeanPropertyWriter beanpropertywriter = abeanpropertywriter[j];
            if(!hashset.contains(beanpropertywriter.getName()))
            {
                arraylist.add(beanpropertywriter);
                if(abeanpropertywriter1 != null)
                    arraylist1.add(abeanpropertywriter1[j]);
            }
            j++;
        }
        _props = (BeanPropertyWriter[])arraylist.toArray(new BeanPropertyWriter[arraylist.size()]);
        BeanPropertyWriter abeanpropertywriter2[] = null;
        if(arraylist1 != null)
            abeanpropertywriter2 = (BeanPropertyWriter[])arraylist1.toArray(new BeanPropertyWriter[arraylist1.size()]);
        _filteredProps = abeanpropertywriter2;
        _typeId = beanserializerbase._typeId;
        _anyGetterWriter = beanserializerbase._anyGetterWriter;
        _objectIdWriter = beanserializerbase._objectIdWriter;
        _propertyFilterId = beanserializerbase._propertyFilterId;
        _serializationShape = beanserializerbase._serializationShape;
    }

    private final String _customTypeId(Object obj)
    {
        Object obj1 = _typeId.getValue(obj);
        if(obj1 == null)
            return "";
        if(obj1 instanceof String)
            return (String)obj1;
        else
            return obj1.toString();
    }

    private static final BeanPropertyWriter[] rename(BeanPropertyWriter abeanpropertywriter[], NameTransformer nametransformer)
    {
        if(abeanpropertywriter == null || abeanpropertywriter.length == 0 || nametransformer == null || nametransformer == NameTransformer.NOP)
            return abeanpropertywriter;
        int i = abeanpropertywriter.length;
        BeanPropertyWriter abeanpropertywriter1[] = new BeanPropertyWriter[i];
        for(int j = 0; j < i; j++)
        {
            BeanPropertyWriter beanpropertywriter = abeanpropertywriter[j];
            if(beanpropertywriter != null)
                abeanpropertywriter1[j] = beanpropertywriter.rename(nametransformer);
        }

        return abeanpropertywriter1;
    }

    protected final void _serializeWithObjectId(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonGenerationException
    {
        ObjectIdWriter objectidwriter = _objectIdWriter;
        WritableObjectId writableobjectid = serializerprovider.findObjectId(obj, objectidwriter.generator);
        if(writableobjectid.writeAsId(jsongenerator, serializerprovider, objectidwriter))
            return;
        Object obj1 = writableobjectid.generateId(obj);
        if(objectidwriter.alwaysAsId)
        {
            objectidwriter.serializer.serialize(obj1, jsongenerator, serializerprovider);
            return;
        }
        String s;
        if(_typeId == null)
            s = null;
        else
            s = _customTypeId(obj);
        if(s == null)
            typeserializer.writeTypePrefixForObject(obj, jsongenerator);
        else
            typeserializer.writeCustomTypePrefixForObject(obj, jsongenerator, s);
        writableobjectid.writeAsField(jsongenerator, serializerprovider, objectidwriter);
        if(_propertyFilterId != null)
            serializeFieldsFiltered(obj, jsongenerator, serializerprovider);
        else
            serializeFields(obj, jsongenerator, serializerprovider);
        if(s == null)
        {
            typeserializer.writeTypeSuffixForObject(obj, jsongenerator);
            return;
        } else
        {
            typeserializer.writeCustomTypeSuffixForObject(obj, jsongenerator, s);
            return;
        }
    }

    protected final void _serializeWithObjectId(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, boolean flag)
        throws IOException, JsonGenerationException
    {
        ObjectIdWriter objectidwriter = _objectIdWriter;
        WritableObjectId writableobjectid = serializerprovider.findObjectId(obj, objectidwriter.generator);
        if(!writableobjectid.writeAsId(jsongenerator, serializerprovider, objectidwriter))
        {
            Object obj1 = writableobjectid.generateId(obj);
            if(objectidwriter.alwaysAsId)
            {
                objectidwriter.serializer.serialize(obj1, jsongenerator, serializerprovider);
                return;
            }
            if(flag)
                jsongenerator.writeStartObject();
            writableobjectid.writeAsField(jsongenerator, serializerprovider, objectidwriter);
            if(_propertyFilterId != null)
                serializeFieldsFiltered(obj, jsongenerator, serializerprovider);
            else
                serializeFields(obj, jsongenerator, serializerprovider);
            if(flag)
            {
                jsongenerator.writeEndObject();
                return;
            }
        }
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonformatvisitorwrapper, JavaType javatype)
        throws JsonMappingException
    {
        if(jsonformatvisitorwrapper != null) goto _L2; else goto _L1
_L1:
        com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor jsonobjectformatvisitor;
        return;
_L2:
        int i;
        if((jsonobjectformatvisitor = jsonformatvisitorwrapper.expectObjectFormat(javatype)) == null)
            continue; /* Loop/switch isn't completed */
        Object obj = _propertyFilterId;
        i = 0;
        if(obj == null)
            break; /* Loop/switch isn't completed */
        PropertyFilter propertyfilter = findPropertyFilter(jsonformatvisitorwrapper.getProvider(), _propertyFilterId, null);
        while(i < _props.length) 
        {
            propertyfilter.depositSchemaProperty(_props[i], jsonobjectformatvisitor, jsonformatvisitorwrapper.getProvider());
            i++;
        }
        if(true) goto _L1; else goto _L3
_L3:
        while(i < _props.length) 
        {
            _props[i].depositSchemaProperty(jsonobjectformatvisitor);
            i++;
        }
        if(true) goto _L1; else goto _L4
_L4:
    }

    protected abstract BeanSerializerBase asArraySerializer();

    public JsonSerializer createContextual(SerializerProvider serializerprovider, BeanProperty beanproperty)
        throws JsonMappingException
    {
        ObjectIdWriter objectidwriter;
        AnnotationIntrospector annotationintrospector;
        Object obj;
        ObjectIdWriter objectidwriter1;
        Object obj1;
        String as[];
        ObjectIdInfo objectidinfo;
        objectidwriter = _objectIdWriter;
        annotationintrospector = serializerprovider.getAnnotationIntrospector();
        com.fasterxml.jackson.annotation.JsonFormat.Value value;
        ObjectIdWriter objectidwriter2;
        Object obj2;
        ObjectIdInfo objectidinfo2;
        if(beanproperty == null || annotationintrospector == null)
            obj = null;
        else
            obj = beanproperty.getMember();
        if(obj == null)
            break MISSING_BLOCK_LABEL_582;
        as = annotationintrospector.findPropertiesToIgnore(((com.fasterxml.jackson.databind.introspect.Annotated) (obj)));
        objectidinfo = annotationintrospector.findObjectIdInfo(((com.fasterxml.jackson.databind.introspect.Annotated) (obj)));
        if(objectidinfo != null) goto _L2; else goto _L1
_L1:
        if(objectidwriter != null)
        {
            objectidinfo2 = annotationintrospector.findObjectReferenceInfo(((com.fasterxml.jackson.databind.introspect.Annotated) (obj)), new ObjectIdInfo(NAME_FOR_OBJECT_REF, null, null));
            objectidwriter = _objectIdWriter.withAlwaysAsId(objectidinfo2.getAlwaysAsId());
        }
_L9:
        obj2 = annotationintrospector.findFilterId(((com.fasterxml.jackson.databind.introspect.Annotated) (obj)));
        BeanSerializerBase beanserializerbase;
        com.fasterxml.jackson.annotation.JsonFormat.Shape shape;
        ObjectIdInfo objectidinfo1;
        Class class1;
        JavaType javatype;
        JavaType javatype1;
        ObjectIdGenerator objectidgenerator;
        String s;
        int i;
        int j;
        BeanPropertyWriter beanpropertywriter;
        JavaType javatype2;
        PropertyBasedObjectIdGenerator propertybasedobjectidgenerator;
        BeanPropertyWriter beanpropertywriter1;
        if(obj2 != null && (_propertyFilterId == null || !obj2.equals(_propertyFilterId)))
        {
            objectidwriter1 = objectidwriter;
            obj1 = obj2;
        } else
        {
            objectidwriter1 = objectidwriter;
            obj1 = null;
        }
_L13:
        if(objectidwriter1 == null) goto _L4; else goto _L3
_L3:
        objectidwriter2 = objectidwriter1.withSerializer(serializerprovider.findValueSerializer(objectidwriter1.idType, beanproperty));
        if(objectidwriter2 == _objectIdWriter) goto _L4; else goto _L5
_L5:
        beanserializerbase = withObjectIdWriter(objectidwriter2);
_L12:
        if(as != null && as.length != 0)
            beanserializerbase = beanserializerbase.withIgnorals(as);
        if(obj1 != null)
            beanserializerbase = beanserializerbase.withFilterId(obj1);
        if(obj == null) goto _L7; else goto _L6
_L6:
        value = annotationintrospector.findFormat(((com.fasterxml.jackson.databind.introspect.Annotated) (obj)));
        if(value == null) goto _L7; else goto _L8
_L8:
        shape = value.getShape();
_L11:
        if(shape == null)
            shape = _serializationShape;
        if(shape == com.fasterxml.jackson.annotation.JsonFormat.Shape.ARRAY)
            return beanserializerbase.asArraySerializer();
        else
            return beanserializerbase;
_L2:
        objectidinfo1 = annotationintrospector.findObjectReferenceInfo(((com.fasterxml.jackson.databind.introspect.Annotated) (obj)), objectidinfo);
        class1 = objectidinfo1.getGeneratorType();
        javatype = serializerprovider.constructType(class1);
        javatype1 = serializerprovider.getTypeFactory().findTypeParameters(javatype, com/fasterxml/jackson/annotation/ObjectIdGenerator)[0];
        if(class1 != com/fasterxml/jackson/annotation/ObjectIdGenerators$PropertyGenerator)
            break MISSING_BLOCK_LABEL_527;
        s = objectidinfo1.getPropertyName().getSimpleName();
        i = _props.length;
        j = 0;
_L10:
label0:
        {
            if(j == i)
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid Object Id definition for ").append(_handledType.getName()).append(": can not find property with name '").append(s).append("'").toString());
            beanpropertywriter = _props[j];
            if(!s.equals(beanpropertywriter.getName()))
                break label0;
            if(j > 0)
            {
                System.arraycopy(_props, 0, _props, 1, j);
                _props[0] = beanpropertywriter;
                if(_filteredProps != null)
                {
                    beanpropertywriter1 = _filteredProps[j];
                    System.arraycopy(_filteredProps, 0, _filteredProps, 1, j);
                    _filteredProps[0] = beanpropertywriter1;
                }
            }
            javatype2 = beanpropertywriter.getType();
            propertybasedobjectidgenerator = new PropertyBasedObjectIdGenerator(objectidinfo1, beanpropertywriter);
            objectidwriter = ObjectIdWriter.construct(javatype2, (PropertyName)null, propertybasedobjectidgenerator, objectidinfo1.getAlwaysAsId());
        }
          goto _L9
        j++;
          goto _L10
        objectidgenerator = serializerprovider.objectIdGeneratorInstance(((com.fasterxml.jackson.databind.introspect.Annotated) (obj)), objectidinfo1);
        objectidwriter = ObjectIdWriter.construct(javatype1, objectidinfo1.getPropertyName(), objectidgenerator, objectidinfo1.getAlwaysAsId());
          goto _L9
_L7:
        shape = null;
          goto _L11
_L4:
        beanserializerbase = this;
          goto _L12
        objectidwriter1 = objectidwriter;
        obj1 = null;
        as = null;
          goto _L13
    }

    protected JsonSerializer findConvertingSerializer(SerializerProvider serializerprovider, BeanPropertyWriter beanpropertywriter)
        throws JsonMappingException
    {
        AnnotationIntrospector annotationintrospector = serializerprovider.getAnnotationIntrospector();
        if(annotationintrospector != null)
        {
            Object obj = annotationintrospector.findSerializationConverter(beanpropertywriter.getMember());
            if(obj != null)
            {
                Converter converter = serializerprovider.converterInstance(beanpropertywriter.getMember(), obj);
                JavaType javatype = converter.getOutputType(serializerprovider.getTypeFactory());
                return new StdDelegatingSerializer(converter, javatype, serializerprovider.findValueSerializer(javatype, beanpropertywriter));
            }
        }
        return null;
    }

    public JsonNode getSchema(SerializerProvider serializerprovider, Type type)
        throws JsonMappingException
    {
        ObjectNode objectnode = createSchemaNode("object", true);
        JsonSerializableSchema jsonserializableschema = (JsonSerializableSchema)_handledType.getAnnotation(com/fasterxml/jackson/databind/jsonschema/JsonSerializableSchema);
        if(jsonserializableschema != null)
        {
            String s = jsonserializableschema.id();
            if(s != null && s.length() > 0)
                objectnode.put("id", s);
        }
        ObjectNode objectnode1 = objectnode.objectNode();
        PropertyFilter propertyfilter;
        int i;
        if(_propertyFilterId != null)
            propertyfilter = findPropertyFilter(serializerprovider, _propertyFilterId, null);
        else
            propertyfilter = null;
        i = 0;
        while(i < _props.length) 
        {
            BeanPropertyWriter beanpropertywriter = _props[i];
            if(propertyfilter == null)
                beanpropertywriter.depositSchemaProperty(objectnode1, serializerprovider);
            else
                propertyfilter.depositSchemaProperty(beanpropertywriter, objectnode1, serializerprovider);
            i++;
        }
        objectnode.put("properties", objectnode1);
        return objectnode;
    }

    public void resolve(SerializerProvider serializerprovider)
        throws JsonMappingException
    {
        int i;
        int k;
        BeanPropertyWriter beanpropertywriter;
        int j;
        JsonSerializer jsonserializer;
        BeanPropertyWriter beanpropertywriter2;
        if(_filteredProps == null)
            i = 0;
        else
            i = _filteredProps.length;
        j = _props.length;
        k = 0;
        if(k >= j)
            break MISSING_BLOCK_LABEL_283;
        beanpropertywriter = _props[k];
        if(!beanpropertywriter.willSuppressNulls() && !beanpropertywriter.hasNullSerializer())
        {
            jsonserializer = serializerprovider.findNullValueSerializer(beanpropertywriter);
            if(jsonserializer != null)
            {
                beanpropertywriter.assignNullSerializer(jsonserializer);
                if(k < i)
                {
                    beanpropertywriter2 = _filteredProps[k];
                    if(beanpropertywriter2 != null)
                        beanpropertywriter2.assignNullSerializer(jsonserializer);
                }
            }
        }
        if(!beanpropertywriter.hasSerializer())
            break; /* Loop/switch isn't completed */
_L4:
        k++;
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_18;
_L1:
        Object obj;
        JavaType javatype;
label0:
        {
            obj = findConvertingSerializer(serializerprovider, beanpropertywriter);
            if(obj != null)
                break MISSING_BLOCK_LABEL_246;
            javatype = beanpropertywriter.getSerializationType();
            if(javatype != null)
                break label0;
            javatype = serializerprovider.constructType(beanpropertywriter.getGenericPropertyType());
            if(javatype.isFinal())
                break label0;
            if(javatype.isContainerType() || javatype.containedTypeCount() > 0)
                beanpropertywriter.setNonTrivialBaseType(javatype);
        }
        continue; /* Loop/switch isn't completed */
        obj = serializerprovider.findValueSerializer(javatype, beanpropertywriter);
        if(javatype.isContainerType())
        {
            TypeSerializer typeserializer = (TypeSerializer)javatype.getContentType().getTypeHandler();
            if(typeserializer != null && (obj instanceof ContainerSerializer))
                obj = ((ContainerSerializer)obj).withValueTypeSerializer(typeserializer);
        }
        beanpropertywriter.assignSerializer(((JsonSerializer) (obj)));
        if(k < i)
        {
            BeanPropertyWriter beanpropertywriter1 = _filteredProps[k];
            if(beanpropertywriter1 != null)
                beanpropertywriter1.assignSerializer(((JsonSerializer) (obj)));
        }
        if(true) goto _L4; else goto _L3
_L3:
        if(_anyGetterWriter != null)
            _anyGetterWriter.resolve(serializerprovider);
        return;
    }

    public abstract void serialize(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException;

    protected void serializeFields(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        BeanPropertyWriter abeanpropertywriter[];
        int i;
        int j;
        BeanPropertyWriter beanpropertywriter;
        if(_filteredProps != null && serializerprovider.getActiveView() != null)
            abeanpropertywriter = _filteredProps;
        else
            abeanpropertywriter = _props;
        i = 0;
        try
        {
            j = abeanpropertywriter.length;
        }
        catch(Exception exception)
        {
            String s1;
            if(i == abeanpropertywriter.length)
                s1 = "[anySetter]";
            else
                s1 = abeanpropertywriter[i].getName();
            wrapAndThrow(serializerprovider, exception, obj, s1);
            return;
        }
        catch(StackOverflowError stackoverflowerror)
        {
            JsonMappingException jsonmappingexception = new JsonMappingException("Infinite recursion (StackOverflowError)", stackoverflowerror);
            String s;
            if(i == abeanpropertywriter.length)
                s = "[anySetter]";
            else
                s = abeanpropertywriter[i].getName();
            jsonmappingexception.prependPath(new com.fasterxml.jackson.databind.JsonMappingException.Reference(obj, s));
            throw jsonmappingexception;
        }
        if(i >= j) goto _L2; else goto _L1
_L1:
        beanpropertywriter = abeanpropertywriter[i];
        if(beanpropertywriter == null)
            continue; /* Loop/switch isn't completed */
        beanpropertywriter.serializeAsField(obj, jsongenerator, serializerprovider);
        i++;
        break MISSING_BLOCK_LABEL_28;
_L2:
        if(_anyGetterWriter != null)
            _anyGetterWriter.getAndSerialize(obj, jsongenerator, serializerprovider);
        return;
    }

    protected void serializeFieldsFiltered(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonGenerationException
    {
        BeanPropertyWriter abeanpropertywriter[];
        PropertyFilter propertyfilter;
        if(_filteredProps != null && serializerprovider.getActiveView() != null)
            abeanpropertywriter = _filteredProps;
        else
            abeanpropertywriter = _props;
        propertyfilter = findPropertyFilter(serializerprovider, _propertyFilterId, obj);
        if(propertyfilter != null) goto _L2; else goto _L1
_L1:
        serializeFields(obj, jsongenerator, serializerprovider);
_L4:
        return;
_L2:
        int i = 0;
        int j;
        BeanPropertyWriter beanpropertywriter;
        try
        {
            j = abeanpropertywriter.length;
        }
        catch(Exception exception)
        {
            String s1;
            if(i == abeanpropertywriter.length)
                s1 = "[anySetter]";
            else
                s1 = abeanpropertywriter[i].getName();
            wrapAndThrow(serializerprovider, exception, obj, s1);
            return;
        }
        catch(StackOverflowError stackoverflowerror)
        {
            JsonMappingException jsonmappingexception = new JsonMappingException("Infinite recursion (StackOverflowError)", stackoverflowerror);
            String s;
            if(i == abeanpropertywriter.length)
                s = "[anySetter]";
            else
                s = abeanpropertywriter[i].getName();
            jsonmappingexception.prependPath(new com.fasterxml.jackson.databind.JsonMappingException.Reference(obj, s));
            throw jsonmappingexception;
        }
        for(; i >= j; i++)
            continue; /* Loop/switch isn't completed */

        beanpropertywriter = abeanpropertywriter[i];
        if(beanpropertywriter == null)
            break MISSING_BLOCK_LABEL_215;
        propertyfilter.serializeAsField(obj, jsongenerator, serializerprovider, beanpropertywriter);
        break MISSING_BLOCK_LABEL_215;
        if(_anyGetterWriter == null) goto _L4; else goto _L3
_L3:
        _anyGetterWriter.getAndFilter(obj, jsongenerator, serializerprovider, propertyfilter);
        return;
    }

    public void serializeWithType(Object obj, JsonGenerator jsongenerator, SerializerProvider serializerprovider, TypeSerializer typeserializer)
        throws IOException, JsonGenerationException
    {
        if(_objectIdWriter != null)
        {
            _serializeWithObjectId(obj, jsongenerator, serializerprovider, typeserializer);
            return;
        }
        String s;
        if(_typeId == null)
            s = null;
        else
            s = _customTypeId(obj);
        if(s == null)
            typeserializer.writeTypePrefixForObject(obj, jsongenerator);
        else
            typeserializer.writeCustomTypePrefixForObject(obj, jsongenerator, s);
        if(_propertyFilterId != null)
            serializeFieldsFiltered(obj, jsongenerator, serializerprovider);
        else
            serializeFields(obj, jsongenerator, serializerprovider);
        if(s == null)
        {
            typeserializer.writeTypeSuffixForObject(obj, jsongenerator);
            return;
        } else
        {
            typeserializer.writeCustomTypeSuffixForObject(obj, jsongenerator, s);
            return;
        }
    }

    public boolean usesObjectId()
    {
        return _objectIdWriter != null;
    }

    protected abstract BeanSerializerBase withFilterId(Object obj);

    protected abstract BeanSerializerBase withIgnorals(String as[]);

    public abstract BeanSerializerBase withObjectIdWriter(ObjectIdWriter objectidwriter);

    protected static final PropertyName NAME_FOR_OBJECT_REF = new PropertyName("#object-ref");
    protected static final BeanPropertyWriter NO_PROPS[] = new BeanPropertyWriter[0];
    protected final AnyGetterWriter _anyGetterWriter;
    protected final BeanPropertyWriter _filteredProps[];
    protected final ObjectIdWriter _objectIdWriter;
    protected final Object _propertyFilterId;
    protected final BeanPropertyWriter _props[];
    protected final com.fasterxml.jackson.annotation.JsonFormat.Shape _serializationShape;
    protected final AnnotatedMember _typeId;

}
