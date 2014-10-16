// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.File;
import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

// Referenced classes of package com.fasterxml.jackson.databind.deser.std:
//            UUIDDeserializer, InetAddressDeserializer, InetSocketAddressDeserializer, CharsetDeserializer, 
//            ClassDeserializer, StackTraceElementDeserializer, AtomicBooleanDeserializer, ByteBufferDeserializer, 
//            FromStringDeserializer

public class JdkDeserializers
{
    public static class CurrencyDeserializer extends FromStringDeserializer
    {

        protected volatile Object _deserialize(String s, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _deserialize(s, deserializationcontext);
        }

        protected Currency _deserialize(String s, DeserializationContext deserializationcontext)
            throws IllegalArgumentException
        {
            return Currency.getInstance(s);
        }

        public static final CurrencyDeserializer instance = new CurrencyDeserializer();


        public CurrencyDeserializer()
        {
            super(java/util/Currency);
        }
    }

    public static class FileDeserializer extends FromStringDeserializer
    {

        protected File _deserialize(String s, DeserializationContext deserializationcontext)
        {
            return new File(s);
        }

        protected volatile Object _deserialize(String s, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _deserialize(s, deserializationcontext);
        }

        public static final FileDeserializer instance = new FileDeserializer();


        public FileDeserializer()
        {
            super(java/io/File);
        }
    }

    protected static class LocaleDeserializer extends FromStringDeserializer
    {

        protected volatile Object _deserialize(String s, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _deserialize(s, deserializationcontext);
        }

        protected Locale _deserialize(String s, DeserializationContext deserializationcontext)
            throws IOException
        {
            int i = s.indexOf('_');
            if(i < 0)
                return new Locale(s);
            String s1 = s.substring(0, i);
            String s2 = s.substring(i + 1);
            int j = s2.indexOf('_');
            if(j < 0)
                return new Locale(s1, s2);
            else
                return new Locale(s1, s2.substring(0, j), s2.substring(j + 1));
        }

        public static final LocaleDeserializer instance = new LocaleDeserializer();


        public LocaleDeserializer()
        {
            super(java/util/Locale);
        }
    }

    public static class PatternDeserializer extends FromStringDeserializer
    {

        protected volatile Object _deserialize(String s, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _deserialize(s, deserializationcontext);
        }

        protected Pattern _deserialize(String s, DeserializationContext deserializationcontext)
            throws IllegalArgumentException
        {
            return Pattern.compile(s);
        }

        public static final PatternDeserializer instance = new PatternDeserializer();


        public PatternDeserializer()
        {
            super(java/util/regex/Pattern);
        }
    }

    public static class URIDeserializer extends FromStringDeserializer
    {

        protected volatile Object _deserialize(String s, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _deserialize(s, deserializationcontext);
        }

        protected URI _deserialize(String s, DeserializationContext deserializationcontext)
            throws IllegalArgumentException
        {
            return URI.create(s);
        }

        public static final URIDeserializer instance = new URIDeserializer();


        public URIDeserializer()
        {
            super(java/net/URI);
        }
    }

    public static class URLDeserializer extends FromStringDeserializer
    {

        protected volatile Object _deserialize(String s, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _deserialize(s, deserializationcontext);
        }

        protected URL _deserialize(String s, DeserializationContext deserializationcontext)
            throws IOException
        {
            return new URL(s);
        }

        public static final URLDeserializer instance = new URLDeserializer();


        public URLDeserializer()
        {
            super(java/net/URL);
        }
    }


    public JdkDeserializers()
    {
    }

    public static JsonDeserializer find(Class class1, String s)
    {
        if(!_classNames.contains(s))
            return null;
        if(class1 == java/net/URI)
            return URIDeserializer.instance;
        if(class1 == java/net/URL)
            return URLDeserializer.instance;
        if(class1 == java/io/File)
            return FileDeserializer.instance;
        if(class1 == java/util/UUID)
            return UUIDDeserializer.instance;
        if(class1 == java/util/Currency)
            return CurrencyDeserializer.instance;
        if(class1 == java/util/regex/Pattern)
            return PatternDeserializer.instance;
        if(class1 == java/util/Locale)
            return LocaleDeserializer.instance;
        if(class1 == java/net/InetAddress)
            return InetAddressDeserializer.instance;
        if(class1 == java/net/InetSocketAddress)
            return InetSocketAddressDeserializer.instance;
        if(class1 == java/nio/charset/Charset)
            return new CharsetDeserializer();
        if(class1 == java/lang/Class)
            return ClassDeserializer.instance;
        if(class1 == java/lang/StackTraceElement)
            return StackTraceElementDeserializer.instance;
        if(class1 == java/util/concurrent/atomic/AtomicBoolean)
            return AtomicBooleanDeserializer.instance;
        if(class1 == java/nio/ByteBuffer)
            return new ByteBufferDeserializer();
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Internal error: can't find deserializer for ").append(s).toString());
    }

    private static final HashSet _classNames;

    static 
    {
        int i = 0;
        _classNames = new HashSet();
        Class aclass[] = {
            java/util/UUID, java/net/URL, java/net/URI, java/io/File, java/util/Currency, java/util/regex/Pattern, java/util/Locale, java/net/InetAddress, java/net/InetSocketAddress, java/nio/charset/Charset, 
            java/util/concurrent/atomic/AtomicBoolean, java/lang/Class, java/lang/StackTraceElement, java/nio/ByteBuffer
        };
        for(int j = aclass.length; i < j; i++)
        {
            Class class1 = aclass[i];
            _classNames.add(class1.getName());
        }

    }
}
