// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.internal.BitArray;
import com.squareup.okhttp.internal.okio.*;
import java.io.IOException;
import java.util.*;

// Referenced classes of package com.squareup.okhttp.internal.spdy:
//            Header

final class HpackDraft05
{
    static final class Reader
    {

        private void clearHeaderTable()
        {
            clearReferenceSet();
            Arrays.fill(headerTable, null);
            nextHeaderIndex = -1 + headerTable.length;
            headerCount = 0;
            headerTableByteCount = 0;
        }

        private void clearReferenceSet()
        {
            referencedHeaders.clear();
            emittedReferencedHeaders.clear();
        }

        private int evictToRecoverBytes(int i)
        {
            int j = 0;
            if(i > 0)
            {
                for(int k = -1 + headerTable.length; k >= nextHeaderIndex && i > 0; k--)
                {
                    i -= headerTable[k].hpackSize;
                    headerTableByteCount = headerTableByteCount - headerTable[k].hpackSize;
                    headerCount = -1 + headerCount;
                    j++;
                }

                referencedHeaders.shiftLeft(j);
                emittedReferencedHeaders.shiftLeft(j);
                System.arraycopy(headerTable, 1 + nextHeaderIndex, headerTable, j + (1 + nextHeaderIndex), headerCount);
                nextHeaderIndex = j + nextHeaderIndex;
            }
            return j;
        }

        private ByteString getName(int i)
        {
            if(isStaticHeader(i))
                return HpackDraft05.STATIC_HEADER_TABLE[i - headerCount].name;
            else
                return headerTable[headerTableIndex(i)].name;
        }

        private int headerTableIndex(int i)
        {
            return i + (1 + nextHeaderIndex);
        }

        private void insertIntoHeaderTable(int i, Header header)
        {
            int j = header.hpackSize;
            if(i != -1)
                j -= headerTable[headerTableIndex(i)].hpackSize;
            if(j > maxHeaderTableByteCount)
            {
                clearHeaderTable();
                emittedHeaders.add(header);
                return;
            }
            int k = evictToRecoverBytes((j + headerTableByteCount) - maxHeaderTableByteCount);
            if(i == -1)
            {
                if(1 + headerCount > headerTable.length)
                {
                    Header aheader[] = new Header[2 * headerTable.length];
                    System.arraycopy(headerTable, 0, aheader, headerTable.length, headerTable.length);
                    if(aheader.length == 64)
                    {
                        referencedHeaders = ((com.squareup.okhttp.internal.BitArray.FixedCapacity)referencedHeaders).toVariableCapacity();
                        emittedReferencedHeaders = ((com.squareup.okhttp.internal.BitArray.FixedCapacity)emittedReferencedHeaders).toVariableCapacity();
                    }
                    referencedHeaders.shiftLeft(headerTable.length);
                    emittedReferencedHeaders.shiftLeft(headerTable.length);
                    nextHeaderIndex = -1 + headerTable.length;
                    headerTable = aheader;
                }
                int i1 = nextHeaderIndex;
                nextHeaderIndex = i1 - 1;
                referencedHeaders.set(i1);
                headerTable[i1] = header;
                headerCount = 1 + headerCount;
            } else
            {
                int l = i + (k + headerTableIndex(i));
                referencedHeaders.set(l);
                headerTable[l] = header;
            }
            headerTableByteCount = j + headerTableByteCount;
        }

        private boolean isStaticHeader(int i)
        {
            return i >= headerCount;
        }

        private int readByte()
            throws IOException
        {
            return 0xff & source.readByte();
        }

        private void readIndexedHeader(int i)
        {
            if(isStaticHeader(i))
            {
                Header header = HpackDraft05.STATIC_HEADER_TABLE[i - headerCount];
                if(maxHeaderTableByteCount == 0)
                {
                    emittedHeaders.add(header);
                    return;
                } else
                {
                    insertIntoHeaderTable(-1, header);
                    return;
                }
            }
            int j = headerTableIndex(i);
            if(!referencedHeaders.get(j))
            {
                emittedHeaders.add(headerTable[j]);
                emittedReferencedHeaders.set(j);
            }
            referencedHeaders.toggle(j);
        }

        private void readLiteralHeaderWithIncrementalIndexingIndexedName(int i)
            throws IOException
        {
            insertIntoHeaderTable(-1, new Header(getName(i), readByteString(false)));
        }

        private void readLiteralHeaderWithIncrementalIndexingNewName()
            throws IOException
        {
            insertIntoHeaderTable(-1, new Header(readByteString(true), readByteString(false)));
        }

        private void readLiteralHeaderWithoutIndexingIndexedName(int i)
            throws IOException
        {
            ByteString bytestring = getName(i);
            ByteString bytestring1 = readByteString(false);
            emittedHeaders.add(new Header(bytestring, bytestring1));
        }

        private void readLiteralHeaderWithoutIndexingNewName()
            throws IOException
        {
            ByteString bytestring = readByteString(true);
            ByteString bytestring1 = readByteString(false);
            emittedHeaders.add(new Header(bytestring, bytestring1));
        }

        void emitReferenceSet()
        {
            for(int i = -1 + headerTable.length; i != nextHeaderIndex; i--)
                if(referencedHeaders.get(i) && !emittedReferencedHeaders.get(i))
                    emittedHeaders.add(headerTable[i]);

        }

        List getAndReset()
        {
            ArrayList arraylist = new ArrayList(emittedHeaders);
            emittedHeaders.clear();
            emittedReferencedHeaders.clear();
            return arraylist;
        }

        int maxHeaderTableByteCount()
        {
            return maxHeaderTableByteCount;
        }

        void maxHeaderTableByteCount(int i)
        {
label0:
            {
                maxHeaderTableByteCount = i;
                if(maxHeaderTableByteCount < headerTableByteCount)
                {
                    if(maxHeaderTableByteCount != 0)
                        break label0;
                    clearHeaderTable();
                }
                return;
            }
            evictToRecoverBytes(headerTableByteCount - maxHeaderTableByteCount);
        }

        ByteString readByteString(boolean flag)
            throws IOException
        {
            int i = readByte();
            boolean flag1;
            int j;
            ByteString bytestring;
            if((i & 0x80) == 128)
                flag1 = true;
            else
                flag1 = false;
            j = readInt(i, 127);
            bytestring = source.readByteString(j);
            if(flag1)
                bytestring = huffmanCodec.decode(bytestring);
            if(flag)
                bytestring = bytestring.toAsciiLowercase();
            return bytestring;
        }

        void readHeaders()
            throws IOException
        {
            while(!source.exhausted()) 
            {
                int i = 0xff & source.readByte();
                if(i == 128)
                    clearReferenceSet();
                else
                if((i & 0x80) == 128)
                    readIndexedHeader(-1 + readInt(i, 127));
                else
                if(i == 64)
                    readLiteralHeaderWithoutIndexingNewName();
                else
                if((i & 0x40) == 64)
                    readLiteralHeaderWithoutIndexingIndexedName(-1 + readInt(i, 63));
                else
                if(i == 0)
                    readLiteralHeaderWithIncrementalIndexingNewName();
                else
                if((i & 0xc0) == 0)
                    readLiteralHeaderWithIncrementalIndexingIndexedName(-1 + readInt(i, 63));
                else
                    throw new AssertionError((new StringBuilder()).append("unhandled byte: ").append(Integer.toBinaryString(i)).toString());
            }
        }

        int readInt(int i, int j)
            throws IOException
        {
            int k = i & j;
            if(k < j)
                return k;
            int l = j;
            int i1 = 0;
            do
            {
                int j1 = readByte();
                if((j1 & 0x80) != 0)
                {
                    l += (j1 & 0x7f) << i1;
                    i1 += 7;
                } else
                {
                    return l + (j1 << i1);
                }
            } while(true);
        }

        private final List emittedHeaders = new ArrayList();
        BitArray emittedReferencedHeaders;
        int headerCount;
        Header headerTable[];
        int headerTableByteCount;
        private final Huffman.Codec huffmanCodec;
        private int maxHeaderTableByteCount;
        int nextHeaderIndex;
        BitArray referencedHeaders;
        private final BufferedSource source;

        Reader(boolean flag, int i, Source source1)
        {
            headerTable = new Header[8];
            nextHeaderIndex = -1 + headerTable.length;
            headerCount = 0;
            referencedHeaders = new com.squareup.okhttp.internal.BitArray.FixedCapacity();
            emittedReferencedHeaders = new com.squareup.okhttp.internal.BitArray.FixedCapacity();
            headerTableByteCount = 0;
            Huffman.Codec codec;
            if(flag)
                codec = Huffman.Codec.RESPONSE;
            else
                codec = Huffman.Codec.REQUEST;
            huffmanCodec = codec;
            maxHeaderTableByteCount = i;
            source = Okio.buffer(source1);
        }
    }

    static final class Writer
    {

        void writeByteString(ByteString bytestring)
            throws IOException
        {
            writeInt(bytestring.size(), 127, 0);
            out.write(bytestring);
        }

        void writeHeaders(List list)
            throws IOException
        {
            int i = 0;
            int j = list.size();
            while(i < j) 
            {
                ByteString bytestring = ((Header)list.get(i)).name;
                Integer integer = (Integer)HpackDraft05.NAME_TO_FIRST_INDEX.get(bytestring);
                if(integer != null)
                {
                    writeInt(1 + integer.intValue(), 63, 64);
                    writeByteString(((Header)list.get(i)).value);
                } else
                {
                    out.writeByte(64);
                    writeByteString(bytestring);
                    writeByteString(((Header)list.get(i)).value);
                }
                i++;
            }
        }

        void writeInt(int i, int j, int k)
            throws IOException
        {
            if(i < j)
            {
                out.writeByte(k | i);
                return;
            }
            out.writeByte(k | j);
            int l;
            for(l = i - j; l >= 128; l >>>= 7)
            {
                int i1 = l & 0x7f;
                out.writeByte(i1 | 0x80);
            }

            out.writeByte(l);
        }

        private final OkBuffer out;

        Writer(OkBuffer okbuffer)
        {
            out = okbuffer;
        }
    }


    private HpackDraft05()
    {
    }

    private static Map nameToFirstIndex()
    {
        LinkedHashMap linkedhashmap = new LinkedHashMap(STATIC_HEADER_TABLE.length);
        for(int i = 0; i < STATIC_HEADER_TABLE.length; i++)
            if(!linkedhashmap.containsKey(STATIC_HEADER_TABLE[i].name))
                linkedhashmap.put(STATIC_HEADER_TABLE[i].name, Integer.valueOf(i));

        return Collections.unmodifiableMap(linkedhashmap);
    }

    private static final Map NAME_TO_FIRST_INDEX = nameToFirstIndex();
    private static final int PREFIX_6_BITS = 63;
    private static final int PREFIX_7_BITS = 127;
    private static final Header STATIC_HEADER_TABLE[];

    static 
    {
        Header aheader[] = new Header[60];
        aheader[0] = new Header(Header.TARGET_AUTHORITY, "");
        aheader[1] = new Header(Header.TARGET_METHOD, "GET");
        aheader[2] = new Header(Header.TARGET_METHOD, "POST");
        aheader[3] = new Header(Header.TARGET_PATH, "/");
        aheader[4] = new Header(Header.TARGET_PATH, "/index.html");
        aheader[5] = new Header(Header.TARGET_SCHEME, "http");
        aheader[6] = new Header(Header.TARGET_SCHEME, "https");
        aheader[7] = new Header(Header.RESPONSE_STATUS, "200");
        aheader[8] = new Header(Header.RESPONSE_STATUS, "500");
        aheader[9] = new Header(Header.RESPONSE_STATUS, "404");
        aheader[10] = new Header(Header.RESPONSE_STATUS, "403");
        aheader[11] = new Header(Header.RESPONSE_STATUS, "400");
        aheader[12] = new Header(Header.RESPONSE_STATUS, "401");
        aheader[13] = new Header("accept-charset", "");
        aheader[14] = new Header("accept-encoding", "");
        aheader[15] = new Header("accept-language", "");
        aheader[16] = new Header("accept-ranges", "");
        aheader[17] = new Header("accept", "");
        aheader[18] = new Header("access-control-allow-origin", "");
        aheader[19] = new Header("age", "");
        aheader[20] = new Header("allow", "");
        aheader[21] = new Header("authorization", "");
        aheader[22] = new Header("cache-control", "");
        aheader[23] = new Header("content-disposition", "");
        aheader[24] = new Header("content-encoding", "");
        aheader[25] = new Header("content-language", "");
        aheader[26] = new Header("content-length", "");
        aheader[27] = new Header("content-location", "");
        aheader[28] = new Header("content-range", "");
        aheader[29] = new Header("content-type", "");
        aheader[30] = new Header("cookie", "");
        aheader[31] = new Header("date", "");
        aheader[32] = new Header("etag", "");
        aheader[33] = new Header("expect", "");
        aheader[34] = new Header("expires", "");
        aheader[35] = new Header("from", "");
        aheader[36] = new Header("host", "");
        aheader[37] = new Header("if-match", "");
        aheader[38] = new Header("if-modified-since", "");
        aheader[39] = new Header("if-none-match", "");
        aheader[40] = new Header("if-range", "");
        aheader[41] = new Header("if-unmodified-since", "");
        aheader[42] = new Header("last-modified", "");
        aheader[43] = new Header("link", "");
        aheader[44] = new Header("location", "");
        aheader[45] = new Header("max-forwards", "");
        aheader[46] = new Header("proxy-authenticate", "");
        aheader[47] = new Header("proxy-authorization", "");
        aheader[48] = new Header("range", "");
        aheader[49] = new Header("referer", "");
        aheader[50] = new Header("refresh", "");
        aheader[51] = new Header("retry-after", "");
        aheader[52] = new Header("server", "");
        aheader[53] = new Header("set-cookie", "");
        aheader[54] = new Header("strict-transport-security", "");
        aheader[55] = new Header("transfer-encoding", "");
        aheader[56] = new Header("user-agent", "");
        aheader[57] = new Header("vary", "");
        aheader[58] = new Header("via", "");
        aheader[59] = new Header("www-authenticate", "");
        STATIC_HEADER_TABLE = aheader;
    }


}
