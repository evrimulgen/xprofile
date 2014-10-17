// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.facebook;

import android.os.Handler;

// Referenced classes of package com.facebook:
//            Settings, Request

class RequestProgress
{

    RequestProgress(Handler handler, Request request1)
    {
        request = request1;
        callbackHandler = handler;
    }

    void addProgress(long l)
    {
        progress = l + progress;
        if(progress >= lastReportedProgress + threshold || progress >= maxProgress)
            reportProgress();
    }

    void addToMax(long l)
    {
        maxProgress = l + maxProgress;
    }

    long getMaxProgress()
    {
        return maxProgress;
    }

    long getProgress()
    {
        return progress;
    }

    void reportProgress()
    {
        if(progress > lastReportedProgress)
        {
            Request.Callback callback = request.getCallback();
            if(maxProgress > 0L && (callback instanceof Request.OnProgressCallback))
            {
                final long currentCopy = progress;
                final long maxProgressCopy = maxProgress;
                final Request.OnProgressCallback callbackCopy = (Request.OnProgressCallback)callback;
                if(callbackHandler == null)
                    callbackCopy.onProgress(currentCopy, maxProgressCopy);
                else
                    callbackHandler.post(new Runnable() {

                        public void run()
                        {
                            callbackCopy.onProgress(currentCopy, maxProgressCopy);
                        }

                        final RequestProgress this$0;
                        final Request.OnProgressCallback val$callbackCopy;
                        final long val$currentCopy;
                        final long val$maxProgressCopy;

            
            {
                this$0 = RequestProgress.this;
                callbackCopy = onprogresscallback;
                currentCopy = l;
                maxProgressCopy = l1;
                super();
            }
                    }
);
                lastReportedProgress = progress;
            }
        }
    }

    private final Handler callbackHandler;
    private long lastReportedProgress;
    private long maxProgress;
    private long progress;
    private final Request request;
    private final long threshold = Settings.getOnProgressThreshold();
}
