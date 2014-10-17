// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.db;

import android.os.Handler;
import android.util.Pair;
import io.segment.android.models.BasePayload;
import io.segment.android.utils.LooperThreadWithHandler;
import java.util.*;

// Referenced classes of package io.segment.android.db:
//            IPayloadDatabaseLayer, PayloadDatabase

public class PayloadDatabaseThread extends LooperThreadWithHandler
    implements IPayloadDatabaseLayer
{

    public PayloadDatabaseThread(PayloadDatabase payloaddatabase)
    {
        database = payloaddatabase;
    }

    public void enqueue(final BasePayload payload, final IPayloadDatabaseLayer.EnqueueCallback callback)
    {
        handler().post(new Runnable() {

            public void run()
            {
                boolean flag = database.addPayload(payload);
                long l = database.getRowCount();
                if(callback != null)
                    callback.onEnqueue(flag, l);
            }

            final PayloadDatabaseThread this$0;
            private final IPayloadDatabaseLayer.EnqueueCallback val$callback;
            private final BasePayload val$payload;

            
            {
                this$0 = PayloadDatabaseThread.this;
                payload = basepayload;
                callback = enqueuecallback;
                super();
            }
        }
);
    }

    public void nextPayload(final IPayloadDatabaseLayer.PayloadCallback callback)
    {
        handler().post(new Runnable() {

            public void run()
            {
                List list;
                long l;
                long l1;
                LinkedList linkedlist;
                list = database.getEvents(20);
                l = 0L;
                l1 = 0L;
                linkedlist = new LinkedList();
                if(list.size() <= 0) goto _L2; else goto _L1
_L1:
                Iterator iterator;
                l = ((Long)((Pair)list.get(0)).first).longValue();
                l1 = ((Long)((Pair)list.get(-1 + list.size())).first).longValue();
                iterator = list.iterator();
_L5:
                if(iterator.hasNext()) goto _L3; else goto _L2
_L2:
                if(callback != null)
                    callback.onPayload(l, l1, linkedlist);
                return;
_L3:
                linkedlist.add((BasePayload)((Pair)iterator.next()).second);
                if(true) goto _L5; else goto _L4
_L4:
            }

            final PayloadDatabaseThread this$0;
            private final IPayloadDatabaseLayer.PayloadCallback val$callback;

            
            {
                this$0 = PayloadDatabaseThread.this;
                callback = payloadcallback;
                super();
            }
        }
);
    }

    public void removePayloads(final long minId, final long maxId, final IPayloadDatabaseLayer.RemoveCallback callback)
    {
        handler().post(new Runnable() {

            public void run()
            {
                int i = database.removeEvents(minId, maxId);
                if(callback != null)
                    callback.onRemoved(i);
            }

            final PayloadDatabaseThread this$0;
            private final IPayloadDatabaseLayer.RemoveCallback val$callback;
            private final long val$maxId;
            private final long val$minId;

            
            {
                this$0 = PayloadDatabaseThread.this;
                minId = l;
                maxId = l1;
                callback = removecallback;
                super();
            }
        }
);
    }

    private PayloadDatabase database;

}
