// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.mixpanel.android.mpmetrics;

import android.util.Log;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.*;
import org.json.*;

class WaitingPeopleRecord
{

    public WaitingPeopleRecord()
    {
        mAdds = new HashMap();
        mAppends = new ArrayList();
        mSets = new JSONObject();
    }

    public List appendMessages()
    {
        return mAppends;
    }

    public void appendToWaitingPeopleRecord(JSONObject jsonobject)
    {
        mAppends.add(jsonobject);
    }

    public Map incrementMessage()
    {
        return mAdds;
    }

    public void incrementToWaitingPeopleRecord(Map map)
    {
        Iterator iterator = map.keySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            String s = (String)iterator.next();
            Number number = (Number)mAdds.get(s);
            Number number1 = (Number)map.get(s);
            if(number == null && number1 != null)
                mAdds.put(s, Double.valueOf(number1.doubleValue()));
            else
            if(number != null && number1 != null)
                mAdds.put(s, Double.valueOf(number.doubleValue() + number1.doubleValue()));
        } while(true);
    }

    public void readFromJSONString(String s)
        throws JSONException
    {
        JSONObject _tmp = JVM INSTR new #32  <Class JSONObject>;
        JSONObject jsonobject = JSONObjectInstrumentation.init(s);
        JSONObject jsonobject1 = new JSONObject();
        if(jsonobject.has("$set"))
            jsonobject1 = jsonobject.getJSONObject("$set");
        HashMap hashmap = new HashMap();
        if(jsonobject.has("$add"))
        {
            JSONObject jsonobject2 = jsonobject.getJSONObject("$add");
            String s1;
            for(Iterator iterator = jsonobject2.keys(); iterator.hasNext(); hashmap.put(s1, Double.valueOf(jsonobject2.getDouble(s1))))
                s1 = (String)iterator.next();

        }
        ArrayList arraylist = new ArrayList();
        if(jsonobject.has("$append"))
        {
            JSONArray jsonarray = jsonobject.getJSONArray("$append");
            for(int i = 0; i < jsonarray.length(); i++)
                arraylist.add(jsonarray.getJSONObject(i));

        }
        mSets = jsonobject1;
        mAdds = hashmap;
        mAppends = arraylist;
    }

    public JSONObject setMessage()
    {
        return mSets;
    }

    public void setOnWaitingPeopleRecord(JSONObject jsonobject)
        throws JSONException
    {
        String s;
        Object obj;
        for(Iterator iterator = jsonobject.keys(); iterator.hasNext(); mSets.put(s, obj))
        {
            s = (String)iterator.next();
            obj = jsonobject.get(s);
            mAdds.remove(s);
            ArrayList arraylist = new ArrayList();
            Iterator iterator1 = arraylist.iterator();
            do
            {
                if(!iterator1.hasNext())
                    break;
                JSONObject jsonobject1 = (JSONObject)iterator1.next();
                jsonobject1.remove(s);
                if(jsonobject1.length() > 0)
                    arraylist.add(jsonobject1);
            } while(true);
            mAppends = arraylist;
        }

    }

    public String toJSONString()
    {
        JSONObject jsonobject;
        try
        {
            jsonobject = new JSONObject();
            String s2;
            for(Iterator iterator = mAdds.keySet().iterator(); iterator.hasNext(); jsonobject.put(s2, (Double)mAdds.get(s2)))
                s2 = (String)iterator.next();

        }
        catch(JSONException jsonexception)
        {
            Log.e("MixpanelAPI", "Could not write Waiting User Properties to JSON", jsonexception);
            return null;
        }
        JSONArray jsonarray;
        jsonarray = new JSONArray();
        for(Iterator iterator1 = mAppends.iterator(); iterator1.hasNext(); jsonarray.put((JSONObject)iterator1.next()));
        JSONObject jsonobject1;
        String s1;
        jsonobject1 = new JSONObject();
        jsonobject1.put("$set", mSets);
        jsonobject1.put("$add", jsonobject);
        jsonobject1.put("$append", jsonarray);
        if(!(jsonobject1 instanceof JSONObject))
        {
            s1 = jsonobject1.toString();
            break MISSING_BLOCK_LABEL_203;
        }
        String s = JSONObjectInstrumentation.toString((JSONObject)jsonobject1);
        s1 = s;
        return s1;
    }

    private static final String LOGTAG = "MixpanelAPI";
    private Map mAdds;
    private List mAppends;
    private JSONObject mSets;
}
