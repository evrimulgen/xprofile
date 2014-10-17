// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.spdy;

import java.io.IOException;

// Referenced classes of package com.squareup.okhttp.internal.spdy:
//            SpdyStream, ErrorCode

public interface IncomingStreamHandler
{

    public abstract void receive(SpdyStream spdystream)
        throws IOException;

    public static final IncomingStreamHandler REFUSE_INCOMING_STREAMS = new IncomingStreamHandler() {

        public void receive(SpdyStream spdystream)
            throws IOException
        {
            spdystream.close(ErrorCode.REFUSED_STREAM);
        }

    }
;

}
