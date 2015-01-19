// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager.protobuf;

import java.util.*;

// Referenced classes of package com.google.tagmanager.protobuf:
//            LazyStringList, UnmodifiableLazyStringList, Internal, ByteString

public class LazyStringArrayList extends AbstractList
    implements LazyStringList, RandomAccess
{
    private static class ByteArrayListView extends AbstractList
    {

        public volatile void add(int i, Object obj)
        {
            add(i, (byte[])obj);
        }

        public void add(int i, byte abyte0[])
        {
            list.add(i, abyte0);
            modCount = 1 + modCount;
        }

        public volatile Object get(int i)
        {
            return get(i);
        }

        public byte[] get(int i)
        {
            Object obj = list.get(i);
            byte abyte0[] = LazyStringArrayList.asByteArray(obj);
            if(abyte0 != obj)
                list.set(i, abyte0);
            return abyte0;
        }

        public volatile Object remove(int i)
        {
            return remove(i);
        }

        public byte[] remove(int i)
        {
            Object obj = list.remove(i);
            modCount = 1 + modCount;
            return LazyStringArrayList.asByteArray(obj);
        }

        public volatile Object set(int i, Object obj)
        {
            return set(i, (byte[])obj);
        }

        public byte[] set(int i, byte abyte0[])
        {
            return LazyStringArrayList.asByteArray(list.set(i, abyte0));
        }

        public int size()
        {
            return list.size();
        }

        private final List list;

        ByteArrayListView(List list1)
        {
            list = list1;
        }
    }


    public LazyStringArrayList()
    {
        list = new ArrayList();
    }

    public LazyStringArrayList(LazyStringList lazystringlist)
    {
        list = new ArrayList(lazystringlist.size());
        addAll(lazystringlist);
    }

    public LazyStringArrayList(List list1)
    {
        list = new ArrayList(list1);
    }

    private static byte[] asByteArray(Object obj)
    {
        if(obj instanceof byte[])
            return (byte[])(byte[])obj;
        if(obj instanceof String)
            return Internal.toByteArray((String)obj);
        else
            return ((ByteString)obj).toByteArray();
    }

    private static ByteString asByteString(Object obj)
    {
        if(obj instanceof ByteString)
            return (ByteString)obj;
        if(obj instanceof String)
            return ByteString.copyFromUtf8((String)obj);
        else
            return ByteString.copyFrom((byte[])(byte[])obj);
    }

    private static String asString(Object obj)
    {
        if(obj instanceof String)
            return (String)obj;
        if(obj instanceof ByteString)
            return ((ByteString)obj).toStringUtf8();
        else
            return Internal.toStringUtf8((byte[])(byte[])obj);
    }

    public volatile void add(int i, Object obj)
    {
        add(i, (String)obj);
    }

    public void add(int i, String s)
    {
        list.add(i, s);
        modCount = 1 + modCount;
    }

    public void add(ByteString bytestring)
    {
        list.add(bytestring);
        modCount = 1 + modCount;
    }

    public void add(byte abyte0[])
    {
        list.add(abyte0);
        modCount = 1 + modCount;
    }

    public boolean addAll(int i, Collection collection)
    {
        Object obj;
        boolean flag;
        if(collection instanceof LazyStringList)
            obj = ((LazyStringList)collection).getUnderlyingElements();
        else
            obj = collection;
        flag = list.addAll(i, ((Collection) (obj)));
        modCount = 1 + modCount;
        return flag;
    }

    public boolean addAll(Collection collection)
    {
        return addAll(size(), collection);
    }

    public boolean addAllByteArray(Collection collection)
    {
        boolean flag = list.addAll(collection);
        modCount = 1 + modCount;
        return flag;
    }

    public boolean addAllByteString(Collection collection)
    {
        boolean flag = list.addAll(collection);
        modCount = 1 + modCount;
        return flag;
    }

    public List asByteArrayList()
    {
        return new ByteArrayListView(list);
    }

    public void clear()
    {
        list.clear();
        modCount = 1 + modCount;
    }

    public volatile Object get(int i)
    {
        return get(i);
    }

    public String get(int i)
    {
        Object obj = list.get(i);
        if(!(obj instanceof String)) goto _L2; else goto _L1
_L1:
        String s = (String)obj;
_L4:
        return s;
_L2:
        if(!(obj instanceof ByteString))
            break; /* Loop/switch isn't completed */
        ByteString bytestring = (ByteString)obj;
        s = bytestring.toStringUtf8();
        if(bytestring.isValidUtf8())
        {
            list.set(i, s);
            return s;
        }
        if(true) goto _L4; else goto _L3
_L3:
        byte abyte0[] = (byte[])(byte[])obj;
        s = Internal.toStringUtf8(abyte0);
        if(Internal.isValidUtf8(abyte0))
        {
            list.set(i, s);
            return s;
        }
        if(true) goto _L4; else goto _L5
_L5:
    }

    public byte[] getByteArray(int i)
    {
        Object obj = list.get(i);
        byte abyte0[] = asByteArray(obj);
        if(abyte0 != obj)
            list.set(i, abyte0);
        return abyte0;
    }

    public ByteString getByteString(int i)
    {
        Object obj = list.get(i);
        ByteString bytestring = asByteString(obj);
        if(bytestring != obj)
            list.set(i, bytestring);
        return bytestring;
    }

    public List getUnderlyingElements()
    {
        return Collections.unmodifiableList(list);
    }

    public void mergeFrom(LazyStringList lazystringlist)
    {
        for(Iterator iterator = lazystringlist.getUnderlyingElements().iterator(); iterator.hasNext();)
        {
            Object obj = iterator.next();
            if(obj instanceof byte[])
            {
                byte abyte0[] = (byte[])(byte[])obj;
                list.add(Arrays.copyOf(abyte0, abyte0.length));
            } else
            {
                list.add(obj);
            }
        }

    }

    public volatile Object remove(int i)
    {
        return remove(i);
    }

    public String remove(int i)
    {
        Object obj = list.remove(i);
        modCount = 1 + modCount;
        return asString(obj);
    }

    public volatile Object set(int i, Object obj)
    {
        return set(i, (String)obj);
    }

    public String set(int i, String s)
    {
        return asString(list.set(i, s));
    }

    public void set(int i, ByteString bytestring)
    {
        list.set(i, bytestring);
    }

    public void set(int i, byte abyte0[])
    {
        list.set(i, abyte0);
    }

    public int size()
    {
        return list.size();
    }

    public static final LazyStringList EMPTY = new UnmodifiableLazyStringList(new LazyStringArrayList());
    private final List list;


}
