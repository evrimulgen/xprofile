// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.containertag.common.Key;
import java.util.*;

// Referenced classes of package com.google.tagmanager:
//            TrackingTag, Types, Log, HitSender, 
//            SharedPreferencesUtil, DelayedHitSender

class ArbitraryPixelTag extends TrackingTag
{
    public static interface HitSenderProvider
    {

        public abstract HitSender get();
    }


    public ArbitraryPixelTag(final Context context)
    {
        this(context, new HitSenderProvider() {

            public HitSender get()
            {
                return DelayedHitSender.getInstance(context);
            }

            final Context val$context;

            
            {
                context = context1;
                super();
            }
        }
);
    }

    ArbitraryPixelTag(Context context, HitSenderProvider hitsenderprovider)
    {
        String s = ID;
        String as[] = new String[1];
        as[0] = URL;
        super(s, as);
        mHitSenderProvider = hitsenderprovider;
        mContext = context;
    }

    public static String getFunctionId()
    {
        return ID;
    }

    private boolean idProcessed(String s)
    {
        boolean flag = true;
        this;
        JVM INSTR monitorenter ;
        boolean flag1 = idInCache(s);
        if(!flag1) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        if(idInSharedPreferences(s))
        {
            unrepeatableIds.add(s);
            continue; /* Loop/switch isn't completed */
        }
        break MISSING_BLOCK_LABEL_46;
        Exception exception;
        exception;
        throw exception;
        flag = false;
        if(true) goto _L1; else goto _L3
_L3:
    }

    void clearCache()
    {
        unrepeatableIds.clear();
    }

    public void evaluateTrackingTag(Map map)
    {
        String s;
        if(map.get(UNREPEATABLE) != null)
            s = Types.valueToString((com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(UNREPEATABLE));
        else
            s = null;
        if(s == null || !idProcessed(s)) goto _L2; else goto _L1
_L1:
        return;
_L2:
        android.net.Uri.Builder builder = Uri.parse(Types.valueToString((com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(URL))).buildUpon();
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value value = (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(ADDITIONAL_PARAMS);
        if(value != null)
        {
            Object obj = Types.valueToObject(value);
            if(!(obj instanceof List))
            {
                Log.e((new StringBuilder()).append("ArbitraryPixel: additional params not a list: not sending partial hit: ").append(builder.build().toString()).toString());
                return;
            }
            for(Iterator iterator = ((List)obj).iterator(); iterator.hasNext();)
            {
                Object obj1 = iterator.next();
                if(!(obj1 instanceof Map))
                {
                    Log.e((new StringBuilder()).append("ArbitraryPixel: additional params contains non-map: not sending partial hit: ").append(builder.build().toString()).toString());
                    return;
                }
                Iterator iterator1 = ((Map)obj1).entrySet().iterator();
                while(iterator1.hasNext()) 
                {
                    java.util.Map.Entry entry = (java.util.Map.Entry)iterator1.next();
                    builder.appendQueryParameter(entry.getKey().toString(), entry.getValue().toString());
                }
            }

        }
        String s1 = builder.build().toString();
        mHitSenderProvider.get().sendHit(s1);
        Log.v((new StringBuilder()).append("ArbitraryPixel: url = ").append(s1).toString());
        if(s == null) goto _L1; else goto _L3
_L3:
        com/google/tagmanager/ArbitraryPixelTag;
        JVM INSTR monitorenter ;
        unrepeatableIds.add(s);
        SharedPreferencesUtil.saveAsync(mContext, ARBITRARY_PIXEL_UNREPEATABLE, s, "true");
        com/google/tagmanager/ArbitraryPixelTag;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        com/google/tagmanager/ArbitraryPixelTag;
        JVM INSTR monitorexit ;
        throw exception;
    }

    boolean idInCache(String s)
    {
        return unrepeatableIds.contains(s);
    }

    boolean idInSharedPreferences(String s)
    {
        return mContext.getSharedPreferences(ARBITRARY_PIXEL_UNREPEATABLE, 0).contains(s);
    }

    private static final String ADDITIONAL_PARAMS;
    static final String ARBITRARY_PIXEL_UNREPEATABLE;
    private static final String ID;
    private static final String UNREPEATABLE;
    private static final String URL;
    private static final Set unrepeatableIds = new HashSet();
    private final Context mContext;
    private final HitSenderProvider mHitSenderProvider;

    static 
    {
        ID = FunctionType.ARBITRARY_PIXEL.toString();
        URL = Key.URL.toString();
        ADDITIONAL_PARAMS = Key.ADDITIONAL_PARAMS.toString();
        UNREPEATABLE = Key.UNREPEATABLE.toString();
        ARBITRARY_PIXEL_UNREPEATABLE = (new StringBuilder()).append("gtm_").append(ID).append("_unrepeatable").toString();
    }
}
