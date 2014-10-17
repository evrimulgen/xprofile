// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.analytics.tracking.android;

import java.util.SortedSet;
import java.util.TreeSet;

class GAUsage
{
    public static final class Field extends Enum
    {

        public static Field valueOf(String s)
        {
            return (Field)Enum.valueOf(com/google/analytics/tracking/android/GAUsage$Field, s);
        }

        public static Field[] values()
        {
            return (Field[])$VALUES.clone();
        }

        private static final Field $VALUES[];
        public static final Field BLANK_04;
        public static final Field BLANK_05;
        public static final Field BLANK_06;
        public static final Field BLANK_07;
        public static final Field BLANK_08;
        public static final Field BLANK_12;
        public static final Field BLANK_13;
        public static final Field BLANK_14;
        public static final Field BLANK_15;
        public static final Field BLANK_16;
        public static final Field BLANK_17;
        public static final Field BLANK_18;
        public static final Field BLANK_19;
        public static final Field BLANK_20;
        public static final Field BLANK_21;
        public static final Field BLANK_22;
        public static final Field BLANK_23;
        public static final Field BLANK_24;
        public static final Field BLANK_25;
        public static final Field BLANK_26;
        public static final Field BLANK_27;
        public static final Field BLANK_28;
        public static final Field BLANK_29;
        public static final Field BLANK_37;
        public static final Field BLANK_38;
        public static final Field BLANK_46;
        public static final Field BLANK_52;
        public static final Field BLANK_53;
        public static final Field CLOSE_TRACKER;
        public static final Field CONSTRUCT_APP_VIEW;
        public static final Field CONSTRUCT_EVENT;
        public static final Field CONSTRUCT_EXCEPTION;
        public static final Field CONSTRUCT_ITEM;
        public static final Field CONSTRUCT_RAW_EXCEPTION;
        public static final Field CONSTRUCT_SOCIAL;
        public static final Field CONSTRUCT_TIMING;
        public static final Field CONSTRUCT_TRANSACTION;
        public static final Field DISPATCH;
        public static final Field EASY_TRACKER_ACTIVITY_START;
        public static final Field EASY_TRACKER_ACTIVITY_STOP;
        public static final Field GET;
        public static final Field GET_APP_OPT_OUT;
        public static final Field GET_DEFAULT_TRACKER;
        public static final Field GET_DRY_RUN;
        public static final Field GET_EXCEPTION_PARSER;
        public static final Field GET_TRACKER;
        public static final Field GET_TRACKER_NAME;
        public static final Field MAP_BUILDER_GET;
        public static final Field MAP_BUILDER_SET;
        public static final Field MAP_BUILDER_SET_ALL;
        public static final Field MAP_BUILDER_SET_CAMPAIGN_PARAMS;
        public static final Field REPORT_UNCAUGHT_EXCEPTIONS;
        public static final Field SEND;
        public static final Field SET;
        public static final Field SET_APP_OPT_OUT;
        public static final Field SET_AUTO_ACTIVITY_TRACKING;
        public static final Field SET_DEFAULT_TRACKER;
        public static final Field SET_DISPATCH_PERIOD;
        public static final Field SET_DRY_RUN;
        public static final Field SET_EXCEPTION_PARSER;
        public static final Field SET_FORCE_LOCAL_DISPATCH;
        public static final Field SET_LOGGER;
        public static final Field SET_SESSION_TIMEOUT;

        static 
        {
            MAP_BUILDER_SET = new Field("MAP_BUILDER_SET", 0);
            MAP_BUILDER_SET_ALL = new Field("MAP_BUILDER_SET_ALL", 1);
            MAP_BUILDER_GET = new Field("MAP_BUILDER_GET", 2);
            MAP_BUILDER_SET_CAMPAIGN_PARAMS = new Field("MAP_BUILDER_SET_CAMPAIGN_PARAMS", 3);
            BLANK_04 = new Field("BLANK_04", 4);
            BLANK_05 = new Field("BLANK_05", 5);
            BLANK_06 = new Field("BLANK_06", 6);
            BLANK_07 = new Field("BLANK_07", 7);
            BLANK_08 = new Field("BLANK_08", 8);
            GET = new Field("GET", 9);
            SET = new Field("SET", 10);
            SEND = new Field("SEND", 11);
            BLANK_12 = new Field("BLANK_12", 12);
            BLANK_13 = new Field("BLANK_13", 13);
            BLANK_14 = new Field("BLANK_14", 14);
            BLANK_15 = new Field("BLANK_15", 15);
            BLANK_16 = new Field("BLANK_16", 16);
            BLANK_17 = new Field("BLANK_17", 17);
            BLANK_18 = new Field("BLANK_18", 18);
            BLANK_19 = new Field("BLANK_19", 19);
            BLANK_20 = new Field("BLANK_20", 20);
            BLANK_21 = new Field("BLANK_21", 21);
            BLANK_22 = new Field("BLANK_22", 22);
            BLANK_23 = new Field("BLANK_23", 23);
            BLANK_24 = new Field("BLANK_24", 24);
            BLANK_25 = new Field("BLANK_25", 25);
            BLANK_26 = new Field("BLANK_26", 26);
            BLANK_27 = new Field("BLANK_27", 27);
            BLANK_28 = new Field("BLANK_28", 28);
            BLANK_29 = new Field("BLANK_29", 29);
            SET_EXCEPTION_PARSER = new Field("SET_EXCEPTION_PARSER", 30);
            GET_EXCEPTION_PARSER = new Field("GET_EXCEPTION_PARSER", 31);
            CONSTRUCT_TRANSACTION = new Field("CONSTRUCT_TRANSACTION", 32);
            CONSTRUCT_EXCEPTION = new Field("CONSTRUCT_EXCEPTION", 33);
            CONSTRUCT_RAW_EXCEPTION = new Field("CONSTRUCT_RAW_EXCEPTION", 34);
            CONSTRUCT_TIMING = new Field("CONSTRUCT_TIMING", 35);
            CONSTRUCT_SOCIAL = new Field("CONSTRUCT_SOCIAL", 36);
            BLANK_37 = new Field("BLANK_37", 37);
            BLANK_38 = new Field("BLANK_38", 38);
            GET_TRACKER = new Field("GET_TRACKER", 39);
            GET_DEFAULT_TRACKER = new Field("GET_DEFAULT_TRACKER", 40);
            SET_DEFAULT_TRACKER = new Field("SET_DEFAULT_TRACKER", 41);
            SET_APP_OPT_OUT = new Field("SET_APP_OPT_OUT", 42);
            GET_APP_OPT_OUT = new Field("GET_APP_OPT_OUT", 43);
            DISPATCH = new Field("DISPATCH", 44);
            SET_DISPATCH_PERIOD = new Field("SET_DISPATCH_PERIOD", 45);
            BLANK_46 = new Field("BLANK_46", 46);
            REPORT_UNCAUGHT_EXCEPTIONS = new Field("REPORT_UNCAUGHT_EXCEPTIONS", 47);
            SET_AUTO_ACTIVITY_TRACKING = new Field("SET_AUTO_ACTIVITY_TRACKING", 48);
            SET_SESSION_TIMEOUT = new Field("SET_SESSION_TIMEOUT", 49);
            CONSTRUCT_EVENT = new Field("CONSTRUCT_EVENT", 50);
            CONSTRUCT_ITEM = new Field("CONSTRUCT_ITEM", 51);
            BLANK_52 = new Field("BLANK_52", 52);
            BLANK_53 = new Field("BLANK_53", 53);
            SET_DRY_RUN = new Field("SET_DRY_RUN", 54);
            GET_DRY_RUN = new Field("GET_DRY_RUN", 55);
            SET_LOGGER = new Field("SET_LOGGER", 56);
            SET_FORCE_LOCAL_DISPATCH = new Field("SET_FORCE_LOCAL_DISPATCH", 57);
            GET_TRACKER_NAME = new Field("GET_TRACKER_NAME", 58);
            CLOSE_TRACKER = new Field("CLOSE_TRACKER", 59);
            EASY_TRACKER_ACTIVITY_START = new Field("EASY_TRACKER_ACTIVITY_START", 60);
            EASY_TRACKER_ACTIVITY_STOP = new Field("EASY_TRACKER_ACTIVITY_STOP", 61);
            CONSTRUCT_APP_VIEW = new Field("CONSTRUCT_APP_VIEW", 62);
            Field afield[] = new Field[63];
            afield[0] = MAP_BUILDER_SET;
            afield[1] = MAP_BUILDER_SET_ALL;
            afield[2] = MAP_BUILDER_GET;
            afield[3] = MAP_BUILDER_SET_CAMPAIGN_PARAMS;
            afield[4] = BLANK_04;
            afield[5] = BLANK_05;
            afield[6] = BLANK_06;
            afield[7] = BLANK_07;
            afield[8] = BLANK_08;
            afield[9] = GET;
            afield[10] = SET;
            afield[11] = SEND;
            afield[12] = BLANK_12;
            afield[13] = BLANK_13;
            afield[14] = BLANK_14;
            afield[15] = BLANK_15;
            afield[16] = BLANK_16;
            afield[17] = BLANK_17;
            afield[18] = BLANK_18;
            afield[19] = BLANK_19;
            afield[20] = BLANK_20;
            afield[21] = BLANK_21;
            afield[22] = BLANK_22;
            afield[23] = BLANK_23;
            afield[24] = BLANK_24;
            afield[25] = BLANK_25;
            afield[26] = BLANK_26;
            afield[27] = BLANK_27;
            afield[28] = BLANK_28;
            afield[29] = BLANK_29;
            afield[30] = SET_EXCEPTION_PARSER;
            afield[31] = GET_EXCEPTION_PARSER;
            afield[32] = CONSTRUCT_TRANSACTION;
            afield[33] = CONSTRUCT_EXCEPTION;
            afield[34] = CONSTRUCT_RAW_EXCEPTION;
            afield[35] = CONSTRUCT_TIMING;
            afield[36] = CONSTRUCT_SOCIAL;
            afield[37] = BLANK_37;
            afield[38] = BLANK_38;
            afield[39] = GET_TRACKER;
            afield[40] = GET_DEFAULT_TRACKER;
            afield[41] = SET_DEFAULT_TRACKER;
            afield[42] = SET_APP_OPT_OUT;
            afield[43] = GET_APP_OPT_OUT;
            afield[44] = DISPATCH;
            afield[45] = SET_DISPATCH_PERIOD;
            afield[46] = BLANK_46;
            afield[47] = REPORT_UNCAUGHT_EXCEPTIONS;
            afield[48] = SET_AUTO_ACTIVITY_TRACKING;
            afield[49] = SET_SESSION_TIMEOUT;
            afield[50] = CONSTRUCT_EVENT;
            afield[51] = CONSTRUCT_ITEM;
            afield[52] = BLANK_52;
            afield[53] = BLANK_53;
            afield[54] = SET_DRY_RUN;
            afield[55] = GET_DRY_RUN;
            afield[56] = SET_LOGGER;
            afield[57] = SET_FORCE_LOCAL_DISPATCH;
            afield[58] = GET_TRACKER_NAME;
            afield[59] = CLOSE_TRACKER;
            afield[60] = EASY_TRACKER_ACTIVITY_START;
            afield[61] = EASY_TRACKER_ACTIVITY_STOP;
            afield[62] = CONSTRUCT_APP_VIEW;
            $VALUES = afield;
        }

        private Field(String s, int i)
        {
            super(s, i);
        }
    }


    private GAUsage()
    {
        mUsedFields = new TreeSet();
        mSequence = new StringBuilder();
        mDisableUsage = false;
    }

    public static GAUsage getInstance()
    {
        return INSTANCE;
    }

    static GAUsage getPrivateInstance()
    {
        return new GAUsage();
    }

    public String getAndClearSequence()
    {
        this;
        JVM INSTR monitorenter ;
        String s;
        if(mSequence.length() > 0)
            mSequence.insert(0, ".");
        s = mSequence.toString();
        mSequence = new StringBuilder();
        this;
        JVM INSTR monitorexit ;
        return s;
        Exception exception;
        exception;
        throw exception;
    }

    public String getAndClearUsage()
    {
        this;
        JVM INSTR monitorenter ;
        StringBuilder stringbuilder = new StringBuilder();
        int i;
        int j;
        i = 0;
        j = 6;
_L4:
        Field field;
        int k;
        if(mUsedFields.size() <= 0)
            break; /* Loop/switch isn't completed */
        field = (Field)mUsedFields.first();
        mUsedFields.remove(field);
        k = field.ordinal();
_L2:
        if(k < j)
            break; /* Loop/switch isn't completed */
        stringbuilder.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".charAt(i));
        j += 6;
        i = 0;
        if(true) goto _L2; else goto _L1
_L1:
        i += 1 << field.ordinal() % 6;
        if(true) goto _L4; else goto _L3
_L3:
        if(i > 0)
            break MISSING_BLOCK_LABEL_112;
        if(stringbuilder.length() != 0)
            break MISSING_BLOCK_LABEL_123;
        stringbuilder.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".charAt(i));
        String s;
        mUsedFields.clear();
        s = stringbuilder.toString();
        this;
        JVM INSTR monitorexit ;
        return s;
        Exception exception;
        exception;
        throw exception;
    }

    public void setDisableUsage(boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        mDisableUsage = flag;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setUsage(Field field)
    {
        this;
        JVM INSTR monitorenter ;
        if(!mDisableUsage)
        {
            mUsedFields.add(field);
            mSequence.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".charAt(field.ordinal()));
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private static final String BASE_64_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";
    private static final GAUsage INSTANCE = new GAUsage();
    private boolean mDisableUsage;
    private StringBuilder mSequence;
    private SortedSet mUsedFields;

}
