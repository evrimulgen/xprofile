// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager.protobuf;

import java.util.*;

class SmallSortedMap extends AbstractMap
{
    private static class EmptySet
    {

        static Iterable iterable()
        {
            return ITERABLE;
        }

        private static final Iterable ITERABLE = new Iterable() {

            public Iterator iterator()
            {
                return EmptySet.ITERATOR;
            }

        }
;
        private static final Iterator ITERATOR = new Iterator() {

            public boolean hasNext()
            {
                return false;
            }

            public Object next()
            {
                throw new NoSuchElementException();
            }

            public void remove()
            {
                throw new UnsupportedOperationException();
            }

        }
;



        private EmptySet()
        {
        }
    }

    private class Entry
        implements java.util.Map.Entry, Comparable
    {

        private boolean equals(Object obj, Object obj1)
        {
            if(obj == null)
                return obj1 == null;
            else
                return obj.equals(obj1);
        }

        public int compareTo(Entry entry)
        {
            return getKey().compareTo(entry.getKey());
        }

        public volatile int compareTo(Object obj)
        {
            return compareTo((Entry)obj);
        }

        public boolean equals(Object obj)
        {
            if(obj != this)
            {
                if(!(obj instanceof java.util.Map.Entry))
                    return false;
                java.util.Map.Entry entry = (java.util.Map.Entry)obj;
                if(!equals(key, entry.getKey()) || !equals(value, entry.getValue()))
                    return false;
            }
            return true;
        }

        public Comparable getKey()
        {
            return key;
        }

        public volatile Object getKey()
        {
            return getKey();
        }

        public Object getValue()
        {
            return value;
        }

        public int hashCode()
        {
            int i;
            Object obj;
            int j;
            if(key == null)
                i = 0;
            else
                i = key.hashCode();
            obj = value;
            j = 0;
            if(obj != null)
                j = value.hashCode();
            return i ^ j;
        }

        public Object setValue(Object obj)
        {
            checkMutable();
            Object obj1 = value;
            value = obj;
            return obj1;
        }

        public String toString()
        {
            return (new StringBuilder()).append(key).append("=").append(value).toString();
        }

        private final Comparable key;
        final SmallSortedMap this$0;
        private Object value;

        Entry(Comparable comparable, Object obj)
        {
            this$0 = SmallSortedMap.this;
            super();
            key = comparable;
            value = obj;
        }

        Entry(java.util.Map.Entry entry)
        {
            this((Comparable)entry.getKey(), entry.getValue());
        }
    }

    private class EntryIterator
        implements Iterator
    {

        private Iterator getOverflowIterator()
        {
            if(lazyOverflowIterator == null)
                lazyOverflowIterator = overflowEntries.entrySet().iterator();
            return lazyOverflowIterator;
        }

        public boolean hasNext()
        {
            return 1 + pos < entryList.size() || getOverflowIterator().hasNext();
        }

        public volatile Object next()
        {
            return next();
        }

        public java.util.Map.Entry next()
        {
            nextCalledBeforeRemove = true;
            int i = 1 + pos;
            pos = i;
            if(i < entryList.size())
                return (java.util.Map.Entry)entryList.get(pos);
            else
                return (java.util.Map.Entry)getOverflowIterator().next();
        }

        public void remove()
        {
            if(!nextCalledBeforeRemove)
                throw new IllegalStateException("remove() was called before next()");
            nextCalledBeforeRemove = false;
            checkMutable();
            if(pos < entryList.size())
            {
                SmallSortedMap smallsortedmap = SmallSortedMap.this;
                int i = pos;
                pos = i - 1;
                smallsortedmap.removeArrayEntryAt(i);
                return;
            } else
            {
                getOverflowIterator().remove();
                return;
            }
        }

        private Iterator lazyOverflowIterator;
        private boolean nextCalledBeforeRemove;
        private int pos;
        final SmallSortedMap this$0;

        private EntryIterator()
        {
            this$0 = SmallSortedMap.this;
            super();
            pos = -1;
        }

    }

    private class EntrySet extends AbstractSet
    {

        public volatile boolean add(Object obj)
        {
            return add((java.util.Map.Entry)obj);
        }

        public boolean add(java.util.Map.Entry entry)
        {
            if(!contains(entry))
            {
                put((Comparable)entry.getKey(), entry.getValue());
                return true;
            } else
            {
                return false;
            }
        }

        public void clear()
        {
            SmallSortedMap.this.clear();
        }

        public boolean contains(Object obj)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)obj;
            Object obj1 = get(entry.getKey());
            Object obj2 = entry.getValue();
            return obj1 == obj2 || obj1 != null && obj1.equals(obj2);
        }

        public Iterator iterator()
        {
            return new EntryIterator();
        }

        public boolean remove(Object obj)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)obj;
            if(contains(entry))
            {
                SmallSortedMap.this.remove(entry.getKey());
                return true;
            } else
            {
                return false;
            }
        }

        public int size()
        {
            return SmallSortedMap.this.size();
        }

        final SmallSortedMap this$0;

        private EntrySet()
        {
            this$0 = SmallSortedMap.this;
            super();
        }

    }


    private SmallSortedMap(int i)
    {
        maxArraySize = i;
        entryList = Collections.emptyList();
        overflowEntries = Collections.emptyMap();
    }


    private int binarySearchInArray(Comparable comparable)
    {
        int i;
        int j;
        i = -1 + entryList.size();
        j = 0;
        if(i < 0) goto _L2; else goto _L1
_L1:
        int i1 = comparable.compareTo(((Entry)entryList.get(i)).getKey());
        if(i1 <= 0) goto _L4; else goto _L3
_L3:
        int k = -(i + 2);
_L6:
        return k;
_L4:
        j = 0;
        if(i1 == 0)
            return i;
_L2:
        int l;
        for(; j <= i; i = k - 1)
        {
            k = (j + i) / 2;
            l = comparable.compareTo(((Entry)entryList.get(k)).getKey());
            if(l >= 0)
                continue; /* Loop/switch isn't completed */
        }

        break MISSING_BLOCK_LABEL_128;
        if(l <= 0) goto _L6; else goto _L5
_L5:
        j = k + 1;
          goto _L2
        if(true) goto _L6; else goto _L7
_L7:
        return -(j + 1);
    }

    private void checkMutable()
    {
        if(isImmutable)
            throw new UnsupportedOperationException();
        else
            return;
    }

    private void ensureEntryArrayMutable()
    {
        checkMutable();
        if(entryList.isEmpty() && !(entryList instanceof ArrayList))
            entryList = new ArrayList(maxArraySize);
    }

    private SortedMap getOverflowEntriesMutable()
    {
        checkMutable();
        if(overflowEntries.isEmpty() && !(overflowEntries instanceof TreeMap))
            overflowEntries = new TreeMap();
        return (SortedMap)overflowEntries;
    }

    static SmallSortedMap newFieldMap(int i)
    {
        return new SmallSortedMap(i) {

            public void makeImmutable()
            {
                if(!isImmutable())
                {
                    for(int j = 0; j < getNumArrayEntries(); j++)
                    {
                        java.util.Map.Entry entry1 = getArrayEntryAt(j);
                        if(((FieldSet.FieldDescriptorLite)entry1.getKey()).isRepeated())
                            entry1.setValue(Collections.unmodifiableList((List)entry1.getValue()));
                    }

                    Iterator iterator = getOverflowEntries().iterator();
                    do
                    {
                        if(!iterator.hasNext())
                            break;
                        java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                        if(((FieldSet.FieldDescriptorLite)entry.getKey()).isRepeated())
                            entry.setValue(Collections.unmodifiableList((List)entry.getValue()));
                    } while(true);
                }
                makeImmutable();
            }

            public volatile Object put(Object obj, Object obj1)
            {
                return put((FieldSet.FieldDescriptorLite)obj, obj1);
            }

        }
;
    }

    static SmallSortedMap newInstanceForTest(int i)
    {
        return new SmallSortedMap(i);
    }

    private Object removeArrayEntryAt(int i)
    {
        checkMutable();
        Object obj = ((Entry)entryList.remove(i)).getValue();
        if(!overflowEntries.isEmpty())
        {
            Iterator iterator = getOverflowEntriesMutable().entrySet().iterator();
            entryList.add(new Entry((java.util.Map.Entry)iterator.next()));
            iterator.remove();
        }
        return obj;
    }

    public void clear()
    {
        checkMutable();
        if(!entryList.isEmpty())
            entryList.clear();
        if(!overflowEntries.isEmpty())
            overflowEntries.clear();
    }

    public boolean containsKey(Object obj)
    {
        Comparable comparable = (Comparable)obj;
        return binarySearchInArray(comparable) >= 0 || overflowEntries.containsKey(comparable);
    }

    public Set entrySet()
    {
        if(lazyEntrySet == null)
            lazyEntrySet = new EntrySet();
        return lazyEntrySet;
    }

    public Object get(Object obj)
    {
        Comparable comparable = (Comparable)obj;
        int i = binarySearchInArray(comparable);
        if(i >= 0)
            return ((Entry)entryList.get(i)).getValue();
        else
            return overflowEntries.get(comparable);
    }

    public java.util.Map.Entry getArrayEntryAt(int i)
    {
        return (java.util.Map.Entry)entryList.get(i);
    }

    public int getNumArrayEntries()
    {
        return entryList.size();
    }

    public int getNumOverflowEntries()
    {
        return overflowEntries.size();
    }

    public Iterable getOverflowEntries()
    {
        if(overflowEntries.isEmpty())
            return EmptySet.iterable();
        else
            return overflowEntries.entrySet();
    }

    public boolean isImmutable()
    {
        return isImmutable;
    }

    public void makeImmutable()
    {
        if(!isImmutable)
        {
            Map map;
            if(overflowEntries.isEmpty())
                map = Collections.emptyMap();
            else
                map = Collections.unmodifiableMap(overflowEntries);
            overflowEntries = map;
            isImmutable = true;
        }
    }

    public Object put(Comparable comparable, Object obj)
    {
        checkMutable();
        int i = binarySearchInArray(comparable);
        if(i >= 0)
            return ((Entry)entryList.get(i)).setValue(obj);
        ensureEntryArrayMutable();
        int j = -(i + 1);
        if(j >= maxArraySize)
            return getOverflowEntriesMutable().put(comparable, obj);
        if(entryList.size() == maxArraySize)
        {
            Entry entry = (Entry)entryList.remove(-1 + maxArraySize);
            getOverflowEntriesMutable().put(entry.getKey(), entry.getValue());
        }
        entryList.add(j, new Entry(comparable, obj));
        return null;
    }

    public volatile Object put(Object obj, Object obj1)
    {
        return put((Comparable)obj, obj1);
    }

    public Object remove(Object obj)
    {
        checkMutable();
        Comparable comparable = (Comparable)obj;
        int i = binarySearchInArray(comparable);
        if(i >= 0)
            return removeArrayEntryAt(i);
        if(overflowEntries.isEmpty())
            return null;
        else
            return overflowEntries.remove(comparable);
    }

    public int size()
    {
        return entryList.size() + overflowEntries.size();
    }

    private List entryList;
    private boolean isImmutable;
    private volatile EntrySet lazyEntrySet;
    private final int maxArraySize;
    private Map overflowEntries;




}
