// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.models;

import java.util.Calendar;
import java.util.List;
import org.json.JSONArray;

// Referenced classes of package io.segment.android.models:
//            EasyJSONObject, Context

public class Batch extends EasyJSONObject
{

    public Batch(String s, List list)
    {
        setWriteKey(s);
        setBatch(list);
    }

    public List getBatch()
    {
        return getArray("batch");
    }

    public Context getContext()
    {
        org.json.JSONObject jsonobject = getObject("context");
        if(jsonobject == null)
            return null;
        else
            return new Context(jsonobject);
    }

    public Calendar getRequestTimestamp()
    {
        return getCalendar("requestTimestamp");
    }

    public String getWriteKey()
    {
        return optString("writeKey", null);
    }

    public void setBatch(List list)
    {
        put("batch", new JSONArray(list));
    }

    public void setContext(Context context)
    {
        put("context", context);
    }

    public void setRequestTimestamp(Calendar calendar)
    {
        super.put("requestTimestamp", calendar);
    }

    public void setWriteKey(String s)
    {
        put("writeKey", s);
    }

    private static final String BATCH_KEY = "batch";
    private static final String CONTEXT_KEY = "context";
    private static final String REQUEST_TIMESTAMP_KEY = "requestTimestamp";
    private static final String WRITE_KEY = "writeKey";
}
