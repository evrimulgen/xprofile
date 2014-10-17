// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.db;

import android.util.Log;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import io.segment.android.Logger;
import io.segment.android.models.*;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package io.segment.android.db:
//            IPayloadSerializer

public class JsonPayloadSerializer
    implements IPayloadSerializer
{

    public JsonPayloadSerializer()
    {
    }

    public BasePayload deseralize(String s)
    {
        JSONObject jsonobject;
        String s1;
        JVM INSTR new #16  <Class JSONObject>;
        jsonobject = JSONObjectInstrumentation.init(s);
        s1 = jsonobject.getString("action");
        if(s1.equals("identify"))
            return new Identify(jsonobject);
        if(!s1.equals("track")) goto _L2; else goto _L1
_L1:
        Track track = new Track(jsonobject);
        return track;
        JSONException jsonexception;
        jsonexception;
        Logger.e((new StringBuilder("Failed to convert json to base payload: ")).append(Log.getStackTraceString(jsonexception)).toString());
_L4:
        return null;
_L2:
        if(s1.equals("alias"))
            return new Alias(jsonobject);
        if(s1.equals("group"))
            return new Group(jsonobject);
        if(!s1.equals("screen")) goto _L4; else goto _L3
_L3:
        Screen screen = new Screen(jsonobject);
        return screen;
    }

    public String serialize(BasePayload basepayload)
    {
        return basepayload.toString();
    }
}
