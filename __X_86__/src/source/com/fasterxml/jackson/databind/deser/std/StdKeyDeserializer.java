// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.EnumResolver;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;

public abstract class StdKeyDeserializer extends KeyDeserializer
    implements Serializable
{
    static final class BoolKD extends StdKeyDeserializer
    {

        public Boolean _parse(String s, DeserializationContext deserializationcontext)
            throws JsonMappingException
        {
            if("true".equals(s))
                return Boolean.TRUE;
            if("false".equals(s))
                return Boolean.FALSE;
            else
                throw deserializationcontext.weirdKeyException(_keyClass, s, "value not 'true' or 'false'");
        }

        public volatile Object _parse(String s, DeserializationContext deserializationcontext)
            throws Exception
        {
            return _parse(s, deserializationcontext);
        }

        private static final long serialVersionUID = 1L;

        BoolKD()
        {
            super(java/lang/Boolean);
        }
    }

    static final class ByteKD extends StdKeyDeserializer
    {

        public Byte _parse(String s, DeserializationContext deserializationcontext)
            throws JsonMappingException
        {
            int i = _parseInt(s);
            if(i < -128 || i > 255)
                throw deserializationcontext.weirdKeyException(_keyClass, s, "overflow, value can not be represented as 8-bit value");
            else
                return Byte.valueOf((byte)i);
        }

        public volatile Object _parse(String s, DeserializationContext deserializationcontext)
            throws Exception
        {
            return _parse(s, deserializationcontext);
        }

        private static final long serialVersionUID = 1L;

        ByteKD()
        {
            super(java/lang/Byte);
        }
    }

    static final class CalendarKD extends StdKeyDeserializer
    {

        public Object _parse(String s, DeserializationContext deserializationcontext)
            throws IllegalArgumentException, JsonMappingException
        {
            Date date = deserializationcontext.parseDate(s);
            if(date == null)
                return null;
            else
                return deserializationcontext.constructCalendar(date);
        }

        private static final long serialVersionUID = 1L;

        protected CalendarKD()
        {
            super(java/util/Calendar);
        }
    }

    static final class CharKD extends StdKeyDeserializer
    {

        public Character _parse(String s, DeserializationContext deserializationcontext)
            throws JsonMappingException
        {
            if(s.length() == 1)
                return Character.valueOf(s.charAt(0));
            else
                throw deserializationcontext.weirdKeyException(_keyClass, s, "can only convert 1-character Strings");
        }

        public volatile Object _parse(String s, DeserializationContext deserializationcontext)
            throws Exception
        {
            return _parse(s, deserializationcontext);
        }

        private static final long serialVersionUID = 1L;

        CharKD()
        {
            super(java/lang/Character);
        }
    }

    static final class DateKD extends StdKeyDeserializer
    {

        public Object _parse(String s, DeserializationContext deserializationcontext)
            throws IllegalArgumentException, JsonMappingException
        {
            return deserializationcontext.parseDate(s);
        }

        private static final long serialVersionUID = 1L;

        protected DateKD()
        {
            super(java/util/Date);
        }
    }

    static final class DelegatingKD extends KeyDeserializer
        implements Serializable
    {

        public final Object deserializeKey(String s, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            Object obj1;
            if(s == null)
            {
                obj1 = null;
            } else
            {
                Object obj;
                try
                {
                    obj = _delegate.deserialize(deserializationcontext.getParser(), deserializationcontext);
                }
                catch(Exception exception)
                {
                    throw deserializationcontext.weirdKeyException(_keyClass, s, (new StringBuilder()).append("not a valid representation: ").append(exception.getMessage()).toString());
                }
                obj1 = obj;
                if(obj1 == null)
                    throw deserializationcontext.weirdKeyException(_keyClass, s, "not a valid representation");
            }
            return obj1;
        }

        public Class getKeyClass()
        {
            return _keyClass;
        }

        private static final long serialVersionUID = 1L;
        protected final JsonDeserializer _delegate;
        protected final Class _keyClass;

        protected DelegatingKD(Class class1, JsonDeserializer jsondeserializer)
        {
            _keyClass = class1;
            _delegate = jsondeserializer;
        }
    }

    static final class DoubleKD extends StdKeyDeserializer
    {

        public Double _parse(String s, DeserializationContext deserializationcontext)
            throws JsonMappingException
        {
            return Double.valueOf(_parseDouble(s));
        }

        public volatile Object _parse(String s, DeserializationContext deserializationcontext)
            throws Exception
        {
            return _parse(s, deserializationcontext);
        }

        private static final long serialVersionUID = 1L;

        DoubleKD()
        {
            super(java/lang/Double);
        }
    }

    static final class EnumKD extends StdKeyDeserializer
    {

        public Object _parse(String s, DeserializationContext deserializationcontext)
            throws JsonMappingException
        {
            if(_factory == null) goto _L2; else goto _L1
_L1:
            Object obj1 = _factory.call1(s);
            Object obj = obj1;
_L4:
            return obj;
            Exception exception;
            exception;
            ClassUtil.unwrapAndThrowAsIAE(exception);
_L2:
            obj = _resolver.findEnum(s);
            if(obj == null && !deserializationcontext.getConfig().isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL))
                throw deserializationcontext.weirdKeyException(_keyClass, s, "not one of values for Enum class");
            if(true) goto _L4; else goto _L3
_L3:
        }

        private static final long serialVersionUID = 1L;
        protected final AnnotatedMethod _factory;
        protected final EnumResolver _resolver;

        protected EnumKD(EnumResolver enumresolver, AnnotatedMethod annotatedmethod)
        {
            super(enumresolver.getEnumClass());
            _resolver = enumresolver;
            _factory = annotatedmethod;
        }
    }

    static final class FloatKD extends StdKeyDeserializer
    {

        public Float _parse(String s, DeserializationContext deserializationcontext)
            throws JsonMappingException
        {
            return Float.valueOf((float)_parseDouble(s));
        }

        public volatile Object _parse(String s, DeserializationContext deserializationcontext)
            throws Exception
        {
            return _parse(s, deserializationcontext);
        }

        private static final long serialVersionUID = 1L;

        FloatKD()
        {
            super(java/lang/Float);
        }
    }

    static final class IntKD extends StdKeyDeserializer
    {

        public Integer _parse(String s, DeserializationContext deserializationcontext)
            throws JsonMappingException
        {
            return Integer.valueOf(_parseInt(s));
        }

        public volatile Object _parse(String s, DeserializationContext deserializationcontext)
            throws Exception
        {
            return _parse(s, deserializationcontext);
        }

        private static final long serialVersionUID = 1L;

        IntKD()
        {
            super(java/lang/Integer);
        }
    }

    static final class LocaleKD extends StdKeyDeserializer
    {

        protected volatile Object _parse(String s, DeserializationContext deserializationcontext)
            throws Exception
        {
            return _parse(s, deserializationcontext);
        }

        protected Locale _parse(String s, DeserializationContext deserializationcontext)
            throws JsonMappingException
        {
            Locale locale;
            try
            {
                locale = _localeDeserializer._deserialize(s, deserializationcontext);
            }
            catch(IOException ioexception)
            {
                throw deserializationcontext.weirdKeyException(_keyClass, s, "unable to parse key as locale");
            }
            return locale;
        }

        private static final long serialVersionUID = 1L;
        protected JdkDeserializers.LocaleDeserializer _localeDeserializer;

        LocaleKD()
        {
            super(java/util/Locale);
            _localeDeserializer = new JdkDeserializers.LocaleDeserializer();
        }
    }

    static final class LongKD extends StdKeyDeserializer
    {

        public Long _parse(String s, DeserializationContext deserializationcontext)
            throws JsonMappingException
        {
            return Long.valueOf(_parseLong(s));
        }

        public volatile Object _parse(String s, DeserializationContext deserializationcontext)
            throws Exception
        {
            return _parse(s, deserializationcontext);
        }

        private static final long serialVersionUID = 1L;

        LongKD()
        {
            super(java/lang/Long);
        }
    }

    static final class ShortKD extends StdKeyDeserializer
    {

        public volatile Object _parse(String s, DeserializationContext deserializationcontext)
            throws Exception
        {
            return _parse(s, deserializationcontext);
        }

        public Short _parse(String s, DeserializationContext deserializationcontext)
            throws JsonMappingException
        {
            int i = _parseInt(s);
            if(i < -32768 || i > 32767)
                throw deserializationcontext.weirdKeyException(_keyClass, s, "overflow, value can not be represented as 16-bit value");
            else
                return Short.valueOf((short)i);
        }

        private static final long serialVersionUID = 1L;

        ShortKD()
        {
            super(java/lang/Integer);
        }
    }

    static final class StringCtorKeyDeserializer extends StdKeyDeserializer
    {

        public Object _parse(String s, DeserializationContext deserializationcontext)
            throws Exception
        {
            return _ctor.newInstance(new Object[] {
                s
            });
        }

        private static final long serialVersionUID = 1L;
        protected final Constructor _ctor;

        public StringCtorKeyDeserializer(Constructor constructor)
        {
            super(constructor.getDeclaringClass());
            _ctor = constructor;
        }
    }

    static final class StringFactoryKeyDeserializer extends StdKeyDeserializer
    {

        public Object _parse(String s, DeserializationContext deserializationcontext)
            throws Exception
        {
            return _factoryMethod.invoke(null, new Object[] {
                s
            });
        }

        private static final long serialVersionUID = 1L;
        final Method _factoryMethod;

        public StringFactoryKeyDeserializer(Method method)
        {
            super(method.getDeclaringClass());
            _factoryMethod = method;
        }
    }

    static final class StringKD extends StdKeyDeserializer
    {

        public static StringKD forType(Class class1)
        {
            if(class1 == java/lang/String)
                return sString;
            if(class1 == java/lang/Object)
                return sObject;
            else
                return new StringKD(class1);
        }

        public volatile Object _parse(String s, DeserializationContext deserializationcontext)
            throws Exception
        {
            return _parse(s, deserializationcontext);
        }

        public String _parse(String s, DeserializationContext deserializationcontext)
            throws JsonMappingException
        {
            return s;
        }

        private static final StringKD sObject = new StringKD(java/lang/Object);
        private static final StringKD sString = new StringKD(java/lang/String);
        private static final long serialVersionUID = 1L;


        private StringKD(Class class1)
        {
            super(class1);
        }
    }

    static final class UuidKD extends StdKeyDeserializer
    {

        public Object _parse(String s, DeserializationContext deserializationcontext)
            throws IllegalArgumentException, JsonMappingException
        {
            return UUID.fromString(s);
        }

        private static final long serialVersionUID = 1L;

        protected UuidKD()
        {
            super(java/util/UUID);
        }
    }


    protected StdKeyDeserializer(Class class1)
    {
        _keyClass = class1;
    }

    protected abstract Object _parse(String s, DeserializationContext deserializationcontext)
        throws Exception;

    protected double _parseDouble(String s)
        throws IllegalArgumentException
    {
        return NumberInput.parseDouble(s);
    }

    protected int _parseInt(String s)
        throws IllegalArgumentException
    {
        return Integer.parseInt(s);
    }

    protected long _parseLong(String s)
        throws IllegalArgumentException
    {
        return Long.parseLong(s);
    }

    public final Object deserializeKey(String s, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        if(s != null)
        {
            Object obj;
            try
            {
                obj = _parse(s, deserializationcontext);
            }
            catch(Exception exception)
            {
                throw deserializationcontext.weirdKeyException(_keyClass, s, (new StringBuilder()).append("not a valid representation: ").append(exception.getMessage()).toString());
            }
            if(obj != null)
                return obj;
            if(!_keyClass.isEnum() || !deserializationcontext.getConfig().isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL))
                throw deserializationcontext.weirdKeyException(_keyClass, s, "not a valid representation");
        }
        return null;
    }

    public Class getKeyClass()
    {
        return _keyClass;
    }

    private static final long serialVersionUID = 1L;
    protected final Class _keyClass;
}
