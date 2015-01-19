// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import android.content.Context;
import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.containertag.common.Key;
import com.google.analytics.tracking.android.Tracker;
import java.util.*;

// Referenced classes of package com.google.tagmanager:
//            TrackingTag, TrackerProvider, DataLayer, Log, 
//            Types

class UniversalAnalyticsTag extends TrackingTag
{

    public UniversalAnalyticsTag(Context context, DataLayer datalayer)
    {
        this(context, datalayer, new TrackerProvider(context));
    }

    UniversalAnalyticsTag(Context context, DataLayer datalayer, TrackerProvider trackerprovider)
    {
        super(ID, new String[0]);
        mDataLayer = datalayer;
        mTrackerProvider = trackerprovider;
        mTurnOffAnonymizeIpValues = new HashSet();
        mTurnOffAnonymizeIpValues.add("");
        mTurnOffAnonymizeIpValues.add("0");
        mTurnOffAnonymizeIpValues.add("false");
    }

    private void addParam(Map map, String s, String s1)
    {
        if(s1 != null)
            map.put(s, s1);
    }

    private boolean checkBooleanProperty(Map map, String s)
    {
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value value = (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(s);
        if(value == null)
            return false;
        else
            return value.getBoolean();
    }

    private Map convertToGaFields(com.google.analytics.midtier.proto.containertag.TypeSystem.Value value)
    {
        Object obj;
        if(value == null || value.getType() != com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Type.MAP)
        {
            obj = new HashMap();
        } else
        {
            obj = valueToMap(value);
            String s = (String)((Map) (obj)).get("&aip");
            if(s != null && mTurnOffAnonymizeIpValues.contains(s.toLowerCase()))
            {
                ((Map) (obj)).remove("&aip");
                return ((Map) (obj));
            }
        }
        return ((Map) (obj));
    }

    private String getDataLayerString(String s)
    {
        Object obj = mDataLayer.get(s);
        if(obj == null)
            return null;
        else
            return obj.toString();
    }

    public static String getFunctionId()
    {
        return ID;
    }

    private Map getTransactionFields(Map map)
    {
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value value = (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(TRANSACTION_DATALAYER_MAP);
        if(value != null)
            return valueToMap(value);
        if(defaultTransactionMap == null)
        {
            HashMap hashmap = new HashMap();
            hashmap.put("transactionId", "&ti");
            hashmap.put("transactionAffiliation", "&ta");
            hashmap.put("transactionTax", "&tt");
            hashmap.put("transactionShipping", "&ts");
            hashmap.put("transactionTotal", "&tr");
            hashmap.put("transactionCurrency", "&cu");
            defaultTransactionMap = hashmap;
        }
        return defaultTransactionMap;
    }

    private Map getTransactionItemFields(Map map)
    {
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value value = (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(TRANSACTION_ITEM_DATALAYER_MAP);
        if(value != null)
            return valueToMap(value);
        if(defaultItemMap == null)
        {
            HashMap hashmap = new HashMap();
            hashmap.put("name", "&in");
            hashmap.put("sku", "&ic");
            hashmap.put("category", "&iv");
            hashmap.put("price", "&ip");
            hashmap.put("quantity", "&iq");
            hashmap.put("currency", "&cu");
            defaultItemMap = hashmap;
        }
        return defaultItemMap;
    }

    private List getTransactionItems()
    {
        Object obj = mDataLayer.get("transactionProducts");
        if(obj == null)
            return null;
        if(!(obj instanceof List))
            throw new IllegalArgumentException("transactionProducts should be of type List.");
        for(Iterator iterator = ((List)obj).iterator(); iterator.hasNext();)
            if(!(iterator.next() instanceof Map))
                throw new IllegalArgumentException("Each element of transactionProducts should be of type Map.");

        return (List)obj;
    }

    private void sendTransaction(Tracker tracker, Map map)
    {
        String s;
        ((com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(ACCOUNT)).getString();
        s = getDataLayerString("transactionId");
        if(s != null) goto _L2; else goto _L1
_L1:
        Log.e("Cannot find transactionId in data layer.");
_L4:
        return;
_L2:
        LinkedList linkedlist;
        Map map1;
        linkedlist = new LinkedList();
        try
        {
            map1 = convertToGaFields((com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(ANALYTICS_FIELDS));
            map1.put("&t", "transaction");
            java.util.Map.Entry entry1;
            for(Iterator iterator = getTransactionFields(map).entrySet().iterator(); iterator.hasNext(); addParam(map1, (String)entry1.getValue(), getDataLayerString((String)entry1.getKey())))
                entry1 = (java.util.Map.Entry)iterator.next();

        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            Log.e("Unable to send transaction", illegalargumentexception);
            return;
        }
        List list;
        linkedlist.add(map1);
        list = getTransactionItems();
        if(list == null)
            break MISSING_BLOCK_LABEL_363;
        Iterator iterator1 = list.iterator();
_L3:
        Map map2;
        if(!iterator1.hasNext())
            break MISSING_BLOCK_LABEL_363;
        map2 = (Map)iterator1.next();
        if(map2.get("name") == null)
        {
            Log.e("Unable to send transaction item hit due to missing 'name' field.");
            return;
        }
        Map map3;
        map3 = convertToGaFields((com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(ANALYTICS_FIELDS));
        map3.put("&t", "item");
        map3.put("&ti", s);
        java.util.Map.Entry entry;
        for(Iterator iterator3 = getTransactionItemFields(map).entrySet().iterator(); iterator3.hasNext(); addParam(map3, (String)entry.getValue(), (String)map2.get(entry.getKey())))
            entry = (java.util.Map.Entry)iterator3.next();

        linkedlist.add(map3);
          goto _L3
        Iterator iterator2 = linkedlist.iterator();
        while(iterator2.hasNext()) 
            tracker.send((Map)iterator2.next());
          goto _L4
    }

    private Map valueToMap(com.google.analytics.midtier.proto.containertag.TypeSystem.Value value)
    {
        Object obj;
        if(value.getType() != com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Type.MAP)
        {
            obj = null;
        } else
        {
            obj = new HashMap(value.getMapKeyCount());
            int i = 0;
            while(i < value.getMapKeyCount()) 
            {
                ((Map) (obj)).put(Types.valueToString(value.getMapKey(i)), Types.valueToString(value.getMapValue(i)));
                i++;
            }
        }
        return ((Map) (obj));
    }

    public void evaluateTrackingTag(Map map)
    {
        Tracker tracker = mTrackerProvider.getTracker("_GTM_DEFAULT_TRACKER_");
        if(checkBooleanProperty(map, ANALYTICS_PASS_THROUGH))
            tracker.send(convertToGaFields((com.google.analytics.midtier.proto.containertag.TypeSystem.Value)map.get(ANALYTICS_FIELDS)));
        else
        if(checkBooleanProperty(map, TRACK_TRANSACTION))
            sendTransaction(tracker, map);
        else
            Log.w("Ignoring unknown tag.");
        mTrackerProvider.close(tracker);
    }

    private static final String ACCOUNT;
    private static final String ANALYTICS_FIELDS;
    private static final String ANALYTICS_PASS_THROUGH;
    private static final String DEFAULT_TRACKING_ID = "_GTM_DEFAULT_TRACKER_";
    private static final String ID;
    private static final String TRACK_TRANSACTION;
    private static final String TRANSACTION_DATALAYER_MAP;
    private static final String TRANSACTION_ITEM_DATALAYER_MAP;
    private static Map defaultItemMap;
    private static Map defaultTransactionMap;
    private final DataLayer mDataLayer;
    private final TrackerProvider mTrackerProvider;
    private final Set mTurnOffAnonymizeIpValues;

    static 
    {
        ID = FunctionType.UNIVERSAL_ANALYTICS.toString();
        ACCOUNT = Key.ACCOUNT.toString();
        ANALYTICS_PASS_THROUGH = Key.ANALYTICS_PASS_THROUGH.toString();
        ANALYTICS_FIELDS = Key.ANALYTICS_FIELDS.toString();
        TRACK_TRANSACTION = Key.TRACK_TRANSACTION.toString();
        TRANSACTION_DATALAYER_MAP = Key.TRANSACTION_DATALAYER_MAP.toString();
        TRANSACTION_ITEM_DATALAYER_MAP = Key.TRANSACTION_ITEM_DATALAYER_MAP.toString();
    }
}
