// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.internal.okio.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

// Referenced classes of package com.squareup.okhttp.internal.spdy:
//            Header, Spdy3

class NameValueBlockReader
{

    public NameValueBlockReader(final BufferedSource source)
    {
        inflaterSource = new InflaterSource(new Source() {

            public void close()
                throws IOException
            {
                source.close();
            }

            public Source deadline(Deadline deadline1)
            {
                source.deadline(deadline1);
                return this;
            }

            public long read(OkBuffer okbuffer, long l)
                throws IOException
            {
                if(compressedLimit == 0)
                    return -1L;
                long l1 = source.read(okbuffer, Math.min(l, compressedLimit));
                if(l1 == -1L)
                {
                    return -1L;
                } else
                {
                    int i = (int)((long)((lang) (this)). - okbuffer);
                    return l1;
                }
            }

            final NameValueBlockReader this$0;
            final BufferedSource val$source;

            
            {
                this$0 = NameValueBlockReader.this;
                source = bufferedsource;
                super();
            }
        }
, new Inflater() {

            public int inflate(byte abyte0[], int i, int j)
                throws DataFormatException
            {
                int k = super.inflate(abyte0, i, j);
                if(k == 0 && needsDictionary())
                {
                    setDictionary(Spdy3.DICTIONARY);
                    k = super.inflate(abyte0, i, j);
                }
                return k;
            }

            final NameValueBlockReader this$0;

            
            {
                this$0 = NameValueBlockReader.this;
                super();
            }
        }
);
        this.source = Okio.buffer(inflaterSource);
    }

    private void doneReading()
        throws IOException
    {
        if(compressedLimit > 0)
        {
            inflaterSource.refill();
            if(compressedLimit != 0)
                throw new IOException((new StringBuilder()).append("compressedLimit > 0: ").append(compressedLimit).toString());
        }
    }

    private ByteString readByteString()
        throws IOException
    {
        int i = source.readInt();
        return source.readByteString(i);
    }

    public void close()
        throws IOException
    {
        source.close();
    }

    public List readNameValueBlock(int i)
        throws IOException
    {
        compressedLimit = i + compressedLimit;
        int j = source.readInt();
        if(j < 0)
            throw new IOException((new StringBuilder()).append("numberOfPairs < 0: ").append(j).toString());
        if(j > 1024)
            throw new IOException((new StringBuilder()).append("numberOfPairs > 1024: ").append(j).toString());
        ArrayList arraylist = new ArrayList(j);
        for(int k = 0; k < j; k++)
        {
            ByteString bytestring = readByteString().toAsciiLowercase();
            ByteString bytestring1 = readByteString();
            if(bytestring.size() == 0)
                throw new IOException("name.size == 0");
            arraylist.add(new Header(bytestring, bytestring1));
        }

        doneReading();
        return arraylist;
    }

    private int compressedLimit;
    private final InflaterSource inflaterSource;
    private final BufferedSource source;



/*
    static int access$022(NameValueBlockReader namevalueblockreader, long l)
    {
        int i = (int)((long)namevalueblockreader.compressedLimit - l);
        namevalueblockreader.compressedLimit = i;
        return i;
    }

*/
}
