// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.facebook;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.*;
import org.json.*;

// Referenced classes of package com.facebook:
//            TokenCachingStrategy, LoggingBehavior

public class SharedPreferencesTokenCachingStrategy extends TokenCachingStrategy
{

    public SharedPreferencesTokenCachingStrategy(Context context)
    {
        this(context, null);
    }

    public SharedPreferencesTokenCachingStrategy(Context context, String s)
    {
        Validate.notNull(context, "context");
        if(Utility.isNullOrEmpty(s))
            s = "com.facebook.SharedPreferencesTokenCachingStrategy.DEFAULT_KEY";
        cacheKey = s;
        Context context1 = context.getApplicationContext();
        if(context1 != null)
            context = context1;
        cache = context.getSharedPreferences(cacheKey, 0);
    }

    private void deserializeKey(String s, Bundle bundle)
        throws JSONException
    {
        JSONObject jsonobject;
        String s2;
        String s1 = cache.getString(s, "{}");
        JVM INSTR new #141 <Class JSONObject>;
        jsonobject = JSONObjectInstrumentation.init(s1);
        s2 = jsonobject.getString("valueType");
        if(!s2.equals("bool")) goto _L2; else goto _L1
_L1:
        bundle.putBoolean(s, jsonobject.getBoolean("value"));
_L4:
        return;
_L2:
        if(s2.equals("bool[]"))
        {
            JSONArray jsonarray8 = jsonobject.getJSONArray("value");
            boolean aflag[] = new boolean[jsonarray8.length()];
            for(int j2 = 0; j2 < aflag.length; j2++)
                aflag[j2] = jsonarray8.getBoolean(j2);

            bundle.putBooleanArray(s, aflag);
            return;
        }
        if(s2.equals("byte"))
        {
            bundle.putByte(s, (byte)jsonobject.getInt("value"));
            return;
        }
        if(s2.equals("byte[]"))
        {
            JSONArray jsonarray7 = jsonobject.getJSONArray("value");
            byte abyte0[] = new byte[jsonarray7.length()];
            for(int i2 = 0; i2 < abyte0.length; i2++)
                abyte0[i2] = (byte)jsonarray7.getInt(i2);

            bundle.putByteArray(s, abyte0);
            return;
        }
        if(s2.equals("short"))
        {
            bundle.putShort(s, (short)jsonobject.getInt("value"));
            return;
        }
        if(s2.equals("short[]"))
        {
            JSONArray jsonarray6 = jsonobject.getJSONArray("value");
            short aword0[] = new short[jsonarray6.length()];
            for(int l1 = 0; l1 < aword0.length; l1++)
                aword0[l1] = (short)jsonarray6.getInt(l1);

            bundle.putShortArray(s, aword0);
            return;
        }
        if(s2.equals("int"))
        {
            bundle.putInt(s, jsonobject.getInt("value"));
            return;
        }
        if(s2.equals("int[]"))
        {
            JSONArray jsonarray5 = jsonobject.getJSONArray("value");
            int ai[] = new int[jsonarray5.length()];
            for(int k1 = 0; k1 < ai.length; k1++)
                ai[k1] = jsonarray5.getInt(k1);

            bundle.putIntArray(s, ai);
            return;
        }
        if(s2.equals("long"))
        {
            bundle.putLong(s, jsonobject.getLong("value"));
            return;
        }
        if(s2.equals("long[]"))
        {
            JSONArray jsonarray4 = jsonobject.getJSONArray("value");
            long al[] = new long[jsonarray4.length()];
            for(int j1 = 0; j1 < al.length; j1++)
                al[j1] = jsonarray4.getLong(j1);

            bundle.putLongArray(s, al);
            return;
        }
        if(s2.equals("float"))
        {
            bundle.putFloat(s, (float)jsonobject.getDouble("value"));
            return;
        }
        if(s2.equals("float[]"))
        {
            JSONArray jsonarray3 = jsonobject.getJSONArray("value");
            float af[] = new float[jsonarray3.length()];
            for(int i1 = 0; i1 < af.length; i1++)
                af[i1] = (float)jsonarray3.getDouble(i1);

            bundle.putFloatArray(s, af);
            return;
        }
        if(s2.equals("double"))
        {
            bundle.putDouble(s, jsonobject.getDouble("value"));
            return;
        }
        if(s2.equals("double[]"))
        {
            JSONArray jsonarray2 = jsonobject.getJSONArray("value");
            double ad[] = new double[jsonarray2.length()];
            for(int l = 0; l < ad.length; l++)
                ad[l] = jsonarray2.getDouble(l);

            bundle.putDoubleArray(s, ad);
            return;
        }
        if(!s2.equals("char"))
            break; /* Loop/switch isn't completed */
        String s5 = jsonobject.getString("value");
        if(s5 != null && s5.length() == 1)
        {
            bundle.putChar(s, s5.charAt(0));
            return;
        }
        if(true) goto _L4; else goto _L3
_L3:
        if(s2.equals("char[]"))
        {
            JSONArray jsonarray1 = jsonobject.getJSONArray("value");
            char ac[] = new char[jsonarray1.length()];
            for(int k = 0; k < ac.length; k++)
            {
                String s4 = jsonarray1.getString(k);
                if(s4 != null && s4.length() == 1)
                    ac[k] = s4.charAt(0);
            }

            bundle.putCharArray(s, ac);
            return;
        }
        if(s2.equals("string"))
        {
            bundle.putString(s, jsonobject.getString("value"));
            return;
        }
        if(s2.equals("stringList"))
        {
            JSONArray jsonarray = jsonobject.getJSONArray("value");
            int i = jsonarray.length();
            ArrayList arraylist = new ArrayList(i);
            int j = 0;
            while(j < i) 
            {
                Object obj = jsonarray.get(j);
                String s3;
                if(obj == JSONObject.NULL)
                    s3 = null;
                else
                    s3 = (String)obj;
                arraylist.add(j, s3);
                j++;
            }
            bundle.putStringArrayList(s, arraylist);
            return;
        }
        if(s2.equals("enum"))
            try
            {
                bundle.putSerializable(s, Enum.valueOf(Class.forName(jsonobject.getString("enumType")), jsonobject.getString("value")));
                return;
            }
            catch(ClassNotFoundException classnotfoundexception)
            {
                return;
            }
            catch(IllegalArgumentException illegalargumentexception)
            {
                return;
            }
        if(true) goto _L4; else goto _L5
_L5:
    }

    private void serializeKey(String s, Bundle bundle, android.content.SharedPreferences.Editor editor)
        throws JSONException
    {
        Object obj = bundle.get(s);
        if(obj != null)
        {
            JSONArray jsonarray = null;
            JSONObject jsonobject = new JSONObject();
            String s1;
            if(obj instanceof Byte)
            {
                s1 = "byte";
                jsonobject.put("value", ((Byte)obj).intValue());
            } else
            if(obj instanceof Short)
            {
                s1 = "short";
                jsonobject.put("value", ((Short)obj).intValue());
                jsonarray = null;
            } else
            if(obj instanceof Integer)
            {
                s1 = "int";
                jsonobject.put("value", ((Integer)obj).intValue());
                jsonarray = null;
            } else
            if(obj instanceof Long)
            {
                s1 = "long";
                jsonobject.put("value", ((Long)obj).longValue());
                jsonarray = null;
            } else
            if(obj instanceof Float)
            {
                s1 = "float";
                jsonobject.put("value", ((Float)obj).doubleValue());
                jsonarray = null;
            } else
            if(obj instanceof Double)
            {
                s1 = "double";
                jsonobject.put("value", ((Double)obj).doubleValue());
                jsonarray = null;
            } else
            if(obj instanceof Boolean)
            {
                s1 = "bool";
                jsonobject.put("value", ((Boolean)obj).booleanValue());
                jsonarray = null;
            } else
            if(obj instanceof Character)
            {
                s1 = "char";
                jsonobject.put("value", obj.toString());
                jsonarray = null;
            } else
            if(obj instanceof String)
            {
                s1 = "string";
                jsonobject.put("value", (String)obj);
                jsonarray = null;
            } else
            if(obj instanceof Enum)
            {
                s1 = "enum";
                jsonobject.put("value", obj.toString());
                jsonobject.put("enumType", obj.getClass().getName());
                jsonarray = null;
            } else
            {
                jsonarray = new JSONArray();
                if(obj instanceof byte[])
                {
                    s1 = "byte[]";
                    byte abyte0[] = (byte[])(byte[])obj;
                    int k3 = abyte0.length;
                    int l3 = 0;
                    while(l3 < k3) 
                    {
                        jsonarray.put(abyte0[l3]);
                        l3++;
                    }
                } else
                if(obj instanceof short[])
                {
                    s1 = "short[]";
                    short aword0[] = (short[])(short[])obj;
                    int i3 = aword0.length;
                    int j3 = 0;
                    while(j3 < i3) 
                    {
                        jsonarray.put(aword0[j3]);
                        j3++;
                    }
                } else
                if(obj instanceof int[])
                {
                    s1 = "int[]";
                    int ai[] = (int[])(int[])obj;
                    int k2 = ai.length;
                    int l2 = 0;
                    while(l2 < k2) 
                    {
                        jsonarray.put(ai[l2]);
                        l2++;
                    }
                } else
                if(obj instanceof long[])
                {
                    s1 = "long[]";
                    long al[] = (long[])(long[])obj;
                    int i2 = al.length;
                    int j2 = 0;
                    while(j2 < i2) 
                    {
                        jsonarray.put(al[j2]);
                        j2++;
                    }
                } else
                if(obj instanceof float[])
                {
                    s1 = "float[]";
                    float af[] = (float[])(float[])obj;
                    int k1 = af.length;
                    int l1 = 0;
                    while(l1 < k1) 
                    {
                        jsonarray.put(af[l1]);
                        l1++;
                    }
                } else
                if(obj instanceof double[])
                {
                    s1 = "double[]";
                    double ad[] = (double[])(double[])obj;
                    int i1 = ad.length;
                    int j1 = 0;
                    while(j1 < i1) 
                    {
                        jsonarray.put(ad[j1]);
                        j1++;
                    }
                } else
                if(obj instanceof boolean[])
                {
                    s1 = "bool[]";
                    boolean aflag[] = (boolean[])(boolean[])obj;
                    int k = aflag.length;
                    int l = 0;
                    while(l < k) 
                    {
                        jsonarray.put(aflag[l]);
                        l++;
                    }
                } else
                if(obj instanceof char[])
                {
                    s1 = "char[]";
                    char ac[] = (char[])(char[])obj;
                    int i = ac.length;
                    int j = 0;
                    while(j < i) 
                    {
                        jsonarray.put(String.valueOf(ac[j]));
                        j++;
                    }
                } else
                if(obj instanceof List)
                {
                    s1 = "stringList";
                    Iterator iterator = ((List)obj).iterator();
                    while(iterator.hasNext()) 
                    {
                        Object obj1 = (String)iterator.next();
                        if(obj1 == null)
                            obj1 = JSONObject.NULL;
                        jsonarray.put(obj1);
                    }
                } else
                {
                    jsonarray = null;
                    s1 = null;
                }
            }
            if(s1 != null)
            {
                jsonobject.put("valueType", s1);
                if(jsonarray != null)
                    jsonobject.putOpt("value", jsonarray);
                String s2;
                if(!(jsonobject instanceof JSONObject))
                    s2 = jsonobject.toString();
                else
                    s2 = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
                editor.putString(s, s2);
                return;
            }
        }
    }

    public void clear()
    {
        cache.edit().clear().commit();
    }

    public Bundle load()
    {
        Bundle bundle;
        Iterator iterator;
        bundle = new Bundle();
        iterator = cache.getAll().keySet().iterator();
_L2:
        String s;
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        s = (String)iterator.next();
        deserializeKey(s, bundle);
        if(true) goto _L2; else goto _L1
        JSONException jsonexception;
        jsonexception;
        Logger.log(LoggingBehavior.CACHE, 5, TAG, (new StringBuilder()).append("Error reading cached value for key: '").append(s).append("' -- ").append(jsonexception).toString());
        bundle = null;
_L1:
        return bundle;
    }

    public void save(Bundle bundle)
    {
        android.content.SharedPreferences.Editor editor;
        Iterator iterator;
        Validate.notNull(bundle, "bundle");
        editor = cache.edit();
        iterator = bundle.keySet().iterator();
_L4:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        String s = (String)iterator.next();
        serializeKey(s, bundle, editor);
        if(true) goto _L4; else goto _L3
_L3:
        JSONException jsonexception;
        jsonexception;
        Logger.log(LoggingBehavior.CACHE, 5, TAG, (new StringBuilder()).append("Error processing value for key: '").append(s).append("' -- ").append(jsonexception).toString());
_L6:
        return;
_L2:
        if(!editor.commit())
        {
            Logger.log(LoggingBehavior.CACHE, 5, TAG, "SharedPreferences.Editor.commit() was not successful");
            return;
        }
        if(true) goto _L6; else goto _L5
_L5:
    }

    private static final String DEFAULT_CACHE_KEY = "com.facebook.SharedPreferencesTokenCachingStrategy.DEFAULT_KEY";
    private static final String JSON_VALUE = "value";
    private static final String JSON_VALUE_ENUM_TYPE = "enumType";
    private static final String JSON_VALUE_TYPE = "valueType";
    private static final String TAG = com/facebook/SharedPreferencesTokenCachingStrategy.getSimpleName();
    private static final String TYPE_BOOLEAN = "bool";
    private static final String TYPE_BOOLEAN_ARRAY = "bool[]";
    private static final String TYPE_BYTE = "byte";
    private static final String TYPE_BYTE_ARRAY = "byte[]";
    private static final String TYPE_CHAR = "char";
    private static final String TYPE_CHAR_ARRAY = "char[]";
    private static final String TYPE_DOUBLE = "double";
    private static final String TYPE_DOUBLE_ARRAY = "double[]";
    private static final String TYPE_ENUM = "enum";
    private static final String TYPE_FLOAT = "float";
    private static final String TYPE_FLOAT_ARRAY = "float[]";
    private static final String TYPE_INTEGER = "int";
    private static final String TYPE_INTEGER_ARRAY = "int[]";
    private static final String TYPE_LONG = "long";
    private static final String TYPE_LONG_ARRAY = "long[]";
    private static final String TYPE_SHORT = "short";
    private static final String TYPE_SHORT_ARRAY = "short[]";
    private static final String TYPE_STRING = "string";
    private static final String TYPE_STRING_LIST = "stringList";
    private SharedPreferences cache;
    private String cacheKey;

}
