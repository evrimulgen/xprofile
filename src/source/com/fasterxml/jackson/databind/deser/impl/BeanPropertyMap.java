// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.Serializable;
import java.util.*;

public final class BeanPropertyMap
    implements Iterable, Serializable
{
    private static final class Bucket
        implements Serializable
    {

        private static final long serialVersionUID = 1L;
        public final int index;
        public final String key;
        public final Bucket next;
        public final SettableBeanProperty value;

        public Bucket(Bucket bucket, String s, SettableBeanProperty settablebeanproperty, int i)
        {
            next = bucket;
            key = s;
            value = settablebeanproperty;
            index = i;
        }
    }

    private static final class IteratorImpl
        implements Iterator
    {

        public boolean hasNext()
        {
            return _currentBucket != null;
        }

        public SettableBeanProperty next()
        {
            Bucket bucket = _currentBucket;
            if(bucket == null)
                throw new NoSuchElementException();
            Bucket bucket1;
            Bucket abucket[];
            int i;
            for(bucket1 = bucket.next; bucket1 == null && _nextBucketIndex < _buckets.length; bucket1 = abucket[i])
            {
                abucket = _buckets;
                i = _nextBucketIndex;
                _nextBucketIndex = i + 1;
            }

            _currentBucket = bucket1;
            return bucket.value;
        }

        public volatile Object next()
        {
            return next();
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        private final Bucket _buckets[];
        private Bucket _currentBucket;
        private int _nextBucketIndex;

        public IteratorImpl(Bucket abucket[])
        {
            int i;
            int j;
            _buckets = abucket;
            i = 0;
            j = _buckets.length;
_L3:
            int k;
            Bucket bucket;
            if(i >= j)
                break MISSING_BLOCK_LABEL_63;
            Bucket abucket1[] = _buckets;
            k = i + 1;
            bucket = abucket1[i];
            if(bucket == null) goto _L2; else goto _L1
_L1:
            _currentBucket = bucket;
_L4:
            _nextBucketIndex = k;
            return;
_L2:
            i = k;
              goto _L3
            k = i;
              goto _L4
        }
    }


    public BeanPropertyMap(Collection collection)
    {
        _nextBucketIndex = 0;
        _size = collection.size();
        int i = findSize(_size);
        _hashMask = i - 1;
        Bucket abucket[] = new Bucket[i];
        for(Iterator iterator1 = collection.iterator(); iterator1.hasNext();)
        {
            SettableBeanProperty settablebeanproperty = (SettableBeanProperty)iterator1.next();
            String s = settablebeanproperty.getName();
            int j = s.hashCode() & _hashMask;
            Bucket bucket = abucket[j];
            int k = _nextBucketIndex;
            _nextBucketIndex = k + 1;
            abucket[j] = new Bucket(bucket, s, settablebeanproperty, k);
        }

        _buckets = abucket;
    }

    private BeanPropertyMap(Bucket abucket[], int i, int j)
    {
        _nextBucketIndex = 0;
        _buckets = abucket;
        _size = i;
        _hashMask = -1 + abucket.length;
        _nextBucketIndex = j;
    }

    private SettableBeanProperty _findWithEquals(String s, int i)
    {
        for(Bucket bucket = _buckets[i]; bucket != null; bucket = bucket.next)
            if(s.equals(bucket.key))
                return bucket.value;

        return null;
    }

    private static final int findSize(int i)
    {
        int j;
        int k;
        if(i <= 32)
            j = i + i;
        else
            j = i + (i >> 2);
        for(k = 2; k < j; k += k);
        return k;
    }

    public BeanPropertyMap assignIndexes()
    {
        Bucket abucket[] = _buckets;
        int i = abucket.length;
        int j = 0;
        int k = 0;
        for(; j < i; j++)
        {
            for(Bucket bucket = abucket[j]; bucket != null;)
            {
                SettableBeanProperty settablebeanproperty = bucket.value;
                int l = k + 1;
                settablebeanproperty.assignIndex(k);
                bucket = bucket.next;
                k = l;
            }

        }

        return this;
    }

    public SettableBeanProperty find(int i)
    {
        int j = _buckets.length;
        for(int k = 0; k < j; k++)
        {
            for(Bucket bucket = _buckets[k]; bucket != null; bucket = bucket.next)
                if(bucket.index == i)
                    return bucket.value;

        }

        return null;
    }

    public SettableBeanProperty find(String s)
    {
        if(s == null)
            throw new IllegalArgumentException("Can not pass null property name");
        int i = s.hashCode() & _hashMask;
        Bucket bucket = _buckets[i];
        if(bucket == null)
            return null;
        if(bucket.key == s)
            return bucket.value;
        do
        {
            bucket = bucket.next;
            if(bucket != null)
            {
                if(bucket.key == s)
                    return bucket.value;
            } else
            {
                return _findWithEquals(s, i);
            }
        } while(true);
    }

    public SettableBeanProperty[] getPropertiesInInsertionOrder()
    {
        SettableBeanProperty asettablebeanproperty[] = new SettableBeanProperty[_nextBucketIndex];
        Bucket abucket[] = _buckets;
        int i = abucket.length;
        for(int j = 0; j < i; j++)
        {
            for(Bucket bucket = abucket[j]; bucket != null; bucket = bucket.next)
                asettablebeanproperty[bucket.index] = bucket.value;

        }

        return asettablebeanproperty;
    }

    public Iterator iterator()
    {
        return new IteratorImpl(_buckets);
    }

    public void remove(SettableBeanProperty settablebeanproperty)
    {
        String s = settablebeanproperty.getName();
        int i = s.hashCode() & -1 + _buckets.length;
        Bucket bucket = _buckets[i];
        boolean flag = false;
        Bucket bucket1 = null;
        while(bucket != null) 
        {
            if(!flag && bucket.key.equals(s))
                flag = true;
            else
                bucket1 = new Bucket(bucket1, bucket.key, bucket.value, bucket.index);
            bucket = bucket.next;
        }
        if(!flag)
        {
            throw new NoSuchElementException((new StringBuilder()).append("No entry '").append(settablebeanproperty).append("' found, can't remove").toString());
        } else
        {
            _buckets[i] = bucket1;
            return;
        }
    }

    public BeanPropertyMap renameAll(NameTransformer nametransformer)
    {
        if(nametransformer == null || nametransformer == NameTransformer.NOP)
            return this;
        Iterator iterator1 = iterator();
        ArrayList arraylist = new ArrayList();
        SettableBeanProperty settablebeanproperty1;
        for(; iterator1.hasNext(); arraylist.add(settablebeanproperty1))
        {
            SettableBeanProperty settablebeanproperty = (SettableBeanProperty)iterator1.next();
            settablebeanproperty1 = settablebeanproperty.withSimpleName(nametransformer.transform(settablebeanproperty.getName()));
            JsonDeserializer jsondeserializer = settablebeanproperty1.getValueDeserializer();
            if(jsondeserializer == null)
                continue;
            JsonDeserializer jsondeserializer1 = jsondeserializer.unwrappingDeserializer(nametransformer);
            if(jsondeserializer1 != jsondeserializer)
                settablebeanproperty1 = settablebeanproperty1.withValueDeserializer(jsondeserializer1);
        }

        return new BeanPropertyMap(arraylist);
    }

    public void replace(SettableBeanProperty settablebeanproperty)
    {
        String s = settablebeanproperty.getName();
        int i = s.hashCode() & -1 + _buckets.length;
        Bucket bucket = _buckets[i];
        int j = -1;
        Bucket bucket1 = null;
        while(bucket != null) 
        {
            if(j < 0 && bucket.key.equals(s))
                j = bucket.index;
            else
                bucket1 = new Bucket(bucket1, bucket.key, bucket.value, bucket.index);
            bucket = bucket.next;
        }
        if(j < 0)
        {
            throw new NoSuchElementException((new StringBuilder()).append("No entry '").append(settablebeanproperty).append("' found, can't replace").toString());
        } else
        {
            _buckets[i] = new Bucket(bucket1, s, settablebeanproperty, j);
            return;
        }
    }

    public int size()
    {
        return _size;
    }

    public String toString()
    {
        int i = 0;
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("Properties=[");
        SettableBeanProperty asettablebeanproperty[] = getPropertiesInInsertionOrder();
        int j = asettablebeanproperty.length;
        int k = 0;
        while(k < j) 
        {
            SettableBeanProperty settablebeanproperty = asettablebeanproperty[k];
            if(settablebeanproperty != null)
            {
                int l = i + 1;
                if(i > 0)
                    stringbuilder.append(", ");
                stringbuilder.append(settablebeanproperty.getName());
                stringbuilder.append('(');
                stringbuilder.append(settablebeanproperty.getType());
                stringbuilder.append(')');
                i = l;
            }
            k++;
        }
        stringbuilder.append(']');
        return stringbuilder.toString();
    }

    public BeanPropertyMap withProperty(SettableBeanProperty settablebeanproperty)
    {
        int i = _buckets.length;
        Bucket abucket[] = new Bucket[i];
        System.arraycopy(_buckets, 0, abucket, 0, i);
        String s = settablebeanproperty.getName();
        if(find(settablebeanproperty.getName()) == null)
        {
            int j = s.hashCode() & _hashMask;
            Bucket bucket = abucket[j];
            int k = _nextBucketIndex;
            _nextBucketIndex = k + 1;
            abucket[j] = new Bucket(bucket, s, settablebeanproperty, k);
            return new BeanPropertyMap(abucket, 1 + _size, _nextBucketIndex);
        } else
        {
            BeanPropertyMap beanpropertymap = new BeanPropertyMap(abucket, i, _nextBucketIndex);
            beanpropertymap.replace(settablebeanproperty);
            return beanpropertymap;
        }
    }

    private static final long serialVersionUID = 1L;
    private final Bucket _buckets[];
    private final int _hashMask;
    private int _nextBucketIndex;
    private final int _size;
}
