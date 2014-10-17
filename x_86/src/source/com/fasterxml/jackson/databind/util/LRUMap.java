// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.util;

import java.io.*;
import java.util.LinkedHashMap;

public class LRUMap extends LinkedHashMap
    implements Serializable
{

    public LRUMap(int i, int j)
    {
        super(i, 0.8F, true);
        _maxEntries = j;
    }

    private void readObject(ObjectInputStream objectinputstream)
        throws IOException
    {
        _jdkSerializeMaxEntries = objectinputstream.readInt();
    }

    private void writeObject(ObjectOutputStream objectoutputstream)
        throws IOException
    {
        objectoutputstream.writeInt(_jdkSerializeMaxEntries);
    }

    protected Object readResolve()
    {
        return new LRUMap(_jdkSerializeMaxEntries, _jdkSerializeMaxEntries);
    }

    protected boolean removeEldestEntry(java.util.Map.Entry entry)
    {
        return size() > _maxEntries;
    }

    private static final long serialVersionUID = 1L;
    protected transient int _jdkSerializeMaxEntries;
    protected final int _maxEntries;
}
