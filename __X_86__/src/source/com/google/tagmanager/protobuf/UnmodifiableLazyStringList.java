// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager.protobuf;

import java.util.*;

// Referenced classes of package com.google.tagmanager.protobuf:
//            LazyStringList, ByteString

public class UnmodifiableLazyStringList extends AbstractList
    implements LazyStringList, RandomAccess
{

    public UnmodifiableLazyStringList(LazyStringList lazystringlist)
    {
        list = lazystringlist;
    }

    public void add(ByteString bytestring)
    {
        throw new UnsupportedOperationException();
    }

    public void add(byte abyte0[])
    {
        throw new UnsupportedOperationException();
    }

    public boolean addAllByteArray(Collection collection)
    {
        throw new UnsupportedOperationException();
    }

    public boolean addAllByteString(Collection collection)
    {
        throw new UnsupportedOperationException();
    }

    public List asByteArrayList()
    {
        return Collections.unmodifiableList(list.asByteArrayList());
    }

    public volatile Object get(int i)
    {
        return get(i);
    }

    public String get(int i)
    {
        return (String)list.get(i);
    }

    public byte[] getByteArray(int i)
    {
        return list.getByteArray(i);
    }

    public ByteString getByteString(int i)
    {
        return list.getByteString(i);
    }

    public List getUnderlyingElements()
    {
        return list.getUnderlyingElements();
    }

    public Iterator iterator()
    {
        return new Iterator() {

            public boolean hasNext()
            {
                return iter.hasNext();
            }

            public volatile Object next()
            {
                return next();
            }

            public String next()
            {
                return (String)iter.next();
            }

            public void remove()
            {
                throw new UnsupportedOperationException();
            }

            Iterator iter;
            final UnmodifiableLazyStringList this$0;

            
            {
                this$0 = UnmodifiableLazyStringList.this;
                super();
                iter = list.iterator();
            }
        }
;
    }

    public ListIterator listIterator(final int index)
    {
        return new ListIterator() {

            public volatile void add(Object obj)
            {
                add((String)obj);
            }

            public void add(String s)
            {
                throw new UnsupportedOperationException();
            }

            public boolean hasNext()
            {
                return iter.hasNext();
            }

            public boolean hasPrevious()
            {
                return iter.hasPrevious();
            }

            public volatile Object next()
            {
                return next();
            }

            public String next()
            {
                return (String)iter.next();
            }

            public int nextIndex()
            {
                return iter.nextIndex();
            }

            public volatile Object previous()
            {
                return previous();
            }

            public String previous()
            {
                return (String)iter.previous();
            }

            public int previousIndex()
            {
                return iter.previousIndex();
            }

            public void remove()
            {
                throw new UnsupportedOperationException();
            }

            public volatile void set(Object obj)
            {
                set((String)obj);
            }

            public void set(String s)
            {
                throw new UnsupportedOperationException();
            }

            ListIterator iter;
            final UnmodifiableLazyStringList this$0;
            final int val$index;

            
            {
                this$0 = UnmodifiableLazyStringList.this;
                index = i;
                super();
                iter = list.listIterator(index);
            }
        }
;
    }

    public void mergeFrom(LazyStringList lazystringlist)
    {
        throw new UnsupportedOperationException();
    }

    public void set(int i, ByteString bytestring)
    {
        throw new UnsupportedOperationException();
    }

    public void set(int i, byte abyte0[])
    {
        throw new UnsupportedOperationException();
    }

    public int size()
    {
        return list.size();
    }

    private final LazyStringList list;

}
