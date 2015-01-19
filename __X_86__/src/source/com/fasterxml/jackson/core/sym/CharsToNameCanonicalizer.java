// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core.sym;

import com.fasterxml.jackson.core.util.InternCache;
import java.util.Arrays;

public final class CharsToNameCanonicalizer
{
    static final class Bucket
    {

        public String find(char ac[], int i, int j)
        {
            String s;
            Bucket bucket;
            s = _symbol;
            bucket = _next;
_L2:
            if(s.length() != j)
                break MISSING_BLOCK_LABEL_61;
            break MISSING_BLOCK_LABEL_21;
            for(int k = 0; s.charAt(k) == ac[i + k] && ++k < j;);
            if(k == j)
                return s;
            break MISSING_BLOCK_LABEL_61;
            if(bucket == null)
                return null;
            s = bucket.getSymbol();
            bucket = bucket.getNext();
            if(true) goto _L2; else goto _L1
_L1:
        }

        public Bucket getNext()
        {
            return _next;
        }

        public String getSymbol()
        {
            return _symbol;
        }

        public int length()
        {
            return _length;
        }

        private final int _length;
        private final Bucket _next;
        private final String _symbol;

        public Bucket(String s, Bucket bucket)
        {
            _symbol = s;
            _next = bucket;
            int i;
            if(bucket == null)
                i = 1;
            else
                i = 1 + bucket._length;
            _length = i;
        }
    }


    private CharsToNameCanonicalizer()
    {
        _canonicalize = true;
        _intern = true;
        _dirty = true;
        _hashSeed = 0;
        _longestCollisionList = 0;
        initTables(64);
    }

    private CharsToNameCanonicalizer(CharsToNameCanonicalizer charstonamecanonicalizer, boolean flag, boolean flag1, String as[], Bucket abucket[], int i, int j, 
            int k)
    {
        _parent = charstonamecanonicalizer;
        _canonicalize = flag;
        _intern = flag1;
        _symbols = as;
        _buckets = abucket;
        _size = i;
        _hashSeed = j;
        int l = as.length;
        _sizeThreshold = _thresholdSize(l);
        _indexMask = l - 1;
        _longestCollisionList = k;
        _dirty = false;
    }

    private static int _thresholdSize(int i)
    {
        return i - (i >> 2);
    }

    private void copyArrays()
    {
        String as[] = _symbols;
        _symbols = (String[])Arrays.copyOf(as, as.length);
        Bucket abucket[] = _buckets;
        _buckets = (Bucket[])Arrays.copyOf(abucket, abucket.length);
    }

    public static CharsToNameCanonicalizer createRoot()
    {
        long l = System.currentTimeMillis();
        return createRoot(1 | (int)l + (int)(l >>> 32));
    }

    protected static CharsToNameCanonicalizer createRoot(int i)
    {
        return sBootstrapSymbolTable.makeOrphan(i);
    }

    private void initTables(int i)
    {
        _symbols = new String[i];
        _buckets = new Bucket[i >> 1];
        _indexMask = i - 1;
        _size = 0;
        _longestCollisionList = 0;
        _sizeThreshold = _thresholdSize(i);
    }

    private CharsToNameCanonicalizer makeOrphan(int i)
    {
        return new CharsToNameCanonicalizer(null, true, true, _symbols, _buckets, _size, i, _longestCollisionList);
    }

    private void mergeChild(CharsToNameCanonicalizer charstonamecanonicalizer)
    {
        if(charstonamecanonicalizer.size() <= 12000 && charstonamecanonicalizer._longestCollisionList <= 63)
            break MISSING_BLOCK_LABEL_40;
        this;
        JVM INSTR monitorenter ;
        initTables(64);
        _dirty = false;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        if(charstonamecanonicalizer.size() <= size())
            break MISSING_BLOCK_LABEL_114;
        this;
        JVM INSTR monitorenter ;
        _symbols = charstonamecanonicalizer._symbols;
        _buckets = charstonamecanonicalizer._buckets;
        _size = charstonamecanonicalizer._size;
        _sizeThreshold = charstonamecanonicalizer._sizeThreshold;
        _indexMask = charstonamecanonicalizer._indexMask;
        _longestCollisionList = charstonamecanonicalizer._longestCollisionList;
        _dirty = false;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception1;
        exception1;
        this;
        JVM INSTR monitorexit ;
        throw exception1;
    }

    private void rehash()
    {
        int i = _symbols.length;
        int j = i + i;
        if(j > 0x10000)
        {
            _size = 0;
            Arrays.fill(_symbols, null);
            Arrays.fill(_buckets, null);
            _dirty = true;
        } else
        {
            String as[] = _symbols;
            Bucket abucket[] = _buckets;
            _symbols = new String[j];
            _buckets = new Bucket[j >> 1];
            _indexMask = j - 1;
            _sizeThreshold = _thresholdSize(j);
            int k = 0;
            int l = 0;
            int i1 = 0;
            while(k < i) 
            {
                String s1 = as[k];
                if(s1 != null)
                {
                    i1++;
                    int i3 = _hashToIndex(calcHash(s1));
                    if(_symbols[i3] == null)
                    {
                        _symbols[i3] = s1;
                    } else
                    {
                        int j3 = i3 >> 1;
                        Bucket bucket3 = new Bucket(s1, _buckets[j3]);
                        _buckets[j3] = bucket3;
                        l = Math.max(l, bucket3.length());
                    }
                }
                k++;
            }
            int j1 = i >> 1;
            int k1 = 0;
            int l1 = i1;
            int i2;
            int j2;
            for(i2 = l; k1 < j1; i2 = j2)
            {
                Bucket bucket = abucket[k1];
                j2 = i2;
                Bucket bucket1 = bucket;
                while(bucket1 != null) 
                {
                    l1++;
                    String s = bucket1.getSymbol();
                    int k2 = _hashToIndex(calcHash(s));
                    if(_symbols[k2] == null)
                    {
                        _symbols[k2] = s;
                    } else
                    {
                        int l2 = k2 >> 1;
                        Bucket bucket2 = new Bucket(s, _buckets[l2]);
                        _buckets[l2] = bucket2;
                        j2 = Math.max(j2, bucket2.length());
                    }
                    bucket1 = bucket1.getNext();
                }
                k1++;
            }

            _longestCollisionList = i2;
            if(l1 != _size)
                throw new Error((new StringBuilder()).append("Internal error on SymbolTable.rehash(): had ").append(_size).append(" entries; now have ").append(l1).append(".").toString());
        }
    }

    public int _hashToIndex(int i)
    {
        return i + (i >>> 15) & _indexMask;
    }

    public int bucketCount()
    {
        return _symbols.length;
    }

    public int calcHash(String s)
    {
        int i = s.length();
        int j = _hashSeed;
        for(int k = 0; k < i;)
        {
            int l = j * 33 + s.charAt(k);
            k++;
            j = l;
        }

        if(j == 0)
            j = 1;
        return j;
    }

    public int calcHash(char ac[], int i, int j)
    {
        int k = _hashSeed;
        for(int l = 0; l < j;)
        {
            int i1 = k * 33 + ac[l];
            l++;
            k = i1;
        }

        if(k == 0)
            k = 1;
        return k;
    }

    public int collisionCount()
    {
        int i = 0;
        Bucket abucket[] = _buckets;
        int j = abucket.length;
        for(int k = 0; k < j; k++)
        {
            Bucket bucket = abucket[k];
            if(bucket != null)
                i += bucket.length();
        }

        return i;
    }

    public String findSymbol(char ac[], int i, int j, int k)
    {
        if(j >= 1) goto _L2; else goto _L1
_L1:
        String s1 = "";
_L4:
        return s1;
_L2:
        int l;
        String s;
        if(!_canonicalize)
            return new String(ac, i, j);
        l = _hashToIndex(k);
        s = _symbols[l];
        if(s == null)
            break MISSING_BLOCK_LABEL_135;
        if(s.length() != j)
            break MISSING_BLOCK_LABEL_101;
        break MISSING_BLOCK_LABEL_61;
        for(int k1 = 0; s.charAt(k1) == ac[i + k1] && ++k1 < j;);
        if(k1 == j)
            return s;
        break MISSING_BLOCK_LABEL_101;
        Bucket bucket1 = _buckets[l >> 1];
        if(bucket1 != null)
        {
            String s2 = bucket1.find(ac, i, j);
            if(s2 != null)
                return s2;
        }
        int i1;
        int j1;
        Bucket bucket;
        if(!_dirty)
        {
            copyArrays();
            _dirty = true;
            i1 = l;
        } else
        if(_size >= _sizeThreshold)
        {
            rehash();
            i1 = _hashToIndex(calcHash(ac, i, j));
        } else
        {
            i1 = l;
        }
        s1 = new String(ac, i, j);
        if(_intern)
            s1 = InternCache.instance.intern(s1);
        _size = 1 + _size;
        if(_symbols[i1] == null)
        {
            _symbols[i1] = s1;
            return s1;
        }
        j1 = i1 >> 1;
        bucket = new Bucket(s1, _buckets[j1]);
        _buckets[j1] = bucket;
        _longestCollisionList = Math.max(bucket.length(), _longestCollisionList);
        if(_longestCollisionList > 255)
        {
            reportTooManyCollisions(255);
            return s1;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public int hashSeed()
    {
        return _hashSeed;
    }

    public CharsToNameCanonicalizer makeChild(boolean flag, boolean flag1)
    {
        this;
        JVM INSTR monitorenter ;
        String as[];
        Bucket abucket[];
        int i;
        int j;
        int k;
        as = _symbols;
        abucket = _buckets;
        i = _size;
        j = _hashSeed;
        k = _longestCollisionList;
        this;
        JVM INSTR monitorexit ;
        return new CharsToNameCanonicalizer(this, flag, flag1, as, abucket, i, j, k);
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int maxCollisionLength()
    {
        return _longestCollisionList;
    }

    public boolean maybeDirty()
    {
        return _dirty;
    }

    public void release()
    {
        while(!maybeDirty() || _parent == null) 
            return;
        _parent.mergeChild(this);
        _dirty = false;
    }

    protected void reportTooManyCollisions(int i)
    {
        throw new IllegalStateException((new StringBuilder()).append("Longest collision chain in symbol table (of size ").append(_size).append(") now exceeds maximum, ").append(i).append(" -- suspect a DoS attack based on hash collisions").toString());
    }

    public int size()
    {
        return _size;
    }

    protected static final int DEFAULT_TABLE_SIZE = 64;
    public static final int HASH_MULT = 33;
    static final int MAX_COLL_CHAIN_FOR_REUSE = 63;
    static final int MAX_COLL_CHAIN_LENGTH = 255;
    static final int MAX_ENTRIES_FOR_REUSE = 12000;
    protected static final int MAX_TABLE_SIZE = 0x10000;
    static final CharsToNameCanonicalizer sBootstrapSymbolTable = new CharsToNameCanonicalizer();
    protected Bucket _buckets[];
    protected final boolean _canonicalize;
    protected boolean _dirty;
    private final int _hashSeed;
    protected int _indexMask;
    protected final boolean _intern;
    protected int _longestCollisionList;
    protected CharsToNameCanonicalizer _parent;
    protected int _size;
    protected int _sizeThreshold;
    protected String _symbols[];

}
