// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.analytics.tracking.android;


// Referenced classes of package com.google.analytics.tracking.android:
//            Log

public final class Fields
{

    private Fields()
    {
    }

    public static String contentGroup(int i)
    {
        return getParam("&cg", i);
    }

    public static String customDimension(int i)
    {
        return getParam("&cd", i);
    }

    public static String customMetric(int i)
    {
        return getParam("&cm", i);
    }

    private static String getParam(String s, int i)
    {
        if(i < 1)
        {
            Log.e((new StringBuilder()).append("index out of range for ").append(s).append(" (").append(i).append(")").toString());
            return "";
        } else
        {
            return (new StringBuilder()).append(s).append(i).toString();
        }
    }

    public static final String ANDROID_APP_UID = "AppUID";
    public static final String ANONYMIZE_IP = "&aip";
    static final String API_VERSION = "&v";
    public static final String APP_ID = "&aid";
    public static final String APP_INSTALLER_ID = "&aiid";
    public static final String APP_NAME = "&an";
    public static final String APP_VERSION = "&av";
    static final String CACHE_BUSTER = "&z";
    public static final String CAMPAIGN_CONTENT = "&cc";
    public static final String CAMPAIGN_ID = "&ci";
    public static final String CAMPAIGN_KEYWORD = "&ck";
    public static final String CAMPAIGN_MEDIUM = "&cm";
    public static final String CAMPAIGN_NAME = "&cn";
    public static final String CAMPAIGN_SOURCE = "&cs";
    public static final String CLIENT_ID = "&cid";
    static final String CLIENT_VERSION = "&_v";
    public static final String CURRENCY_CODE = "&cu";
    static final String DCLID = "&dclid";
    public static final String DESCRIPTION = "&cd";
    public static final String ENCODING = "&de";
    public static final String EVENT_ACTION = "&ea";
    public static final String EVENT_CATEGORY = "&ec";
    public static final String EVENT_LABEL = "&el";
    public static final String EVENT_VALUE = "&ev";
    public static final String EX_DESCRIPTION = "&exd";
    public static final String EX_FATAL = "&exf";
    public static final String FLASH_VERSION = "&fl";
    static final String GCLID = "&gclid";
    static final String GMOB_T = "&gmob_t";
    static final String HIT_TIME = "&ht";
    public static final String HIT_TYPE = "&t";
    public static final String HOSTNAME = "&dh";
    public static final String ITEM_CATEGORY = "&iv";
    public static final String ITEM_NAME = "&in";
    public static final String ITEM_PRICE = "&ip";
    public static final String ITEM_QUANTITY = "&iq";
    public static final String ITEM_SKU = "&ic";
    public static final String JAVA_ENABLED = "&je";
    public static final String LANGUAGE = "&ul";
    public static final String LOCATION = "&dl";
    public static final String NON_INTERACTION = "&ni";
    public static final String PAGE = "&dp";
    static final String QUEUE_TIME = "&qt";
    public static final String REFERRER = "&dr";
    public static final String SAMPLE_RATE = "&sf";
    public static final String SCREEN_COLORS = "&sd";
    public static final String SCREEN_NAME = "&cd";
    public static final String SCREEN_RESOLUTION = "&sr";
    public static final String SESSION_CONTROL = "&sc";
    public static final String SOCIAL_ACTION = "&sa";
    public static final String SOCIAL_NETWORK = "&sn";
    public static final String SOCIAL_TARGET = "&st";
    public static final String TIMING_CATEGORY = "&utc";
    public static final String TIMING_LABEL = "&utl";
    public static final String TIMING_VALUE = "&utt";
    public static final String TIMING_VAR = "&utv";
    public static final String TITLE = "&dt";
    public static final String TRACKING_ID = "&tid";
    public static final String TRANSACTION_AFFILIATION = "&ta";
    public static final String TRANSACTION_ID = "&ti";
    public static final String TRANSACTION_REVENUE = "&tr";
    public static final String TRANSACTION_SHIPPING = "&ts";
    public static final String TRANSACTION_TAX = "&tt";
    static final String USAGE_TRACKING = "&_u";
    public static final String USE_SECURE = "useSecure";
    public static final String VIEWPORT_SIZE = "&vp";
}
