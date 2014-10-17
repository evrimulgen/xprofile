// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.instrumentation.io;

import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.io.*;
import java.nio.ByteBuffer;

// Referenced classes of package com.newrelic.agent.android.instrumentation.io:
//            StreamCompleteListenerSource, StreamCompleteListenerManager, StreamCompleteEvent, StreamCompleteListener

public final class CountingInputStream extends InputStream
    implements StreamCompleteListenerSource
{

    public CountingInputStream(InputStream inputstream)
    {
        count = 0L;
        listenerManager = new StreamCompleteListenerManager();
        enableBuffering = false;
        impl = inputstream;
        if(enableBuffering)
        {
            buffer = ByteBuffer.allocate(Agent.getResponseBodyLimit());
            fillBuffer();
            return;
        } else
        {
            buffer = null;
            return;
        }
    }

    public CountingInputStream(InputStream inputstream, boolean flag)
    {
        count = 0L;
        listenerManager = new StreamCompleteListenerManager();
        enableBuffering = false;
        impl = inputstream;
        enableBuffering = flag;
        if(flag)
        {
            buffer = ByteBuffer.allocate(Agent.getResponseBodyLimit());
            fillBuffer();
            return;
        } else
        {
            buffer = null;
            return;
        }
    }

    private boolean bufferEmpty()
    {
        return !buffer.hasRemaining();
    }

    private boolean bufferHasBytes(long l)
    {
        return (long)buffer.remaining() >= l;
    }

    private void notifyStreamComplete()
    {
        if(!listenerManager.isComplete())
            listenerManager.notifyStreamComplete(new StreamCompleteEvent(this, count));
    }

    private void notifyStreamError(Exception exception)
    {
        if(!listenerManager.isComplete())
            listenerManager.notifyStreamError(new StreamCompleteEvent(this, count, exception));
    }

    private int readBuffer()
    {
        if(bufferEmpty())
            return -1;
        else
            return buffer.get();
    }

    private int readBufferBytes(byte abyte0[])
    {
        return readBufferBytes(abyte0, 0, abyte0.length);
    }

    private int readBufferBytes(byte abyte0[], int i, int j)
    {
        if(bufferEmpty())
        {
            return -1;
        } else
        {
            int k = buffer.remaining();
            buffer.get(abyte0, i, j);
            return k - buffer.remaining();
        }
    }

    public void addStreamCompleteListener(StreamCompleteListener streamcompletelistener)
    {
        listenerManager.addStreamCompleteListener(streamcompletelistener);
    }

    public int available()
        throws IOException
    {
        boolean flag = enableBuffering;
        int i = 0;
        if(flag)
            i = buffer.remaining();
        int j;
        try
        {
            j = impl.available();
        }
        catch(IOException ioexception)
        {
            notifyStreamError(ioexception);
            throw ioexception;
        }
        return j + i;
    }

    public void close()
        throws IOException
    {
        try
        {
            impl.close();
            notifyStreamComplete();
            return;
        }
        catch(IOException ioexception)
        {
            notifyStreamError(ioexception);
            throw ioexception;
        }
    }

    public void fillBuffer()
    {
        if(buffer != null && buffer.hasArray()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int j = impl.read(buffer.array(), 0, buffer.capacity());
        int i = j;
_L4:
        if(i <= 0)
        {
            buffer.limit(0);
            return;
        }
        break; /* Loop/switch isn't completed */
        IOException ioexception;
        ioexception;
        log.error(ioexception.toString());
        i = 0;
        if(true) goto _L4; else goto _L3
_L3:
        if(i >= buffer.capacity()) goto _L1; else goto _L5
_L5:
        buffer.limit(i);
        return;
    }

    public String getBufferAsString()
    {
        if(buffer != null)
        {
            byte abyte0[] = new byte[buffer.limit()];
            for(int i = 0; i < buffer.limit(); i++)
                abyte0[i] = buffer.get(i);

            return new String(abyte0);
        } else
        {
            return "";
        }
    }

    public void mark(int i)
    {
        if(!markSupported())
        {
            return;
        } else
        {
            impl.mark(i);
            return;
        }
    }

    public boolean markSupported()
    {
        return impl.markSupported();
    }

    public int read()
        throws IOException
    {
        int i;
        if(enableBuffering && bufferHasBytes(1L))
        {
            int j = readBuffer();
            if(j >= 0)
                count = 1L + count;
            return j;
        }
        try
        {
            i = impl.read();
        }
        catch(IOException ioexception)
        {
            notifyStreamError(ioexception);
            throw ioexception;
        }
        if(i < 0)
            break MISSING_BLOCK_LABEL_61;
        count = 1L + count;
        break MISSING_BLOCK_LABEL_76;
        notifyStreamComplete();
        return i;
    }

    public int read(byte abyte0[])
        throws IOException
    {
        int i = abyte0.length;
        boolean flag = enableBuffering;
        int j = 0;
        if(flag)
        {
            if(bufferHasBytes(i))
            {
                int i1 = readBufferBytes(abyte0);
                if(i1 >= 0)
                {
                    count = count + (long)i1;
                    return i1;
                } else
                {
                    throw new RuntimeException("readBufferBytes failed");
                }
            }
            int l = buffer.remaining();
            j = 0;
            if(l > 0)
            {
                j = readBufferBytes(abyte0, 0, l);
                if(j < 0)
                    throw new RuntimeException("partial read from buffer failed");
                i -= j;
                count = count + (long)j;
            }
        }
        int k;
        try
        {
            k = impl.read(abyte0, j, i);
        }
        catch(IOException ioexception)
        {
            log.error(ioexception.toString());
            System.out.println((new StringBuilder()).append("NOTIFY STREAM ERROR: ").append(ioexception).toString());
            ioexception.printStackTrace();
            notifyStreamError(ioexception);
            throw ioexception;
        }
        if(k < 0)
            break MISSING_BLOCK_LABEL_156;
        count = count + (long)k;
        return k + j;
        if(j > 0)
            break MISSING_BLOCK_LABEL_168;
        notifyStreamComplete();
        return k;
        return j;
    }

    public int read(byte abyte0[], int i, int j)
        throws IOException
    {
        int k = j;
        boolean flag = enableBuffering;
        int l = 0;
        if(flag)
        {
            if(bufferHasBytes(k))
            {
                int k1 = readBufferBytes(abyte0, i, j);
                if(k1 >= 0)
                {
                    count = count + (long)k1;
                    return k1;
                } else
                {
                    throw new RuntimeException("readBufferBytes failed");
                }
            }
            int j1 = buffer.remaining();
            l = 0;
            if(j1 > 0)
            {
                l = readBufferBytes(abyte0, i, j1);
                if(l < 0)
                    throw new RuntimeException("partial read from buffer failed");
                k -= l;
                count = count + (long)l;
            }
        }
        int i1;
        try
        {
            i1 = impl.read(abyte0, i + l, k);
        }
        catch(IOException ioexception)
        {
            notifyStreamError(ioexception);
            throw ioexception;
        }
        if(i1 < 0)
            break MISSING_BLOCK_LABEL_166;
        count = count + (long)i1;
        return i1 + l;
        if(l > 0)
            break MISSING_BLOCK_LABEL_178;
        notifyStreamComplete();
        return i1;
        return l;
    }

    public void removeStreamCompleteListener(StreamCompleteListener streamcompletelistener)
    {
        listenerManager.removeStreamCompleteListener(streamcompletelistener);
    }

    public void reset()
        throws IOException
    {
        if(!markSupported())
            return;
        try
        {
            impl.reset();
            return;
        }
        catch(IOException ioexception)
        {
            notifyStreamError(ioexception);
            throw ioexception;
        }
    }

    public void setBufferingEnabled(boolean flag)
    {
        enableBuffering = flag;
    }

    public long skip(long l)
        throws IOException
    {
label0:
        {
            long l1 = l;
            if(enableBuffering)
            {
                if(bufferHasBytes(l))
                {
                    buffer.position((int)l);
                    count = l + count;
                    return l;
                }
                l1 = l - (long)buffer.remaining();
                if(l1 <= 0L)
                    break label0;
                buffer.position(buffer.remaining());
            }
            long l2;
            try
            {
                l2 = impl.skip(l1);
                count = l2 + count;
            }
            catch(IOException ioexception)
            {
                notifyStreamError(ioexception);
                throw ioexception;
            }
            return l2;
        }
        throw new RuntimeException("partial read from buffer (skip) failed");
    }

    private static final AgentLog log = AgentLogManager.getAgentLog();
    private final ByteBuffer buffer;
    private long count;
    private boolean enableBuffering;
    private final InputStream impl;
    private final StreamCompleteListenerManager listenerManager;

}
