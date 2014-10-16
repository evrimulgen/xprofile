// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.models;

import java.util.Calendar;
import java.util.UUID;
import org.json.JSONObject;

// Referenced classes of package io.segment.android.models:
//            EasyJSONObject, Context

public class BasePayload extends EasyJSONObject
{

    public BasePayload(String s, String s1, Calendar calendar, Context context)
    {
        if(calendar == null)
            calendar = Calendar.getInstance();
        if(context == null)
            context = new Context();
        setSessionId(s);
        setUserId(s1);
        setTimestamp(calendar);
        setContext(context);
        setRequestId(UUID.randomUUID().toString());
    }

    public BasePayload(JSONObject jsonobject)
    {
        super(jsonobject);
    }

    public Context getContext()
    {
        JSONObject jsonobject = getObject("context");
        if(jsonobject == null)
            return null;
        else
            return new Context(jsonobject);
    }

    public String getRequestId()
    {
        return optString("requestId", null);
    }

    public String getSessionId()
    {
        return optString("sessionId", null);
    }

    public Calendar getTimestamp()
    {
        return getCalendar("timestamp");
    }

    public String getUserId()
    {
        return optString("userId", null);
    }

    public void setContext(Context context)
    {
        put("context", context);
    }

    public void setRequestId(String s)
    {
        put("requestId", s);
    }

    public void setSessionId(String s)
    {
        put("sessionId", s);
    }

    public void setTimestamp(Calendar calendar)
    {
        super.put("timestamp", calendar);
    }

    public void setUserId(String s)
    {
        put("userId", s);
    }

    public String toDescription()
    {
        return (new StringBuilder("[Item ")).append(getRequestId()).append("] ").append(getString("action", "action")).toString();
    }

    private static final String CONTEXT_KEY = "context";
    private static final String REQUEST_ID_KEY = "requestId";
    private static final String SESSION_ID_KEY = "sessionId";
    private static final String TIMESTAMP_KEY = "timestamp";
    private static final String USER_ID_KEY = "userId";
}
