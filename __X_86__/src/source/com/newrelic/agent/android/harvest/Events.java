// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.JsonArray;
import java.util.*;

// Referenced classes of package com.newrelic.agent.android.harvest:
//            Event

public class Events extends HarvestableArray
{

    public Events()
    {
    }

    public void addEvent(Event event)
    {
        events.add(event);
    }

    public JsonArray asJsonArray()
    {
        JsonArray jsonarray = new JsonArray();
        for(Iterator iterator = events.iterator(); iterator.hasNext(); jsonarray.add(((Event)iterator.next()).asJson()));
        return jsonarray;
    }

    private final Collection events = new ArrayList();
}
