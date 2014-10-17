// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.models;

import android.text.TextUtils;
import android.util.Log;
import io.segment.android.Logger;
import io.segment.android.utils.ISO8601;
import java.text.ParseException;
import java.util.*;
import org.json.*;

public class EasyJSONObject extends JSONObject
{

    public EasyJSONObject()
    {
    }

    public EasyJSONObject(JSONObject jsonobject)
    {
        if(jsonobject == null) goto _L2; else goto _L1
_L1:
        Iterator iterator = jsonobject.keys();
_L5:
        if(iterator.hasNext()) goto _L3; else goto _L2
_L2:
        return;
_L3:
        String s = (String)iterator.next();
        try
        {
            putObject(s, jsonobject.get(s));
        }
        catch(JSONException jsonexception)
        {
            Logger.w((new StringBuilder("JSON object had an invalid value during merge. ")).append(Log.getStackTraceString(jsonexception)).toString());
        }
        if(true) goto _L5; else goto _L4
_L4:
    }

    public transient EasyJSONObject(Object aobj[])
    {
        if(aobj == null) goto _L2; else goto _L1
_L1:
        if(aobj.length % 2 == 0) goto _L4; else goto _L3
_L3:
        Logger.w("Segment.io objects must be initialized with an even number of arguments, like so: [Key, Value, Key, Value]");
_L2:
        return;
_L4:
        if(aobj.length > 1)
        {
            int i = 0;
            while(i < aobj.length) 
            {
                putObject(aobj[i].toString(), aobj[i + 1]);
                i += 2;
            }
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public static boolean equals(Object obj, Object obj1)
    {
        if(obj != null && obj1 != null) goto _L2; else goto _L1
_L1:
        boolean flag;
        flag = false;
        if(obj == obj1)
            flag = true;
_L6:
        return flag;
_L2:
        if(!(obj instanceof JSONObject)) goto _L4; else goto _L3
_L3:
        boolean flag2;
        flag2 = obj1 instanceof JSONObject;
        flag = false;
        if(!flag2) goto _L6; else goto _L5
_L5:
        boolean flag3;
        flag3 = equals((JSONObject)obj, (JSONObject)obj1);
        flag = false;
        if(!flag3) goto _L6; else goto _L7
_L7:
        return true;
_L4:
        boolean flag1;
        if(!(obj instanceof JSONArray))
            continue; /* Loop/switch isn't completed */
        flag1 = obj1 instanceof JSONArray;
        flag = false;
        if(!flag1) goto _L6; else goto _L8
_L8:
        if(equals((JSONArray)obj, (JSONArray)obj1)) goto _L7; else goto _L9
_L9:
        return false;
        if(obj.equals(obj1)) goto _L7; else goto _L10
_L10:
        return false;
    }

    public static boolean equals(JSONArray jsonarray, JSONArray jsonarray1)
    {
        if(jsonarray != null && jsonarray1 != null) goto _L2; else goto _L1
_L1:
        boolean flag;
        flag = false;
        if(jsonarray == jsonarray1)
            flag = true;
_L4:
        return flag;
_L2:
        int i;
        int j;
        i = jsonarray.length();
        j = jsonarray1.length();
        flag = false;
        if(i != j) goto _L4; else goto _L3
_L3:
        int k = 0;
_L6:
        if(k >= jsonarray.length())
            return true;
        boolean flag1;
        try
        {
            flag1 = equals(jsonarray.get(k), jsonarray1.get(k));
        }
        catch(JSONException jsonexception)
        {
            return false;
        }
        flag = false;
        if(!flag1) goto _L4; else goto _L5
_L5:
        k++;
          goto _L6
    }

    public static boolean equals(JSONObject jsonobject, JSONObject jsonobject1)
    {
        if(jsonobject != null && jsonobject1 != null) goto _L2; else goto _L1
_L1:
        boolean flag;
        flag = false;
        if(jsonobject == jsonobject1)
            flag = true;
_L4:
        return flag;
_L2:
        int i = jsonobject.length();
        int j = jsonobject1.length();
        flag = false;
        if(i != j)
            continue; /* Loop/switch isn't completed */
        Iterator iterator = jsonobject.keys();
        do
        {
            if(!iterator.hasNext())
                return true;
            String s = (String)iterator.next();
            boolean flag1 = jsonobject1.has(s);
            flag = false;
            if(!flag1)
                continue; /* Loop/switch isn't completed */
            boolean flag2;
            try
            {
                flag2 = equals(jsonobject.get(s), jsonobject1.get(s));
            }
            catch(JSONException jsonexception)
            {
                return false;
            }
            if(!flag2)
                return false;
        } while(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof JSONObject))
            return false;
        else
            return equals(((JSONObject) (this)), (JSONObject)obj);
    }

    public Object get(String s)
    {
        Object obj;
        try
        {
            obj = super.get(s);
        }
        catch(JSONException jsonexception)
        {
            Logger.e((new StringBuilder("Failed to read json key. ")).append(String.format("[%s] : ", new Object[] {
                s
            })).toString());
            return null;
        }
        return obj;
    }

    public List getArray(String s)
    {
        JSONArray jsonarray;
        LinkedList linkedlist;
        int i;
        try
        {
            jsonarray = getJSONArray(s);
            linkedlist = new LinkedList();
        }
        catch(JSONException jsonexception)
        {
            return null;
        }
        i = 0;
        if(i >= jsonarray.length())
            return linkedlist;
        linkedlist.add(jsonarray.get(i));
        i++;
        if(false)
            ;
        else
            break MISSING_BLOCK_LABEL_18;
    }

    public Boolean getBoolean(String s, Boolean boolean1)
    {
        Boolean boolean2;
        try
        {
            boolean2 = Boolean.valueOf(super.getBoolean(s));
        }
        catch(JSONException jsonexception)
        {
            return boolean1;
        }
        return boolean2;
    }

    public Calendar getCalendar(String s)
    {
        String s1 = optString(s, null);
        Calendar calendar = null;
        if(s1 != null)
        {
            Calendar calendar1;
            try
            {
                calendar1 = ISO8601.toCalendar(s1);
            }
            catch(ParseException parseexception)
            {
                Logger.w((new StringBuilder("Failed to parse timestamp string into ISO 8601 format: ")).append(Log.getStackTraceString(parseexception)).toString());
                return null;
            }
            calendar = calendar1;
        }
        return calendar;
    }

    public Double getDouble(String s, Double double1)
    {
        Double double2;
        try
        {
            double2 = Double.valueOf(super.getDouble(s));
        }
        catch(JSONException jsonexception)
        {
            return double1;
        }
        return double2;
    }

    public Integer getInt(String s, Integer integer)
    {
        Integer integer1;
        try
        {
            integer1 = Integer.valueOf(super.getInt(s));
        }
        catch(JSONException jsonexception)
        {
            return integer;
        }
        return integer1;
    }

    public JSONObject getObject(String s)
    {
        JSONObject jsonobject;
        try
        {
            jsonobject = getJSONObject(s);
        }
        catch(JSONException jsonexception)
        {
            return null;
        }
        return jsonobject;
    }

    public String getString(String s)
    {
        String s1;
        try
        {
            s1 = super.getString(s);
        }
        catch(JSONException jsonexception)
        {
            return null;
        }
        return s1;
    }

    public String getString(String s, String s1)
    {
        String s2;
        boolean flag;
        try
        {
            s2 = super.getString(s);
            flag = TextUtils.isEmpty(s2);
        }
        catch(JSONException jsonexception)
        {
            return s1;
        }
        if(flag)
            return s1;
        else
            return s2;
    }

    public JSONObject put(String s, double d)
    {
        return putObject(s, Double.valueOf(d));
    }

    public JSONObject put(String s, int i)
    {
        return putObject(s, Integer.valueOf(i));
    }

    public JSONObject put(String s, Object obj)
    {
        return putObject(s, obj);
    }

    public JSONObject put(String s, String s1)
    {
        return putObject(s, s1);
    }

    public JSONObject put(String s, JSONArray jsonarray)
    {
        return putObject(s, jsonarray);
    }

    public JSONObject put(String s, JSONObject jsonobject)
    {
        return putObject(s, jsonobject);
    }

    public JSONObject put(String s, boolean flag)
    {
        return putObject(s, Boolean.valueOf(flag));
    }

    public void put(String s, Calendar calendar)
    {
        if(calendar == null)
        {
            remove(s);
            return;
        } else
        {
            putObject(s, ISO8601.fromCalendar(calendar));
            return;
        }
    }

    public JSONObject putObject(String s, Object obj)
    {
        JSONObject jsonobject;
        try
        {
            jsonobject = super.put(s, obj);
        }
        catch(JSONException jsonexception)
        {
            Logger.e((new StringBuilder("Failed to add json key => value")).append(String.format("[%s => %s] : ", new Object[] {
                s, obj
            })).append(Log.getStackTraceString(jsonexception)).toString());
            return null;
        }
        return jsonobject;
    }

    public Map toStringMap()
    {
        HashMap hashmap = new HashMap();
        Iterator iterator = keys();
        do
        {
            if(!iterator.hasNext())
                return hashmap;
            String s = (String)iterator.next();
            String s1 = (new StringBuilder()).append(get(s)).toString();
            if(s1.length() > 255)
                s1 = s1.substring(0, 255);
            hashmap.put(s, s1);
        } while(true);
    }
}
