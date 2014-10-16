// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core.sym;

import com.fasterxml.jackson.core.util.InternCache;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package com.fasterxml.jackson.core.sym:
//            Name1, Name2, Name3, NameN, 
//            Name

public final class BytesToNameCanonicalizer
{
    static final class Bucket
    {

        public Name find(int i, int j, int k)
        {
            if(_name.hashCode() != i || !_name.equals(j, k)) goto _L2; else goto _L1
_L1:
            Name name = _name;
_L4:
            return name;
_L2:
            Bucket bucket = _next;
label0:
            do
            {
label1:
                {
                    if(bucket == null)
                        break label1;
                    name = bucket._name;
                    if(name.hashCode() == i && name.equals(j, k))
                        break label0;
                    bucket = bucket._next;
                }
            } while(true);
            if(true) goto _L4; else goto _L3
_L3:
            return null;
        }

        public Name find(int i, int ai[], int j)
        {
            if(_name.hashCode() != i || !_name.equals(ai, j)) goto _L2; else goto _L1
_L1:
            Name name = _name;
_L4:
            return name;
_L2:
            Bucket bucket = _next;
label0:
            do
            {
label1:
                {
                    if(bucket == null)
                        break label1;
                    name = bucket._name;
                    if(name.hashCode() == i && name.equals(ai, j))
                        break label0;
                    bucket = bucket._next;
                }
            } while(true);
            if(true) goto _L4; else goto _L3
_L3:
            return null;
        }

        public int length()
        {
            return _length;
        }

        private final int _length;
        protected final Name _name;
        protected final Bucket _next;

        Bucket(Name name, Bucket bucket)
        {
            _name = name;
            _next = bucket;
            int i;
            if(bucket == null)
                i = 1;
            else
                i = 1 + bucket._length;
            _length = i;
        }
    }

    private static final class TableInfo
    {

        public final int collCount;
        public final int collEnd;
        public final Bucket collList[];
        public final int count;
        public final int longestCollisionList;
        public final int mainHash[];
        public final int mainHashMask;
        public final Name mainNames[];

        public TableInfo(int i, int j, int ai[], Name aname[], Bucket abucket[], int k, int l, 
                int i1)
        {
            count = i;
            mainHashMask = j;
            mainHash = ai;
            mainNames = aname;
            collList = abucket;
            collCount = k;
            collEnd = l;
            longestCollisionList = i1;
        }

        public TableInfo(BytesToNameCanonicalizer bytestonamecanonicalizer)
        {
            count = bytestonamecanonicalizer._count;
            mainHashMask = bytestonamecanonicalizer._mainHashMask;
            mainHash = bytestonamecanonicalizer._mainHash;
            mainNames = bytestonamecanonicalizer._mainNames;
            collList = bytestonamecanonicalizer._collList;
            collCount = bytestonamecanonicalizer._collCount;
            collEnd = bytestonamecanonicalizer._collEnd;
            longestCollisionList = bytestonamecanonicalizer._longestCollisionList;
        }
    }


    private BytesToNameCanonicalizer(int i, boolean flag, int j)
    {
        int k;
        k = 16;
        super();
        _parent = null;
        _hashSeed = j;
        _intern = flag;
        if(i >= k) goto _L2; else goto _L1
_L1:
        i = k;
_L4:
        _tableInfo = new AtomicReference(initTableInfo(i));
        return;
_L2:
        if((i & i - 1) != 0)
        {
            for(; k < i; k += k);
            i = k;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private BytesToNameCanonicalizer(BytesToNameCanonicalizer bytestonamecanonicalizer, boolean flag, int i, TableInfo tableinfo)
    {
        _parent = bytestonamecanonicalizer;
        _hashSeed = i;
        _intern = flag;
        _tableInfo = null;
        _count = tableinfo.count;
        _mainHashMask = tableinfo.mainHashMask;
        _mainHash = tableinfo.mainHash;
        _mainNames = tableinfo.mainNames;
        _collList = tableinfo.collList;
        _collCount = tableinfo.collCount;
        _collEnd = tableinfo.collEnd;
        _longestCollisionList = tableinfo.longestCollisionList;
        _needRehash = false;
        _mainHashShared = true;
        _mainNamesShared = true;
        _collListShared = true;
    }

    private void _addSymbol(int i, Name name)
    {
        int j;
        if(_mainHashShared)
            unshareMain();
        if(_needRehash)
            rehash();
        _count = 1 + _count;
        j = i & _mainHashMask;
        if(_mainNames[j] != null) goto _L2; else goto _L1
_L1:
        _mainHash[j] = i << 8;
        if(_mainNamesShared)
            unshareNames();
        _mainNames[j] = name;
_L9:
        int j1 = _mainHash.length;
        if(_count <= j1 >> 1) goto _L4; else goto _L3
_L3:
        int k1 = j1 >> 2;
        if(_count <= j1 - k1) goto _L6; else goto _L5
_L5:
        _needRehash = true;
_L4:
        return;
_L2:
        if(_collListShared)
            unshareCollision();
        _collCount = 1 + _collCount;
        int k = _mainHash[j];
        int l = k & 0xff;
        int i1;
        if(l == 0)
        {
            Bucket bucket;
            if(_collEnd <= 254)
            {
                i1 = _collEnd;
                _collEnd = 1 + _collEnd;
                if(i1 >= _collList.length)
                    expandCollision();
            } else
            {
                i1 = findBestBucket();
            }
            _mainHash[j] = k & 0xffffff00 | i1 + 1;
        } else
        {
            i1 = l - 1;
        }
        bucket = new Bucket(name, _collList[i1]);
        _collList[i1] = bucket;
        _longestCollisionList = Math.max(bucket.length(), _longestCollisionList);
        if(_longestCollisionList > 255)
            reportTooManyCollisions(255);
        continue; /* Loop/switch isn't completed */
_L6:
        if(_collCount < k1) goto _L4; else goto _L7
_L7:
        _needRehash = true;
        return;
        if(true) goto _L9; else goto _L8
_L8:
    }

    protected static int[] calcQuads(byte abyte0[])
    {
        int i = abyte0.length;
        int ai[] = new int[(i + 3) / 4];
        int l;
        for(int j = 0; j < i; j = l + 1)
        {
            int k = 0xff & abyte0[j];
            l = j + 1;
            if(l < i)
            {
                k = k << 8 | 0xff & abyte0[l];
                if(++l < i)
                {
                    k = k << 8 | 0xff & abyte0[l];
                    if(++l < i)
                        k = k << 8 | 0xff & abyte0[l];
                }
            }
            ai[l >> 2] = k;
        }

        return ai;
    }

    private static Name constructName(int i, String s, int j, int k)
    {
        if(k == 0)
            return new Name1(s, i, j);
        else
            return new Name2(s, i, j, k);
    }

    private static Name constructName(int i, String s, int ai[], int j)
    {
        if(j >= 4) goto _L2; else goto _L1
_L1:
        j;
        JVM INSTR tableswitch 1 3: default 32
    //                   1 61
    //                   2 74
    //                   3 90;
           goto _L2 _L3 _L4 _L5
_L2:
        int ai1[];
        ai1 = new int[j];
        for(int k = 0; k < j; k++)
            ai1[k] = ai[k];

        break; /* Loop/switch isn't completed */
_L3:
        return new Name1(s, i, ai[0]);
_L4:
        return new Name2(s, i, ai[0], ai[1]);
_L5:
        return new Name3(s, i, ai[0], ai[1], ai[2]);
        return new NameN(s, i, ai1, j);
    }

    public static BytesToNameCanonicalizer createRoot()
    {
        long l = System.currentTimeMillis();
        return createRoot(1 | (int)l + (int)(l >>> 32));
    }

    protected static BytesToNameCanonicalizer createRoot(int i)
    {
        return new BytesToNameCanonicalizer(64, true, i);
    }

    private void expandCollision()
    {
        Bucket abucket[] = _collList;
        _collList = (Bucket[])Arrays.copyOf(abucket, 2 * abucket.length);
    }

    private int findBestBucket()
    {
        Bucket abucket[] = _collList;
        int i = 0x7fffffff;
        int j = -1;
        int k = 0;
        int l = _collEnd;
        while(k < l) 
        {
            int i1 = abucket[k].length();
            if(i1 < i)
            {
                if(i1 == 1)
                    return k;
                j = k;
            } else
            {
                i1 = i;
            }
            k++;
            i = i1;
        }
        return j;
    }

    public static Name getEmptyName()
    {
        return Name1.getEmptyName();
    }

    private TableInfo initTableInfo(int i)
    {
        return new TableInfo(0, i - 1, new int[i], new Name[i], null, 0, 0, 0);
    }

    private void mergeChild(TableInfo tableinfo)
    {
        int i = tableinfo.count;
        TableInfo tableinfo1 = (TableInfo)_tableInfo.get();
        if(i <= tableinfo1.count)
            return;
        if(i > 6000 || tableinfo.longestCollisionList > 63)
            tableinfo = initTableInfo(64);
        _tableInfo.compareAndSet(tableinfo1, tableinfo);
    }

    private void nukeSymbols()
    {
        _count = 0;
        _longestCollisionList = 0;
        Arrays.fill(_mainHash, 0);
        Arrays.fill(_mainNames, null);
        Arrays.fill(_collList, null);
        _collCount = 0;
        _collEnd = 0;
    }

    private void rehash()
    {
        int i = 0;
        _needRehash = false;
        _mainNamesShared = false;
        int j = _mainHash.length;
        int k = j + j;
        if(k > 0x10000)
        {
            nukeSymbols();
        } else
        {
            _mainHash = new int[k];
            _mainHashMask = k - 1;
            Name aname[] = _mainNames;
            _mainNames = new Name[k];
            int l = 0;
            int i1 = 0;
            for(; l < j; l++)
            {
                Name name1 = aname[l];
                if(name1 != null)
                {
                    i1++;
                    int i4 = name1.hashCode();
                    int j4 = i4 & _mainHashMask;
                    _mainNames[j4] = name1;
                    _mainHash[j4] = i4 << 8;
                }
            }

            int j1 = _collEnd;
            if(j1 == 0)
            {
                _longestCollisionList = 0;
                return;
            }
            _collCount = 0;
            _collEnd = 0;
            _collListShared = false;
            Bucket abucket[] = _collList;
            _collList = new Bucket[abucket.length];
            int k1 = 0;
            int l1;
            int i2;
            for(l1 = i1; k1 < j1; l1 = i2)
            {
                Bucket bucket = abucket[k1];
                i2 = l1;
                Bucket bucket1 = bucket;
                while(bucket1 != null) 
                {
                    int j2 = i2 + 1;
                    Name name = bucket1._name;
                    int k2 = name.hashCode();
                    int l2 = k2 & _mainHashMask;
                    int i3 = _mainHash[l2];
                    int l3;
                    if(_mainNames[l2] == null)
                    {
                        _mainHash[l2] = k2 << 8;
                        _mainNames[l2] = name;
                        l3 = i;
                    } else
                    {
                        _collCount = 1 + _collCount;
                        int j3 = i3 & 0xff;
                        int k3;
                        if(j3 == 0)
                        {
                            Bucket bucket2;
                            if(_collEnd <= 254)
                            {
                                k3 = _collEnd;
                                _collEnd = 1 + _collEnd;
                                if(k3 >= _collList.length)
                                    expandCollision();
                            } else
                            {
                                k3 = findBestBucket();
                            }
                            _mainHash[l2] = i3 & 0xffffff00 | k3 + 1;
                        } else
                        {
                            k3 = j3 - 1;
                        }
                        bucket2 = new Bucket(name, _collList[k3]);
                        _collList[k3] = bucket2;
                        l3 = Math.max(i, bucket2.length());
                    }
                    bucket1 = bucket1._next;
                    i = l3;
                    i2 = j2;
                }
                k1++;
            }

            _longestCollisionList = i;
            if(l1 != _count)
                throw new RuntimeException((new StringBuilder()).append("Internal error: count after rehash ").append(l1).append("; should be ").append(_count).toString());
        }
    }

    private void unshareCollision()
    {
        Bucket abucket[] = _collList;
        if(abucket == null)
            _collList = new Bucket[32];
        else
            _collList = (Bucket[])Arrays.copyOf(abucket, abucket.length);
        _collListShared = false;
    }

    private void unshareMain()
    {
        int ai[] = _mainHash;
        _mainHash = Arrays.copyOf(ai, ai.length);
        _mainHashShared = false;
    }

    private void unshareNames()
    {
        Name aname[] = _mainNames;
        _mainNames = (Name[])Arrays.copyOf(aname, aname.length);
        _mainNamesShared = false;
    }

    public Name addName(String s, int i, int j)
    {
        if(_intern)
            s = InternCache.instance.intern(s);
        int k;
        Name name;
        if(j == 0)
            k = calcHash(i);
        else
            k = calcHash(i, j);
        name = constructName(k, s, i, j);
        _addSymbol(k, name);
        return name;
    }

    public Name addName(String s, int ai[], int i)
    {
        if(_intern)
            s = InternCache.instance.intern(s);
        int j;
        Name name;
        if(i < 3)
        {
            if(i == 1)
                j = calcHash(ai[0]);
            else
                j = calcHash(ai[0], ai[1]);
        } else
        {
            j = calcHash(ai, i);
        }
        name = constructName(j, s, ai, i);
        _addSymbol(j, name);
        return name;
    }

    public int bucketCount()
    {
        return _mainHash.length;
    }

    public int calcHash(int i)
    {
        int j = i ^ _hashSeed;
        int k = j + (j >>> 15);
        return k ^ k >>> 9;
    }

    public int calcHash(int i, int j)
    {
        int k = (i ^ i >>> 15) + j * 33 ^ _hashSeed;
        return k + (k >>> 7);
    }

    public int calcHash(int ai[], int i)
    {
        int j = 3;
        if(i < j)
            throw new IllegalArgumentException();
        int k = ai[0] ^ _hashSeed;
        int l = 0x1003f * (33 * (k + (k >>> 9)) + ai[1]);
        int i1 = l + (l >>> 15) ^ ai[2];
        int j1 = i1 + (i1 >>> 17);
        for(; j < i; j++)
        {
            int l1 = j1 * 31 ^ ai[j];
            int i2 = l1 + (l1 >>> 3);
            j1 = i2 ^ i2 << 7;
        }

        int k1 = j1 + (j1 >>> 15);
        return k1 ^ k1 << 9;
    }

    public int collisionCount()
    {
        return _collCount;
    }

    public Name findName(int i)
    {
        int j;
        int k;
        int l;
        j = calcHash(i);
        k = j & _mainHashMask;
        l = _mainHash[k];
        if((j ^ l >> 8) << 8 != 0) goto _L2; else goto _L1
_L1:
        Name name = _mainNames[k];
        if(name != null) goto _L4; else goto _L3
_L3:
        return null;
_L4:
        if(name.equals(i))
            return name;
        break; /* Loop/switch isn't completed */
_L2:
        if(l == 0) goto _L3; else goto _L5
_L5:
        int i1 = l & 0xff;
        if(i1 > 0)
        {
            int j1 = i1 - 1;
            Bucket bucket = _collList[j1];
            if(bucket != null)
                return bucket.find(j, i, 0);
        }
        if(true) goto _L3; else goto _L6
_L6:
    }

    public Name findName(int i, int j)
    {
        int k;
        int l;
        int i1;
        if(j == 0)
            k = calcHash(i);
        else
            k = calcHash(i, j);
        l = k & _mainHashMask;
        i1 = _mainHash[l];
        if((k ^ i1 >> 8) << 8 == 0)
        {
            Name name = _mainNames[l];
            if(name == null)
                return null;
            if(name.equals(i, j))
                return name;
        } else
        if(i1 == 0)
            return null;
        int j1 = i1 & 0xff;
        if(j1 > 0)
        {
            int k1 = j1 - 1;
            Bucket bucket = _collList[k1];
            if(bucket != null)
                return bucket.find(k, i, j);
        }
        return null;
    }

    public Name findName(int ai[], int i)
    {
        if(i >= 3) goto _L2; else goto _L1
_L1:
        Name name;
        int k1 = ai[0];
        int l1 = 0;
        if(i >= 2)
            l1 = ai[1];
        name = findName(k1, l1);
_L6:
        return name;
_L2:
        int j;
        int k;
        int l;
        j = calcHash(ai, i);
        k = j & _mainHashMask;
        l = _mainHash[k];
        if((j ^ l >> 8) << 8 != 0) goto _L4; else goto _L3
_L3:
        name = _mainNames[k];
        if(name == null || name.equals(ai, i)) goto _L6; else goto _L5
_L5:
        int i1 = l & 0xff;
        if(i1 > 0)
        {
            int j1 = i1 - 1;
            Bucket bucket = _collList[j1];
            if(bucket != null)
                return bucket.find(j, ai, i);
        }
        break; /* Loop/switch isn't completed */
_L4:
        if(l == 0)
            return null;
        if(true) goto _L5; else goto _L7
_L7:
        return null;
    }

    public int hashSeed()
    {
        return _hashSeed;
    }

    public BytesToNameCanonicalizer makeChild(boolean flag, boolean flag1)
    {
        return new BytesToNameCanonicalizer(this, flag1, _hashSeed, (TableInfo)_tableInfo.get());
    }

    public int maxCollisionLength()
    {
        return _longestCollisionList;
    }

    public boolean maybeDirty()
    {
        return !_mainHashShared;
    }

    public void release()
    {
        if(_parent != null && maybeDirty())
        {
            _parent.mergeChild(new TableInfo(this));
            _mainHashShared = true;
            _mainNamesShared = true;
            _collListShared = true;
        }
    }

    protected void reportTooManyCollisions(int i)
    {
        throw new IllegalStateException((new StringBuilder()).append("Longest collision chain in symbol table (of size ").append(_count).append(") now exceeds maximum, ").append(i).append(" -- suspect a DoS attack based on hash collisions").toString());
    }

    public int size()
    {
        if(_tableInfo != null)
            return ((TableInfo)_tableInfo.get()).count;
        else
            return _count;
    }

    protected static final int DEFAULT_TABLE_SIZE = 64;
    static final int INITIAL_COLLISION_LEN = 32;
    static final int LAST_VALID_BUCKET = 254;
    static final int MAX_COLL_CHAIN_FOR_REUSE = 63;
    static final int MAX_COLL_CHAIN_LENGTH = 255;
    static final int MAX_ENTRIES_FOR_REUSE = 6000;
    protected static final int MAX_TABLE_SIZE = 0x10000;
    static final int MIN_HASH_SIZE = 16;
    private static final int MULT = 33;
    private static final int MULT2 = 0x1003f;
    private static final int MULT3 = 31;
    protected int _collCount;
    protected int _collEnd;
    protected Bucket _collList[];
    private boolean _collListShared;
    protected int _count;
    private final int _hashSeed;
    protected final boolean _intern;
    protected int _longestCollisionList;
    protected int _mainHash[];
    protected int _mainHashMask;
    private boolean _mainHashShared;
    protected Name _mainNames[];
    private boolean _mainNamesShared;
    private transient boolean _needRehash;
    protected final BytesToNameCanonicalizer _parent;
    protected final AtomicReference _tableInfo;
}
