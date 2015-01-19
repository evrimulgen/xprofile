// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.internal.okio.ByteString;
import java.io.*;

class Huffman
{
    static final class Codec extends Enum
    {

        private void addCode(int i, int j, byte byte0)
        {
            Node node = new Node(i, byte0);
            Node node1;
            int k1;
            for(node1 = root; byte0 > 8; node1 = node1.children[k1])
            {
                byte0 -= 8;
                k1 = 0xff & j >>> byte0;
                if(node1.children == null)
                    throw new IllegalStateException("invalid dictionary: prefix not unique");
                if(node1.children[k1] == null)
                    node1.children[k1] = new Node();
            }

            int k = 8 - byte0;
            int l = 0xff & j << k;
            int i1 = 1 << k;
            for(int j1 = l; j1 < l + i1; j1++)
                node1.children[j1] = node;

        }

        private void buildTree(int ai[], byte abyte0[])
        {
            for(int i = 0; i < abyte0.length; i++)
                addCode(i, ai[i], abyte0[i]);

        }

        public static Codec valueOf(String s)
        {
            return (Codec)Enum.valueOf(com/squareup/okhttp/internal/spdy/Huffman$Codec, s);
        }

        public static Codec[] values()
        {
            return (Codec[])$VALUES.clone();
        }

        ByteString decode(ByteString bytestring)
            throws IOException
        {
            return ByteString.of(decode(bytestring.toByteArray()));
        }

        byte[] decode(byte abyte0[])
            throws IOException
        {
            ByteArrayOutputStream bytearrayoutputstream;
            Node node;
            int i;
            int j;
            Node node1;
            bytearrayoutputstream = new ByteArrayOutputStream();
            node = root;
            i = 0;
            j = 0;
            for(int k = 0; k < abyte0.length; k++)
            {
                i = 0xff & abyte0[k] | i << 8;
                for(j += 8; j >= 8;)
                {
                    int i1 = 0xff & i >>> j - 8;
                    node = node.children[i1];
                    if(node.children == null)
                    {
                        bytearrayoutputstream.write(node.symbol);
                        j -= node.terminalBits;
                        node = root;
                    } else
                    {
                        j -= 8;
                    }
                }

            }

_L4:
            if(j <= 0)
                break; /* Loop/switch isn't completed */
            int l = 0xff & i << 8 - j;
            node1 = node.children[l];
            if(node1.children == null && node1.terminalBits <= j) goto _L2; else goto _L1
_L1:
            break; /* Loop/switch isn't completed */
_L2:
            bytearrayoutputstream.write(node1.symbol);
            j -= node1.terminalBits;
            node = root;
            if(true) goto _L4; else goto _L3
_L3:
            return bytearrayoutputstream.toByteArray();
        }

        void encode(byte abyte0[], OutputStream outputstream)
            throws IOException
        {
            long l = 0L;
            int i = 0;
            for(int j = 0; j < abyte0.length; j++)
            {
                int k = 0xff & abyte0[j];
                int i1 = codes[k];
                byte byte0 = lengths[k];
                l = l << byte0 | (long)i1;
                for(i += byte0; i >= 8;)
                {
                    i -= 8;
                    outputstream.write((int)(l >> i));
                }

            }

            if(i > 0)
                outputstream.write((int)(l << 8 - i | (long)(255 >>> i)));
        }

        int encodedLength(byte abyte0[])
        {
            long l = 0L;
            for(int i = 0; i < abyte0.length; i++)
            {
                int j = 0xff & abyte0[i];
                l += lengths[j];
            }

            return (int)(7L + l >> 3);
        }

        private static final Codec $VALUES[];
        public static final Codec REQUEST;
        public static final Codec RESPONSE;
        private final int codes[];
        private final byte lengths[];
        private final Node root = new Node();

        static 
        {
            REQUEST = new Codec("REQUEST", 0, Huffman.REQUEST_CODES, Huffman.REQUEST_CODE_LENGTHS);
            RESPONSE = new Codec("RESPONSE", 1, Huffman.RESPONSE_CODES, Huffman.RESPONSE_CODE_LENGTHS);
            Codec acodec[] = new Codec[2];
            acodec[0] = REQUEST;
            acodec[1] = RESPONSE;
            $VALUES = acodec;
        }

        private Codec(String s, int i, int ai[], byte abyte0[])
        {
            super(s, i);
            buildTree(ai, abyte0);
            codes = ai;
            lengths = abyte0;
        }
    }

    private static final class Node
    {

        private final Node children[];
        private final int symbol;
        private final int terminalBits;




        Node()
        {
            children = new Node[256];
            symbol = 0;
            terminalBits = 0;
        }

        Node(int i, int j)
        {
            children = null;
            symbol = i;
            int k = j & 7;
            if(k == 0)
                k = 8;
            terminalBits = k;
        }
    }


    Huffman()
    {
    }

    private static final int REQUEST_CODES[] = {
        0x7ffffba, 0x7ffffbb, 0x7ffffbc, 0x7ffffbd, 0x7ffffbe, 0x7ffffbf, 0x7ffffc0, 0x7ffffc1, 0x7ffffc2, 0x7ffffc3, 
        0x7ffffc4, 0x7ffffc5, 0x7ffffc6, 0x7ffffc7, 0x7ffffc8, 0x7ffffc9, 0x7ffffca, 0x7ffffcb, 0x7ffffcc, 0x7ffffcd, 
        0x7ffffce, 0x7ffffcf, 0x7ffffd0, 0x7ffffd1, 0x7ffffd2, 0x7ffffd3, 0x7ffffd4, 0x7ffffd5, 0x7ffffd6, 0x7ffffd7, 
        0x7ffffd8, 0x7ffffd9, 232, 4092, 16378, 32764, 32765, 36, 110, 32766, 
        2042, 2043, 1018, 2044, 233, 37, 4, 0, 5, 6, 
        7, 38, 39, 40, 41, 42, 43, 44, 492, 234, 
        0x3fffe, 45, 0x1fffc, 493, 16379, 111, 235, 236, 237, 238, 
        112, 494, 495, 496, 497, 1019, 498, 239, 499, 500, 
        501, 502, 503, 240, 241, 504, 505, 506, 507, 508, 
        1020, 16380, 0x7ffffda, 8188, 16381, 46, 0x7fffe, 8, 47, 9, 
        48, 1, 49, 50, 51, 10, 113, 114, 11, 52, 
        12, 13, 14, 242, 15, 16, 17, 53, 115, 54, 
        243, 244, 245, 0x1fffd, 2045, 0x1fffe, 4093, 0x7ffffdb, 0x7ffffdc, 0x7ffffdd, 
        0x7ffffde, 0x7ffffdf, 0x7ffffe0, 0x7ffffe1, 0x7ffffe2, 0x7ffffe3, 0x7ffffe4, 0x7ffffe5, 0x7ffffe6, 0x7ffffe7, 
        0x7ffffe8, 0x7ffffe9, 0x7ffffea, 0x7ffffeb, 0x7ffffec, 0x7ffffed, 0x7ffffee, 0x7ffffef, 0x7fffff0, 0x7fffff1, 
        0x7fffff2, 0x7fffff3, 0x7fffff4, 0x7fffff5, 0x7fffff6, 0x7fffff7, 0x7fffff8, 0x7fffff9, 0x7fffffa, 0x7fffffb, 
        0x7fffffc, 0x7fffffd, 0x7fffffe, 0x7ffffff, 0x3ffff80, 0x3ffff81, 0x3ffff82, 0x3ffff83, 0x3ffff84, 0x3ffff85, 
        0x3ffff86, 0x3ffff87, 0x3ffff88, 0x3ffff89, 0x3ffff8a, 0x3ffff8b, 0x3ffff8c, 0x3ffff8d, 0x3ffff8e, 0x3ffff8f, 
        0x3ffff90, 0x3ffff91, 0x3ffff92, 0x3ffff93, 0x3ffff94, 0x3ffff95, 0x3ffff96, 0x3ffff97, 0x3ffff98, 0x3ffff99, 
        0x3ffff9a, 0x3ffff9b, 0x3ffff9c, 0x3ffff9d, 0x3ffff9e, 0x3ffff9f, 0x3ffffa0, 0x3ffffa1, 0x3ffffa2, 0x3ffffa3, 
        0x3ffffa4, 0x3ffffa5, 0x3ffffa6, 0x3ffffa7, 0x3ffffa8, 0x3ffffa9, 0x3ffffaa, 0x3ffffab, 0x3ffffac, 0x3ffffad, 
        0x3ffffae, 0x3ffffaf, 0x3ffffb0, 0x3ffffb1, 0x3ffffb2, 0x3ffffb3, 0x3ffffb4, 0x3ffffb5, 0x3ffffb6, 0x3ffffb7, 
        0x3ffffb8, 0x3ffffb9, 0x3ffffba, 0x3ffffbb, 0x3ffffbc, 0x3ffffbd, 0x3ffffbe, 0x3ffffbf, 0x3ffffc0, 0x3ffffc1, 
        0x3ffffc2, 0x3ffffc3, 0x3ffffc4, 0x3ffffc5, 0x3ffffc6, 0x3ffffc7, 0x3ffffc8, 0x3ffffc9, 0x3ffffca, 0x3ffffcb, 
        0x3ffffcc, 0x3ffffcd, 0x3ffffce, 0x3ffffcf, 0x3ffffd0, 0x3ffffd1, 0x3ffffd2, 0x3ffffd3, 0x3ffffd4, 0x3ffffd5, 
        0x3ffffd6, 0x3ffffd7, 0x3ffffd8, 0x3ffffd9, 0x3ffffda, 0x3ffffdb
    };
    private static final byte REQUEST_CODE_LENGTHS[] = {
        27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 
        27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 
        27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 
        27, 27, 8, 12, 14, 15, 15, 6, 7, 15, 
        11, 11, 10, 11, 8, 6, 5, 4, 5, 5, 
        5, 6, 6, 6, 6, 6, 6, 6, 9, 8, 
        18, 6, 17, 9, 14, 7, 8, 8, 8, 8, 
        7, 9, 9, 9, 9, 10, 9, 8, 9, 9, 
        9, 9, 9, 8, 8, 9, 9, 9, 9, 9, 
        10, 14, 27, 13, 14, 6, 19, 5, 6, 5, 
        6, 4, 6, 6, 6, 5, 7, 7, 5, 6, 
        5, 5, 5, 8, 5, 5, 5, 6, 7, 6, 
        8, 8, 8, 17, 11, 17, 12, 27, 27, 27, 
        27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 
        27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 
        27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 
        27, 27, 27, 27, 26, 26, 26, 26, 26, 26, 
        26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 
        26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 
        26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 
        26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 
        26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 
        26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 
        26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 
        26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 
        26, 26, 26, 26, 26, 26
    };
    private static final int RESPONSE_CODES[] = {
        0x1ffffbc, 0x1ffffbd, 0x1ffffbe, 0x1ffffbf, 0x1ffffc0, 0x1ffffc1, 0x1ffffc2, 0x1ffffc3, 0x1ffffc4, 0x1ffffc5, 
        0x1ffffc6, 0x1ffffc7, 0x1ffffc8, 0x1ffffc9, 0x1ffffca, 0x1ffffcb, 0x1ffffcc, 0x1ffffcd, 0x1ffffce, 0x1ffffcf, 
        0x1ffffd0, 0x1ffffd1, 0x1ffffd2, 0x1ffffd3, 0x1ffffd4, 0x1ffffd5, 0x1ffffd6, 0x1ffffd7, 0x1ffffd8, 0x1ffffd9, 
        0x1ffffda, 0x1ffffdb, 0, 4090, 106, 8186, 16380, 492, 1016, 8187, 
        493, 494, 4091, 2042, 34, 35, 36, 107, 1, 2, 
        3, 8, 9, 10, 37, 38, 11, 12, 13, 495, 
        65530, 108, 8188, 4092, 65531, 109, 234, 235, 236, 237, 
        238, 39, 496, 239, 240, 1017, 497, 40, 241, 242, 
        498, 1018, 499, 41, 14, 500, 501, 243, 1019, 502, 
        1020, 2043, 8189, 2044, 32764, 503, 0x1fffe, 15, 110, 42, 
        43, 16, 111, 112, 113, 44, 504, 505, 114, 45, 
        46, 47, 48, 506, 49, 50, 51, 52, 115, 244, 
        116, 245, 507, 65532, 16381, 65533, 65534, 0x1ffffdc, 0x1ffffdd, 0x1ffffde, 
        0x1ffffdf, 0x1ffffe0, 0x1ffffe1, 0x1ffffe2, 0x1ffffe3, 0x1ffffe4, 0x1ffffe5, 0x1ffffe6, 0x1ffffe7, 0x1ffffe8, 
        0x1ffffe9, 0x1ffffea, 0x1ffffeb, 0x1ffffec, 0x1ffffed, 0x1ffffee, 0x1ffffef, 0x1fffff0, 0x1fffff1, 0x1fffff2, 
        0x1fffff3, 0x1fffff4, 0x1fffff5, 0x1fffff6, 0x1fffff7, 0x1fffff8, 0x1fffff9, 0x1fffffa, 0x1fffffb, 0x1fffffc, 
        0x1fffffd, 0x1fffffe, 0x1ffffff, 0xffff80, 0xffff81, 0xffff82, 0xffff83, 0xffff84, 0xffff85, 0xffff86, 
        0xffff87, 0xffff88, 0xffff89, 0xffff8a, 0xffff8b, 0xffff8c, 0xffff8d, 0xffff8e, 0xffff8f, 0xffff90, 
        0xffff91, 0xffff92, 0xffff93, 0xffff94, 0xffff95, 0xffff96, 0xffff97, 0xffff98, 0xffff99, 0xffff9a, 
        0xffff9b, 0xffff9c, 0xffff9d, 0xffff9e, 0xffff9f, 0xffffa0, 0xffffa1, 0xffffa2, 0xffffa3, 0xffffa4, 
        0xffffa5, 0xffffa6, 0xffffa7, 0xffffa8, 0xffffa9, 0xffffaa, 0xffffab, 0xffffac, 0xffffad, 0xffffae, 
        0xffffaf, 0xffffb0, 0xffffb1, 0xffffb2, 0xffffb3, 0xffffb4, 0xffffb5, 0xffffb6, 0xffffb7, 0xffffb8, 
        0xffffb9, 0xffffba, 0xffffbb, 0xffffbc, 0xffffbd, 0xffffbe, 0xffffbf, 0xffffc0, 0xffffc1, 0xffffc2, 
        0xffffc3, 0xffffc4, 0xffffc5, 0xffffc6, 0xffffc7, 0xffffc8, 0xffffc9, 0xffffca, 0xffffcb, 0xffffcc, 
        0xffffcd, 0xffffce, 0xffffcf, 0xffffd0, 0xffffd1, 0xffffd2, 0xffffd3, 0xffffd4, 0xffffd5, 0xffffd6, 
        0xffffd7, 0xffffd8, 0xffffd9, 0xffffda, 0xffffdb, 0xffffdc
    };
    private static final byte RESPONSE_CODE_LENGTHS[] = {
        25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 
        25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 
        25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 
        25, 25, 4, 12, 7, 13, 14, 9, 10, 13, 
        9, 9, 12, 11, 6, 6, 6, 7, 4, 4, 
        4, 5, 5, 5, 6, 6, 5, 5, 5, 9, 
        16, 7, 13, 12, 16, 7, 8, 8, 8, 8, 
        8, 6, 9, 8, 8, 10, 9, 6, 8, 8, 
        9, 10, 9, 6, 5, 9, 9, 8, 10, 9, 
        10, 11, 13, 11, 15, 9, 17, 5, 7, 6, 
        6, 5, 7, 7, 7, 6, 9, 9, 7, 6, 
        6, 6, 6, 9, 6, 6, 6, 6, 7, 8, 
        7, 8, 9, 16, 14, 16, 16, 25, 25, 25, 
        25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 
        25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 
        25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 
        25, 25, 25, 24, 24, 24, 24, 24, 24, 24, 
        24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 
        24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 
        24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 
        24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 
        24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 
        24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 
        24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 
        24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 
        24, 24, 24, 24, 24, 24
    };





}
