// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager.protobuf;

import java.io.IOException;
import java.util.Iterator;

// Referenced classes of package com.google.tagmanager.protobuf:
//            MessageLite, Parser, ByteString, ExtensionRegistryLite

class LazyField
{
    static class LazyEntry
        implements java.util.Map.Entry
    {

        public LazyField getField()
        {
            return (LazyField)entry.getValue();
        }

        public Object getKey()
        {
            return entry.getKey();
        }

        public Object getValue()
        {
            LazyField lazyfield = (LazyField)entry.getValue();
            if(lazyfield == null)
                return null;
            else
                return lazyfield.getValue();
        }

        public Object setValue(Object obj)
        {
            if(!(obj instanceof MessageLite))
                throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
            else
                return ((LazyField)entry.getValue()).setValue((MessageLite)obj);
        }

        private java.util.Map.Entry entry;

        private LazyEntry(java.util.Map.Entry entry1)
        {
            entry = entry1;
        }

    }

    static class LazyIterator
        implements Iterator
    {

        public boolean hasNext()
        {
            return iterator.hasNext();
        }

        public volatile Object next()
        {
            return next();
        }

        public java.util.Map.Entry next()
        {
            Object obj = (java.util.Map.Entry)iterator.next();
            if(((java.util.Map.Entry) (obj)).getValue() instanceof LazyField)
                obj = new LazyEntry(((java.util.Map.Entry) (obj)));
            return ((java.util.Map.Entry) (obj));
        }

        public void remove()
        {
            iterator.remove();
        }

        private Iterator iterator;

        public LazyIterator(Iterator iterator1)
        {
            iterator = iterator1;
        }
    }


    public LazyField(MessageLite messagelite, ExtensionRegistryLite extensionregistrylite, ByteString bytestring)
    {
        isDirty = false;
        defaultInstance = messagelite;
        extensionRegistry = extensionregistrylite;
        bytes = bytestring;
    }

    private void ensureInitialized()
    {
        if(value != null)
            return;
        this;
        JVM INSTR monitorenter ;
        if(value == null)
            break MISSING_BLOCK_LABEL_25;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        try
        {
            if(bytes != null)
                value = (MessageLite)defaultInstance.getParserForType().parseFrom(bytes, extensionRegistry);
        }
        catch(IOException ioexception) { }
        this;
        JVM INSTR monitorexit ;
    }

    public boolean equals(Object obj)
    {
        ensureInitialized();
        return value.equals(obj);
    }

    public int getSerializedSize()
    {
        if(isDirty)
            return value.getSerializedSize();
        else
            return bytes.size();
    }

    public MessageLite getValue()
    {
        ensureInitialized();
        return value;
    }

    public int hashCode()
    {
        ensureInitialized();
        return value.hashCode();
    }

    public MessageLite setValue(MessageLite messagelite)
    {
        MessageLite messagelite1 = value;
        value = messagelite;
        bytes = null;
        isDirty = true;
        return messagelite1;
    }

    public ByteString toByteString()
    {
        if(!isDirty)
            return bytes;
        this;
        JVM INSTR monitorenter ;
        ByteString bytestring1;
        if(isDirty)
            break MISSING_BLOCK_LABEL_35;
        bytestring1 = bytes;
        this;
        JVM INSTR monitorexit ;
        return bytestring1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        ByteString bytestring;
        bytes = value.toByteString();
        isDirty = false;
        bytestring = bytes;
        this;
        JVM INSTR monitorexit ;
        return bytestring;
    }

    public String toString()
    {
        ensureInitialized();
        return value.toString();
    }

    private ByteString bytes;
    private final MessageLite defaultInstance;
    private final ExtensionRegistryLite extensionRegistry;
    private volatile boolean isDirty;
    private volatile MessageLite value;
}
