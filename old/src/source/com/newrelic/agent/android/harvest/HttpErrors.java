// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.JsonArray;
import java.util.*;

// Referenced classes of package com.newrelic.agent.android.harvest:
//            HttpError

public class HttpErrors extends HarvestableArray
{

    public HttpErrors()
    {
    }

    public void addHttpError(HttpError httperror)
    {
        httperror;
        JVM INSTR monitorenter ;
        Iterator iterator = httpErrors.iterator();
        HttpError httperror1;
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_55;
            httperror1 = (HttpError)iterator.next();
        } while(!httperror.getHash().equals(httperror1.getHash()));
        httperror1.incrementCount();
        httperror;
        JVM INSTR monitorexit ;
        return;
        httpErrors.add(httperror);
        httperror;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        httperror;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public JsonArray asJsonArray()
    {
        JsonArray jsonarray = new JsonArray();
        for(Iterator iterator = httpErrors.iterator(); iterator.hasNext(); jsonarray.add(((HttpError)iterator.next()).asJson()));
        return jsonarray;
    }

    public void clear()
    {
        httpErrors.clear();
    }

    public int count()
    {
        return httpErrors.size();
    }

    public Collection getHttpErrors()
    {
        return httpErrors;
    }

    public void removeHttpError(HttpError httperror)
    {
        this;
        JVM INSTR monitorenter ;
        httpErrors.remove(httperror);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private final Collection httpErrors = new ArrayList();
}
