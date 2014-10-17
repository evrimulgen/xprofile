// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.EnumResolver;
import java.io.IOException;
import java.lang.reflect.Method;

// Referenced classes of package com.fasterxml.jackson.databind.deser.std:
//            StdScalarDeserializer

public class EnumDeserializer extends StdScalarDeserializer
{
    protected static class FactoryBasedDeserializer extends StdScalarDeserializer
    {

        public Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            Object obj;
            Object obj1;
            if(_inputType == null)
                obj = jsonparser.getText();
            else
            if(_inputType == java/lang/Integer)
                obj = Integer.valueOf(jsonparser.getValueAsInt());
            else
            if(_inputType == java/lang/Long)
                obj = Long.valueOf(jsonparser.getValueAsLong());
            else
                throw deserializationcontext.mappingException(_enumClass);
            try
            {
                obj1 = _factory.invoke(_enumClass, new Object[] {
                    obj
                });
            }
            catch(Exception exception)
            {
                Throwable throwable = ClassUtil.getRootCause(exception);
                if(throwable instanceof IOException)
                    throw (IOException)throwable;
                else
                    throw deserializationcontext.instantiationException(_enumClass, throwable);
            }
            return obj1;
        }

        private static final long serialVersionUID = 0x941930f8f421ec66L;
        protected final Class _enumClass;
        protected final Method _factory;
        protected final Class _inputType;

        public FactoryBasedDeserializer(Class class1, AnnotatedMethod annotatedmethod, Class class2)
        {
            super(java/lang/Enum);
            _enumClass = class1;
            _factory = annotatedmethod.getAnnotated();
            _inputType = class2;
        }
    }


    public EnumDeserializer(EnumResolver enumresolver)
    {
        super(java/lang/Enum);
        _resolver = enumresolver;
    }

    public static JsonDeserializer deserializerForCreator(DeserializationConfig deserializationconfig, Class class1, AnnotatedMethod annotatedmethod)
    {
        Class class2 = annotatedmethod.getRawParameterType(0);
        Object obj;
        if(class2 == java/lang/String)
            obj = null;
        else
        if(class2 == Integer.TYPE || class2 == java/lang/Integer)
            obj = java/lang/Integer;
        else
        if(class2 == Long.TYPE || class2 == java/lang/Long)
            obj = java/lang/Long;
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Parameter #0 type for factory method (").append(annotatedmethod).append(") not suitable, must be java.lang.String or int/Integer/long/Long").toString());
        if(deserializationconfig.canOverrideAccessModifiers())
            ClassUtil.checkAndFixAccess(annotatedmethod.getMember());
        return new FactoryBasedDeserializer(class1, annotatedmethod, ((Class) (obj)));
    }

    public Enum deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        if(jsontoken != JsonToken.VALUE_STRING && jsontoken != JsonToken.FIELD_NAME) goto _L2; else goto _L1
_L1:
        String s;
        Enum enum;
        s = jsonparser.getText();
        enum = _resolver.findEnum(s);
        if(enum != null) goto _L4; else goto _L3
_L3:
        if(!deserializationcontext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) || s.length() != 0 && s.trim().length() != 0) goto _L6; else goto _L5
_L5:
        enum = null;
_L4:
        return enum;
_L6:
        if(!deserializationcontext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL))
            throw deserializationcontext.weirdStringException(s, _resolver.getEnumClass(), (new StringBuilder()).append("value not one of declared Enum instance names: ").append(_resolver.getEnums()).toString());
        continue; /* Loop/switch isn't completed */
_L2:
        if(jsontoken == JsonToken.VALUE_NUMBER_INT)
        {
            if(deserializationcontext.isEnabled(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS))
                throw deserializationcontext.mappingException("Not allowed to deserialize Enum value out of JSON number (disable DeserializationConfig.DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS to allow)");
            int i = jsonparser.getIntValue();
            enum = _resolver.getEnum(i);
            if(enum == null && !deserializationcontext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL))
                throw deserializationcontext.weirdNumberException(Integer.valueOf(i), _resolver.getEnumClass(), (new StringBuilder()).append("index value outside legal index range [0..").append(_resolver.lastValidIndex()).append("]").toString());
        } else
        {
            throw deserializationcontext.mappingException(_resolver.getEnumClass());
        }
        if(true) goto _L4; else goto _L7
_L7:
    }

    public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        return deserialize(jsonparser, deserializationcontext);
    }

    public boolean isCachable()
    {
        return true;
    }

    private static final long serialVersionUID = 0xae36ebf01ba084e2L;
    protected final EnumResolver _resolver;
}
