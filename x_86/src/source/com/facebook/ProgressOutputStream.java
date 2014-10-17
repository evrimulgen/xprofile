// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.facebook;

import android.os.Handler;
import java.io.*;
import java.util.*;

// Referenced classes of package com.facebook:
//            RequestOutputStream, Settings, RequestProgress, RequestBatch, 
//            Request

class ProgressOutputStream extends FilterOutputStream
    implements RequestOutputStream
{

    ProgressOutputStream(OutputStream outputstream, RequestBatch requestbatch, Map map, long l)
    {
        super(outputstream);
        requests = requestbatch;
        progressMap = map;
        maxProgress = l;
    }

    private void addProgress(long l)
    {
        if(currentRequestProgress != null)
            currentRequestProgress.addProgress(l);
        batchProgress = l + batchProgress;
        if(batchProgress >= lastReportedProgress + threshold || batchProgress >= maxProgress)
            reportBatchProgress();
    }

    private void reportBatchProgress()
    {
        if(batchProgress > lastReportedProgress)
        {
            Iterator iterator = requests.getCallbacks().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                RequestBatch.Callback callback = (RequestBatch.Callback)iterator.next();
                if(callback instanceof RequestBatch.OnProgressCallback)
                {
                    Handler handler = requests.getCallbackHandler();
                    final RequestBatch.OnProgressCallback progressCallback = (RequestBatch.OnProgressCallback)callback;
                    if(handler == null)
                        progressCallback.onBatchProgress(requests, batchProgress, maxProgress);
                    else
                        handler.post(new Runnable() {

                            public void run()
                            {
                                progressCallback.onBatchProgress(requests, batchProgress, maxProgress);
                            }

                            final ProgressOutputStream this$0;
                            final RequestBatch.OnProgressCallback val$progressCallback;

            
            {
                this$0 = ProgressOutputStream.this;
                progressCallback = onprogresscallback;
                super();
            }
                        }
);
                }
            } while(true);
            lastReportedProgress = batchProgress;
        }
    }

    public void close()
        throws IOException
    {
        super.close();
        for(Iterator iterator = progressMap.values().iterator(); iterator.hasNext(); ((RequestProgress)iterator.next()).reportProgress());
        reportBatchProgress();
    }

    long getBatchProgress()
    {
        return batchProgress;
    }

    long getMaxProgress()
    {
        return maxProgress;
    }

    public void setCurrentRequest(Request request)
    {
        RequestProgress requestprogress;
        if(request != null)
            requestprogress = (RequestProgress)progressMap.get(request);
        else
            requestprogress = null;
        currentRequestProgress = requestprogress;
    }

    public void write(int i)
        throws IOException
    {
        out.write(i);
        addProgress(1L);
    }

    public void write(byte abyte0[])
        throws IOException
    {
        out.write(abyte0);
        addProgress(abyte0.length);
    }

    public void write(byte abyte0[], int i, int j)
        throws IOException
    {
        out.write(abyte0, i, j);
        addProgress(j);
    }

    private long batchProgress;
    private RequestProgress currentRequestProgress;
    private long lastReportedProgress;
    private long maxProgress;
    private final Map progressMap;
    private final RequestBatch requests;
    private final long threshold = Settings.getOnProgressThreshold();



}
