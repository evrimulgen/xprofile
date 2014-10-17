// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.com.google.gson.internal;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.*;

public final class LinkedTreeMap extends AbstractMap
    implements Serializable
{
    class EntrySet extends AbstractSet
    {

        public void clear()
        {
            LinkedTreeMap.this.clear();
        }

        public boolean contains(Object obj)
        {
            return (obj instanceof java.util.Map.Entry) && findByEntry((java.util.Map.Entry)obj) != null;
        }

        public Iterator iterator()
        {
            return new LinkedTreeMapIterator() {

                public volatile Object next()
                {
                    return next();
                }

                public java.util.Map.Entry next()
                {
                    return nextNode();
                }

                final EntrySet this$1;

            
            {
                this$1 = EntrySet.this;
                super();
            }
            }
;
        }

        public boolean remove(Object obj)
        {
            Node node;
            if(obj instanceof java.util.Map.Entry)
                if((node = findByEntry((java.util.Map.Entry)obj)) != null)
                {
                    removeInternal(node, true);
                    return true;
                }
            return false;
        }

        public int size()
        {
            return LinkedTreeMap.this.size;
        }

        final LinkedTreeMap this$0;

        EntrySet()
        {
            this$0 = LinkedTreeMap.this;
            super();
        }
    }

    class KeySet extends AbstractSet
    {

        public void clear()
        {
            LinkedTreeMap.this.clear();
        }

        public boolean contains(Object obj)
        {
            return containsKey(obj);
        }

        public Iterator iterator()
        {
            return new LinkedTreeMapIterator() {

                public Object next()
                {
                    return nextNode().key;
                }

                final KeySet this$1;

            
            {
                this$1 = KeySet.this;
                super();
            }
            }
;
        }

        public boolean remove(Object obj)
        {
            return removeInternalByKey(obj) != null;
        }

        public int size()
        {
            return LinkedTreeMap.this.size;
        }

        final LinkedTreeMap this$0;

        KeySet()
        {
            this$0 = LinkedTreeMap.this;
            super();
        }
    }

    private abstract class LinkedTreeMapIterator
        implements Iterator
    {

        public final boolean hasNext()
        {
            return next != header;
        }

        final Node nextNode()
        {
            Node node = next;
            if(node == header)
                throw new NoSuchElementException();
            if(modCount != expectedModCount)
            {
                throw new ConcurrentModificationException();
            } else
            {
                next = node.next;
                lastReturned = node;
                return node;
            }
        }

        public final void remove()
        {
            if(lastReturned == null)
            {
                throw new IllegalStateException();
            } else
            {
                removeInternal(lastReturned, true);
                lastReturned = null;
                expectedModCount = modCount;
                return;
            }
        }

        int expectedModCount;
        Node lastReturned;
        Node next;
        final LinkedTreeMap this$0;

        private LinkedTreeMapIterator()
        {
            this$0 = LinkedTreeMap.this;
            super();
            next = header.next;
            lastReturned = null;
            expectedModCount = modCount;
        }

    }

    static final class Node
        implements java.util.Map.Entry
    {

        public boolean equals(Object obj)
        {
            boolean flag;
            boolean flag1;
            flag = obj instanceof java.util.Map.Entry;
            flag1 = false;
            if(!flag) goto _L2; else goto _L1
_L1:
            java.util.Map.Entry entry = (java.util.Map.Entry)obj;
            if(key != null) goto _L4; else goto _L3
_L3:
            Object obj2;
            obj2 = entry.getKey();
            flag1 = false;
            if(obj2 != null) goto _L2; else goto _L5
_L5:
            if(value != null) goto _L7; else goto _L6
_L6:
            Object obj1;
            obj1 = entry.getValue();
            flag1 = false;
            if(obj1 != null) goto _L2; else goto _L8
_L8:
            flag1 = true;
_L2:
            return flag1;
_L4:
            boolean flag2;
            flag2 = key.equals(entry.getKey());
            flag1 = false;
            if(!flag2) goto _L2; else goto _L5
_L7:
            boolean flag3;
            flag3 = value.equals(entry.getValue());
            flag1 = false;
            if(!flag3) goto _L2; else goto _L8
        }

        public Node first()
        {
            Node node = this;
            for(Node node1 = node.left; node1 != null; node1 = node.left)
                node = node1;

            return node;
        }

        public Object getKey()
        {
            return key;
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

        public Node last()
        {
            Node node = this;
            for(Node node1 = node.right; node1 != null; node1 = node.right)
                node = node1;

            return node;
        }

        public Object setValue(Object obj)
        {
            Object obj1 = value;
            value = obj;
            return obj1;
        }

        public String toString()
        {
            return (new StringBuilder()).append(key).append("=").append(value).toString();
        }

        int height;
        final Object key;
        Node left;
        Node next;
        Node parent;
        Node prev;
        Node right;
        Object value;

        Node()
        {
            key = null;
            prev = this;
            next = this;
        }

        Node(Node node, Object obj, Node node1, Node node2)
        {
            parent = node;
            key = obj;
            height = 1;
            next = node1;
            prev = node2;
            node2.next = this;
            node1.prev = this;
        }
    }


    public LinkedTreeMap()
    {
        this(NATURAL_ORDER);
    }

    public LinkedTreeMap(Comparator comparator1)
    {
        size = 0;
        modCount = 0;
        header = new Node();
        if(comparator1 == null)
            comparator1 = NATURAL_ORDER;
        comparator = comparator1;
    }

    private boolean equal(Object obj, Object obj1)
    {
        return obj == obj1 || obj != null && obj.equals(obj1);
    }

    private void rebalance(Node node, boolean flag)
    {
        Node node1 = node;
_L11:
        if(node1 == null) goto _L2; else goto _L1
_L1:
        Node node2;
        Node node3;
        int i;
        int j;
        int k;
        node2 = node1.left;
        node3 = node1.right;
        Node node6;
        Node node7;
        if(node2 != null)
            i = node2.height;
        else
            i = 0;
        if(node3 != null)
            j = node3.height;
        else
            j = 0;
        k = i - j;
        if(k != -2) goto _L4; else goto _L3
_L3:
        node6 = node3.left;
        node7 = node3.right;
        int k1;
        int l1;
        int i2;
        if(node7 != null)
            k1 = node7.height;
        else
            k1 = 0;
        if(node6 != null)
            l1 = node6.height;
        else
            l1 = 0;
        i2 = l1 - k1;
        if(i2 == -1 || i2 == 0 && !flag)
        {
            rotateLeft(node1);
        } else
        {
            if(!$assertionsDisabled && i2 != 1)
                throw new AssertionError();
            rotateRight(node3);
            rotateLeft(node1);
        }
        if(!flag) goto _L5; else goto _L2
_L2:
        return;
_L4:
        if(k != 2) goto _L7; else goto _L6
_L6:
        Node node4 = node2.left;
        Node node5 = node2.right;
        int l;
        int i1;
        int j1;
        if(node5 != null)
            l = node5.height;
        else
            l = 0;
        if(node4 != null)
            i1 = node4.height;
        else
            i1 = 0;
        j1 = i1 - l;
        if(j1 == 1 || j1 == 0 && !flag)
        {
            rotateRight(node1);
        } else
        {
            if(!$assertionsDisabled && j1 != -1)
                throw new AssertionError();
            rotateLeft(node2);
            rotateRight(node1);
        }
        if(flag) goto _L2; else goto _L5
_L5:
        node1 = node1.parent;
        continue; /* Loop/switch isn't completed */
_L7:
        if(k != 0)
            break; /* Loop/switch isn't completed */
        node1.height = i + 1;
        if(flag)
            return;
        if(true) goto _L5; else goto _L8
_L8:
        if(!$assertionsDisabled && k != -1 && k != 1)
            throw new AssertionError();
        node1.height = 1 + Math.max(i, j);
        if(flag) goto _L5; else goto _L9
_L9:
        return;
        if(true) goto _L11; else goto _L10
_L10:
    }

    private void replaceInParent(Node node, Node node1)
    {
        Node node2 = node.parent;
        node.parent = null;
        if(node1 != null)
            node1.parent = node2;
        if(node2 != null)
        {
            if(node2.left == node)
            {
                node2.left = node1;
                return;
            }
            if(!$assertionsDisabled && node2.right != node)
            {
                throw new AssertionError();
            } else
            {
                node2.right = node1;
                return;
            }
        } else
        {
            root = node1;
            return;
        }
    }

    private void rotateLeft(Node node)
    {
        Node node1 = node.left;
        Node node2 = node.right;
        Node node3 = node2.left;
        Node node4 = node2.right;
        node.right = node3;
        if(node3 != null)
            node3.parent = node;
        replaceInParent(node, node2);
        node2.left = node;
        node.parent = node2;
        int i;
        int j;
        int k;
        int l;
        if(node1 != null)
            i = node1.height;
        else
            i = 0;
        if(node3 != null)
            j = node3.height;
        else
            j = 0;
        node.height = 1 + Math.max(i, j);
        k = node.height;
        l = 0;
        if(node4 != null)
            l = node4.height;
        node2.height = 1 + Math.max(k, l);
    }

    private void rotateRight(Node node)
    {
        Node node1 = node.left;
        Node node2 = node.right;
        Node node3 = node1.left;
        Node node4 = node1.right;
        node.left = node4;
        if(node4 != null)
            node4.parent = node;
        replaceInParent(node, node1);
        node1.right = node;
        node.parent = node1;
        int i;
        int j;
        int k;
        int l;
        if(node2 != null)
            i = node2.height;
        else
            i = 0;
        if(node4 != null)
            j = node4.height;
        else
            j = 0;
        node.height = 1 + Math.max(i, j);
        k = node.height;
        l = 0;
        if(node3 != null)
            l = node3.height;
        node1.height = 1 + Math.max(k, l);
    }

    private Object writeReplace()
        throws ObjectStreamException
    {
        return new LinkedHashMap(this);
    }

    public void clear()
    {
        root = null;
        size = 0;
        modCount = 1 + modCount;
        Node node = header;
        node.prev = node;
        node.next = node;
    }

    public boolean containsKey(Object obj)
    {
        return findByObject(obj) != null;
    }

    public Set entrySet()
    {
        EntrySet entryset = entrySet;
        if(entryset != null)
        {
            return entryset;
        } else
        {
            EntrySet entryset1 = new EntrySet();
            entrySet = entryset1;
            return entryset1;
        }
    }

    Node find(Object obj, boolean flag)
    {
        Comparator comparator1;
        Node node;
        int i;
        comparator1 = comparator;
        node = root;
        i = 0;
        if(node == null) goto _L2; else goto _L1
_L1:
        Node node1;
        Comparable comparable;
        if(comparator1 == NATURAL_ORDER)
            comparable = (Comparable)obj;
        else
            comparable = null;
_L9:
        if(comparable != null)
            i = comparable.compareTo(node.key);
        else
            i = comparator1.compare(obj, node.key);
        if(i != 0) goto _L4; else goto _L3
_L3:
        node1 = node;
_L7:
        return node1;
_L4:
        Node node2;
        Node node4;
        if(i < 0)
            node4 = node.left;
        else
            node4 = node.right;
        if(node4 != null) goto _L5; else goto _L2
_L2:
        node1 = null;
        if(!flag) goto _L7; else goto _L6
_L5:
        node = node4;
        if(true) goto _L9; else goto _L8
_L6:
        Node node3;
        node2 = header;
        if(node != null)
            break; /* Loop/switch isn't completed */
        if(comparator1 == NATURAL_ORDER && !(obj instanceof Comparable))
            throw new ClassCastException((new StringBuilder()).append(obj.getClass().getName()).append(" is not Comparable").toString());
        node3 = new Node(node, obj, node2, node2.prev);
        root = node3;
_L11:
        size = 1 + size;
        modCount = 1 + modCount;
        return node3;
_L8:
        node3 = new Node(node, obj, node2, node2.prev);
        if(i < 0)
            node.left = node3;
        else
            node.right = node3;
        rebalance(node, true);
        if(true) goto _L11; else goto _L10
_L10:
    }

    Node findByEntry(java.util.Map.Entry entry)
    {
        Node node = findByObject(entry.getKey());
        boolean flag;
        if(node != null && equal(node.value, entry.getValue()))
            flag = true;
        else
            flag = false;
        if(flag)
            return node;
        else
            return null;
    }

    Node findByObject(Object obj)
    {
        Node node = null;
        if(obj != null)
        {
            Node node1;
            try
            {
                node1 = find(obj, false);
            }
            catch(ClassCastException classcastexception)
            {
                return null;
            }
            node = node1;
        }
        return node;
    }

    public Object get(Object obj)
    {
        Node node = findByObject(obj);
        if(node != null)
            return node.value;
        else
            return null;
    }

    public Set keySet()
    {
        KeySet keyset = keySet;
        if(keyset != null)
        {
            return keyset;
        } else
        {
            KeySet keyset1 = new KeySet();
            keySet = keyset1;
            return keyset1;
        }
    }

    public Object put(Object obj, Object obj1)
    {
        if(obj == null)
        {
            throw new NullPointerException("key == null");
        } else
        {
            Node node = find(obj, true);
            Object obj2 = node.value;
            node.value = obj1;
            return obj2;
        }
    }

    public Object remove(Object obj)
    {
        Node node = removeInternalByKey(obj);
        if(node != null)
            return node.value;
        else
            return null;
    }

    void removeInternal(Node node, boolean flag)
    {
        if(flag)
        {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        Node node1 = node.left;
        Node node2 = node.right;
        Node node3 = node.parent;
        if(node1 != null && node2 != null)
        {
            Node node4;
            Node node5;
            int i;
            Node node6;
            int j;
            if(node1.height > node2.height)
                node4 = node1.last();
            else
                node4 = node2.first();
            removeInternal(node4, false);
            node5 = node.left;
            i = 0;
            if(node5 != null)
            {
                i = node5.height;
                node4.left = node5;
                node5.parent = node4;
                node.left = null;
            }
            node6 = node.right;
            j = 0;
            if(node6 != null)
            {
                j = node6.height;
                node4.right = node6;
                node6.parent = node4;
                node.right = null;
            }
            node4.height = 1 + Math.max(i, j);
            replaceInParent(node, node4);
            return;
        }
        if(node1 != null)
        {
            replaceInParent(node, node1);
            node.left = null;
        } else
        if(node2 != null)
        {
            replaceInParent(node, node2);
            node.right = null;
        } else
        {
            replaceInParent(node, null);
        }
        rebalance(node3, false);
        size = -1 + size;
        modCount = 1 + modCount;
    }

    Node removeInternalByKey(Object obj)
    {
        Node node = findByObject(obj);
        if(node != null)
            removeInternal(node, true);
        return node;
    }

    public int size()
    {
        return size;
    }

    static final boolean $assertionsDisabled;
    private static final Comparator NATURAL_ORDER = new Comparator() {

        public int compare(Comparable comparable, Comparable comparable1)
        {
            return comparable.compareTo(comparable1);
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((Comparable)obj, (Comparable)obj1);
        }

    }
;
    Comparator comparator;
    private EntrySet entrySet;
    final Node header;
    private KeySet keySet;
    int modCount;
    Node root;
    int size;

    static 
    {
        boolean flag;
        if(!com/newrelic/com/google/gson/internal/LinkedTreeMap.desiredAssertionStatus())
            flag = true;
        else
            flag = false;
        $assertionsDisabled = flag;
    }
}
