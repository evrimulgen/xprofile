// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager.protobuf;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.*;

// Referenced classes of package com.google.tagmanager.protobuf:
//            ByteString, LiteralByteString, CodedInputStream

class RopeByteString extends ByteString
{
    private static class Balancer
    {

        private ByteString balance(ByteString bytestring, ByteString bytestring1)
        {
            doBalance(bytestring);
            doBalance(bytestring1);
            Object obj;
            for(obj = (ByteString)prefixesStack.pop(); !prefixesStack.isEmpty(); obj = new RopeByteString((ByteString)prefixesStack.pop(), ((ByteString) (obj))));
            return ((ByteString) (obj));
        }

        private void doBalance(ByteString bytestring)
        {
            if(bytestring.isBalanced())
            {
                insert(bytestring);
                return;
            }
            if(bytestring instanceof RopeByteString)
            {
                RopeByteString ropebytestring = (RopeByteString)bytestring;
                doBalance(ropebytestring.left);
                doBalance(ropebytestring.right);
                return;
            } else
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Has a new type of ByteString been created? Found ").append(bytestring.getClass()).toString());
            }
        }

        private int getDepthBinForLength(int i)
        {
            int j = Arrays.binarySearch(RopeByteString.minLengthByDepth, i);
            if(j < 0)
                j = -1 + -(j + 1);
            return j;
        }

        private void insert(ByteString bytestring)
        {
            int i = getDepthBinForLength(bytestring.size());
            int j = RopeByteString.minLengthByDepth[i + 1];
            if(prefixesStack.isEmpty() || ((ByteString)prefixesStack.peek()).size() >= j)
            {
                prefixesStack.push(bytestring);
                return;
            }
            int k = RopeByteString.minLengthByDepth[i];
            Object obj;
            for(obj = (ByteString)prefixesStack.pop(); !prefixesStack.isEmpty() && ((ByteString)prefixesStack.peek()).size() < k; obj = new RopeByteString((ByteString)prefixesStack.pop(), ((ByteString) (obj))));
            RopeByteString ropebytestring = new RopeByteString(((ByteString) (obj)), bytestring);
            do
            {
                if(prefixesStack.isEmpty())
                    break;
                int l = getDepthBinForLength(ropebytestring.size());
                int i1 = RopeByteString.minLengthByDepth[l + 1];
                if(((ByteString)prefixesStack.peek()).size() >= i1)
                    break;
                ropebytestring = new RopeByteString((ByteString)prefixesStack.pop(), ropebytestring);
            } while(true);
            prefixesStack.push(ropebytestring);
        }

        private final Deque prefixesStack;


        private Balancer()
        {
            prefixesStack = new ArrayDeque(RopeByteString.minLengthByDepth.length);
        }

    }

    private static class PieceIterator
        implements Iterator
    {

        private LiteralByteString getLeafByLeft(ByteString bytestring)
        {
            ByteString bytestring1;
            RopeByteString ropebytestring;
            for(bytestring1 = bytestring; bytestring1 instanceof RopeByteString; bytestring1 = ropebytestring.left)
            {
                ropebytestring = (RopeByteString)bytestring1;
                breadCrumbs.push(ropebytestring);
            }

            return (LiteralByteString)bytestring1;
        }

        private LiteralByteString getNextNonEmptyLeaf()
        {
            LiteralByteString literalbytestring;
            do
            {
                if(breadCrumbs.isEmpty())
                    return null;
                literalbytestring = getLeafByLeft(((RopeByteString)breadCrumbs.pop()).right);
            } while(literalbytestring.isEmpty());
            return literalbytestring;
        }

        public boolean hasNext()
        {
            return next != null;
        }

        public LiteralByteString next()
        {
            if(next == null)
            {
                throw new NoSuchElementException();
            } else
            {
                LiteralByteString literalbytestring = next;
                next = getNextNonEmptyLeaf();
                return literalbytestring;
            }
        }

        public volatile Object next()
        {
            return next();
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        private final Deque breadCrumbs;
        private LiteralByteString next;

        private PieceIterator(ByteString bytestring)
        {
            breadCrumbs = new ArrayDeque(RopeByteString.minLengthByDepth.length);
            next = getLeafByLeft(bytestring);
        }

    }

    private class RopeByteIterator
        implements ByteString.ByteIterator
    {

        public boolean hasNext()
        {
            return bytesRemaining > 0;
        }

        public Byte next()
        {
            return Byte.valueOf(nextByte());
        }

        public volatile Object next()
        {
            return next();
        }

        public byte nextByte()
        {
            if(!bytes.hasNext())
                bytes = pieces.next().iterator();
            bytesRemaining = -1 + bytesRemaining;
            return bytes.nextByte();
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        private ByteString.ByteIterator bytes;
        int bytesRemaining;
        private final PieceIterator pieces;
        final RopeByteString this$0;

        private RopeByteIterator()
        {
            this$0 = RopeByteString.this;
            super();
            pieces = new PieceIterator(RopeByteString.this);
            bytes = pieces.next().iterator();
            bytesRemaining = size();
        }

    }

    private class RopeInputStream extends InputStream
    {

        private void advanceIfCurrentPieceFullyRead()
        {
label0:
            {
                if(currentPiece != null && currentPieceIndex == currentPieceSize)
                {
                    currentPieceOffsetInRope = currentPieceOffsetInRope + currentPieceSize;
                    currentPieceIndex = 0;
                    if(!pieceIterator.hasNext())
                        break label0;
                    currentPiece = pieceIterator.next();
                    currentPieceSize = currentPiece.size();
                }
                return;
            }
            currentPiece = null;
            currentPieceSize = 0;
        }

        private void initialize()
        {
            pieceIterator = new PieceIterator(RopeByteString.this);
            currentPiece = pieceIterator.next();
            currentPieceSize = currentPiece.size();
            currentPieceIndex = 0;
            currentPieceOffsetInRope = 0;
        }

        private int readSkipInternal(byte abyte0[], int i, int j)
        {
            int k;
            int l;
            for(k = j; k > 0; k -= l)
            {
                advanceIfCurrentPieceFullyRead();
                if(currentPiece == null)
                {
                    if(k == j)
                        return -1;
                    break;
                }
                l = Math.min(currentPieceSize - currentPieceIndex, k);
                if(abyte0 != null)
                {
                    currentPiece.copyTo(abyte0, currentPieceIndex, i, l);
                    i += l;
                }
                currentPieceIndex = l + currentPieceIndex;
            }

            return j - k;
        }

        public int available()
            throws IOException
        {
            int i = currentPieceOffsetInRope + currentPieceIndex;
            return size() - i;
        }

        public void mark(int i)
        {
            mark = currentPieceOffsetInRope + currentPieceIndex;
        }

        public boolean markSupported()
        {
            return true;
        }

        public int read()
            throws IOException
        {
            advanceIfCurrentPieceFullyRead();
            if(currentPiece == null)
            {
                return -1;
            } else
            {
                LiteralByteString literalbytestring = currentPiece;
                int i = currentPieceIndex;
                currentPieceIndex = i + 1;
                return 0xff & literalbytestring.byteAt(i);
            }
        }

        public int read(byte abyte0[], int i, int j)
        {
            if(abyte0 == null)
                throw new NullPointerException();
            if(i < 0 || j < 0 || j > abyte0.length - i)
                throw new IndexOutOfBoundsException();
            else
                return readSkipInternal(abyte0, i, j);
        }

        public void reset()
        {
            this;
            JVM INSTR monitorenter ;
            initialize();
            readSkipInternal(null, 0, mark);
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public long skip(long l)
        {
            if(l < 0L)
                throw new IndexOutOfBoundsException();
            if(l > 0x7fffffffL)
                l = 0x7fffffffL;
            return (long)readSkipInternal(null, 0, (int)l);
        }

        private LiteralByteString currentPiece;
        private int currentPieceIndex;
        private int currentPieceOffsetInRope;
        private int currentPieceSize;
        private int mark;
        private PieceIterator pieceIterator;
        final RopeByteString this$0;

        public RopeInputStream()
        {
            this$0 = RopeByteString.this;
            super();
            initialize();
        }
    }


    private RopeByteString(ByteString bytestring, ByteString bytestring1)
    {
        hash = 0;
        left = bytestring;
        right = bytestring1;
        leftLength = bytestring.size();
        totalLength = leftLength + bytestring1.size();
        treeDepth = 1 + Math.max(bytestring.getTreeDepth(), bytestring1.getTreeDepth());
    }


    static ByteString concatenate(ByteString bytestring, ByteString bytestring1)
    {
        RopeByteString ropebytestring;
        if(bytestring instanceof RopeByteString)
            ropebytestring = (RopeByteString)bytestring;
        else
            ropebytestring = null;
        if(bytestring1.size() == 0)
            return bytestring;
        if(bytestring.size() == 0)
            return bytestring1;
        int i = bytestring.size() + bytestring1.size();
        if(i < 128)
            return concatenateBytes(bytestring, bytestring1);
        if(ropebytestring != null && ropebytestring.right.size() + bytestring1.size() < 128)
        {
            LiteralByteString literalbytestring = concatenateBytes(ropebytestring.right, bytestring1);
            return new RopeByteString(ropebytestring.left, literalbytestring);
        }
        if(ropebytestring != null && ropebytestring.left.getTreeDepth() > ropebytestring.right.getTreeDepth() && ropebytestring.getTreeDepth() > bytestring1.getTreeDepth())
        {
            RopeByteString ropebytestring1 = new RopeByteString(ropebytestring.right, bytestring1);
            return new RopeByteString(ropebytestring.left, ropebytestring1);
        }
        int j = 1 + Math.max(bytestring.getTreeDepth(), bytestring1.getTreeDepth());
        if(i >= minLengthByDepth[j])
            return new RopeByteString(bytestring, bytestring1);
        else
            return (new Balancer()).balance(bytestring, bytestring1);
    }

    private static LiteralByteString concatenateBytes(ByteString bytestring, ByteString bytestring1)
    {
        int i = bytestring.size();
        int j = bytestring1.size();
        byte abyte0[] = new byte[i + j];
        bytestring.copyTo(abyte0, 0, 0, i);
        bytestring1.copyTo(abyte0, 0, i, j);
        return new LiteralByteString(abyte0);
    }

    private boolean equalsFragments(ByteString bytestring)
    {
        int i = 0;
        PieceIterator pieceiterator = new PieceIterator(this);
        LiteralByteString literalbytestring = (LiteralByteString)pieceiterator.next();
        int j = 0;
        PieceIterator pieceiterator1 = new PieceIterator(bytestring);
        LiteralByteString literalbytestring1 = (LiteralByteString)pieceiterator1.next();
        int k = 0;
        do
        {
            int l = literalbytestring.size() - i;
            int i1 = literalbytestring1.size() - j;
            int j1 = Math.min(l, i1);
            boolean flag;
            if(i == 0)
                flag = literalbytestring.equalsRange(literalbytestring1, j, j1);
            else
                flag = literalbytestring1.equalsRange(literalbytestring, i, j1);
            if(!flag)
                return false;
            k += j1;
            if(k >= totalLength)
                if(k == totalLength)
                    return true;
                else
                    throw new IllegalStateException();
            if(j1 == l)
            {
                i = 0;
                literalbytestring = (LiteralByteString)pieceiterator.next();
            } else
            {
                i += j1;
            }
            if(j1 == i1)
            {
                literalbytestring1 = (LiteralByteString)pieceiterator1.next();
                j = 0;
            } else
            {
                j += j1;
            }
        } while(true);
    }

    static RopeByteString newInstanceForTest(ByteString bytestring, ByteString bytestring1)
    {
        return new RopeByteString(bytestring, bytestring1);
    }

    public ByteBuffer asReadOnlyByteBuffer()
    {
        return ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer();
    }

    public List asReadOnlyByteBufferList()
    {
        ArrayList arraylist = new ArrayList();
        for(PieceIterator pieceiterator = new PieceIterator(this); pieceiterator.hasNext(); arraylist.add(pieceiterator.next().asReadOnlyByteBuffer()));
        return arraylist;
    }

    public byte byteAt(int i)
    {
        if(i < 0)
            throw new ArrayIndexOutOfBoundsException((new StringBuilder()).append("Index < 0: ").append(i).toString());
        if(i > totalLength)
            throw new ArrayIndexOutOfBoundsException((new StringBuilder()).append("Index > length: ").append(i).append(", ").append(totalLength).toString());
        if(i < leftLength)
            return left.byteAt(i);
        else
            return right.byteAt(i - leftLength);
    }

    public void copyTo(ByteBuffer bytebuffer)
    {
        left.copyTo(bytebuffer);
        right.copyTo(bytebuffer);
    }

    protected void copyToInternal(byte abyte0[], int i, int j, int k)
    {
        if(i + k <= leftLength)
        {
            left.copyToInternal(abyte0, i, j, k);
            return;
        }
        if(i >= leftLength)
        {
            right.copyToInternal(abyte0, i - leftLength, j, k);
            return;
        } else
        {
            int l = leftLength - i;
            left.copyToInternal(abyte0, i, j, l);
            right.copyToInternal(abyte0, 0, j + l, k - l);
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if(obj != this)
        {
            if(!(obj instanceof ByteString))
                return false;
            ByteString bytestring = (ByteString)obj;
            if(totalLength != bytestring.size())
                return false;
            if(totalLength != 0)
            {
                if(hash != 0)
                {
                    int i = bytestring.peekCachedHashCode();
                    if(i != 0 && hash != i)
                        return false;
                }
                return equalsFragments(bytestring);
            }
        }
        return true;
    }

    protected int getTreeDepth()
    {
        return treeDepth;
    }

    public int hashCode()
    {
        int i = hash;
        if(i == 0)
        {
            i = partialHash(totalLength, 0, totalLength);
            if(i == 0)
                i = 1;
            hash = i;
        }
        return i;
    }

    protected boolean isBalanced()
    {
        return totalLength >= minLengthByDepth[treeDepth];
    }

    public boolean isValidUtf8()
    {
        int i = left.partialIsValidUtf8(0, 0, leftLength);
        int j = right.partialIsValidUtf8(i, 0, right.size());
        boolean flag = false;
        if(j == 0)
            flag = true;
        return flag;
    }

    public ByteString.ByteIterator iterator()
    {
        return new RopeByteIterator();
    }

    public volatile Iterator iterator()
    {
        return iterator();
    }

    public CodedInputStream newCodedInput()
    {
        return CodedInputStream.newInstance(new RopeInputStream());
    }

    public InputStream newInput()
    {
        return new RopeInputStream();
    }

    protected int partialHash(int i, int j, int k)
    {
        if(j + k <= leftLength)
            return left.partialHash(i, j, k);
        if(j >= leftLength)
        {
            return right.partialHash(i, j - leftLength, k);
        } else
        {
            int l = leftLength - j;
            int i1 = left.partialHash(i, j, l);
            return right.partialHash(i1, 0, k - l);
        }
    }

    protected int partialIsValidUtf8(int i, int j, int k)
    {
        if(j + k <= leftLength)
            return left.partialIsValidUtf8(i, j, k);
        if(j >= leftLength)
        {
            return right.partialIsValidUtf8(i, j - leftLength, k);
        } else
        {
            int l = leftLength - j;
            int i1 = left.partialIsValidUtf8(i, j, l);
            return right.partialIsValidUtf8(i1, 0, k - l);
        }
    }

    protected int peekCachedHashCode()
    {
        return hash;
    }

    public int size()
    {
        return totalLength;
    }

    public ByteString substring(int i, int j)
    {
        if(i < 0)
            throw new IndexOutOfBoundsException((new StringBuilder()).append("Beginning index: ").append(i).append(" < 0").toString());
        if(j > totalLength)
            throw new IndexOutOfBoundsException((new StringBuilder()).append("End index: ").append(j).append(" > ").append(totalLength).toString());
        int k = j - i;
        if(k < 0)
            throw new IndexOutOfBoundsException((new StringBuilder()).append("Beginning index larger than ending index: ").append(i).append(", ").append(j).toString());
        if(k == 0)
            return ByteString.EMPTY;
        if(k == totalLength)
            return this;
        if(j <= leftLength)
            return left.substring(i, j);
        if(i >= leftLength)
            return right.substring(i - leftLength, j - leftLength);
        else
            return new RopeByteString(left.substring(i), right.substring(0, j - leftLength));
    }

    public String toString(String s)
        throws UnsupportedEncodingException
    {
        return new String(toByteArray(), s);
    }

    public void writeTo(OutputStream outputstream)
        throws IOException
    {
        left.writeTo(outputstream);
        right.writeTo(outputstream);
    }

    void writeToInternal(OutputStream outputstream, int i, int j)
        throws IOException
    {
        if(i + j <= leftLength)
        {
            left.writeToInternal(outputstream, i, j);
            return;
        }
        if(i >= leftLength)
        {
            right.writeToInternal(outputstream, i - leftLength, j);
            return;
        } else
        {
            int k = leftLength - i;
            left.writeToInternal(outputstream, i, k);
            right.writeToInternal(outputstream, 0, j - k);
            return;
        }
    }

    private static final int minLengthByDepth[];
    private int hash;
    private final ByteString left;
    private final int leftLength;
    private final ByteString right;
    private final int totalLength;
    private final int treeDepth;

    static 
    {
        ArrayList arraylist = new ArrayList();
        int i = 1;
        int l;
        for(int j = 1; j > 0; j = l)
        {
            arraylist.add(Integer.valueOf(j));
            l = i + j;
            i = j;
        }

        arraylist.add(Integer.valueOf(0x7fffffff));
        minLengthByDepth = new int[arraylist.size()];
        for(int k = 0; k < minLengthByDepth.length; k++)
            minLengthByDepth[k] = ((Integer)arraylist.get(k)).intValue();

    }



}
