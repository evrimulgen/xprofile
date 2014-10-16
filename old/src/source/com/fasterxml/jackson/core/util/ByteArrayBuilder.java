// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core.util;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedList;

// Referenced classes of package com.fasterxml.jackson.core.util:
//            BufferRecycler

public final class ByteArrayBuilder extends OutputStream
{

    public ByteArrayBuilder()
    {
        this(((BufferRecycler) (null)));
    }

    public ByteArrayBuilder(int i)
    {
        this(null, i);
    }

    public ByteArrayBuilder(BufferRecycler bufferrecycler)
    {
        this(bufferrecycler, 500);
    }

    public ByteArrayBuilder(BufferRecycler bufferrecycler, int i)
    {
        _pastBlocks = new LinkedList();
        _bufferRecycler = bufferrecycler;
        if(bufferrecycler == null)
        {
            _currBlock = new byte[i];
            return;
        } else
        {
            _currBlock = bufferrecycler.allocByteBuffer(BufferRecycler.ByteBufferType.WRITE_CONCAT_BUFFER);
            return;
        }
    }

    private void _allocMore()
    {
        int i = 0x40000;
        _pastLen = _pastLen + _currBlock.length;
        int j = Math.max(_pastLen >> 1, 1000);
        if(j <= i)
            i = j;
        _pastBlocks.add(_currBlock);
        _currBlock = new byte[i];
        _currBlockPtr = 0;
    }

    public void append(int i)
    {
        if(_currBlockPtr >= _currBlock.length)
            _allocMore();
        byte abyte0[] = _currBlock;
        int j = _currBlockPtr;
        _currBlockPtr = j + 1;
        abyte0[j] = (byte)i;
    }

    public void appendThreeBytes(int i)
    {
        if(2 + _currBlockPtr < _currBlock.length)
        {
            byte abyte0[] = _currBlock;
            int j = _currBlockPtr;
            _currBlockPtr = j + 1;
            abyte0[j] = (byte)(i >> 16);
            byte abyte1[] = _currBlock;
            int k = _currBlockPtr;
            _currBlockPtr = k + 1;
            abyte1[k] = (byte)(i >> 8);
            byte abyte2[] = _currBlock;
            int l = _currBlockPtr;
            _currBlockPtr = l + 1;
            abyte2[l] = (byte)i;
            return;
        } else
        {
            append(i >> 16);
            append(i >> 8);
            append(i);
            return;
        }
    }

    public void appendTwoBytes(int i)
    {
        if(1 + _currBlockPtr < _currBlock.length)
        {
            byte abyte0[] = _currBlock;
            int j = _currBlockPtr;
            _currBlockPtr = j + 1;
            abyte0[j] = (byte)(i >> 8);
            byte abyte1[] = _currBlock;
            int k = _currBlockPtr;
            _currBlockPtr = k + 1;
            abyte1[k] = (byte)i;
            return;
        } else
        {
            append(i >> 8);
            append(i);
            return;
        }
    }

    public void close()
    {
    }

    public byte[] completeAndCoalesce(int i)
    {
        _currBlockPtr = i;
        return toByteArray();
    }

    public byte[] finishCurrentSegment()
    {
        _allocMore();
        return _currBlock;
    }

    public void flush()
    {
    }

    public byte[] getCurrentSegment()
    {
        return _currBlock;
    }

    public int getCurrentSegmentLength()
    {
        return _currBlockPtr;
    }

    public void release()
    {
        reset();
        if(_bufferRecycler != null && _currBlock != null)
        {
            _bufferRecycler.releaseByteBuffer(BufferRecycler.ByteBufferType.WRITE_CONCAT_BUFFER, _currBlock);
            _currBlock = null;
        }
    }

    public void reset()
    {
        _pastLen = 0;
        _currBlockPtr = 0;
        if(!_pastBlocks.isEmpty())
            _pastBlocks.clear();
    }

    public byte[] resetAndGetFirstSegment()
    {
        reset();
        return _currBlock;
    }

    public void setCurrentSegmentLength(int i)
    {
        _currBlockPtr = i;
    }

    public byte[] toByteArray()
    {
        int i = _pastLen + _currBlockPtr;
        if(i == 0)
            return NO_BYTES;
        byte abyte0[] = new byte[i];
        Iterator iterator = _pastBlocks.iterator();
        int j;
        int l;
        for(j = 0; iterator.hasNext(); j += l)
        {
            byte abyte1[] = (byte[])iterator.next();
            l = abyte1.length;
            System.arraycopy(abyte1, 0, abyte0, j, l);
        }

        System.arraycopy(_currBlock, 0, abyte0, j, _currBlockPtr);
        int k = j + _currBlockPtr;
        if(k != i)
            throw new RuntimeException((new StringBuilder()).append("Internal error: total len assumed to be ").append(i).append(", copied ").append(k).append(" bytes").toString());
        if(!_pastBlocks.isEmpty())
            reset();
        return abyte0;
    }

    public void write(int i)
    {
        append(i);
    }

    public void write(byte abyte0[])
    {
        write(abyte0, 0, abyte0.length);
    }

    public void write(byte abyte0[], int i, int j)
    {
        do
        {
            int k = Math.min(_currBlock.length - _currBlockPtr, j);
            if(k > 0)
            {
                System.arraycopy(abyte0, i, _currBlock, _currBlockPtr, k);
                i += k;
                _currBlockPtr = k + _currBlockPtr;
                j -= k;
            }
            if(j <= 0)
                return;
            _allocMore();
        } while(true);
    }

    static final int DEFAULT_BLOCK_ARRAY_SIZE = 40;
    private static final int INITIAL_BLOCK_SIZE = 500;
    private static final int MAX_BLOCK_SIZE = 0x40000;
    private static final byte NO_BYTES[] = new byte[0];
    private final BufferRecycler _bufferRecycler;
    private byte _currBlock[];
    private int _currBlockPtr;
    private final LinkedList _pastBlocks;
    private int _pastLen;

}
