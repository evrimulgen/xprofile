// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.flush;

import android.os.Handler;
import android.util.Log;
import io.segment.android.Analytics;
import io.segment.android.Logger;
import io.segment.android.db.IPayloadDatabaseLayer;
import io.segment.android.models.BasePayload;
import io.segment.android.models.Batch;
import io.segment.android.request.IRequester;
import io.segment.android.stats.AnalyticsStatistics;
import io.segment.android.utils.LooperThreadWithHandler;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.apache.http.*;
import org.apache.http.util.EntityUtils;

// Referenced classes of package io.segment.android.flush:
//            IFlushLayer

public class FlushThread extends LooperThreadWithHandler
    implements IFlushLayer
{
    public static interface BatchFactory
    {

        public abstract Batch create(List list);
    }

    public static interface RequestCallback
    {

        public abstract void onRequestCompleted(boolean flag);
    }


    public FlushThread(IRequester irequester, BatchFactory batchfactory, IPayloadDatabaseLayer ipayloaddatabaselayer)
    {
        requester = irequester;
        batchFactory = batchfactory;
        databaseLayer = ipayloaddatabaselayer;
    }

    private void performRequest(final Batch batch, final RequestCallback callback)
    {
        handler().post(new Runnable() {

            public void run()
            {
                HttpResponse httpresponse;
                boolean flag;
                httpresponse = requester.send(batch);
                flag = false;
                if(httpresponse != null) goto _L2; else goto _L1
_L1:
                Logger.w("Failed to make request to the server.");
_L4:
                if(callback != null)
                    callback.onRequestCompleted(flag);
                return;
_L2:
                if(httpresponse.getStatusLine().getStatusCode() != 200)
                {
                    try
                    {
                        String s = EntityUtils.toString(httpresponse.getEntity());
                        Logger.e((new StringBuilder("Received a failed response from the server.")).append(s).toString());
                    }
                    catch(ParseException parseexception)
                    {
                        Logger.w((new StringBuilder("Failed to parse the response from the server.")).append(Log.getStackTraceString(parseexception)).toString());
                        flag = false;
                        continue; /* Loop/switch isn't completed */
                    }
                    catch(IOException ioexception)
                    {
                        Logger.w((new StringBuilder("Failed to read the response from the server.")).append(Log.getStackTraceString(ioexception)).toString());
                        flag = false;
                        continue; /* Loop/switch isn't completed */
                    }
                    flag = false;
                    continue; /* Loop/switch isn't completed */
                }
                Logger.d("Successfully sent a batch to the server");
                flag = true;
                if(true) goto _L4; else goto _L3
_L3:
            }

            final FlushThread this$0;
            private final Batch val$batch;
            private final RequestCallback val$callback;

            
            {
                this$0 = FlushThread.this;
                batch = batch1;
                callback = requestcallback;
                super();
            }
        }
);
    }

    public void flush(final IFlushLayer.FlushCallback callback)
    {
        databaseLayer.nextPayload(new io.segment.android.db.IPayloadDatabaseLayer.PayloadCallback() {

            public void onPayload(final long minId, long l, final List payloads)
            {
                if(payloads.size() > 0)
                {
                    final long start = System.currentTimeMillis();
                    Batch batch = batchFactory.create(payloads);
                    performRequest(batch, l. new RequestCallback() {

                        public void onRequestCompleted(boolean flag)
                        {
                            Iterator iterator = payloads.iterator();
                            do
                            {
                                BasePayload basepayload;
                                if(!iterator.hasNext())
                                    if(!flag)
                                    {
                                        if(callback != null)
                                            callback.onFlushCompleted(false);
                                        return;
                                    } else
                                    {
                                        long l = System.currentTimeMillis() - start;
                                        Analytics.getStatistics().updateRequestTime(l);
                                        databaseLayer.removePayloads(minId, maxId, callback. new io.segment.android.db.IPayloadDatabaseLayer.RemoveCallback() {

                                            public void onRemoved(int i)
                                            {
                                                AnalyticsStatistics analyticsstatistics = Analytics.getStatistics();
                                                if(i != -1) goto _L2; else goto _L1
_L1:
                                                int l = 0;
_L8:
                                                if(l < i) goto _L4; else goto _L3
_L3:
                                                Logger.e("We failed to remove payload from the database.");
                                                if(callback != null)
                                                    callback.onFlushCompleted(false);
_L6:
                                                return;
_L4:
                                                analyticsstatistics.updateFailed(1.0D);
                                                l++;
                                                continue; /* Loop/switch isn't completed */
_L2:
                                                if(i == 0)
                                                {
                                                    int k = 0;
                                                    do
                                                    {
                                                        if(k >= i)
                                                        {
                                                            Logger.e("We didn't end up removing anything from the database.");
                                                            if(callback != null)
                                                            {
                                                                callback.onFlushCompleted(false);
                                                                return;
                                                            }
                                                            continue; /* Loop/switch isn't completed */
                                                        }
                                                        analyticsstatistics.updateFailed(1.0D);
                                                        k++;
                                                    } while(true);
                                                }
                                                int j = 0;
                                                do
                                                {
                                                    if(j >= i)
                                                    {
                                                        flush(callback);
                                                        return;
                                                    }
                                                    analyticsstatistics.updateSuccessful(1.0D);
                                                    j++;
                                                } while(true);
                                                if(true) goto _L6; else goto _L5
_L5:
                                                if(true) goto _L8; else goto _L7
_L7:
                                            }

                                            final _cls1 this$2;
                                            private final IFlushLayer.FlushCallback val$callback;

            
            {
                this$2 = final__pcls1;
                callback = IFlushLayer.FlushCallback.this;
                super();
            }
                                        }
);
                                        return;
                                    }
                                basepayload = (BasePayload)iterator.next();
                                if(flag)
                                    Logger.i((new StringBuilder("Item ")).append(basepayload.toDescription()).append(" successfully sent.").toString());
                                else
                                    Logger.w((new StringBuilder("Item ")).append(basepayload.toDescription()).append(" failed to be sent.").toString());
                            } while(true);
                        }

                        final _cls1 this$1;
                        private final IFlushLayer.FlushCallback val$callback;
                        private final long val$maxId;
                        private final long val$minId;
                        private final List val$payloads;
                        private final long val$start;


            
            {
                this$1 = final__pcls1;
                payloads = list;
                callback = flushcallback;
                start = l;
                minId = l1;
                maxId = J.this;
                super();
            }
                    }
);
                } else
                if(callback != null)
                {
                    callback.onFlushCompleted(true);
                    return;
                }
            }

            final FlushThread this$0;
            private final IFlushLayer.FlushCallback val$callback;


            
            {
                this$0 = FlushThread.this;
                callback = flushcallback;
                super();
            }
        }
);
    }

    private BatchFactory batchFactory;
    private IPayloadDatabaseLayer databaseLayer;
    private IRequester requester;




}
