// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package dagger.internal;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class ArrayQueue extends AbstractCollection
    implements Queue, Cloneable, Serializable
{
    private class QueueIterator
        implements Iterator
    {

        public boolean hasNext()
        {
            return cursor != fence;
        }

        public Object next()
        {
            if(cursor == fence)
                throw new NoSuchElementException();
            Object obj = elements[cursor];
            if(tail != fence || obj == null)
            {
                throw new ConcurrentModificationException();
            } else
            {
                lastRet = cursor;
                cursor = 1 + cursor & -1 + elements.length;
                return obj;
            }
        }

        public void remove()
        {
            if(lastRet < 0)
                throw new IllegalStateException();
            if(delete(lastRet))
            {
                cursor = -1 + cursor & -1 + elements.length;
                fence = tail;
            }
            lastRet = -1;
        }

        private int cursor;
        private int fence;
        private int lastRet;
        final ArrayQueue this$0;

        private QueueIterator()
        {
            this$0 = ArrayQueue.this;
            super();
            cursor = head;
            fence = tail;
            lastRet = -1;
        }

    }


    public ArrayQueue()
    {
        elements = new Object[16];
    }

    public ArrayQueue(int i)
    {
        allocateElements(i);
    }

    public ArrayQueue(Collection collection)
    {
        allocateElements(collection.size());
        addAll(collection);
    }

    private void allocateElements(int i)
    {
        int j = 8;
        if(i >= j)
        {
            int k = i | i >>> 1;
            int l = k | k >>> 2;
            int i1 = l | l >>> 4;
            int j1 = i1 | i1 >>> 8;
            j = 1 + (j1 | j1 >>> 16);
            if(j < 0)
                j >>>= 1;
        }
        elements = new Object[j];
    }

    private boolean delete(int i)
    {
        Object aobj[] = elements;
        int j = -1 + aobj.length;
        int k = head;
        int l = tail;
        int i1 = j & i - k;
        int j1 = j & l - i;
        if(i1 >= (j & l - k))
            throw new ConcurrentModificationException();
        if(i1 < j1)
        {
            if(k <= i)
            {
                System.arraycopy(((Object) (aobj)), k, ((Object) (aobj)), k + 1, i1);
            } else
            {
                System.arraycopy(((Object) (aobj)), 0, ((Object) (aobj)), 1, i);
                aobj[0] = aobj[j];
                System.arraycopy(((Object) (aobj)), k, ((Object) (aobj)), k + 1, j - k);
            }
            aobj[k] = null;
            head = j & k + 1;
            return false;
        }
        if(i < l)
        {
            System.arraycopy(((Object) (aobj)), i + 1, ((Object) (aobj)), i, j1);
            tail = l - 1;
        } else
        {
            System.arraycopy(((Object) (aobj)), i + 1, ((Object) (aobj)), i, j - i);
            aobj[j] = aobj[0];
            System.arraycopy(((Object) (aobj)), 1, ((Object) (aobj)), 0, l);
            tail = j & l - 1;
        }
        return true;
    }

    private void doubleCapacity()
    {
        int i = head;
        int j = elements.length;
        int k = j - i;
        int l = j << 1;
        if(l < 0)
        {
            throw new IllegalStateException("Sorry, queue too big");
        } else
        {
            Object aobj[] = new Object[l];
            System.arraycopy(((Object) (elements)), i, ((Object) (aobj)), 0, k);
            System.arraycopy(((Object) (elements)), 0, ((Object) (aobj)), k, i);
            elements = aobj;
            head = 0;
            tail = j;
            return;
        }
    }

    private void readObject(ObjectInputStream objectinputstream)
        throws IOException, ClassNotFoundException
    {
        objectinputstream.defaultReadObject();
        int i = objectinputstream.readInt();
        allocateElements(i);
        head = 0;
        tail = i;
        for(int j = 0; j < i; j++)
            elements[j] = objectinputstream.readObject();

    }

    private void writeObject(ObjectOutputStream objectoutputstream)
        throws IOException
    {
        objectoutputstream.defaultWriteObject();
        objectoutputstream.writeInt(size());
        int i = -1 + elements.length;
        for(int j = head; j != tail; j = i & j + 1)
            objectoutputstream.writeObject(elements[j]);

    }

    public boolean add(Object obj)
    {
        if(obj == null)
            throw new NullPointerException("e == null");
        elements[tail] = obj;
        int i = 1 + tail & -1 + elements.length;
        tail = i;
        if(i == head)
            doubleCapacity();
        return true;
    }

    public void clear()
    {
        int i = head;
        int j = tail;
        if(i != j)
        {
            tail = 0;
            head = 0;
            int k = i;
            int l = -1 + elements.length;
            do
            {
                elements[k] = null;
                k = l & k + 1;
            } while(k != j);
        }
    }

    public ArrayQueue clone()
    {
        ArrayQueue arrayqueue;
        try
        {
            arrayqueue = (ArrayQueue)super.clone();
            Object aobj[] = (Object[])(Object[])Array.newInstance(((Object) (elements)).getClass().getComponentType(), elements.length);
            System.arraycopy(((Object) (elements)), 0, ((Object) (aobj)), 0, elements.length);
            arrayqueue.elements = aobj;
        }
        catch(CloneNotSupportedException clonenotsupportedexception)
        {
            throw new AssertionError();
        }
        return arrayqueue;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public boolean contains(Object obj)
    {
        if(obj != null) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        int i = -1 + elements.length;
        int j = head;
        do
        {
            Object obj1 = elements[j];
            if(obj1 == null)
                continue;
            if(obj.equals(obj1))
                return true;
            j = i & j + 1;
        } while(true);
        if(true) goto _L1; else goto _L3
_L3:
    }

    public Object element()
    {
        Object obj = elements[head];
        if(obj == null)
            throw new NoSuchElementException();
        else
            return obj;
    }

    public boolean isEmpty()
    {
        return head == tail;
    }

    public Iterator iterator()
    {
        return new QueueIterator();
    }

    public boolean offer(Object obj)
    {
        return add(obj);
    }

    public Object peek()
    {
        return elements[head];
    }

    public Object poll()
    {
        int i = head;
        Object obj = elements[i];
        if(obj == null)
        {
            return null;
        } else
        {
            elements[i] = null;
            head = i + 1 & -1 + elements.length;
            return obj;
        }
    }

    public Object remove()
    {
        Object obj = poll();
        if(obj == null)
            throw new NoSuchElementException();
        else
            return obj;
    }

    public boolean remove(Object obj)
    {
        if(obj != null) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        int i = -1 + elements.length;
        int j = head;
        do
        {
            Object obj1 = elements[j];
            if(obj1 == null)
                continue;
            if(obj.equals(obj1))
            {
                delete(j);
                return true;
            }
            j = i & j + 1;
        } while(true);
        if(true) goto _L1; else goto _L3
_L3:
    }

    public int size()
    {
        return tail - head & -1 + elements.length;
    }

    public Object[] toArray()
    {
        return toArray(new Object[size()]);
    }

    public Object[] toArray(Object aobj[])
    {
        int i;
        i = size();
        if(aobj.length < i)
            aobj = (Object[])(Object[])Array.newInstance(((Object) (aobj)).getClass().getComponentType(), i);
        if(head >= tail) goto _L2; else goto _L1
_L1:
        System.arraycopy(((Object) (elements)), head, ((Object) (aobj)), 0, size());
_L4:
        if(aobj.length > i)
            aobj[i] = null;
        return aobj;
_L2:
        if(head > tail)
        {
            int j = elements.length - head;
            System.arraycopy(((Object) (elements)), head, ((Object) (aobj)), 0, j);
            System.arraycopy(((Object) (elements)), 0, ((Object) (aobj)), j, tail);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static final int MIN_INITIAL_CAPACITY = 8;
    private static final long serialVersionUID = 0x207cda2e240da08bL;
    private transient Object elements[];
    private transient int head;
    private transient int tail;




}
